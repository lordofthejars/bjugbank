package com.lordofthejars.bank.account.boundary;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.lordofthejars.bank.account.control.JpaAccountRepository;
import com.lordofthejars.bank.account.control.AccountService;
import com.lordofthejars.bank.customer.entity.Customer;

@Named
@RequestScoped
public class TransferController {
	
	@EJB
	private AccountService accountService;
	
	@EJB
	private JpaAccountRepository jpaAccountRepository;
	
	@Inject
	@Named("currentCustomer")
	private Customer currentCustomer;
	
	@Inject
    private FacesContext facesContext;
	
	private String fromAccount;
	private String toAccount;
	private int amount;
	
	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}
	
	public String getFromAccount() {
		return fromAccount;
	}
	
	public String getToAccount() {
		return toAccount;
	}
	
	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void transfer() {
		
		if(currentCustomer.getAge() < 18) {
			facesContext.addMessage(null, new FacesMessage("Underage users cannot do transfers."));
		} else {
			this.accountService.transfer(fromAccount, toAccount, amount);
		}
	}
	
}
