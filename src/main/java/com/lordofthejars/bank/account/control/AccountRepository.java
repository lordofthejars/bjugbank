package com.lordofthejars.bank.account.control;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.lordofthejars.bank.account.entity.Account;

@Stateless
public class AccountRepository {

	public static List<Account> accounts = new ArrayList<Account>() {
			{
				add(new Account("AA00", 100));
				add(new Account("BB00", 200));
				add(new Account("CC00", 300));
			}
		};
	
	
	public void createAccount(Account account) {
		accounts.add(account);
	}
		
	public Account getForAccountNumber(String accountNumber) {
		for (Account account : this.accounts) {
			if(account.getAccountNumber().equals(accountNumber)) {
				return account;
			}
		}
		return null;
	}

}
