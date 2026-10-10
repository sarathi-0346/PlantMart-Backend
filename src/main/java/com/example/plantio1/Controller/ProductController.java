package com.example.plantio1.Controller;

import java.util.List;

import com.example.plantio1.Entity.Category;
import com.example.plantio1.Entity.Product;
import com.example.plantio1.Repository.CategoryRepository;
import com.example.plantio1.Services.ProductServices;
import com.example.plantio1.Services.ProductUploadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    @Autowired
    private ProductUploadService productUploadService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/getAllProduct")
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productServices.getAll());
    }

    @GetMapping("/getByIdProduct/{id}")
    public ResponseEntity<Product> getById(@PathVariable long id) {
        return ResponseEntity.ok(productServices.getById(id));
    }

    @GetMapping("/getByNameProduct")
    public ResponseEntity<Product> getByName(@RequestParam String name) {
        return ResponseEntity.ok(productServices.getByName(name));
    }

    @GetMapping("/searchByNameProduct")
    public ResponseEntity<List<Product>> searchByName(
            @RequestParam String name) {

        return ResponseEntity.ok(productServices.searchByName(name));
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productServices.addProduct(product));
    }

    @PostMapping("/addAll")
    public ResponseEntity<List<Product>> addAll(
            @RequestBody List<Product> products) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productServices.addAll(products));
    }

    @PostMapping(
            value = "/addWithImage",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Product> addWithImage(
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("placement") String placement,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile image) {

        Product savedProduct = productUploadService.addWithImage(
                name,
                price,
                categoryId,
                placement,
                description,
                image
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedProduct);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateDate(
            @PathVariable long id,
            @RequestBody Product product) {

        return ResponseEntity.ok(
                productServices.updateProduct(id, product)
        );
    }

    @DeleteMapping("/deleteData/{id}")
    public ResponseEntity<String> deleteData(@PathVariable long id) {
        productServices.deleteData(id);
        return ResponseEntity.ok("Product Deleted Successfully");
    }

    @GetMapping("/product-upload/categories")
    public List<Category> getUploadCategories() {
        return categoryRepository.findAll();
    }
}