package com.lordofthejars.bank.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class DataSourceProducer {


    @PersistenceContext(unitName = "bank")
    EntityManager em;
    
    
    @Produces @BankEntityManager EntityManager entityManager() {
        return this.em;
    }
    
}
