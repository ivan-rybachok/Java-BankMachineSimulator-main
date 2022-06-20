
public class AirmileSavingsAccount extends Account {
	// variables 
	private double withdrawFee;
	private int airmiles;
	private String accountType;
	// constructor method
	public AirmileSavingsAccount() {
		withdrawFee = 0.75;
		airmiles = 0;
		accountType = "Airmile Savings Account";
	}
	// initial air miles on account creation
	public void initialAirMiles() {
		airmiles = airmiles + 10;
	}
	// add air miles 
	public void addAirmiles(int airmilesToAdd) {
		airmiles = airmiles + airmilesToAdd;
	}
	// get withdraw fee
	public double getWithdrawFee() {
		return withdrawFee;
	}
	// get air miles balance
	public int getAirmilesBalance() {
		return airmiles;
	}
	// get account type
	public String getAccountType() {
		return accountType;
	}
	// set air miles balance
	public void setAirmilesBalance(int miles) {
		airmiles = miles;
	}
	// add air miles to balance
	public void addAirMiles(int newMiles) {
		airmiles = airmiles + newMiles;
	}



}
