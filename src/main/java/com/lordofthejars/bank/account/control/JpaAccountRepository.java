package com.lordofthejars.bank.account.control;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.lordofthejars.bank.account.entity.Account;
import com.lordofthejars.bank.util.BankEntityManager;
import com.lordofthejars.bank.util.persistence.NamedQuery;
import com.lordofthejars.bank.util.persistence.Persist;
import com.lordofthejars.bank.util.persistence.PersistenceHandler;
import com.lordofthejars.bank.util.persistence.QueryParam;

@Stateless
public abstract class JpaAccountRepository implements Serializable, InvocationHandler {

    /**
     * 
     */
    private static final long serialVersionUID = -20398768083031833L;
    
    @Inject
    @BankEntityManager
    EntityManager em;
	
    @Persist
	public abstract void createAccount(Account account);
	
    @NamedQuery(Account.FIND_BY_NUMBER)
	public abstract Account getForAccountNumber(@QueryParam("accountNumber") String accountNumber);
	
	@Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return PersistenceHandler.invoke(this.em, method, args);
    }

}
