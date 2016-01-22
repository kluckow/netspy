package netspy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class EmailContainer.
 */
public class EmailContainer {

	/** The mails. */
	private List<Email> mails = new ArrayList<>();
	
	/**
	 * Instantiates a new email container.
	 *
	 * @param mails the mails
	 */
	public EmailContainer() {
	}

	/**
	 * Gets the mails.
	 *
	 * @return the mails
	 */
	public List<Email> getMails() {
		
		return mails;
	}

	/**
	 * Sets the mails.
	 *
	 * @param mails the new mails
	 */
	public void setMails(List<Email> mails) {
		
		this.mails = mails;
	}
	
}
