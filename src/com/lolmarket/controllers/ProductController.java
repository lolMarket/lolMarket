package com.lolmarket.controllers;


import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.lolmarket.domain.Product;
import com.lolmarket.facades.ProductFacade;
import com.lolmarket.facades.ProviderFacade;

@ManagedBean(name = "productController")
@RequestScoped
public class ProductController {
	
	private final String PRODUCT_REGISTERED = "Product registered!";
	private final String ERROR_PRODUCT_EXISTS = "The product code provided is already registered";
	
	private String message = "";
	
	@EJB
	private ProductFacade productFacade;
	
	@EJB
	private ProviderFacade providerFacade;
	
	private String code;
	private String name;
	private Float price;
	private int quantity;
	private String description;
	private List<Product> productCatalog;
	private String[] selectedProvidersVatin;
	
	public ProductController () {}
	
	public String registerProduct() {
		
		Product product = new Product(this.code, this.name);
		product.setPrice(this.price);
		product.setQuantity(this.quantity);
		product.setDescription(this.description);
		
		try {
			providerFacade.updateProviders(this.selectedProvidersVatin, product);
			this.message = PRODUCT_REGISTERED;
		} catch (Exception e) {
			this.message = ERROR_PRODUCT_EXISTS;
		}
		
		return "";
	}
	
	public List<Product> getProductCatalog() {
		this.productCatalog = productFacade.retrieveAllProducts();
		return this.productCatalog;
	}
	
	public void setProductCatalog(List<Product> productCatalog) {
		this.productCatalog = productCatalog;
	}

	public void setProductFacade(ProductFacade productFacade) {
		this.productFacade = productFacade;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public String getMessage() {
		return this.message;
	}

	public String[] getSelectedProvidersVatin() {
		return selectedProvidersVatin;
	}

	public void setSelectedProvidersVatin(String[] selectedProvidersVatin) {
		this.selectedProvidersVatin = selectedProvidersVatin;
	}

	public ProviderFacade getProviderFacade() {
		return providerFacade;
	}

	public void setProviderFacade(ProviderFacade providerFacade) {
		this.providerFacade = providerFacade;
	}
	
	
}
