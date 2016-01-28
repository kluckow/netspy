package netspy.components.util;

import java.util.Scanner;

/**
 * The Class InputHelper.
 */
public class InputHelper {
	
	/** The input helper. */
	private static InputHelper inputHelper = null;
	
	/**
	 * Instantiates a new input helper.
	 */
	private InputHelper() {}
	
	/**
	 * Gets the single instance of InputHelper.
	 *
	 * @return single instance of InputHelper
	 */
	public static InputHelper getInstance() {
		
		if (InputHelper.inputHelper == null) {
			inputHelper = new InputHelper();
		}
		return inputHelper;
	}

	/**
	 * Expect string.
	 *
	 * @param scanner the scanner
	 * @return the string
	 */
	public String expectString(Scanner scanner) {
		
		String userInput = ""; 
		boolean isInputValid = false;
		
		while (!isInputValid) {
			
			userInput = scanner.nextLine();
			isInputValid = this.isInputValid(userInput);
		}
		return userInput;
	}
	
	/**
	 * Checks if is input valid.
	 *
	 * @param userInput the user input
	 * @return true, if is input valid
	 */
	private boolean isInputValid(String userInput) {
		
		return !userInput.isEmpty();
	}
}
