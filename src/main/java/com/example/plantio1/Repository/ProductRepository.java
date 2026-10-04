package com.example.plantio1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.plantio1.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findByName(String name);
	
	List<Product> findByNameContainingIgnoreCase(String name);
}
