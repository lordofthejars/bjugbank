package com.lordofthejars.bank.account.persistence;

import javax.annotation.sql.DataSourceDefinition;
import javax.annotation.sql.DataSourceDefinitions;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@DataSourceDefinitions({
    @DataSourceDefinition (
        className="org.hsqldb.jdbcDriver",
        name="bankDS",
        user="sa",
        password="",
        databaseName="bank",
        properties = {"connectionAttributes=;create=true"},
        url = "jdbc:hsqldb:mem:bank"
    ),
    @DataSourceDefinition (
            transactional = false,
            className="org.hsqldb.jdbcDriver",
            name="bankDSNonJta",
            user="sa",
            password="",
            databaseName="bank",
            properties = {"connectionAttributes=;create=true"},
            url = "jdbc:hsqldb:mem:bank"
        )
    })
public class DbDefinition {

}
