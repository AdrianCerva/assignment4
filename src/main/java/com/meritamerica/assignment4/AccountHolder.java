package com.meritamerica.assignment4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;

public class AccountHolder implements Comparable<AccountHolder> {

	/*
	 * Constants:
	 */

	public static final long MAX_ALLOWED = 250000;

	/*
	 * Instance variables:
	 */

	private String firstName;
	private String middleName;
	private String lastName;
	private String ssn;
	private CheckingAccount[] checkAccounts = new CheckingAccount[0];
	private SavingsAccount[] saveAccounts = new SavingsAccount[0];
	private CDAccount[] cdAccounts = new CDAccount[0];
	// public FraudQueue[] fraudQueue = new FraudQueue[0];

	/*
	 * Constructors:
	 */

	public AccountHolder(String firstName, String middleName, String lastName, String ssn) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.ssn = ssn;
		// this.fraudQueue = new FraudQueue();
	}

	/*
	 * Class Methods:
	 */
	public String getFirstName() {

		return this.firstName;
	}

	private void setFirstName(String firstName) {

		this.firstName = firstName;
	}

	public String getMiddleName() {

		return this.middleName;
	}

	private void setMiddleName(String middleName) {

		this.middleName = middleName;
	}

	public String getLastName() {

		return this.lastName;
	}

	private void setLastName(String lastName) {

		this.lastName = lastName;
	}

	public String getSSN() {

		return this.ssn;
	}

	private void setSSN(String ssn) {

		this.ssn = ssn;
	}

	/*
	 * addCheckingAccount
	 * 
	 * These two methods add a checking account to the checkingAccount class array.
	 * One accepts an opening balance and creates a new checking account object.
	 * Then, if the combined balance of all accounts is less than 250000, we call
	 * the other addCheckingAccount method and pass it our new checking account.
	 * 
	 * In the second method, we create a new array equal with a size equal to the
	 * old checking account array plus one. Loop through and re-add old objects,
	 * then add our newest one.
	 * 
	 * Finally, we set the old checking account array equal to the new one. This
	 * essentially updates the pointer in the checkAccounts array.
	 */
	public CheckingAccount addCheckingAccount(double openingBalance) throws ExceedsCombinedBalanceLimitException {
		CheckingAccount newname = new CheckingAccount(openingBalance);

		if (getCombinedBalance() + openingBalance >= MAX_ALLOWED) {
			throw new ExceedsCombinedBalanceLimitException(
					"You have reached the maximum total balance across all accounts. Cannot create another.");
		} else {
			// Add transaction:
			DepositTransaction newTransaction = new DepositTransaction(newname, openingBalance);
			return addCheckingAccount(newname);
		}

	}

	public CheckingAccount addCheckingAccount(CheckingAccount checkingAccount)
			throws ExceedsCombinedBalanceLimitException {
		if (getCombinedBalance() + checkingAccount.getBalance() >= MAX_ALLOWED) {

			throw new ExceedsCombinedBalanceLimitException(
					"You have reached the maximum total balance across all accounts. Cannot create another.");
//			return null;
		} else {
			CheckingAccount[] newArray = new CheckingAccount[checkAccounts.length + 1];
			int i;
			for (i = 0; i < checkAccounts.length; i++) {
				newArray[i] = checkAccounts[i];
			}

			newArray[i] = checkingAccount;
			checkAccounts = newArray;

			return checkingAccount;
		}
	}

	/*
	 * getCheckingAccounts
	 * 
	 * This method returns the checkAccounts array.
	 */
	public CheckingAccount[] getCheckingAccounts() {
		return checkAccounts;
	}

	/*
	 * getNumberOfCheckingAccounts
	 * 
	 * Simply returns how many checking accounts we have.
	 */
	public int getNumberOfCheckingAccounts() {
		return this.checkAccounts.length;
	}

	/*
	 * getCheckingBalance
	 * 
	 * This method returns a total of all the checking accounts combined.
	 */
	public double getCheckingBalance() {
		double total = 0;
		for (int i = 0; i < checkAccounts.length; i++) {
			total += checkAccounts[i].getBalance();
		}

		return total;
	}

	public SavingsAccount addSavingsAccount(double openingBalance) throws ExceedsCombinedBalanceLimitException {
		SavingsAccount newname = new SavingsAccount(openingBalance);

		// if (openingBalance > 1000.0) {
		// throw new ExceedsFraudSuspicionLimitException("Amount exceeds $1,000. Fraud
		// team notified.");
		// }
		if (getCombinedBalance() + openingBalance >= MAX_ALLOWED) {

			throw new ExceedsCombinedBalanceLimitException(
					"You have reached the maximum total balance across all accounts. Cannot create another.");

		} else {
			// Add a deposit transaction:
			DepositTransaction newTransaction = new DepositTransaction(newname, openingBalance);
			return addSavingsAccount(newname);
		}
	}

	public SavingsAccount addSavingsAccount(SavingsAccount savingsAccount) throws ExceedsCombinedBalanceLimitException {
		if (getCombinedBalance() + savingsAccount.getBalance() >= MAX_ALLOWED) {
			throw new ExceedsCombinedBalanceLimitException(
					"You have reached the maximum total balance across all accounts. Cannot create another.");

//			return null;
		} else {
			SavingsAccount[] newArray = new SavingsAccount[saveAccounts.length + 1];
			int i;
			for (i = 0; i < saveAccounts.length; i++) {
				newArray[i] = saveAccounts[i];
			}
			newArray[i] = savingsAccount;
			saveAccounts = newArray;
			return savingsAccount;
		}
	}

	public SavingsAccount[] getSavingsAccounts() {
		return this.saveAccounts;
	}

	public int getNumberOfSavingsAccounts() {
		return this.saveAccounts.length;
	}

	public double getSavingsBalance() {
		double total = 0;

		for (int i = 0; i < saveAccounts.length; i++) {
			total += saveAccounts[i].getBalance();
		}

		return total;
	}

	public CDAccount addCDAccount(CDAccount cdAccount) {
		CDAccount[] newArray = new CDAccount[cdAccounts.length + 1];
		int i;
		for (i = 0; i < cdAccounts.length; i++) {
			newArray[i] = cdAccounts[i];
		}
		newArray[i] = cdAccount;
		cdAccounts = newArray;
		return cdAccount;
	}

	public CDAccount addCDAccount(CDOffering offering, double openingBalance)
			throws ExceedsFraudSuspicionLimitException {

		if (openingBalance > 1000) {
			throw new ExceedsFraudSuspicionLimitException(
					"The opening balance has exceeded the fraud suspicion limit.");
		}

		CDAccount newName = new CDAccount(offering, openingBalance);
		DepositTransaction newTransaction = new DepositTransaction(newName, openingBalance);
		return addCDAccount(newName);
	}

	public CDAccount[] getCDAccounts() {
		return this.cdAccounts;
	}

	public int getNumberOfCDAccounts() {
		return this.cdAccounts.length;
	}

	public double getCDBalance() {
		double total = 0;

		for (int i = 0; i < cdAccounts.length; i++) {
			total += cdAccounts[i].getBalance();
		}

		return total;
	}

	public double getCombinedBalance() {
		double total = 0;

		for (int i = 0; i < checkAccounts.length; i++) {
			total += checkAccounts[i].getBalance();
		}

		for (int i = 0; i < saveAccounts.length; i++) {
			total += saveAccounts[i].getBalance();
		}

		for (int i = 0; i < cdAccounts.length; i++) {
			total += cdAccounts[i].getBalance();
		}

		return total;
	}

	public String toString() {
		return "Name: " + this.firstName + " " + this.middleName + " " + this.lastName + "\n" + "SSN: " + this.ssn
				+ "\n" + this.getCheckingAccounts().toString();
	}

	public static AccountHolder readFromString(String accountHolderData) throws ParseException {

		String[] newAccountHolder = accountHolderData.split(",");
		return new AccountHolder(newAccountHolder[0], newAccountHolder[1], newAccountHolder[2], newAccountHolder[3]);

	}

	@Override
	public int compareTo(AccountHolder arg0) {

		if (this.getCombinedBalance() > arg0.getCombinedBalance()) {
			return 1;
		} else {
			return -1;
		}
	}

	public String writeToString() {
		String newString = this.firstName + "," + this.middleName + "," + this.lastName + "," + this.ssn;
		return newString;
	}
}
