package com.lordofthejars.bank.util;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Resources {

	/*@PersistenceContext(unitName = "")
	@Produces
	@BankEntityManager
	EntityManager em;*/
	
	@Produces
	FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
}
