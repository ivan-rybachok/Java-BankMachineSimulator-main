import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class mainMenu extends JFrame {
	// variables
	private JPanel contentPane;
	private Model model;
	
	// Button Variables
	private JButton btnQuit;
	private JButton btnSelectAccount;
	private JButton btnCreate;
	private JButton btnDelete;
	private JButton btnDeposit;
	private JButton btnView;
	private JButton btnWithdrawl;
	private int disabler;
	
	// constructor method
	public mainMenu(Model myModel) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnSelectAccount = new JButton("Select");
		btnSelectAccount.setBounds(47, 45, 89, 23);
		contentPane.add(btnSelectAccount);
		
		btnDeposit = new JButton("Deposit");
		btnDeposit.setBounds(47, 79, 89, 23);
		contentPane.add(btnDeposit);
		
		btnWithdrawl = new JButton("Withdraw");
		btnWithdrawl.setBounds(47, 113, 89, 23);
		contentPane.add(btnWithdrawl);
		
		btnCreate = new JButton("Create");
		btnCreate.setBounds(47, 147, 89, 23);
		contentPane.add(btnCreate);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(47, 181, 89, 23);
		contentPane.add(btnDelete);
		
		btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnView.setBounds(47, 215, 89, 23);
		contentPane.add(btnView);
		
		btnQuit = new JButton("Quit");
		btnQuit.setBounds(47, 249, 89, 23);
		contentPane.add(btnQuit);
		
		JLabel lblApplicationTitle = new JLabel("Bank Machine Simulation V1.0 >");
		lblApplicationTitle.setBounds(10, 11, 181, 23);
		contentPane.add(lblApplicationTitle);
		
		model = myModel;
		disabler = model.getAccountCount();
		if(disabler == 0) {
			buttonDisable();
		}
		
	}
	
	// --------------------------------------------------------------- Public Methods
	// add action listener to button
	public void addQuitButtonListener(ActionListener quitButton) {
		btnQuit.addActionListener(quitButton);
	}
	// add action listener to button
	public void addSelectButtonListener(ActionListener selectButton) {
		btnSelectAccount.addActionListener(selectButton);
	}
	// add action listener to button
	public void addCreateButtonListener(ActionListener createButton) {
		btnCreate.addActionListener(createButton);
	}
	// add action listener to button
	public void addDeleteButtonListener(ActionListener deleteButton) {
		btnDelete.addActionListener(deleteButton);
	}
	// add action listener to button
	public void addDepositButtonListener(ActionListener depositButton) {
		btnDeposit.addActionListener(depositButton);
	}
	// add action listener to button
	public void addViewAccButtonListener(ActionListener viewAccButton) {
		btnView.addActionListener(viewAccButton);
	}
	// add action listener to button
	public void addWithdrawlButtonListener(ActionListener withdrawlButton) {
		btnWithdrawl.addActionListener(withdrawlButton);
	}
	// disable buttons
	public void buttonDisable() {
		btnSelectAccount.setEnabled(false);
		btnDeposit.setEnabled(false);
		btnWithdrawl.setEnabled(false);
		btnDelete.setEnabled(false);
		btnView.setEnabled(false);
	}
	// enable buttons
	public void buttonEnable() {
		btnSelectAccount.setEnabled(true);
		btnDeposit.setEnabled(true);
		btnWithdrawl.setEnabled(true);
		btnDelete.setEnabled(true);
		btnView.setEnabled(true);
		
	}
	
	// check how many accounts there are
	public int checkAccountCount() {
		disabler = model.getAccountCount();
		return disabler;
	}
	
	// --------------------------------------------------------------- Changing Views
	// make this window disappear
	public void windowDisappear() {
		this.setVisible(false);
	}
	// make this window visible
	public void windowAppear() {
		this.setVisible(true);
	}
	
}
