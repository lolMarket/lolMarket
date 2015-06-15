package com.lolmarket.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.lolmarket.domain.Order;
import com.lolmarket.sessions.CustomerSession;

@ManagedBean(name = "orderController")
@RequestScoped
public class OrderController {
	
	@ManagedProperty ("#{customer}")
	private CustomerSession customerSession;
	
	@ManagedProperty ("#{createOrderController}")
	private CreateOrderController createOrderController;
	
	public OrderController() {}
	
	public String continueOrder(Long orderId) {
		Order o = customerSession.getCurrent().getOrderById(orderId);
		createOrderController.setOrder(o);
		return "CreateOrder.xhtml";
	}
	
	public String viewOrder(Long orderId) {
		return "ViewOrder.xhtml?faces-redirect=true&id=" + orderId.toString();
		
	}
	
	public void setCustomerSession(CustomerSession session) {
		this.customerSession = session;
	}
	
	public void setCreateOrderController(CreateOrderController createOrderCtlr) {
		this.createOrderController = createOrderCtlr;
	}
}