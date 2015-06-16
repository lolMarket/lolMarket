package com.lolmarket.facades;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.lolmarket.domain.Product;
import com.lolmarket.domain.Provider;

@Stateless(name = "providerFacade")
public class ProviderFacade {
	
	@PersistenceContext(unitName = "lolMarket")
	private EntityManager entityManager;
	
	public void registerProvider(Provider provider) {
		entityManager.persist(provider);
	}
	
	@SuppressWarnings("unchecked")
	public List<Provider> retrieveAllProviders() {
		Query query = entityManager.createNamedQuery("retrieveAllProviders");
		
		List<Provider> result =(List<Provider>) query.getResultList();
		
		return result;
	}

	public void updateProviders(String[] selectedProvidersVatin, Product product) {
		Provider provider;
		for(String vatin : selectedProvidersVatin) {
			provider = entityManager.find(Provider.class, vatin);
			provider.addProduct(product);
			entityManager.persist(provider);
		}
	}
	
}
