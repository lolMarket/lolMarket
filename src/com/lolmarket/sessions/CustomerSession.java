package com.lolmarket.sessions;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.lolmarket.domain.Order;
import com.lolmarket.domain.users.Customer;

@ManagedBean(name = "customer")
@SessionScoped
public class CustomerSession {
	
	private Customer current;
	
	public CustomerSession() {}
	
	public void destroy() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
	
	public Customer getCurrent() {
		return this.current;
	}

	public void setCurrent(Customer current) {
		this.current = current;
	}
}