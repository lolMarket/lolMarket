package com.lolmarket.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.lolmarket.domain.users.Customer;

@Stateless(name="userFacade")
public class CustomerFacade {

	@PersistenceContext(unitName = "lolMarket")
	private EntityManager entityManager;
	
	public void registerCustomer(Customer customer) {
		entityManager.persist(customer);
	}
	
}