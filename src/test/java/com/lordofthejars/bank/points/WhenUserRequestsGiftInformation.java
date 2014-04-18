package com.lordofthejars.bank.points;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;

import java.net.URI;
import java.net.URL;

import javax.ws.rs.core.UriBuilder;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.lordofthejars.bank.Deployments;


@RunWith(Arquillian.class)
public class WhenUserRequestsGiftInformation {

    @Deployment(testable=false)
    public static WebArchive createDeployment() {
        return Deployments.createGiftCatalog();
    }
    
    @ArquillianResource
    private URL contextPath;
    
    @Test
    public void gift_by_name_should_be_returned() {
        
       URI pointsUri = UriBuilder.fromUri(contextPath.toExternalForm())
                                  .path("rest/{points}/{productname}").build("points", "podometer");
        
       get(pointsUri).then()
                       .body("gift.name", is("podometer"))
                       .body("gift.description", is("digital screen, clock, belt"))
                       .body("gift.points", is(2000));
        
    }
    
}
