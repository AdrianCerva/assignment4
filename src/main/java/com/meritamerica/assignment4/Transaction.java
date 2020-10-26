/*
 * File: Transaction.java
 * 
 * This class contains a single Transaction object to be created
 * in withdraw, deposit, or transfer transactions.
 * 
 * These transactions are stored 
 */

package com.meritamerica.assignment4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Transaction {
	
	BankAccount sourceAccount;
	BankAccount targetAccount;
	double amount;
	java.util.Date transactionDate;
	String rejectionReason;
	boolean isProcessed;
	static private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	
	/*
	 * Getter and setter methods:
	 */
	
	public BankAccount getSourceAccount() {
		return this.sourceAccount;
	}
	
	public void setSourceAccount(BankAccount sourceAccount) {
		this.sourceAccount = sourceAccount;
	}
	
	BankAccount getTargetAccount() {
		return this.targetAccount;
	}
	
	public void setTargetAccount(BankAccount targetAccount) {
		this.targetAccount = targetAccount;
	}

	public double getAmount() {
		return this.amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public java.util.Date getTransactionDate(){
		return this.transactionDate;
	}
	
	public void setTransactionDate(java.util.Date date) {
		this.transactionDate = date;
	}
	
	/*
	 * writeToString
	 * 
	 * Used in writing to a file.
	 */
	
	public String writeToString() {
		return null; // NEED TO WRITE //////////////////////////////////////
	}
	
	/*
	 * readFromString
	 * 
	 * Used in reading from a file.
	 */
	public static Transaction readFromString(String transactionDataString) throws ParseException {
        Transaction trans;
        ArrayList<String> aL = new ArrayList<>(Arrays.asList(transactionDataString.split(",")));
        if (aL.get(0).equals("-1")) {
            if (Double.parseDouble(aL.get(2)) > 0) {
                trans = new DepositTransaction(MeritBank.getBankAccount(Long.parseLong(aL.get(1))), Double.parseDouble(aL.get(2)));
                trans.setTransactionDate(formatter.parse(aL.get(3)));
                return trans;
            } else {
                trans = new WithdrawTransaction(MeritBank.getBankAccount(Long.parseLong(aL.get(1))), Math.abs(Double.parseDouble(aL.get(2))));
                trans.setTransactionDate(formatter.parse(aL.get(3)));
                return trans;
            }
        } else {
            trans = new TransferTransaction(MeritBank.getBankAccount(Long.parseLong(aL.get(0))), MeritBank.getBankAccount(Long.parseLong(aL.get(1))), Math.abs(Double.parseDouble(aL.get(2))));
            trans.setTransactionDate(formatter.parse(aL.get(3)));
            return trans;
        }
    }
	
	/*
	 * process
	 * 
	 * This is an abstract method that gets written in any class that extends this class.
	 * 
	 * 
	 */
	public abstract void process() throws NegativeAmountException,
	ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException;
		
	/*
	 * isProcessedByFraudTeam
	 * 
	 * Checks if it's been processed by the fraud team.
	 */
	public boolean isProcessedByFraudTeam() {
		if(this.isProcessed == true) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * setProcessedByFraudTeam
	 * 
	 * Sets the class variable to true.
	 */
	public void setProcessedByFraudTeam(boolean isProcessed) {
		this.isProcessed = true;
	}
	
	/*
	 * getRejectionReason
	 * 
	 * Returns the reason for the rejection.
	 */
	public String getRejectionReason() {
		return rejectionReason;
	}
	
	/*
	 * setRejectionReason
	 * 
	 * Allows the fraud team to include a reason for the rejected 
	 * transaction.
	 */
	public void setRejectionReason(String reason) {
		this.rejectionReason = reason;
	}
}
