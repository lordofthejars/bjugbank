package com.lordofthejars.bank.account.control;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lordofthejars.bank.account.entity.Account;

@Stateless
public class AccountService {

	@EJB
	private JpaAccountRepository accountRepository;
	
	public void transfer(String from, String to, int amount) {
		
		Account toAccount = this.accountRepository.getForAccountNumber(to);
		Account fromAccount = this.accountRepository.getForAccountNumber(from);
		
		fromAccount.withdraw(amount);
		toAccount.deposit(amount);
	}

}
