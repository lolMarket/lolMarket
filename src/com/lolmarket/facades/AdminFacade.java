package com.lolmarket.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.lolmarket.domain.users.Admin;

@Stateless(name="adminFacade")
public class AdminFacade {

	@PersistenceContext(unitName = "lolMarket")
	private EntityManager entityManager;
	
	
	public void registerAdmin(Admin admin) {
		entityManager.persist(admin);
	}
	
	public Admin getAdmin(String email, String password) {
		Query getAdminQuery = entityManager.createNamedQuery("getAdmin");
		getAdminQuery.setParameter("email", email);
		getAdminQuery.setParameter("pass", password);
		Admin admin = (Admin) getAdminQuery.getSingleResult();
		return admin;
	}
	
}