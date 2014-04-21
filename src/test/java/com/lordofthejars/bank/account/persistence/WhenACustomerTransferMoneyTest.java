package com.lordofthejars.bank.account.persistence;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.descriptor.api.persistence20.PersistenceDescriptor;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.lordofthejars.bank.account.control.AccountService;
import com.lordofthejars.bank.account.control.JpaAccountRepository;
import com.lordofthejars.bank.account.entity.Account;
import com.lordofthejars.bank.customer.control.JpaCustomerRepository;
import com.lordofthejars.bank.customer.entity.Customer;
import com.lordofthejars.bank.util.BankEntityManager;
import com.lordofthejars.bank.util.persistence.PersistenceHandler;

@RunWith(Arquillian.class)
public class WhenACustomerTransferMoneyTest {

	@Deployment
	@OverProtocol("Servlet 3.0")
	public static JavaArchive createDeployment() {
		JavaArchive javaArchive = ShrinkWrap.create(JavaArchive.class)
		        .addPackage(BankEntityManager.class.getPackage())
		        .addPackage(PersistenceHandler.class.getPackage())
				.addClass(Account.class)
				.addClass(Customer.class)
				.addClass(AccountService.class)
				.addClass(JpaAccountRepository.class)
				.addClass(JpaCustomerRepository.class)
				.addClass(DbDefinition.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsManifestResource(new StringAsset(descriptor().exportAsString()), "persistence.xml");
		return javaArchive;
	}

	public static  PersistenceDescriptor descriptor() {
	    return Descriptors.create(PersistenceDescriptor.class)
	                .createPersistenceUnit()
	                    .name("bank")
	                    .getOrCreateProperties()
	                        .createProperty()
	                            .name("openjpa.jdbc.SynchronizeMappings")
	                            .value("buildSchema(ForeignKeys=true)")
	                        .up()
	                     .up()
	                .jtaDataSource("bankDS")
	                .clazz("com.lordofthejars.bank.account.entity.Account","com.lordofthejars.bank.customer.entity.Customer")
	                .up();
	}
	
	@EJB
	AccountService accountService;

	@EJB
	JpaAccountRepository accountRepository;
	
	@Test
	@UsingDataSet("transfer.yml")
	public void money_should_be_transferred_from_his_account_to_other_account() {
		
		accountService.transfer("AA00", "BB00", 50);
		
		Account fromAccount = accountRepository.getForAccountNumber("AA00");
		Account toAccount = accountRepository.getForAccountNumber("BB00");
		
		assertThat(fromAccount.getBalance(), is(50));
		assertThat(toAccount.getBalance(), is(250));
		
	}
	
}
