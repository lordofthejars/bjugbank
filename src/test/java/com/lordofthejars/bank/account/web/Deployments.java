package com.lordofthejars.bank.account.web;

import java.io.File;

import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import com.lordofthejars.bank.account.boundary.TransferController;
import com.lordofthejars.bank.account.control.AccountRepository;
import com.lordofthejars.bank.account.control.AccountService;
import com.lordofthejars.bank.account.entity.Account;
import com.lordofthejars.bank.customer.boundary.LogInController;
import com.lordofthejars.bank.customer.control.CustomerRepository;
import com.lordofthejars.bank.customer.entity.Customer;
import com.lordofthejars.bank.util.Resources;

public class Deployments {

	private static final String WEBAPP_SRC = "src/main/webapp";
	
	public static WebArchive createLogin() {
		return ShrinkWrap
				.create(WebArchive.class, "login.war")
				.addClass(Resources.class)
				.addClasses(Account.class, AccountService.class,
						AccountService.class, TransferController.class,
						AccountRepository.class, AccountRepository.class)
				.addClasses(Customer.class, LogInController.class,
						CustomerRepository.class, CustomerRepository.class)
				.merge(ShrinkWrap.create(GenericArchive.class)
						.as(ExplodedImporter.class)
						.importDirectory(WEBAPP_SRC + "/resources/css")
						.as(GenericArchive.class), "/resources/css", Filters.includeAll())
				.merge(ShrinkWrap.create(GenericArchive.class)
						.as(ExplodedImporter.class)
						.importDirectory(WEBAPP_SRC + "/resources/fonts")
						.as(GenericArchive.class), "/resources/fonts", Filters.includeAll())
				.merge(ShrinkWrap.create(GenericArchive.class)
						.as(ExplodedImporter.class)
						.importDirectory(WEBAPP_SRC + "/resources/js")
						.as(GenericArchive.class), "/resources/js", Filters.includeAll())
				.addAsWebResource(new File(WEBAPP_SRC, "login.xhtml"))
				.addAsWebResource(new File(WEBAPP_SRC, "template.xhtml"))
				.addAsWebResource(new File(WEBAPP_SRC, "transfer.xhtml"))
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource(
						new File(WEBAPP_SRC, "WEB-INF/faces-config.xml"))
				.addAsWebInfResource(new File(WEBAPP_SRC, "WEB-INF/web.xml"));
	}
	
}
