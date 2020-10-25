package com.meritamerica.assignment4;

public abstract class Transaction {
	
	BankAccount sourceAccount;
	BankAccount targetAccount;
	double amount;
	java.util.Date transactionDate;
	String rejectionReason;
	boolean isProcessed;
	
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
	 * ToString
	 */
	
	public String writeToString() {
		
	}
	
	public static Transaction readFromString(String transactionDataString) {
		
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
		
	
	public boolean isProcessedByFraudTeam() {
		if(this.amount > 1000) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setProcessedByFraudTeam(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}
	
	public String getRejectionReason() {
		return rejectionReason;
	}
	
	public void setRejectionReason(String reason) {
		this.rejectionReason = reason;
	}
}
