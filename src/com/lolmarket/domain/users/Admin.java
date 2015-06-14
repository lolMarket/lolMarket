package com.lolmarket.domain.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "lm_admin")
@NamedQuery(name = "getAdmin", query = "SELECT a FROM Admin a WHERE a.email = :email AND a.password = :pass")
public class Admin {
	
	@Id
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	public Admin() {}
	
	public Admin(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
