/*
 * File: MeritBank.java
 * 
 * The purpose of this class is to store the account holders and cdofferings, and provide methods to 
 * manage and retrieve them. This class also has access to files stored on a computer's hard drive. The files
 * store the various account holders and their bank accounts. Lastly, this class keeps track of the next
 * available account number.
 * 
 */

package com.meritamerica.assignment4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;

import java.util.Arrays;
//import java.util.Random;

public class MeritBank {

	static AccountHolder[] accountHolders = new AccountHolder[0];
	static CDOffering[] cdOfferings = new CDOffering[0];
	static long nextAccountNumber = 0;

// Account Holder Methods //=================================================================================

	/*
	 * addAccountHolder
	 * 
	 * This method increases the size of the accountHolders array by one and adds
	 * the provided accountHolder to the end of it.
	 */
	static void addAccountHolder(AccountHolder accountHolder) {

		AccountHolder[] newAccountHolders = new AccountHolder[accountHolders.length + 1];
		int i = 0;
		for (i = 0; i < accountHolders.length; i++) {
			newAccountHolders[i] = accountHolders[i];
		}
		newAccountHolders[i] = accountHolder;
		accountHolders = newAccountHolders;
	}

	static AccountHolder[] getAccountHolders() {
		return accountHolders;
	}

	static AccountHolder[] sortAccountHolders() {	
		Arrays.sort(accountHolders);	
		return accountHolders;
	}

	static long getNextAccountNumber() {
		return nextAccountNumber++;
	}

	static void setNextAccountNumber(long newAccountNumber) {
		nextAccountNumber = newAccountNumber;
	}

// CDOffering Methods //======================================================================================

	/*
	 * getCDOfferings
	 * 
	 * This method returns the cdOfferings array.
	 */
	static CDOffering[] getCDOfferings() {
		// System.out.println(cdOfferings.length);
		return cdOfferings;
	}

	/*
	 * setCDOfferings
	 * 
	 * This method takes an input CDOffering array and overwrites the old one stored
	 * in this file.
	 */
	static void setCDOfferings(CDOffering[] offerings) {
		cdOfferings = offerings;
	}

	/*
	 * getBestCDOffering
	 * 
	 * This method loops through the CDOffering array and looks for the offering
	 * with the best future value. The getSecondBestCDOffering method will return
	 * the second best one.
	 */
	static CDOffering getBestCDOffering(double depositAmount) {
		// double highestAmount = 0;
		// for (int i = 0; i < cdOfferings.length; i++) {
		// if (cdOfferings.) {
		//
		// }
		// }
		return null;
	}

	static CDOffering getSecondBestCDOffering(double depositAmount) {
		// double secondHighestAmount = 0;
		return null;
	}

	/*
	 * clearCDOfferings
	 * 
	 * This method overwrites the old cdOfferings array with a new, empty one.
	 */
	static void clearCDOfferings() {
		cdOfferings = new CDOffering[0];
	}

	/*
	 * totalBalances
	 * 
	 * This method accesses each account holder and adds the combined balance of
	 * their accounts to a total to be returned.
	 */
	static double totalBalances() {
		double total = 0;
		for (int i = 0; i < accountHolders.length; i++) {
			total += accountHolders[i].getCombinedBalance();
		}
		return total;
	}

// File Operations //=========================================================================================

	/*
	 * readFromFile
	 * 
	 * This routine takes in a file name and reads that file of the same name. It
	 * will then go line-by-line and read in a particular format. This read data
	 * will overwrite the class information such as the CDOfferings and
	 * AccountHolders.
	 */
	
	/*
	static boolean readFromFile(String fileName) {

		File textFile = new File(fileName);
		
		try {
			// Set up the file to be read from, the file reader, and the buffered reader:
			
			FileReader reader = new FileReader(textFile);
			BufferedReader bufferedReader = new BufferedReader(reader);

			// Set the next available account number:
			setNextAccountNumber(Integer.valueOf(bufferedReader.readLine()));

			// Create a new CDOffering array to replace the old one:
			CDOffering[] newOfferings = new CDOffering[Integer.valueOf(bufferedReader.readLine())];
			// Make x amount of CDofferings:
			for (int i = 0; i < newOfferings.length; i++) {
				newOfferings[i] = CDOffering.readFromString(bufferedReader.readLine());
			}
			// Overwrite the old cdOfferings with the new one:
			cdOfferings = newOfferings;

			// Create a new account holder array based on the number of accounts in the
			// file:
			AccountHolder[] newAccountHolderArray = new AccountHolder[Integer.valueOf(bufferedReader.readLine())];

			// Now loop through the file and fill up the account holder and its bank
			// accounts:
			for (int i = 0; i < newAccountHolderArray.length; i++) {

				// Add the account holder to the temporary array:
				newAccountHolderArray[i] = AccountHolder.readFromString(bufferedReader.readLine());

				// Read x amount of checking accounts:

				for (int j = 0; j < Integer.valueOf(bufferedReader.readLine()); j++) {
					newAccountHolderArray[i]
							.addCheckingAccount(CheckingAccount.readFromString(bufferedReader.readLine()));
				}

				// Read x amount of savings accounts:
				for (int k = 0; k < Integer.valueOf(bufferedReader.readLine()); k++) {
					newAccountHolderArray[i]
							.addSavingsAccount(SavingsAccount.readFromString(bufferedReader.readLine()));
				}

				// Read x amount of cd accounts:
				for (int l = 0; l < Integer.valueOf(bufferedReader.readLine()); l++) {
					newAccountHolderArray[i].addCDAccount(CDAccount.readFromString(bufferedReader.readLine()));
				}

			}

			// Close the reader:
			reader.close();
			return true;
		} catch (IOException e) {
			System.out.println("File was not found.");
			//return false;
		} catch (ParseException e) {
			// throw new java.io.IOException();
			System.out.println("Parse exception.");
			//return false;
		} catch (NumberFormatException e) {
			System.out.println("Format error.");
			// throw new java.lang.NumberFormatException();
			//return false;
		}
		return false;
	}

*/
	
	public static boolean readFromFile(String fileName) {
		File input = new File(fileName);

		try (BufferedReader bufferRead = new BufferedReader(new FileReader(input))){

			nextAccountNumber = Long.valueOf(bufferRead.readLine());		// Sets the masterAccountNumber to 11
			
			// Reads the line with the number of CDOfferings...
			CDOffering[] newOfferings = new CDOffering[Integer.valueOf(bufferRead.readLine())];
			
			for (int i = 0; i < newOfferings.length; i++) {
				// Assigns 
				newOfferings[i] = CDOffering.readFromString(bufferRead.readLine());

			}
			cdOfferings = newOfferings;
			
			AccountHolder[] newAccountHolders = new AccountHolder[Integer.valueOf(bufferRead.readLine())];
			for (int i = 0; i < newAccountHolders.length; i++) {
				newAccountHolders[i] = AccountHolder.readFromString(bufferRead.readLine());


				CheckingAccount[] newCheckingAccounts = new CheckingAccount[Integer.valueOf(bufferRead.readLine())];
				for (int j = 0; j < newCheckingAccounts.length; j++) {
					newAccountHolders[i].addCheckingAccount(CheckingAccount.readFromString(bufferRead.readLine()));

				}

				SavingsAccount[] newSavingsAccounts = new SavingsAccount[Integer.valueOf(bufferRead.readLine())];
				for (int j = 0; j < newSavingsAccounts.length; j++) {
					newAccountHolders[i].addSavingsAccount(SavingsAccount.readFromString(bufferRead.readLine()));
				}
				
				CDAccount[] newCDAccounts = new CDAccount[Integer.valueOf(bufferRead.readLine())];
				for (int j = 0; j < newCDAccounts.length; j++) {
					newAccountHolders[i].addCDAccount(CDAccount.readFromString(bufferRead.readLine()));
				}
			}
			accountHolders = newAccountHolders;

			return true;

		} catch (FileNotFoundException e) {
			System.out.println("Error: File not found!");

		} catch (IOException e) {
			System.out.println("Error: Input / output error!");

		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	static boolean writeToFile(String fileName) {
		try {
			FileWriter fr = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fr);

			bw.write(String.valueOf(nextAccountNumber));
			bw.newLine();
			bw.write(String.valueOf(cdOfferings.length));
			bw.newLine();
			for (int i = 0; i < cdOfferings.length; i++) {
				bw.write(cdOfferings[i].writeToString());
				bw.newLine();
			}
			bw.write(String.valueOf(accountHolders.length));
			bw.newLine();
			for (int i = 0; i < accountHolders.length; i++) {
				bw.write(accountHolders[i].writeToString());
				bw.newLine();
				bw.write(accountHolders[i].getNumberOfCheckingAccounts());
				for (int j = 0; j < accountHolders[i].getNumberOfCheckingAccounts(); j++) {
					bw.write(String.valueOf(accountHolders[i].getCheckingAccounts()[j].writeToString()));
					bw.newLine();
				}
				for (int k = 0; k < accountHolders[i].getNumberOfSavingsAccounts(); k++) {
					bw.write(String.valueOf(accountHolders[i].getSavingsAccounts()[k].writeToString()));
					bw.newLine();
				}
				for (int g = 0; g < accountHolders[i].getNumberOfCDAccounts(); g++) {
					bw.write(String.valueOf(accountHolders[i].getCDAccounts()[g].writeToString()));
					bw.newLine();
				}
			}
			bw.close();
			return true;
		} catch (IOException e) {
			return false;
		}

	}
	
	public static double recursiveFutureValue(double amount, int years, double interestRate) {
		
	}

	public static boolean processTransaction(Transaction transaction) throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException{
		
	}
	
	public static FraudQueue getFraudQueue() {
		
	}
	
	public static BankAccount getBankAccount(long accountId) {
		
	}
	
	static boolean readFromFile(String fileName) {
		
	}
	
	static boolean writeToFile(String fileName) {
		
	}
}
