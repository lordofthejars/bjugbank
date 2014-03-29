package com.lordofthejars.bank.account.web;

import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(Arquillian.class)
@RunAsClient
public class WhenACustomerEnterItsCredentials {

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		return Deployments.createLogin();
	}

	@ArquillianResource
	URL contextPath;

	@Drone
	WebDriver driver;

	@Page
	private TransferPage transferPage;

	@Test
	public void should_login_succesful(@InitialPage LoginPage loginPage) {

		loginPage.signIn("aa", "bb");
		transferPage.assertOnTransferPageWithWelcomeMessage("aa");

	}

}
