package com.lolmarket.controllers;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.lolmarket.domain.Order;
import com.lolmarket.domain.Product;
import com.lolmarket.facades.OrderGateway;
import com.lolmarket.facades.ProductFacade;
import com.lolmarket.sessions.CustomerSession;

@ManagedBean(name = "orderController")
@SessionScoped
public class OrderController {
	
	@EJB
	private ProductFacade productFacade;
	
	@EJB
	private OrderGateway orderGateway;
	
	@ManagedProperty ("#{customer}")
	private CustomerSession customerSession;
	
	private Order order;
	
	public OrderController() {}
	
	/*
	 * here the bean is fully created
	 * all dependencies (if any) are injected
	 */
	@PostConstruct
	public void init() {
		order = new Order(customerSession.getCurrent());
		this.orderGateway.manage(order);
	}
	
	public String addProductUnit(String code) {
		Product p = productFacade.retriveById(code);
		this.order.addProductUnit(p);
		return "";
	}
	
	public String removeProductUnit(String code) {
		Product p = productFacade.retriveById(code);
		this.order.removeProductUnit(p);
		return "";
	}
	
	public String closeOrder() {
		this.order.close();
		this.saveOrder();
		return "CustomerHome.xhtml";
	}
	
	public String saveOrder() {
		this.orderGateway.save();
		return "";
	}
	
	public Order getOrder() {
		return this.order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public void setCustomerSession(CustomerSession session) {
		this.customerSession = session;
	}
}
