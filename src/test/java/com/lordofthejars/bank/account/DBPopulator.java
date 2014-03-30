package com.lordofthejars.bank.account;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.lordofthejars.bank.account.entity.Account;
import com.lordofthejars.bank.customer.control.JpaCustomerRepository;
import com.lordofthejars.bank.customer.entity.Customer;

@Singleton
@Startup
public class DBPopulator {

    @EJB
    JpaCustomerRepository customerRepository;
    
    @PostConstruct
    private void populateDb() {
        initCustomers();
    }
    
    private void initCustomers() {
        
        //Customer1
        {
            Customer customer1 = new Customer("aa", "bb", 20);
        
            //Accounts
            Account account1 = new Account("AA00", 100, customer1);
            customer1.addAccount(account1);
        
            Account account2 = new Account("BB00", 200, customer1);
            customer1.addAccount(account2);
        
            Account account3 = new Account("CC00", 300, customer1);
            customer1.addAccount(account3);
            
            customerRepository.createCustomer(customer1);
        }
        
    }
    
}
