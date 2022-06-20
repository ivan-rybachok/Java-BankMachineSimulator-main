import java.util.ArrayList;

public abstract class Account implements java.io.Serializable {
	// variables 
	protected double balance;
	private int accountNumber;
	private String description;
	private String accountType;
	private String transactionDescription;
	ArrayList<String> transactions = new ArrayList<String>();
	
	// ------------------------------------------- Constructor Method
	// constructor method
	public Account() {
		balance = 0;
	}
	
	// ------------------------------------------- Gets / Sets
	// set starting balance
	public void setStartingBalance(double initialDeposit) {
		balance = initialDeposit;
	}
	// get balance
	public double getBalance() {
		return balance;
	}
	// set account number
	public void setAccountNumber(int accNumb) {
		accountNumber = accNumb;
	}
	// set account description
	public void setAccountDescription(String desc) {
		description = desc;
	}
	// get account number 
	public int getAccountNumber() {
		return accountNumber;
	}
	// get account description 
	public String getAccountDescription() {
		return description;
	}
	// get account type 
	public String getAccountType() {
		return accountType;
	}
	// set the new balance of the account
	public void setBalance(double newBalance) {
		balance = newBalance;
	}
	// set transaction description
	public void setTransactionDescription(String desc) {
		transactionDescription = desc;
	}
	// get transaction description
	public String getTransactionDescription() {
		return transactionDescription;
	}
	// get transactions 
	public ArrayList<String> getTransactions() {
		return transactions;
	}
	
	// -------------------------------------------- Public Methods
	
	// 
	public void chargeWithdrawFee(double fee) {
		balance = balance - fee;
	}
	// add transactions to history
	public void addTransactionToHistory(String historyToAdd) {
		transactions.add(historyToAdd);
	}
	// abstract methods for use with withdraw fee and air miles accounts
	public abstract double getWithdrawFee();
	public abstract int getAirmilesBalance();
	public abstract void setAirmilesBalance(int miles);
	public abstract void addAirMiles(int newMiles);
	
	
	
}
