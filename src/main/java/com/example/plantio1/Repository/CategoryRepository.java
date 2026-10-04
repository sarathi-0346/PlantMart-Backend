package com.example.plantio1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.plantio1.Entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
