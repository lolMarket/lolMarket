package com.lolmarket.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lolmarket.domain.users.Customer;

@Entity
@Table(name = "lm_order")
@NamedQuery(name = "retrieveOrdersToProcess", query = "SELECT o FROM Order o WHERE o.closingDate IS NOT NULL AND o.evasionDate is NULL")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column (nullable = false)
	@Temporal (TemporalType.TIMESTAMP)
	private Date openingDate;
	
	@Column
	@Temporal (TemporalType.TIMESTAMP)
	private Date closingDate;
	
	@Column
	@Temporal (TemporalType.TIMESTAMP)
	private Date evasionDate;
	
	@Column (nullable = false)
	@ManyToOne
	private Customer customer;
	
	@Column (nullable = false)
	private Float total;
	
	@OneToMany (mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<OrderLine> orderLines;
	
	public Order() {
		
	}
	
	public Order(Customer customer) {
		this.orderLines = new ArrayList<OrderLine>();
		this.openingDate = Calendar.getInstance().getTime();
		this.total = 0.0f;
		this.customer = customer;
		this.customer.addOrder(this);
	}
	
	public boolean process() {
		/*
		 * try a dry run first, and only
		 * if all is ok perform the real one so
		 * the order is process entirely.
		 * 
		 * WARNING:
		 * this simple code may lead to a race condition.
		 * If the dry run for a order X succeed and, *before* the real one starts, 
		 * another order is processed from another request and the quantity is reduced 
		 * such that the order X can't be totally processed anymore, the real doProcess 
		 * continue and partially confirm the order leading to an inconsistent state.
		 * I'm not sure about that since the transactional nature of ejb can avoid 
		 * such situations.
		 */
		if(doProcess(true) && doProcess(false)) {
			this.evasionDate = Calendar.getInstance().getTime();
			return true;
		}
		
		return false;
	}
	
	private boolean doProcess(Boolean dryRun) {
		for(OrderLine ol: this.orderLines) {
			if(ol.process(dryRun) == false)
				return false;
		}
		return true;
	}
	
	public void addProductUnit(Product p) {
		OrderLine orderLine = getOrderLineByProduct(p);
		if(orderLine == null) {
			addProduct(p, 1);
		} else {
			orderLine.incrementQuantity();
			this.total += p.getPrice(); 
		}
	}
	
	public void removeProductUnit(Product p) {
		OrderLine orderLine = getOrderLineByProduct(p);
		
		if(orderLine == null)
			return;
		
        if(orderLine.getQuantity() == 1) {
            removeOrderLine(orderLine);
        } else {
        	orderLine.reduceQuantity();
            this.total -= p.getPrice();
        }
	}
	
	public void addProduct(Product product, Integer quantity) {
		OrderLine orderLine = new OrderLine(product, quantity);
		orderLine.setOrder(this);
		this.orderLines.add(orderLine);
		this.total += orderLine.getPrice(); 
	}
	
	public void close() {
		this.closingDate = Calendar.getInstance().getTime();
	}

	public boolean isClose() {
		return this.closingDate != null;
	}
	
	public List<OrderLine> getOrderLines() {
		return this.orderLines;
	}
	
	private OrderLine getOrderLineByProduct(Product p) {
		for(OrderLine o: this.orderLines) {
			if(o != null && o.getProduct().equals(p))
				return o;
		}
		return null;
	}

	private void removeOrderLine(OrderLine orderLine) {
		this.orderLines.remove(orderLine);
		this.total -= orderLine.getPrice();
	}
	
	public Float getTotal() {
		return this.total;
	}
	
	public Date getOpeningDate() {
		return this.openingDate;
	}
	
	public Date getClosingDate() {
		return this.closingDate;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
	
}
