package com.lolmarket.domain.users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lolmarket.domain.Order;

@Entity
@Table(name = "lm_customer")
@NamedQuery(name = "getCustomer", query = "SELECT c FROM Customer c WHERE c.email = :email AND c.password = :pass")
public class Customer {
	@Id
	private String email;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date registrationDate;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private String password;
	
	@OneToMany (mappedBy="customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Order> orders;
	
	public Customer() {}
	
	public Customer(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	
		this.orders = new ArrayList<Order>();
	}
	
	public void addOrder(Order o) {
		this.orders.add(o);
	}
	
	public Order getOrderById(Long id) {
		for(Order o: this.orders)
			if(o.getId().equals(id))
				return o;
		return null;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public List<Order> getOrders() {
		return this.orders;
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

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return this.firstName + " " + this.lastName;
	}

}
