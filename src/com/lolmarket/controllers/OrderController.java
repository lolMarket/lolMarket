package com.lolmarket.controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.lolmarket.domain.Order;
import com.lolmarket.facades.OrderFacade;
import com.lolmarket.sessions.CustomerSession;

@ManagedBean(name = "orderController")
@RequestScoped
public class OrderController {
	
	private final String ERROR_PROCESS = "This order can't be processed";
	private final String PROCESS_COMPLETE = "Order Processed";
			
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
			this.sendMessage(FacesMessage.SEVERITY_ERROR, "orderController", ERROR_PROCESS);
		} else {
			orderFacade.mergeOrder(order);
			this.sendMessage(FacesMessage.SEVERITY_INFO, "orderController", PROCESS_COMPLETE);
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
	
	public void sendMessage(Severity severity, String id, String message) {
		FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(severity, message, ""));
	}
}