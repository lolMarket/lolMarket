package com.lolmarket.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "lm_product")
@NamedQueries({
	@NamedQuery(name = "retrieveAllProducts", query = "SELECT p FROM Product p"),
	@NamedQuery(name = "retriveById", query = "SELECT p FROM Product p WHERE p.code = :code")
})
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
	private String description;
	
	@ManyToMany(mappedBy = "products")
	private List<Provider> providers;
	
	
	public Product() {}
	
	public Product(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
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
	
	public void reduceQuantity(int quantity) {
		if(this.quantity >= quantity)
			this.quantity -= quantity;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Provider> getProviders() {
		return providers;
	}

	public void setProviders(List<Provider> providers) {
		this.providers = providers;
	}
	
}
