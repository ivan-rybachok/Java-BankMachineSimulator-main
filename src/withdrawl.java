import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

public class withdrawl extends JFrame {
	// variables
	private JPanel contentPane;
	private JTextField txtWithdrawAmount;
	private JTextField txtTransactionDesc;
	private JLabel lblInsufficientFunds;
	private TextFieldValidator txtValidator1;
	private TextFieldValidator txtValidator2;
	private Model model;
	private Boolean inputValid = false;
	
	// Button Variables
	private JButton btnOkWithdrawl;
	private JButton btnCancelWithdrawl;
	
	// constructor method
	public withdrawl(Model myModel) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblApplicationTitle = new JLabel("Bank Machine Simulation V1.0 >");
		lblApplicationTitle.setBounds(10, 11, 181, 23);
		contentPane.add(lblApplicationTitle);
		
		JLabel lblWithdrawAmount = new JLabel("Enter Amount to withdraw: $");
		lblWithdrawAmount.setBounds(10, 45, 181, 14);
		contentPane.add(lblWithdrawAmount);
		
		txtWithdrawAmount = new JTextField();
		txtWithdrawAmount.setBounds(197, 42, 95, 20);
		txtValidator1 = new TextFieldValidator(txtWithdrawAmount);
		txtValidator1.setRegExp("^(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))(\\.\\d\\d)?$");
		contentPane.add(txtWithdrawAmount);
		txtWithdrawAmount.setColumns(10);
		
		JLabel lblTransactionDesc = new JLabel("Transaction Description:");
		lblTransactionDesc.setBounds(22, 82, 152, 14);
		contentPane.add(lblTransactionDesc);
		
		txtTransactionDesc = new JTextField();
		txtTransactionDesc.setColumns(10);
		txtTransactionDesc.setBounds(196, 79, 267, 20);
		txtValidator2 = new TextFieldValidator(txtTransactionDesc);
		txtValidator2.setRegExp("^[A-Za-z_\\s]{1,50}$");
		contentPane.add(txtTransactionDesc);
		
		btnOkWithdrawl = new JButton("Ok");
		btnOkWithdrawl.setBounds(160, 123, 89, 23);
		contentPane.add(btnOkWithdrawl);
		
		btnCancelWithdrawl = new JButton("Cancel");
		btnCancelWithdrawl.setBounds(259, 123, 89, 23);
		contentPane.add(btnCancelWithdrawl);
		
		lblInsufficientFunds = new JLabel("");
		lblInsufficientFunds.setForeground(Color.RED);
		lblInsufficientFunds.setBounds(302, 45, 161, 14);
		contentPane.add(lblInsufficientFunds);
		
		model = myModel;
	}
	
	// --------------------------------------------------------------- Public Methods
	// add action listener to button
	public void addOkButtonListener(ActionListener okButton) {
		btnOkWithdrawl.addActionListener(okButton);
	}
	// add action listener to button
	public void addCancelButtonListener(ActionListener cancelButton) {
		btnCancelWithdrawl.addActionListener(cancelButton);
	}
	// withdraw money method
	public void withdrawMoney() {
		// initialize things
		lblInsufficientFunds.setText("");
		String description = "";
		// check input
		if(txtValidator1.check() && txtValidator2.check()) {
			txtValidator1.setErrorColor(Color.GRAY);
			txtValidator2.setErrorColor(Color.GRAY);
			String newWithdraw = "";
			// get the information from withdraw
			newWithdraw = txtWithdrawAmount.getText();
			description = txtTransactionDesc.getText();
			// hand information over to model to do calculations
			model.withdraw(newWithdraw, description);
		}
		// checking input
		else if(txtValidator1.check() == false && txtValidator2.check() == false){
			txtValidator1.setErrorColor(new Color(255,0,0));
			txtValidator2.setErrorColor(new Color(255,0,0));
			System.out.println("error");
		}
		// checking input
		else if(txtValidator1.check() && txtValidator2.check() == false) {
			txtValidator2.setErrorColor(new Color(255,0,0));
			System.out.println("error");
		}
		// checking input
		else if(txtValidator1.check() == false && txtValidator2.check()) {
			txtValidator1.setErrorColor(new Color(255,0,0));
			System.out.println("error");
		}
			
	}
	// make view show that there are insufficient funds for the withdraw
	public void insufficientFunds() {
		txtValidator1.setErrorColor(new Color(255,0,0));
		lblInsufficientFunds.setText("Insufficient Funds");
	}
	// checking the input with textfieldvalidator
	public Boolean checkInput() {
		if(txtValidator1.check() && txtValidator2.check()) {
			inputValid = true;
		}
		else {
			inputValid = false;
		}
		
		return inputValid;
	}
	// reset method to clear all input from previous uses 
	public void reset() {
		txtValidator1.setErrorColor(Color.GRAY);
		txtValidator2.setErrorColor(Color.GRAY);
		txtWithdrawAmount.setText("");
		txtTransactionDesc.setText("");
		lblInsufficientFunds.setText("");
	}

	// --------------------------------------------------------------- Changing Views
	// make this window visible
	public void withdrawlAccountVisible() {
		this.setVisible(true);
	}
	// make this window disappear
	public void windowDisappear() {
		this.setVisible(false);
	}
}
