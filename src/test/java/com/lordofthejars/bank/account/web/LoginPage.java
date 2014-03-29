package com.lordofthejars.bank.account.web;

import static org.jboss.arquillian.graphene.Graphene.guardHttp;

import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Location("login.xhtml")
public class LoginPage {

	@FindBy
	private WebElement username;
	
	@FindBy
	private WebElement password;
	
	@FindBy
	private WebElement submit;
	
	public void signIn(String login, String password) {
		
		this.username.sendKeys(login);
		this.password.sendKeys(password);
		
		guardHttp(this.submit).click();
		
	}
	
	
}
