package com.lolmarket.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "lm_provider")
@NamedQueries({
	@NamedQuery(name = "retrieveAllProviders", query = "SELECT p FROM Provider p"),
	@NamedQuery(name = "retrieveProvider", query = "SELECT p FROM Provider p WHERE p.vatin = :vatin")
	})
public class Provider {
	
	@Id
	private String vatin;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = true)
	private String address;
	
	@Column(nullable = true)
	private String phoneNumber;
	
	@Column(nullable = true)
	private String email;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Product> products;
	
	public Provider() {}
	
	public Provider(String vatin, String name) {
		this.vatin = vatin;
		this.name = name;
	}

	public String getVatin() {
		return vatin;
	}

	public void setVatin(String vatin) {
		this.vatin = vatin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public void addProduct(Product product) {
		if(this.products == null)
			this.products = new ArrayList<Product>();
		this.products.add(product);
	}
	
}
