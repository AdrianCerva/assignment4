package com.meritamerica.assignment4;

import java.util.List;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public abstract class BankAccount {
	
	/*
	 * Instance Variables:
	 */
	
	public double accountBalance;
	public long accountNumber;
	public double interestRate;
	public Date startDate;
	//static private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	private List<Transaction> transactionList = new ArrayList<Transaction>();
	
	/*
	 * Constructors:
	 */
	
	public BankAccount(double balance) {
		this.accountBalance = balance;
		this.accountNumber = MeritBank.getNextAccountNumber();
	}
	
	public BankAccount(double balance, double interestRate) {
		this.accountBalance = balance;
		this.interestRate = interestRate;
		this.accountNumber = MeritBank.getNextAccountNumber();
	}
	
	public BankAccount(long accountNumber, double balance, double interestRate) {
		this.accountNumber = accountNumber;
		this.accountBalance = balance;
		this.interestRate = interestRate;
	}
	
	public BankAccount(long accountNumber, double balance, double interestRate, java.util.Date accountOpenedOn) {
		this.accountNumber = accountNumber;
		this.accountBalance = balance;
		this.interestRate = interestRate;
		this.startDate = accountOpenedOn;
	}
	
	public BankAccount(long accountNumber, double balance, double interestRate, java.util.Date accountOpenedOn, int term) {
		this.accountNumber = accountNumber;
		this.accountBalance = balance;
		this.interestRate = interestRate;
		this.startDate = accountOpenedOn;
		
	}
	
	/*
	 * Class methods:
	 */
	
	public long getAccountNumber() {
		return this.accountNumber;
	}
	
	public double getBalance() {
		
		return this.accountBalance;
	}
	
	public boolean withdraw(double amount) {
		if(amount <= this.accountBalance) {
			this.accountBalance -= amount;
			return true;
		} else {
			System.out.println("You have insufficient funds to complete this transaction. " +
								"Please call your bank if you feel this information is incorrect.");
			return false;
		}
	}
	
	public boolean deposit(double amount) {
		if(amount <= 0) {
			System.out.println("Cannot add a value of $0 or less!");
			return false;
		} else {
			this.accountBalance += amount;
			DepositTransaction transaction = new DepositTransaction(this, amount);
			transactionList.add(transaction);
			System.out.println("Deposited $" + amount + " into your account.");
			return true;
		}
	}
	
	java.util.Date getOpenedOn() {
		return startDate;
	}
	
	public double futureValue(int years) {
		return MeritBank.recursiveFutureValue(accountBalance, years, getInterestRate());
	}
	
	public double getInterestRate() {
		return this.interestRate;
	}
	
	/*
	 * truncate
	 * 
	 * This will truncate a double to the hundredth's place. Good for displaying
	 * money.
	 */
	public double truncateValue(double toTruncate) {		// Optionally truncates the output of the account balance in 3 years.
		
		toTruncate *= 100; 
		int truncatedInt = (int)toTruncate;
		double truncatedDouble = (double)truncatedInt / 100;
		return truncatedDouble;
	}

	
	public String writeToString() {
		String newString = this.accountNumber + "," + this.accountBalance + "," + this.interestRate + "," + this.startDate;
		return newString;
	}

	public String toString() {
		
		return  "Account Balance: $" + getBalance() + "\n" +
				"Account Interest Rate: 0.0001\n" +
				"Account Balance in 3 years: $" + truncateValue(futureValue(3));
	}
	
	public void addTransaction(Transaction transaction) {
		this.transactionList.add(transaction);
	}
	
	public List<Transaction> getTransactions() {
		return this.transactionList;
	}
	
	
}
