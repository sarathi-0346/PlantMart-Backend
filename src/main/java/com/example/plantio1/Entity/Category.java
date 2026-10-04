package com.example.plantio1.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;
    private String category_name;

	@JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product > product;
    

	public Category(Long category_id, String category_name, List<Product> product) {
		super();
		this.category_id = category_id;
		this.category_name = category_name;
		this.product = product;
	}


	


	public Long getCid() {
		return category_id;
	}


	public void setCid(Long cid) {
		this.category_id = category_id;
	}


	public String getName() {
		return category_name;
	}


	public void setName(String name) {
		this.category_name = category_name;
	}


	public List<Product> getProduct() {
		return product;
	}


	public void setProduct(List<Product> product) {
		this.product = product;
	}


	public Category() {
		super();
	}
    
}
