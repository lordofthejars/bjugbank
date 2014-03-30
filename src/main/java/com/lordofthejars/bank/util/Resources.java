package com.lordofthejars.bank.util;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

public class Resources {
	
	@Produces
	FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
}
