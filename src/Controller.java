import java.awt.event.ActionEvent;

public class Controller {
	
	private Model model;
	private mainMenu mainMenu;
	private createAccount createAcc;
	private deleteAccount deleteAcc;
	private deposit makeDeposit;
	private selectAccount selectAcc;
	private viewAccount viewAcc;
	private withdrawl withdrawlAcc;
	
	// Constructor method handing in all views and model for usage
	public Controller(Model myModel, mainMenu menuMain, createAccount create, deleteAccount delete, deposit depositAcc, selectAccount select, viewAccount view, withdrawl withdrawlFromAcc) {
		// Declaring all of the variables so they are accessible 
		model = myModel;
		mainMenu = menuMain;
		createAcc = create;
		deleteAcc = delete;
		makeDeposit = depositAcc;
		selectAcc = select;
		viewAcc = view;
		withdrawlAcc = withdrawlFromAcc;

		// ------------------------------------------------ Main Menu Action Listeners
		mainMenu.addQuitButtonListener((ActionEvent e) -> {
			System.out.println("clicked - Main Menu - Quit Button");
			 // Quits the program when the quit button is clicked
			System.exit(0);
		});
		
		mainMenu.addSelectButtonListener((ActionEvent e) -> {
			System.out.println("clicked - Main Menu - Select Button");
			// populate the selection of accounts when button is clicked 
			selectAcc.populateSelectionList();
			// make main menu disappear 
			mainMenu.windowDisappear();
			// make select menu appear
			selectAcc.selectAccountVisible();
		});
		
		mainMenu.addCreateButtonListener((ActionEvent e) -> {
			// make main menu disappear
			mainMenu.windowDisappear();
			// make the create account visible
			createAcc.createAccountVisible();
			System.out.println("clicked - Main Menu - Create Button");
		});
		
		mainMenu.addDeleteButtonListener((ActionEvent e) -> {
			// make the main menu disappear
			mainMenu.windowDisappear();
			// make the delete account appear
			deleteAcc.deleteAccountVisible();
			System.out.println("clicked - Main Menu - Delete Button");
		});
		
		mainMenu.addDepositButtonListener((ActionEvent e) -> {
			// make the main menu disappear
			mainMenu.windowDisappear();
			// make the deposit menu appear
			makeDeposit.depositAccountVisible();
			System.out.println("clicked - Main Menu - Deposit Button");
		});
		
		mainMenu.addViewAccButtonListener((ActionEvent e) -> {
			// make the main menu disappear
			mainMenu.windowDisappear();
			// populate the transactions from the account that is currently selected
			viewAcc.populateTransactions();
			// make the view account menu visible
			viewAcc.viewAccountVisible();
			System.out.println("clicked - Main Menu - View Button");
		});
		
		mainMenu.addWithdrawlButtonListener((ActionEvent e) -> {
			// make the main menu disappear
			mainMenu.windowDisappear();
			// make the withdraw menu appear 
			withdrawlAcc.withdrawlAccountVisible();
			System.out.println("clicked - Main Menu - Withdrawl Button");
		});
		
		// ------------------------------------------------ Create Account Action Listeners
		
		createAcc.addOkButtonListener((ActionEvent e) -> {
			// gets all data when account is created
			createAcc.getSelectedAccountIndex();
			createAcc.getInitialDeposit();
			createAcc.getAccountDescription();
			// check to see if data is okay
			if (createAcc.checkInput()) {
				// create the account
				model.createAccount();
				// make the create account menu disappear
				createAcc.windowDisappear();
				// enable the main menu buttons now that there is an account
				mainMenu.buttonEnable();
				// make the main menu appear 
				mainMenu.windowAppear();
				// reset fields inside of the create account menu
				createAcc.reset();
			}
			//-------------------------------------
			System.out.println("clicked - Create Menu - Ok Button");
		});
		
		createAcc.addCanelButtonListener((ActionEvent e) -> {
			// make create account menu disappear
			createAcc.windowDisappear();
			// make the main menu appear
			mainMenu.windowAppear();
			// clear fields inside of the create account menu
			createAcc.reset();
			System.out.println("clicked - Create Menu - Cancel Button");
		});
		
		// ------------------------------------------------ Delete Account Action Listeners
		
		deleteAcc.addDeleteButtonListener((ActionEvent e) -> {
			// run the delete account method
			model.deleteAccount();
			// check how many accounts there are
			mainMenu.checkAccountCount();
			// check to see if there are accounts
			if(mainMenu.checkAccountCount() > 0) {
				// keep buttons enabled
				mainMenu.buttonEnable();
			}
			// check to see if there are no accounts
			else if(mainMenu.checkAccountCount() == 0) {
				// disable buttons if there are no accounts
				mainMenu.buttonDisable();
			}
			// make delete account menu disappear
			deleteAcc.windowDisappear();
			// make the main menu appear
			mainMenu.windowAppear();
			System.out.println("clicked - Delete Menu - Delete Button");
		});
		
		deleteAcc.addCanelButtonListener((ActionEvent e) -> {
			// make the delete account menu disappear
			deleteAcc.windowDisappear();
			// make the main menu appear 
			mainMenu.windowAppear();
			System.out.println("clicked - Delete Menu - Canel Button");
		});
		
		// ------------------------------------------------ Deposit Account Action Listeners
		
		makeDeposit.addOkButtonListener((ActionEvent e) -> {
			// run the deposit money method
			makeDeposit.depositMoney();
			// check and see if input is okay
			if (makeDeposit.checkInput()) {
				// make the deposit menu disappear
				makeDeposit.windowDisappear();
				// make the main menu appear
				mainMenu.windowAppear();
				// reset fields in the deposit menu
				makeDeposit.reset();
			}
			
			System.out.println("clicked - Deposit Menu - Ok Button");
		});
		
		makeDeposit.addCancelButtonListener((ActionEvent e) -> {
			// make the deposit menu disappear
			makeDeposit.windowDisappear();
			// make the main menu appear
			mainMenu.windowAppear();
			// reset fields in the deposit menu
			makeDeposit.reset();
			System.out.println("clicked - Deposit Menu - Cancel Button");
			
		});
		
		// ------------------------------------------------ Withdraw Account Action Listeners
		
		withdrawlAcc.addOkButtonListener((ActionEvent e) -> {
			// run the withdrawMoney method
			withdrawlAcc.withdrawMoney();
			// Check input and see if there is enough money
			if(withdrawlAcc.checkInput() && model.enoughFunds() == true) {
				// make the withdraw menu disappear
				withdrawlAcc.windowDisappear();
				// make the main menu appear
				mainMenu.windowAppear();
				// reset the fields on the withdraw menu
				withdrawlAcc.reset();
			}
			// check to see if there is not enough money
			if(withdrawlAcc.checkInput() && model.enoughFunds() == false) {
				// run the insufficient funds method if there is not enough money
				withdrawlAcc.insufficientFunds();
			}
			System.out.println("clicked - Withdrawl Menu - Ok Button");
		});
		
		withdrawlAcc.addCancelButtonListener((ActionEvent e) -> {
			// make the withdraw menu disappear
			withdrawlAcc.windowDisappear();
			// make the main menu appear
			mainMenu.windowAppear();
			// reset fields inside of the withdraw menu
			withdrawlAcc.reset();
			System.out.println("clicked - Withdrawl Menu - Cancel Button");
		});
		
		// ------------------------------------------------ Select Account Action Listeners
		
		selectAcc.addOkButtonListener((ActionEvent e) -> {
			// select the account
			selectAcc.getAccountIndex();
			// make select menu disappear
			selectAcc.windowDisappear();
			// make the main menu appear
			mainMenu.windowAppear();
			System.out.println("clicked - Select Menu - Ok Button");
		});
		
		selectAcc.addCancelButtonListener((ActionEvent e) -> {
			// make the select menu disappear
			selectAcc.windowDisappear();
			// make the main menu appear
			mainMenu.windowAppear();
			System.out.println("clicked - Select Menu - Cancel Button");
		});
		
		// ------------------------------------------------ View Account Action Listeners
		
		
		viewAcc.addOkButtonListener((ActionEvent e) -> {
			// make the view menu disappear
			viewAcc.windowDisappear();
			// make the main menu appear
			mainMenu.windowAppear();
			System.out.println("clicked - View Account Menu - Ok Button");
		});
		
		
		
		
		
		
		
		
	}
}
	
