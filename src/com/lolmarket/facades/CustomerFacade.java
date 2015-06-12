package com.lolmarket.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.lolmarket.domain.users.Customer;

@Stateless(name="userFacade")
public class CustomerFacade {

	@PersistenceContext(unitName = "lolMarket")
	private EntityManager entityManager;
	
	public void registerCustomer(Customer customer) {
		entityManager.persist(customer);
	}
	
	public Customer getCustomer(String email, String password) {
		Query getCustomerQuery = entityManager.createNamedQuery("getCustomer");
		getCustomerQuery.setParameter("email", email);
		getCustomerQuery.setParameter("pass", password);
		Customer customer = (Customer) getCustomerQuery.getSingleResult();
		return customer;
	}
	
}