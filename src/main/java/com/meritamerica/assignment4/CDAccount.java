package com.meritamerica.assignment4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CDAccount extends BankAccount {

	/*
	 * Instance Variables:
	 */

	private CDOffering offering;
	static private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	/*
	 * Constructor:
	 */

	CDAccount(CDOffering offering, double balance) {
		super(balance);
		this.offering = offering;
	}

	CDAccount(long accountNumber, double balance, double interestRate, Date startDate, int term) {
		super(accountNumber, balance, interestRate, startDate, term);
		offering = new CDOffering(term,interestRate);

	}

	/*
	 * Class methods:
	 */

	public double getInterestRate() {
		return this.offering.getInterestRate();
	}

	int getTerm() {
		return this.offering.getTerm();
	}

	public static CDAccount readFromString(String accountData) throws ParseException {
		try {
			String[] newAccountHolder = accountData.split(",");
			java.util.Date startDate = formatter.parse(newAccountHolder[3]);

			return new CDAccount(Long.parseLong(newAccountHolder[0]), Double.parseDouble(newAccountHolder[1]),
					Double.parseDouble(newAccountHolder[2]), startDate, Integer.parseInt(newAccountHolder[4]));
		} catch (ParseException e) {
			throw new java.lang.NumberFormatException();
		}
	}

	@Override
	public boolean deposit(double amount) {
		return false;
	}

	@Override
	public boolean withdraw(double amount) {
		return false;
	}

	public double futureValue() {
		return this.getBalance() * (Math.pow(1 + this.getInterestRate(), this.getTerm()));
	}

}