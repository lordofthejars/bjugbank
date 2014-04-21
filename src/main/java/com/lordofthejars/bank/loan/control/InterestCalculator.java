package com.lordofthejars.bank.loan.control;

import javax.ejb.Stateless;

@Stateless
public class InterestCalculator {

    public double calculate(double amount, double anualInterest, double numberOfPaymentsPerYear) {
        double periodicRate = anualInterest / numberOfPaymentsPerYear;
        return amount * periodicRate; 
    }
}
