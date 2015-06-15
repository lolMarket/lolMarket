package com.lolmarket.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.lolmarket.domain.Order;
import com.lolmarket.domain.users.Customer;

@Stateless (name = "orderFacade")
public class OrderFacade {
	
	@PersistenceContext (unitName = "lolMarket")
	private EntityManager entityManager;
	
	public Order create(Customer customer) {
		Order o = new Order(customer);
		this.entityManager.persist(o);
		return o;
	}
	
	public Order getOrderById(String idOrder) {
		return this.entityManager.find(Order.class, idOrder);
	}
	
	public void mergeOrder(Order order) {
		this.entityManager.merge(order);
	}
	
}
