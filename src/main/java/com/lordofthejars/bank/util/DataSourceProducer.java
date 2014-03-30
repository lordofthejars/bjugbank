package com.lordofthejars.bank.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DataSourceProducer {


    @PersistenceContext(unitName = "bank")
    @Produces
    @BankEntityManager
    EntityManager em;
    
}
