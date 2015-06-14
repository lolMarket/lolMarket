package com.lolmarket.facades;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.lolmarket.domain.Order;

@Stateful (name = "orderFacade")
public class OrderGateway {
	
	@PersistenceContext (unitName = "lolMarket", type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	private Order order;
	
	public void manage(Order order) {
		this.order = order;
		entityManager.persist(order);
	}
	
	public Order getOrder() {
		return this.order;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void save() {
	
	}
	
}
