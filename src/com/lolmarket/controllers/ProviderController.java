package com.lolmarket.controllers;


import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.lolmarket.domain.Provider;
import com.lolmarket.facades.ProviderFacade;

@ManagedBean(name = "providerController")
@RequestScoped
public class ProviderController {
	
	private final String PROVIDER_REGISTERED = "Provider registered!";
	private final String ERROR_PROVIDER_EXISTS = "The VAT Identification Number provided is already registered";
	
	private String message = "";
	
	@EJB
	private ProviderFacade providerFacade;
	
	private String vatin;
	private String name;
	private String address;
	private String phoneNumber;
	private String email;
	private List<Provider> providers;

	
	public ProviderController () {}
	
	public String registerProvider() {
		
		Provider provider= new Provider(this.vatin, this.name);
		provider.setAddress(this.address);
		provider.setPhoneNumber(this.phoneNumber);
		provider.setEmail(this.email);
		
		try {
			providerFacade.registerProvider(provider);
			this.message = PROVIDER_REGISTERED;
		} catch (Exception e) {
			this.message = ERROR_PROVIDER_EXISTS;
		}
		
		return "";
	}

	public void setProviderFacade(ProviderFacade providerFacade) {
		this.providerFacade = providerFacade;
	}

	public String getVatin() {
		return vatin;
	}

	public void setVatin(String vatin) {
		this.vatin = vatin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Provider> getProviders() {
		providers = providerFacade.retrieveAllProviders();
		return providers;
	}

	public void setProviders(List<Provider> providers) {
		this.providers = providers;
	}
	
	public String getMessage() {
		return this.message;
	}
	
}
