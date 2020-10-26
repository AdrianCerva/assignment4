package com.meritamerica.assignment4;

public class DepositTransaction extends Transaction {

	DepositTransaction(BankAccount targetAccount, double amount) {
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.isProcessed = false;
	}

	public void process()
			throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {

		if (amount > 1000) {
			FraudQueue.addTransaction(this);
			throw new ExceedsFraudSuspicionLimitException(
					"This transaction is greater than or equal to 1000, and must be reviewed.");
		}

		if (amount <= 0) {
			throw new NegativeAmountException("You cannot deposit a negative or zero amount.");
		}

		this.targetAccount.deposit(amount);

	}

}
