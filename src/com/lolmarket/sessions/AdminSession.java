package com.lolmarket.sessions;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.lolmarket.domain.users.Admin;

@ManagedBean(name = "admin")
@SessionScoped
public class AdminSession {
	
	private Admin current;
	
	public AdminSession() {}
	
	public void destroy() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
	
	public Admin getCurrent() {
		return this.current;
	}

	public void setCurrent(Admin current) {
		this.current = current;
	}
}