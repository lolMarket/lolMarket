package com.lolmarket.facades;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	
	@SuppressWarnings("unchecked")
	public List<Order> retrieveOrdersToProcess() {
		Query query = this.entityManager.createNamedQuery("retrieveOrdersToProcess");
		return query.getResultList();
	}
}
