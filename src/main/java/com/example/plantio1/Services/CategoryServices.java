package com.example.plantio1.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.plantio1.Entity.Category;
import com.example.plantio1.Repository.CategoryRepository;

@Service
public class CategoryServices {

    @Autowired
    CategoryRepository categoryRepository;

    // Add Category
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    // Get All Categories
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    // Get Category By ID
    public Category getById(long id) {
        return categoryRepository.getReferenceById(id);
    }

    // Update Category
    public Category updateCategory(Long id, Category category) {

        Category oldCategory = categoryRepository.getReferenceById(id);

        oldCategory.setName(category.getName());

        return categoryRepository.save(oldCategory);
    }

    // Delete All Categories
    public void deleteAll() {
        categoryRepository.deleteAll();
    }

    // Delete Category By ID
    public void deleteById(Long id) {

        Category category = categoryRepository.getReferenceById(id);

        categoryRepository.delete(category);
    }
}