
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextArea;

public class Model {
	
	// Variables
	private Account currentSelectedAccount;
	static ArrayList<Account> accounts = new ArrayList<Account>();
	private int createAccountIndex;
	private int newAccountNumber;
	private String accountDescription;
	private double initialDeposit;
	private boolean enoughAvailable;
	private int accountsCreated;
	
	// constructor method 
	public Model() {
		createAccountIndex = 0;
		accountsCreated = 0;
		setAccountCount();
	}
	
	// ------------------------------------------------------- Button Disabler
	// set the account count
	public void setAccountCount() {
		// sets the account count to the size of the ArrayList that contains the account 
		accountsCreated = accounts.size(); 
	}
	
	// gets the account count
	public int getAccountCount() {
		return accountsCreated; 
	}
	
	// ------------------------------------------------------- Creating Accounts
	// gets the account type index
	public void getSelectedAccountIndex(int accountSelected) {
		createAccountIndex = accountSelected;
	}
	// gets the new accounts description
	public void getNewAccountDescription(String accountDesc) {
		accountDescription = accountDesc;
	}
	// creating the account
	public void createAccount() {
		// if account is a saving account
		if(createAccountIndex == 1) {
			// assign an account number
			getNewAccountNumber();
			// creates the account object
			SavingsAccount savings = new SavingsAccount();
			// gets initial deposit and updates balance
			savings.setStartingBalance(initialDeposit);
			// sets the account number for the account 
			savings.setAccountNumber(newAccountNumber);
			//gets the account description
			savings.setAccountDescription(accountDescription);
			// sets this account as the current selected account
			setSelectedAccount(savings);
			// add this account to the ArrayList that contains all of the accounts
			accounts.add(savings);
		}
		// if account is an air miles account
		else if(createAccountIndex == 2) {
			// gets an account number for the account
			getNewAccountNumber();
			// creates the account object
			AirmileSavingsAccount airmileSavings = new AirmileSavingsAccount();
			// gets initial deposit and updates balance
			airmileSavings.setStartingBalance(initialDeposit);
			// sets the account number for the account 
			airmileSavings.setAccountNumber(newAccountNumber);
			// sets the account description
			airmileSavings.setAccountDescription(accountDescription);
			// adds the 10 air miles for creating the air miles account
			airmileSavings.addAirmiles(10);
			// sets this account as the current selected account
			setSelectedAccount(airmileSavings);
			// add this account to the ArrayList that contains all of the accounts 
			accounts.add(airmileSavings);
		}
	}
	// gets the initial deposit
	public void getInitialDeposit(Double firstDeposit) {
		initialDeposit = firstDeposit;
	}
	// gets the new account number
	public void getNewAccountNumber() {
		newAccountNumber = accounts.size() + 1;
	}
	
	// ------------------------------------------------------- Selecting accounts
	// sets the current selected account
	public void setSelectedAccount(Account account) {
		currentSelectedAccount = account;
	}
	// returns all accounts
	public ArrayList<Account> getAccounts() {
		return accounts;
	}
	// sets selected account with the index from the ArrayList
	public void selectAccount(int index) {
		currentSelectedAccount = accounts.get(index);
	}
	
	// ------------------------------------------------------- View Account
	// generate the transaction data
	public void generateTransactions(JTextArea transactions) {
		// sets transaction string to empty
		transactions.setText("");
		// check account type if it is savings account 
		if(currentSelectedAccount.getAccountType() == "Savings Account") {
			// set text of account type
			transactions.setText(currentSelectedAccount.getAccountType() +"\n");
			// append the account description
			transactions.append("Account Description: " + currentSelectedAccount.getAccountDescription() + "\n");
			// append the account type
			transactions.append("Account Type: " + currentSelectedAccount.getAccountType() +"\n");
			// append the balance of the account
			transactions.append("Balance: $" + currentSelectedAccount.getBalance() + "\n");
			// populate the transaction history
			populateHistory(transactions);
		}
		
		if(currentSelectedAccount.getAccountType() == "Airmile Savings Account") {
			// gets air miles balance
			String airmilesBalance = String.valueOf(currentSelectedAccount.getAirmilesBalance());
			// set text of the account type
			transactions.setText(currentSelectedAccount.getAccountType() + "\n");
			// appends the air miles balance
			transactions.append("Airmiles Balance: " + airmilesBalance + "\n");
			// append the account description
			transactions.append("Account Description: " + currentSelectedAccount.getAccountDescription() + "\n");
			// append the account type
			transactions.append("Account Type: " + currentSelectedAccount.getAccountType() +"\n");
			// append the balance of the account
			transactions.append("Balance: $" + currentSelectedAccount.getBalance()+ "\n");
			// populate the transaction history
			populateHistory(transactions);
		}
		
	}
	// populate history of transactions
	public void populateHistory(JTextArea trans) {
		// gets transactions of current selected account
		ArrayList<String> transactions = currentSelectedAccount.getTransactions();
		// loops through transactions and appends them so user can view all transactions
		for (int i=0; i<transactions.size(); i++) {
			trans.append(transactions.get(i)+"\n");
		}
	}
	

	// ------------------------------------------------------- Deposit
	// deposits the money into the account
	public void deposit(String toDeposit, String description) {
		// gets current balance
		double currentBalance = currentSelectedAccount.getBalance();
		// gets amount to be deposited
		double toDepositConvert = Double.parseDouble(toDeposit);
		// do air miles calculation
		airmilesCalculate(toDepositConvert);
		// setting variables for transaction
		String transactionDescription = description;
		String transactionDetails = "";
		String date = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").format(new Date());
		String dateNoDot = date.replace(".", ""); 
		// sets the string that contains the information about the transaction
		transactionDetails = dateNoDot+": $" + toDepositConvert + " ["+transactionDescription+"]";
		// adds the transaction to the history
		currentSelectedAccount.addTransactionToHistory(transactionDetails);
		// sets the new balance of the account
		currentSelectedAccount.setBalance(currentBalance + toDepositConvert);
		// if it is an air miles account add air miles 
		if(currentSelectedAccount.getAccountType() == "Airmile Savings Account") {
			// add the air miles to the current air miles account
			currentSelectedAccount.addAirMiles(airmilesCalculate(toDepositConvert));
		}
		
	}
	// algorithm for calculating air miles 
	public int airmilesCalculate(double deposit) {
		int result = 0;
		int checker = 0;
		while(checker < deposit) {
			if (checker <= deposit) {
				checker = checker + 30;
				if(checker <= deposit) {
					result++;
				}
			}
		}
		return result;
	}
	// gets the description of the transaction
	public void getNewTransactionDescription(String description) {
		currentSelectedAccount.setTransactionDescription(description);
	}
	
	// ------------------------------------------------------- Withdraw
	// withdraw money from the account
	public void withdraw(String toWithdraw, String description) {
		// getting the current balance 
		double currentBalance = currentSelectedAccount.getBalance();
		// get the amount to withdraw from the account
		double toWithdrawConvert = Double.parseDouble(toWithdraw);
		// check to see if there is enough money to withdraw
		if((currentBalance - toWithdrawConvert - currentSelectedAccount.getWithdrawFee()) >= 0 ) {
			// if there is enough money continue this block
			enoughAvailable = true;
			// set transaction variables 
			String transactionDescription = description;
			String transactionDetails = "";
			String date = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").format(new Date());
			String dateNoDot = date.replace(".", ""); 
			// set the transaction details in string form
			transactionDetails = dateNoDot+": $-" + toWithdrawConvert + " ["+transactionDescription+"]";
			// add this transaction to the history
			currentSelectedAccount.addTransactionToHistory(transactionDetails);
			// update the balance of the account
			currentSelectedAccount.setBalance(currentBalance - toWithdrawConvert - currentSelectedAccount.getWithdrawFee());
		}
		// if there is not enough fund 
		else{
			enoughAvailable = false;
		}
		
	}
	// check to see if enough funds is true or false
	public boolean enoughFunds() {
		return enoughAvailable;
	}
	
	// ------------------------------------------------------- Delete Accounts
	// deleting account
	public void deleteAccount() {
		// remove the account from the ArrayList
		accounts.remove(currentSelectedAccount);
		// set the account count
		setAccountCount();
		// if account count is greater than zero
		if(accountsCreated > 0) {
			// set selected account to the first account that was created
			setSelectedAccount(accounts.get(0));
		}
		
		
	}
	
	
	
}
