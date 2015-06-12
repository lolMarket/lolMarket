package com.lolmarket.controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.lolmarket.domain.users.Customer;
import com.lolmarket.facades.CustomerFacade;
import com.lolmarket.sessions.CustomerSession;

@ManagedBean(name = "loginController")
@RequestScoped
public class LoginController {

	private String errorDescription = "";
	
	@EJB
	private CustomerFacade customerFacade;
	
	@ManagedProperty ("#{customer}")
	private CustomerSession customerSession;
	
	private String email;
	private String password;
	
	public LoginController() {}
	
	public String login() {
		String nextPage = "CustomerHome.xhtml";
		
		try {
			Customer customer = customerFacade.getCustomer(email, password);
			customerSession.setCurrent(customer);
		} catch (Exception e) {
			this.errorDescription = "Wrong email or password";
			nextPage = "";
		}
		
		return nextPage;
	}
	
	public String logout() {
		customerSession.destroy();
		return "Login.xhtml";
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserFacade(CustomerFacade userFacade) {
		this.customerFacade = userFacade;
	}

	public String getPassword() {
		return this.password;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getErrorDescription() {
		return this.errorDescription;
	}

	public void setCustomerSession(CustomerSession session) {
		this.customerSession = session;
	}
}
 