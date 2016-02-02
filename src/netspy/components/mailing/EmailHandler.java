/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.mailing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import netspy.components.filehandling.lists.Blacklist;
import netspy.components.filehandling.manager.FileManager;
import netspy.components.logging.LogManager;
import netspy.components.util.StringHelper;

/**
 * The Class EmailHandler.
 */
public class EmailHandler {
	
//	TODO: make security lvl configurable or not?
	/** The Constant SECURITY_LEVEL. */
	public static final int SECURITY_LEVEL = 6;
	
	/** The Constant EML_FILE_EXTENSION. */
	public static final String EML_FILE_EXTENSION = ".eml";

	/** The Constant HTML_MAIL_PART_START_IDENTIFIER. */
	private static final String HTML_MAIL_PART_START_IDENTIFIER = "Content-Type: text/html;";
	
	/** The Constant MAIL_SENDER_IDENTIFIER. */
	private static final String MAIL_SENDER_IDENTIFIER = "from:";
	
	/** The Constant MAIL_RECEIVER_IDENTIFIER. */
	private static final String MAIL_RECEIVER_IDENTIFIER = "to:";
	
	/** The Constant MAIL_SUBJECT_IDENTIFIER. */
	private static final String MAIL_SUBJECT_IDENTIFIER = "subject:";
	
	/** The Constant MAIL_SENDING_DATE_IDENTIFIER. */
	private static final String MAIL_SENDING_DATE_IDENTIFIER = "date:";
	
	/** The mail containers. */
	private EmailContainer mailContainer = new EmailContainer();
		
	/** The eml files. */
	private List<File> emlFiles = null;
	
	/** The blacklist. */
	private Blacklist blacklist;
	
	/** The index list of non suspicious emails. */
	private List<Integer> indexListOfNonSuspiciousEmails = new ArrayList<>();
	
	/**
	 * Check mailbox.
	 *
	 * @return true, if successful
	 */
	public boolean checkMailbox() {
		
		return !getEmlFiles().isEmpty();
	}
	
	/**
	 * Gets the eml files.
	 *
	 * @return the eml files
	 */
	public List<File> getEmlFiles() {
		
		// avoid reading over and over again
		if (this.emlFiles == null) {
			this.emlFiles = new FileManager().getFilesByExtension(EML_FILE_EXTENSION);
		}
		return this.emlFiles;
	}
	
	/**
	 * Extract email properties.
	 *
	 * @param email the email
	 * @return the email
	 */
	private Email extractEmailProperties(Email email) {
		
		for (String line: email.getLines()) {
			
			if (line.startsWith(MAIL_SENDER_IDENTIFIER)) {
				email.setSender(this.extractSender(line));
			}
			if (line.startsWith(MAIL_RECEIVER_IDENTIFIER)) {
				email.setReceiver(this.extractReceiver(line));
			}
			if (line.startsWith(MAIL_SUBJECT_IDENTIFIER)) {
				email.setSubject(this.extractSubject(line));
			}
			if (line.startsWith(MAIL_SENDING_DATE_IDENTIFIER)) {
				email.setSendingDate(this.extractSendingDate(line));
			}
		}
		return email;
	}

	/**
	 * Extract sending date.
	 *
	 * @param line the line
	 * @return the string
	 */
	@SuppressWarnings("deprecation")
	private String extractSendingDate(String line) {
		// TODO: default string if data unknown (see extractSender)
		String date = new StringHelper().splitString(line, ": ").get(1).trim();
		return new Date(date).toLocaleString();
	}

	/**
	 * Extract subject.
	 *
	 * @param line the line
	 * @return the string
	 */
	private String extractSubject(String line) {
		// TODO: default string if data unknown (see extractSender)
		return new StringHelper().splitString(line, ": ").get(1).trim();
	}

	/**
	 * Extract sender.
	 *
	 * @param line the line
	 * @return the string
	 */
	private String extractSender(String line) {
		
		String sender = new StringHelper().splitString(line, ": ").get(1).trim();
		sender = sender.substring(sender.indexOf("<") + 1, sender.indexOf(">"));
		
		if (sender.length() <= 0 || sender == null) {
			return "Nicht definiert";
		}
		return sender;
	}
	
	/**
	 * Extract receiver.
	 *
	 * @param line the line
	 * @return the string receiver
	 */
	private String extractReceiver(String line) {
		
		String receiver = new StringHelper().splitString(line, ": ").get(1).trim();
		receiver = receiver.substring(receiver.indexOf("<") + 1, receiver.indexOf(">"));
		
		if (receiver.length() <= 0 || receiver == null) {
			return "Nicht definiert";
		}
		return receiver;
	}

	/**
	 * Gets the mail container.
	 *
	 * @return the mail container
	 */
	public EmailContainer getMailContainer() {
		
		return this.mailContainer;
	}
	
	/**
	 * Sets the mail container.
	 *
	 * @param mailContainer the new mail container
	 */
	public void setMailContainer(EmailContainer mailContainer) {
		
		this.mailContainer = mailContainer;
	}

	/**
	 * Scan mails.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void scanMails() {
		
		int index = -1;
		for (Email email: this.getMailContainer().getMails()) {
			
			List<String> mailContent = null;
			mailContent = email.getLines();
			
//			replace ascii, then to lower case
			mailContent = new StringHelper().toLowerCase(new StringHelper().replaceAscii(mailContent));
			email.setLines(mailContent);
			
			email = this.checkAgainstBlacklist(email);
			email = this.extractEmailProperties(email);
			// just if mail is suspicious, get its properties,
			// else remove mail and go to next mail
			if (!email.isSuspicious()) {
				
//				safe indices of emails, that does not need to be moved
//				into quarantine and safe it inside email handler
				index = mailContainer.getMails().indexOf(email);
				indexListOfNonSuspiciousEmails.add(index);
				continue;
			} else {
//				System.out.println("Email with subject " + email.getSubject() + " is suspicious");
			}
		}	
		
	}
	
	/**
	 * Put mails into quarantine.
	 */
	public void putMailsIntoQuarantine() {
		
		for (Email email: this.getMailContainer().getMails()) {
			
			if (!indexListOfNonSuspiciousEmails.contains(email.getIndex())) {
				new LogManager().log(email);
				new FileManager().moveFile(email.getRelativePath(), FileManager.QUARANTINE_PATH);
			}
		}
//		clear mail container after files are moved
		this.mailContainer = null;
	}
	
	/**
	 * Check against blacklist.
	 *
	 * @param email the email
	 * @return the email
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private Email checkAgainstBlacklist(Email email) {
		
		Map<String, Integer> hitMap = new HashMap<>();
		int hitsInLine = 0;
		int totalHits = 0;
		int hitsBefore = 0;
		
		for (String line: email.getLines()) {
			
			for (String blacklistWord: this.getBlacklist()) {
				
				// convert to lower case before checking
				blacklistWord = blacklistWord.toLowerCase();
				
				if (line.contains(blacklistWord)) {
					
					// count hits in current line
					hitsInLine = new StringHelper().countOccurrences(line, blacklistWord);
					
					// add it to total hits
					totalHits += hitsInLine;

					// if the word was not found yet
					if (!hitMap.keySet().contains(blacklistWord)) {
						
						// add it to the hitmap
						hitMap.put(blacklistWord, hitsInLine);
					} else {
						// add hits to existing amount
						hitsBefore = hitMap.get(blacklistWord);
						hitMap.remove(blacklistWord);
						hitMap.put(blacklistWord, (hitsBefore + hitsInLine));
					}
				}
			}
			// set suspicious flag to an email if amount of hits
			// has passed a specific limit
			if (totalHits >= SECURITY_LEVEL && !email.isSuspicious()) {
				email.setSuspicious(true);
			}
		}
		email.setHitMap(hitMap);

		return email;
	}
	
	/**
	 * Log results.
	 */
	public void logResults() {
	}

	/**
	 * Gets the blacklist.
	 *
	 * @return the blacklist
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private List<String> getBlacklist() {
		
//		avoid reading the file over and over again
		if (this.blacklist == null) {
		    try {
		        this.blacklist = new Blacklist(new FileManager().getBlacklist());
		    } catch (IOException e) {
		        
		    }
		}
		return this.blacklist.getBlacklist();
	}

	/**
	 * Gets the mail content.
	 *
	 * @param file the file
	 * @return the mail content
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public List<String> getMailContent(File file) throws IOException {
		
		return new FileManager().readFile(file.getPath(), "UTF-8", HTML_MAIL_PART_START_IDENTIFIER);
	}

	/**
	 * Sets the eml files.
	 *
	 * @param emlFiles the new eml files
	 */
	public void setEmlFiles(List<File> emlFiles) {
		
		this.emlFiles = emlFiles;
	}
}
