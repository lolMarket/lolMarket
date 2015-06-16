package com.lolmarket.controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.lolmarket.domain.users.Admin;
import com.lolmarket.domain.users.Customer;
import com.lolmarket.facades.AdminFacade;
import com.lolmarket.facades.CustomerFacade;
import com.lolmarket.sessions.AdminSession;
import com.lolmarket.sessions.CustomerSession;

@ManagedBean(name = "loginController")
@RequestScoped
public class LoginController {

	private String errorDescription = "";
	
	private final String LOGIN_PAGE_URL = "Login.xhtml";
	private final String WRONG_CREDENTIALS = "Wrong email or password";
	
	@EJB
	private CustomerFacade customerFacade;
	
	@EJB
	private AdminFacade adminFacade;
	
	@ManagedProperty ("#{admin}")
	private AdminSession adminSession;
	
	@ManagedProperty ("#{customer}")
	private CustomerSession customerSession;
	
	private String email;
	private String password;
	private boolean isAdmin;
	
	public LoginController() {}
	
	public String login() {
		
		String nextPage = "";
		
		if(this.isAdmin) {
			try {
				Admin admin = adminFacade.getAdmin(email, password);
				adminSession.setCurrent(admin);
				nextPage = "AdminHome.xhtml"+"?faces-redirect=true";
			} catch (Exception e) {
				this.errorDescription = WRONG_CREDENTIALS;
				nextPage = LOGIN_PAGE_URL;
			}
		} else {
			try {
				Customer customer = customerFacade.getCustomer(email, password);
				customerSession.setCurrent(customer);
				nextPage = "CustomerHome.xhtml"+"?faces-redirect=true";
			} catch (Exception e) {
				this.errorDescription = WRONG_CREDENTIALS;
				nextPage = LOGIN_PAGE_URL;
			}
		}
		
		return nextPage;
	}
	
	public String customerLogout() {
		customerSession.destroy();
		return LOGIN_PAGE_URL;
	}
	
	public String adminLogout() {
		adminSession.destroy();
		return LOGIN_PAGE_URL;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setCustomerFacade(CustomerFacade customerFacade) {
		this.customerFacade = customerFacade;
	}
	
	public void setAdminFacade(AdminFacade adminFacade) {
		this.adminFacade = adminFacade;
	}

	public String getPassword() {
		return this.password;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public boolean getIsAdmin() {
		return this.isAdmin;
	}
	
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public String getErrorDescription() {
		return this.errorDescription;
	}

	public void setCustomerSession(CustomerSession session) {
		this.customerSession = session;
	}
	
	public void setAdminSession(AdminSession session) {
		this.adminSession = session;
	}
	
}
 