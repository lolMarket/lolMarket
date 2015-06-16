package com.lolmarket.controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.lolmarket.domain.Order;
import com.lolmarket.facades.OrderFacade;
import com.lolmarket.sessions.CustomerSession;

@ManagedBean(name = "orderController")
@RequestScoped
public class OrderController {
	
	private String errorDescription;
	
	@ManagedProperty ("#{customer}")
	private CustomerSession customerSession;
	
	@ManagedProperty ("#{createOrderController}")
	private CreateOrderController createOrderController;
	
	@EJB
	private OrderFacade orderFacade;
	
	private List<Order> ordersToProcess;
	
	public OrderController() {}
	
	public String continueOrder(Long orderId) {
		Order o = customerSession.getCurrent().getOrderById(orderId);
		createOrderController.setOrder(o);
		return "CreateOrder.xhtml" + "?faces-redirect=true";
	}
	
	public String viewOrder(Long orderId) {
		return "OrderDetails.xhtml" + "?faces-redirect=true&id=" + orderId.toString();
		
	}
	
	
	public String processOrder(Order order) {
		if(! order.process()) {
			this.errorDescription = "This order can't be processed";
		} else {
			orderFacade.mergeOrder(order);
		}
		return "";
	}
	
	public Order getOrderById(Long id) {
		return this.customerSession.getCurrent().getOrderById(id);
	}
	
	public List<Order> getOrdersToProcess() {
		this.ordersToProcess = orderFacade.retrieveOrdersToProcess();
		return this.ordersToProcess;
	}
	
	public void setCustomerSession(CustomerSession session) {
		this.customerSession = session;
	}
	
	public void setCreateOrderController(CreateOrderController createOrderCtlr) {
		this.createOrderController = createOrderCtlr;
	}
	
	public void setOrderFacade(OrderFacade orderFacade) {
		this.orderFacade = orderFacade;
	}
	
	public String getErrorDescription() {
		return this.errorDescription;
	}
}