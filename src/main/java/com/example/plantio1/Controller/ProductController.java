package com.example.plantio1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.plantio1.Entity.Product;
import com.example.plantio1.Services.ProductServices;

@RestController
public class ProductController {

    @Autowired
    ProductServices productServices;

    @GetMapping("/getAllProduct")
    public ResponseEntity<List<Product>> getAll() {

        return new ResponseEntity<>(
                productServices.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/getByIdProduct/{id}")
    public ResponseEntity<Product> getById(@PathVariable long id) {

        return new ResponseEntity<>(
                productServices.getById(id),
                HttpStatus.OK
        );
    }

    @GetMapping("/getByNameProduct")
    public ResponseEntity<Product> getByName(@RequestParam String name) {

        return new ResponseEntity<>(
                productServices.getByName(name),
                HttpStatus.OK
        );
    }

    @GetMapping("/searchByNameProduct")
    public ResponseEntity<List<Product>> searchByName(@RequestParam String name) {

        return new ResponseEntity<>(
                productServices.searchByName(name),
                HttpStatus.OK
        );
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {

        return new ResponseEntity<>(
                productServices.addProduct(product),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/addAll")
    public ResponseEntity<List<Product>> addAll(
            @RequestBody List<Product> products) {

        return new ResponseEntity<>(
                productServices.addAll(products),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateDate(
            @PathVariable long id,
            @RequestBody Product product) {

        return new ResponseEntity<>(
                productServices.updateProduct(id, product),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/deleteData/{id}")
    public ResponseEntity<String> deleteData(@PathVariable long id) {

        productServices.deleteData(id);

        return new ResponseEntity<>(
                "Product Deleted Successfully",
                HttpStatus.OK
        );
    }
}