package com.lolmarket.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "lm_order_line")
public class OrderLine {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	@Column (nullable = false)
	private Integer quantity;
	
	@Column (nullable = false)
	private Product product;
	
	@Column (nullable = false)
	private Float price;
	
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "lm_order_id")
	private Order order;
	
	public OrderLine() {}
	
	public OrderLine(Product product, Integer quantity) {
		this.product = product;
		this.setQuantity(quantity);
	}
	
	public void incrementQuantity() {
		this.setQuantity(this.quantity + 1);
	}

	public void reduceQuantity() {
		if(this.quantity > 0) {
			this.setQuantity(this.quantity - 1);
		}
			
	}
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
		this.price = this.product.getPrice() * quantity;
	}

	public Product getProduct() {
		return product;
	}

	public Float getPrice() {
		return this.price; 
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
}
