package com.lordofthejars.bank.loan;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.lordofthejars.bank.loan.control.InterestCalculator;

@RunWith(Arquillian.class)
public class InterestCalculatorTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addClass(InterestCalculator.class);
    }
    
    @EJB
    InterestCalculator interestCalculator;
    
    @Test
    public void should_calculate_the_intereset_of_a_load() {
        assertThat(interestCalculator.calculate(3600, 0.084, 24), is(12.6));
    }
    
}
