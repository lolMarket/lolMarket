package com.lolmarket.controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.lolmarket.domain.users.Customer;
import com.lolmarket.facades.CustomerFacade;

@ManagedBean(name = "customerController")
@RequestScoped
public class CustomerController {

	Customer customer;
	
	@EJB
	private CustomerFacade customerFacade;
	
	public CustomerController() {}
	
	public void setCustomerFacade(CustomerFacade customerFacade) {
		this.customerFacade = customerFacade;
	}

	public Customer getCustomer() {
		return customer;
	}
	
	public Customer getCustomer(String email) {
		this.customer = this.customerFacade.getCustomer(email);
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
} 