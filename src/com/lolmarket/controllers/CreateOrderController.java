package com.lolmarket.controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.lolmarket.domain.Order;
import com.lolmarket.domain.Product;
import com.lolmarket.domain.users.Customer;
import com.lolmarket.facades.OrderFacade;
import com.lolmarket.facades.ProductFacade;
import com.lolmarket.sessions.CustomerSession;

@ManagedBean(name = "createOrderController")
@SessionScoped
public class CreateOrderController {
	
	@EJB
	private ProductFacade productFacade;
	
	@EJB
	private OrderFacade orderFacade;
	
	@ManagedProperty ("#{customer}")
	private CustomerSession customerSession;
	
	private Order order;
	
	public CreateOrderController() {}
	
	public String createNewOrder() {
		Customer customer = customerSession.getCurrent();
		this.order = orderFacade.create(customer);
		return "";
	}
	
	public String addProductUnit(String code) {
		Product p = productFacade.retrieveById(code);
		this.order.addProductUnit(p);
		return "CreateOrder.xhtml";
	}
	
	public String removeProductUnit(String code) {
		Product p = productFacade.retrieveById(code);
		this.order.removeProductUnit(p);
		return "CreateOrder.xhtml";
	}
	
	public String closeOrder() {
		this.order.close();
		this.saveOrder();
		this.order = null;
		return "CustomerHome.xhtml?faces-redirect=true";
	}
	
	public String saveOrder() {
		orderFacade.mergeOrder(order);
		return "CreateOrder.xhtml";
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
