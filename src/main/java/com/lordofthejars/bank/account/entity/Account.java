package com.lordofthejars.bank.account.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.lordofthejars.bank.customer.entity.Customer;

@Entity
@NamedQueries({ 
                @NamedQuery(name = Account.FIND_BY_NUMBER, query = "SELECT a FROM Account a WHERE a.accountNumber = :accountNumber") 
              })
public class Account {

    public static final String FIND_BY_NUMBER = "account.findByAccountNumber";

    @Id
    @GeneratedValue
    private long id;

    private String accountNumber;
    private int balance;

    @ManyToOne
    @JoinColumn(name = "product_fk", nullable = false)
    private Customer customer;
    
    public Account() {

    }

    public Account(String accountNumber, int balance, Customer customer) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customer = customer;
    }

    public void withdraw(int amount) {
        this.balance = this.balance - amount;
    }

    public void deposit(int amount) {
        this.balance = this.balance + amount;
    }

    public int getBalance() {
        return this.balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String toString() {
        return this.getAccountNumber();
    }

}
