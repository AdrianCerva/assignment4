package com.meritamerica.assignment4;

public class TransferTransaction extends Transaction {

	TransferTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount) {
		this.sourceAccount = sourceAccount;
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

		if (amount < 0) {
			throw new NegativeAmountException("You cannot transfer a negative number.");
		}

		if (amount > sourceAccount.getBalance()) {
			throw new ExceedsAvailableBalanceException("You do not have enough funds to make this transfer.");
		}

		// If the above checks don't throw an exception, make the transfer:
		this.sourceAccount.withdraw(amount);
		this.targetAccount.deposit(amount);

	}

}
