package com.lordofthejars.bank;

import java.io.File;

import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import com.lordofthejars.bank.account.boundary.TransferService;
import com.lordofthejars.bank.account.control.AccountService;
import com.lordofthejars.bank.account.control.JpaAccountRepository;
import com.lordofthejars.bank.account.entity.Account;
import com.lordofthejars.bank.customer.boundary.LogInService;
import com.lordofthejars.bank.customer.control.JpaCustomerRepository;
import com.lordofthejars.bank.customer.entity.Customer;
import com.lordofthejars.bank.points.boundary.CatalogService;
import com.lordofthejars.bank.points.control.GiftCatalogService;
import com.lordofthejars.bank.points.model.Gift;
import com.lordofthejars.bank.rs.ApplicationConfig;
import com.lordofthejars.bank.util.Resources;

public class Deployments {

	private static final String WEBAPP_SRC = "src/main/webapp";
	
	public static WebArchive createGiftCatalog() {
	    
	    WebArchive javaArchive = ShrinkWrap.create(WebArchive.class, "giftcatalog.war")
                .addClass(CatalogService.class)
                .addClass(ApplicationConfig.class)
                .addClass(GiftCatalogService.class)
                .addClass(Gift.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        return javaArchive;
	    
	}
	
	public static WebArchive createLogin() {
		return ShrinkWrap
				.create(WebArchive.class, "login.war")
				.addClass(Resources.class)
				.addClasses(Account.class,
						AccountService.class, TransferService.class,
						JpaAccountRepository.class)
				.addClasses(Customer.class, LogInService.class,
						JpaCustomerRepository.class)
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
