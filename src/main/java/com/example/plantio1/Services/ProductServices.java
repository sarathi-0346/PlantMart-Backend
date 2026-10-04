package com.example.plantio1.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.plantio1.Entity.Product;
import com.example.plantio1.Repository.ProductRepository;

@Service
public class ProductServices {

    @Autowired
    ProductRepository productRepository;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> addAll(List<Product> product) {
        return productRepository.saveAll(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(long id) {
        return productRepository.getReferenceById(id);
    }

    public Product getByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public Product updateProduct(Long id, Product newProduct) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setName(newProduct.getName());
        existingProduct.setImg(newProduct.getImg());
        existingProduct.setPrice(newProduct.getPrice());

        return productRepository.save(existingProduct);
    }

    public void deleteData(long id) {
        productRepository.deleteById(id);
    }
}