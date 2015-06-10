package com.lolmarket.controllers;

import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.lolmarket.domain.users.Customer;
import com.lolmarket.facades.CustomerFacade;

@ManagedBean(name = "registrationController")
@RequestScoped
public class RegistrationController {

	private final String ERROR_CUSTOMER_EXISTS = "The email provided is already registered";
	
	private String errorDescription = "";
	
	@EJB
	private CustomerFacade customerFacade;
	
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String address;
	private String email;
	private String password;
	
	public RegistrationController() {}
	
	public String registerCustomer() {
		String nextPage = "RegistrationComplete.jsp";
		
		Customer customer = new Customer(this.firstName, this.lastName, this.email);
		customer.setBirthDate(this.birthDate);
		customer.setAddress(this.address);
		customer.setPassword(this.password);
		customer.setRegistrationDate(Calendar.getInstance().getTime());

		try {
			customerFacade.registerCustomer(customer);
		} catch (Exception e) {
			this.errorDescription = ERROR_CUSTOMER_EXISTS;
			nextPage = "";
		}
		
		return nextPage;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
}
 