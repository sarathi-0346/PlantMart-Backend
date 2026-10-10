package com.example.plantio1.Services;

import com.example.plantio1.Entity.Category;
import com.example.plantio1.Entity.Product;
import com.example.plantio1.Repository.CategoryRepository;
import com.example.plantio1.Repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.UUID;

@Service
public class ProductUploadService {


    private final ProductRepository productRepository;


    private final CategoryRepository categoryRepository;


    private final Path uploadDirectory;

    public ProductUploadService(
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            @Value("${app.upload-dir}") String uploadDirectory) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;

        this.uploadDirectory = Path.of(uploadDirectory)
                .toAbsolutePath()
                .normalize();
    }

    public Product addWithImage(
            String name,
            double price,
            Long categoryId,
            String placement,
            String description,
            MultipartFile image) {

        // 1. Validate the product details.
        if (name == null || name.isBlank()
                || name.trim().length() > 255) {
            throw badRequest(
                    "Product name must contain 1 to 255 characters"
            );
        }

        if (placement == null ||
                !java.util.Set.of("INDOOR", "OUTDOOR", "BOTH")
                        .contains(placement)) {
            throw badRequest("Select a valid placement");
        }

        if (description == null || description.isBlank()) {
            throw badRequest("Product description is required");
        }
        if (!Double.isFinite(price) || price <= 0) {
            throw badRequest("Price must be greater than zero");
        }

        if (image.isEmpty()) {
            throw badRequest("Select a product image");
        }

        if (image.getSize() > 5 * 1024 * 1024) {
            throw badRequest("Image must be 5 MB or smaller");
        }

        // 2. Find the category selected in the form.
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        badRequest("Selected category does not exist"));

        // 3. Read and validate the actual image content.
        BufferedImage decodedImage = readImage(image);

        // 4. Generate a unique filename.
        // JPG and PNG uploads are both saved as PNG in this example.
        String filename = UUID.randomUUID() + ".png";
        Path destination = uploadDirectory.resolve(filename);

        // 5. Save the image file.
        try {
            Files.createDirectories(uploadDirectory);

            boolean written = ImageIO.write(
                    decodedImage,
                    "png",
                    destination.toFile()
            );

            if (!written) {
                throw new IOException("Could not encode image");
            }
        } catch (IOException exception) {
            deleteFile(destination);

            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Could not save image",
                    exception
            );
        }

        // 6. Prepare the database record using your existing setters.
        // 6. Prepare the database record.
        Product product = new Product();
        product.setName(name.trim());
        product.setPrice(price);
        product.setCategory(category);
        product.setImg("/images/products/" + filename);
        product.setPlacement(placement);
        product.setDescription(description.trim());

        // 7. Save the product.
        try {
            return productRepository.saveAndFlush(product);
        } catch (RuntimeException exception) {
            // Remove the uploaded file if the database save fails.
            deleteFile(destination);
            throw exception;
        }
    }

    private BufferedImage readImage(MultipartFile file) {
        try (var stream = file.getInputStream();
             ImageInputStream input =
                     ImageIO.createImageInputStream(stream)) {

            if (input == null) {
                throw badRequest("Invalid image");
            }

            Iterator<ImageReader> readers =
                    ImageIO.getImageReaders(input);

            if (!readers.hasNext()) {
                throw badRequest("Upload a valid JPG or PNG");
            }

            ImageReader reader = readers.next();

            try {
                String format = reader.getFormatName();

                if (!format.equalsIgnoreCase("JPEG")
                        && !format.equalsIgnoreCase("PNG")) {
                    throw badRequest("Only JPG and PNG are supported");
                }

                reader.setInput(input);

                long pixels =
                        (long) reader.getWidth(0) * reader.getHeight(0);

                if (pixels > 16_000_000) {
                    throw badRequest(
                            "Image must not exceed 16 megapixels"
                    );
                }

                return reader.read(0);
            } finally {
                reader.dispose();
            }
        } catch (IOException exception) {
            throw badRequest("Could not read the image");
        }
    }

    private ResponseStatusException badRequest(String message) {
        return new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                message
        );
    }

    private void deleteFile(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException exception) {
            System.err.println(
                    "Could not clean up uploaded file: " + path
            );
        }
    }
}