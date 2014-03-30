package com.lordofthejars.bank.customer.control;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.lordofthejars.bank.customer.entity.Customer;
import com.lordofthejars.bank.util.persistence.NamedQuery;
import com.lordofthejars.bank.util.persistence.Persist;
import com.lordofthejars.bank.util.persistence.PersistenceHandler;
import com.lordofthejars.bank.util.persistence.QueryParam;

@Stateless
public abstract class JpaCustomerRepository implements Serializable, InvocationHandler {

    /**
     * 
     */
    private static final long serialVersionUID = -5158660449533256673L;
    
    @Inject
    private EntityManager em;

    @Persist
    public abstract Customer createCustomer(Customer customer);

    @NamedQuery(Customer.FIND_BY_NAME_PASSWORD)
    public abstract Customer getCustomerByNameAndPassword(@QueryParam("name") String name, @QueryParam("password") String password);

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return PersistenceHandler.invoke(this.em, method, args);
    }

}
