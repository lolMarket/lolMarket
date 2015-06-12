package com.lolmarket.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "lm_product")
@NamedQuery(name = "retrieveAllProducts", query = "SELECT p FROM Product p")
public class Product {
	
	@Id
	private String code;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Float price;
	
	@Column(nullable = false)
	private int quantity;
	
	@Column(nullable = true, length = 2000)
	private String description; //da gestire cosi' o con una ProductDescription?
	
	
	public Product() {}
	
	public Product(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
