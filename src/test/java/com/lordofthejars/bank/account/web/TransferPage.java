package com.lordofthejars.bank.account.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TransferPage {

	@FindBy
	private WebElement welcomeMessage;
	
	@FindBy(id = "summary")
	private WebElement summaryTable;
	
	public void assertOnTransferPageWithWelcomeMessage(String username) {
		assertThat(welcomeMessage.getText(), is("Welcome back, "+username));
	}
	
}
