import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TextFieldValidator {
	//private final String VALID_GUESS = "[1-9]";
	
	private JTextField targetTextField;
	private String regExp;
	private Color myErrorColor;
	//--------------------------------------------------- Constructor method

	/**
	 * Constructor method of the TextFieldValidator object
	 * @param textField This will construct the TextFieldValidator object from the input of the JTextField.
	 */
	public TextFieldValidator(JTextField textField) {
		// Give access to the JTextField 
		targetTextField = textField;
		// Default Regular Expression
		regExp = "\\w";
		// Default Color of the JTextField borders
		myErrorColor = Color.GRAY;
	}
	/**
	 * Constructor method of the TextFieldValidator object
	 * @param textField This will construct the TextFieldValidator object from the input of the JTextField.
	 * @param errorColor This will set the color of the JTextFields border when validating the JTextField.
	 */
	public TextFieldValidator(JTextField textField, Color errorColor) {
		// Give access to the JTextField
		targetTextField = textField;
		// Default regular Expression
		regExp = "\\w";
		// Gets error color if the input is incorrect
		myErrorColor = errorColor;
	}
	
	
	//------------------------------------------------------ Public Methods
	
	/**
	 *  This method is used to check the input from the user and validate it that it passes the regular expression.
	 * @return This will return true or false for the input being valid.
	 */
	public boolean check() {
		// Sets the isValid to false before checking the input
		boolean isValid = false;
		// Checks to see if the targetTextField matches the regular expression
		if (targetTextField.getText().matches(regExp)) {
			// Sets isValid to true if passes check
			isValid = true;
			// Sets the border color to the default color because it passed the input.
			targetTextField.setBorder(BorderFactory.createLineBorder(myErrorColor, 1));
		}
		// If the check fails it will enter this else statement 
		else {
			// Sets isValid to false because the input did not pass the validation from the regular expression
			isValid = false;
			// Sets error color because the input did not pass the validation
			targetTextField.setBorder(BorderFactory.createLineBorder(myErrorColor, 1));

		}
		// Return whether or not the input passed the check
		return isValid;
	}
	
	/**
	 * This method is used to reset the fields back to the default.
	 */
	public void reset() {
		// Sets the error color
		setErrorColor(Color.GRAY);
		// Sets the color of the JTextField boxes back to the default when the reset button is pressed
		targetTextField.setBorder(BorderFactory.createLineBorder(myErrorColor, 1));
	}
	
	//------------------------------------------------------ gets and sets 
	/**
	 * This method is given the regular expression to check the input from the JTextField object.
	 * @param value regular expression to use for validation
	 */
	public void setRegExp(String value) {
		// Sets the regular expression to the value that was given
		regExp = value;
	}
	/**
	 * This method is used to set the color of the JtextField borders if there is an error in the input from the user.
	 * @param value the value is the color if input is incorrect
	 */
	public void setErrorColor(Color value) {
		// Sets the error color to the value that was given
		myErrorColor = value;
	}
	
}
