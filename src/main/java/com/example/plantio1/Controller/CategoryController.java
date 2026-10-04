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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.plantio1.Entity.Category;
import com.example.plantio1.Services.CategoryServices;

@RestController
@RequestMapping("/Category")
public class CategoryController {

    @Autowired
    CategoryServices categoryServices;

    // Add Category
    @PostMapping("/addCategory")
    public ResponseEntity<String> addCategory(@RequestBody Category category) {

        categoryServices.addCategory(category);

        return new ResponseEntity<>(
                "Category Added Successfully",
                HttpStatus.CREATED
        );
    }

    // Get All Categories
    @GetMapping("/getAllCategory")
    public ResponseEntity<List<Category>> getAll() {

        return new ResponseEntity<>(
                categoryServices.getAll(),
                HttpStatus.OK
        );
    }

    // Get Category By ID
    @GetMapping("/getByCId/{id}")
    public ResponseEntity<Category> getById(@PathVariable long id) {

        return new ResponseEntity<>(
                categoryServices.getById(id),
                HttpStatus.OK
        );
    }

    // Update Category
    @PutMapping("/updateCategory/{cid}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable Long cid,
            @RequestBody Category category) {

        return new ResponseEntity<>(
                categoryServices.updateCategory(cid, category),
                HttpStatus.OK
        );
    }

    // Delete All Categories
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll() {

        categoryServices.deleteAll();

        return new ResponseEntity<>(
                "All Categories Deleted Successfully",
                HttpStatus.OK
        );
    }

    // Delete Category By ID
    @DeleteMapping("/deleteById/{cid}")
    public ResponseEntity<String> deleteById(@PathVariable Long cid) {

        categoryServices.deleteById(cid);

        return new ResponseEntity<>(
                "Category Deleted Successfully",
                HttpStatus.OK
        );
    }
}