package com.example.plantio1.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cart")
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "User_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "Product_id")
	private Product product;
	
	private int Quantity;

	

	public Card(Long id, User user, Product product, int Quantity) {
		super();
		this.id = id;
		this.user = user;
		this.product = product;
		this.Quantity = Quantity;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}


	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return Quantity;
	}



	public void setQuantity(int Quantity) {
		this.Quantity = Quantity;
	}



	public Card() {
		super();
	}
	
}
