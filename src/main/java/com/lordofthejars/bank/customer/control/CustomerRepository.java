package com.lordofthejars.bank.customer.control;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.lordofthejars.bank.account.control.AccountRepository;
import com.lordofthejars.bank.account.entity.Account;
import com.lordofthejars.bank.customer.entity.Customer;

@Stateless
public class CustomerRepository {

	public static List<Customer> customers = new ArrayList<Customer>(){
		{
			List<Account> accounts = AccountRepository.accounts;
			
			add(new Customer("aa", "bb", 18, accounts.subList(0, 2)));
			add(new Customer("cc", "dd", 16, accounts.subList(2, 3)));
			
		}
	};
	
	public Customer getCustomerByNameAndPassword(String name, String password) {
		for (Customer customer : customers) {
			if(customer.getName().equals(name) && customer.getPassword().equals(password)) {
				return customer;
			}
		}
		
		return null;
	}

}
