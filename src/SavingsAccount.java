
public class SavingsAccount extends Account {
	// variables
	private double withdrawFee;
	private String accountType;
	// constructor method
	public SavingsAccount() {
		withdrawFee = 0.5;
		accountType = "Savings Account";
	}
	// get withdraw fee
	public double getWithdrawFee() {
		return withdrawFee;
	}
	// get account type
	public String getAccountType() {
		return accountType;
	}

 	@Override // not to be used
	public int getAirmilesBalance() {
		return 0;
	}
	

	@Override // not to be used
	public void setAirmilesBalance(int miles) {
		// TODO Auto-generated method stub
		
	}

	@Override // not to be used
	public void addAirMiles(int newMiles) {
		
	}
}
