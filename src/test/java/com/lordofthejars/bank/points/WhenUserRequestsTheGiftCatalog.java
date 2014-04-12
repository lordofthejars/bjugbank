package com.lordofthejars.bank.points;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.lordofthejars.bank.Deployments;


@RunWith(Arquillian.class)
public class WhenUserRequestsTheGiftCatalog {

    @Deployment
    public static WebArchive createDeployment() {
        return Deployments.createGiftCatalog();
    }
    
    @Test
    public void test() {
        
        System.out.println("AAA");
        
    }
    
}
