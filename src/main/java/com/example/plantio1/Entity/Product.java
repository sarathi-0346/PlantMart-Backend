package com.example.plantio1.Entity;

import jakarta.persistence.*;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Product_id;

	private String Product_name;
	private String Product_img;
	private double Product_price;

	@ManyToOne
	@JoinColumn(
			name = "category_id",
			referencedColumnName = "category_id"
	)
	private Category category;

	public Product() {
	}

	public Product(Long id, String name, String img,
				   double price, Category category) {
		this.Product_id = id;
		this.Product_name = name;
		this.Product_img = img;
		this.Product_price = price;
		this.category = category;
	}

	public Long getId() {
		return Product_id;
	}

	public void setId(Long id) {
		this.Product_id = id;
	}

	public String getName() {
		return Product_name;
	}

	public void setName(String name) {
		this.Product_name = name;
	}

	public String getImg() {
		return Product_img;
	}

	public void setImg(String img) {
		this.Product_img = img;
	}

	public double getPrice() {
		return Product_price;
	}

	public void setPrice(double price) {
		this.Product_price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}