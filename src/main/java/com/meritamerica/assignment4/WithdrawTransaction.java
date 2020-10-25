package com.meritamerica.assignment4;

public class WithdrawTransaction extends Transaction {

	WithdrawTransaction(BankAccount targetAccount, double amount) {
		this.targetAccount = targetAccount;
		this.amount = amount;
	}

	public void process() {
		try {

			if (amount <= 0) {
				throw new NegativeAmountException("You cannot withdraw a negative or zero amount."); 
			}
			
			if (targetAccount.getBalance() < amount){
				throw new ExceedsAvailableBalanceException("You do not have enough funds to complete this transaction.");
			} else {
				targetAccount.withdraw(amount);
			}

		} catch (NegativeAmountException e) {
			System.out.println(e);
		} catch (ExceedsAvailableBalanceException e) {
			System.out.println(e);
		}
	}

}
