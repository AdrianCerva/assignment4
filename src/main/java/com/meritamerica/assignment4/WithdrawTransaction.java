package com.meritamerica.assignment4;

public class WithdrawTransaction extends Transaction {

	WithdrawTransaction(BankAccount targetAccount, double amount) {
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.isProcessed = false;
	}

	public void process()
			throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {

		if (amount > 1000) {
			FraudQueue.addTransaction(this);
			throw new ExceedsFraudSuspicionLimitException("Warning: amount is greater than $1,000!");
		}

		if (amount <= 0) {
			throw new NegativeAmountException("You cannot withdraw a negative or zero amount.");
		}

		if (targetAccount.getBalance() < amount) {
			throw new ExceedsAvailableBalanceException("You do not have enough funds to complete this transaction.");
		}

		// If the above tests do not throw an exception, go ahead with the withdrawal:
		this.targetAccount.withdraw(amount);

	}
}
