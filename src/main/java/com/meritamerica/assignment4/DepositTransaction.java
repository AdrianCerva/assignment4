package com.meritamerica.assignment4;

public class DepositTransaction extends Transaction {
	
	
	DepositTransaction(BankAccount targetAccount, double amount){
		this.targetAccount = targetAccount;
		this.amount = amount;
	}

	public void process() {
		try {
			
			if(amount <= 0) {
				throw new NegativeAmountException("You cannot deposit a negative or zero amount.");
			} else {
				targetAccount.deposit(amount);
			}
			
		} catch(NegativeAmountException e) {
			System.out.println(e);
		}
		
	}

}
