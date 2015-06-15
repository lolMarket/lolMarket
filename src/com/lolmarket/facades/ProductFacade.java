package com.lolmarket.facades;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.lolmarket.domain.Product;

@Stateless(name = "productFacade")
public class ProductFacade {
	
	@PersistenceContext(unitName = "lolMarket")
	private EntityManager entityManager;
	
	public void registerProduct(Product product) {
		entityManager.persist(product);
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> retrieveAllProducts() {
		Query query = entityManager.createNamedQuery("retrieveAllProducts");
		
		List<Product> result =(List<Product>) query.getResultList();
		
		return result;
	}
	
	public Product retrieveById(String code) {
		Query query = entityManager.createNamedQuery("retriveById");
		query.setParameter("code", code);
		return (Product) query.getSingleResult();
	}
	
}
