package com.lordofthejars.bank.customer.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.lordofthejars.bank.account.entity.Account;

@Entity(name="customer")
@NamedQueries({ 
                @NamedQuery(name = Customer.FIND_BY_NAME_PASSWORD, query = "SELECT c FROM Customer c WHERE c.name = :name AND c.password = :password")
              })
public class Customer implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = -2692936915554094415L;

    public static final String FIND_BY_NAME_PASSWORD = "customer.findByNameAndPassword";

    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String password;
    private int age;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<Account>();

    public Customer() {

    }

    public Customer(String name, String password, int age) {
        super();
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

}
