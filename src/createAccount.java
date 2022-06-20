import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;

public class createAccount extends JFrame {
	// variables
	private JPanel contentPane;
	private JTextField txtAccountDescription;
	private JTextField txtStartingBalance;
	private TextFieldValidator txtValidator1;
	private TextFieldValidator txtValidator2;
	private Model model;
	private Boolean inputValid = false;
	private JLabel lblMustSelectAccount;
	
	// Button Variables
	private JButton btnOkCreate;
	private JButton btnCancelCreate;
	
	// Other Variables
	private JList<String> list;
	private DefaultListModel<String> listModel = new DefaultListModel();
	
	// constructor method
	public createAccount(Model myModel) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblApplicationTitle = new JLabel("Bank Machine Simulation V1.0 >");
		lblApplicationTitle.setBounds(10, 11, 181, 23);
		contentPane.add(lblApplicationTitle);
		
		JLabel lblSelectAccountType = new JLabel("Select Account Type:");
		lblSelectAccountType.setBounds(10, 45, 133, 14);
		contentPane.add(lblSelectAccountType);
		
		
		listModel.addElement("Savings Account");
		listModel.addElement("Airmiles Savings Account");
		list = new JList(listModel);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(153, 45, 181, 43);
		contentPane.add(list);
		
		JLabel lblAccountDescription = new JLabel("Account Description:");
		lblAccountDescription.setBounds(10, 99, 133, 14);
		contentPane.add(lblAccountDescription);
		
		txtAccountDescription = new JTextField();
		txtAccountDescription.setBounds(153, 96, 285, 20);
		contentPane.add(txtAccountDescription);
		txtAccountDescription.setColumns(10);
		txtValidator2 = new TextFieldValidator(txtAccountDescription);
		txtValidator2.setRegExp("^[A-Za-z_\\s]{1,50}$");
		
		JLabel lblStartingBalance = new JLabel("Starting Balance: $");
		lblStartingBalance.setBounds(10, 130, 133, 14);
		contentPane.add(lblStartingBalance);
		
		txtStartingBalance = new JTextField();
		txtStartingBalance.setBounds(153, 127, 102, 20);
		contentPane.add(txtStartingBalance);
		txtStartingBalance.setColumns(10);
		txtValidator1 = new TextFieldValidator(txtStartingBalance);
		txtValidator1.setRegExp("^(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))(\\.\\d\\d)?$");
		
		btnOkCreate = new JButton("Ok");
		btnOkCreate.setBounds(166, 178, 89, 23);
		contentPane.add(btnOkCreate);
		
		btnCancelCreate = new JButton("Cancel");
		btnCancelCreate.setBounds(311, 178, 89, 23);
		contentPane.add(btnCancelCreate);
		
		lblMustSelectAccount = new JLabel("");
		lblMustSelectAccount.setForeground(Color.RED);
		lblMustSelectAccount.setBounds(344, 45, 153, 14);
		contentPane.add(lblMustSelectAccount);
		
		model = myModel;
	}
	
	
	// --------------------------------------------------------------- Public Methods
	// add action listener to button
	public void addOkButtonListener(ActionListener okButton) {
		btnOkCreate.addActionListener(okButton);
	}
	// add action listener to button
	public void addCanelButtonListener(ActionListener cancelButton) {
		btnCancelCreate.addActionListener(cancelButton);
	}
	// get what type of account was selected
	public void getSelectedAccountIndex() {
		 int accountSelected = 0;

		if(list.getSelectedIndex() == 0) {
			accountSelected = 1;
			model.getSelectedAccountIndex(accountSelected);
		}
		else if (list.getSelectedIndex() == 1) {
			accountSelected = 2;
			model.getSelectedAccountIndex(accountSelected);
		}
		else {
			// if no account is selected 
			 if(list.isSelectionEmpty()) {
				 lblMustSelectAccount.setText("Must Select an Account!");
			 }
		}
		
		//model.getSelectedAccountIndex(accountSelected);
	}
	// get the initial deposit when creating account and check the input
	public void getInitialDeposit() {
		if(txtValidator1.check()) {
			txtValidator1.setErrorColor(Color.GRAY);
			double firstDeposit = 0;
			firstDeposit = Double.parseDouble(txtStartingBalance.getText());
			model.getInitialDeposit(firstDeposit);
		}
		else if(txtValidator1.check() == false){
			txtValidator1.setErrorColor(new Color(255,0,0));
			System.out.println("error");
		}
	}
	// get the account description when creating the account and check the input
	public void getAccountDescription() {
		String description = "";
		if(txtValidator2.check()) {
			txtValidator2.setErrorColor(Color.GRAY);
			description = txtAccountDescription.getText();
			model.getNewAccountDescription(description);
		}
		else if(txtValidator2.check() == false) {
			txtValidator2.setErrorColor(new Color(255,0,0));
		}
	}
	// check all of the input to see if it is good 
	public Boolean checkInput() {
		if(txtValidator1.check() && txtValidator2.check() && !list.isSelectionEmpty()) {
			inputValid = true;
		}
		else {
			inputValid = false;
		}
		
		return inputValid;
	}
	// reset method to clear input fields 
	public void reset() {
		txtAccountDescription.setText("");
		txtStartingBalance.setText("");
		txtValidator1.setErrorColor(Color.GRAY);
		txtValidator2.setErrorColor(Color.GRAY);
		list.clearSelection();
		lblMustSelectAccount.setText("");
	}
	
	// --------------------------------------------------------------- Changing Views
	// make this window visible
	public void createAccountVisible() {
		this.setVisible(true);
	}
	// make this window disappear
	public void windowDisappear() {
		this.setVisible(false);
	}
}
