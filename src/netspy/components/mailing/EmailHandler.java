/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.mailing;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import netspy.components.config.ConfigPropertiesManager;
import netspy.components.filehandling.manager.FileManager;
import netspy.components.gui.components.frame.components.LogBox;
import netspy.components.logging.LogManager;
import netspy.components.util.DateHelper;
import netspy.components.util.StringHelper;

/**
 * The Class EmailHandler.
 */
public class EmailHandler {
	
	/** The Constant SECURITY_LEVEL. */
	private static final int SECURITY_LEVEL = 6;

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

	/** The logbox. */
	private LogBox logbox;
		
	/**
	 * Instantiates a new email handler.
	 *
	 * @param logbox the logbox
	 */
	public EmailHandler(LogBox logbox) {
		
		this.logbox = logbox;
	}
	
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
		
		return new FileManager().getFilesByExtension(FileManager.EML_FILE_EXTENSION,
			new ConfigPropertiesManager().getInboxPath());
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
	private String extractSendingDate(String line) {
		
		String date = new StringHelper().splitString(line, ": ").get(1).trim();
		
		// set "Nicht definiert" if property is not defined
		if (date.length() <= 0 || date == null) {
			return "Nicht definiert";
		}
		return new DateHelper().formatDate(date);
	}

	/**
	 * Extract subject.
	 *
	 * @param line the line
	 * @return the string
	 */
	private String extractSubject(String line) {
		
		String subject = new StringHelper().splitString(line, ": ").get(1).trim();
		
		// set "Nicht definiert" if property is not defined
		if (subject.length() <= 0 || subject == null) {
			return "Nicht definiert";
		}
		return subject;
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
		
		// set "Nicht definiert" if property is not defined
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
		
		// set "Nicht definiert" if property is not defined
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
	 */
	public void scanMails() {
		
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
				
				continue;
			}
		}
		
	}
	
	/**
	 * Put mails into quarantine.
	 */
	public void putMailsIntoQuarantine() {
		
		int counterSuspiciousMails = 0;
		
		for (Email email: this.getMailContainer().getMails()) {
			
			if (email.isSuspicious()) {
				
				new LogManager().log(email);
				new FileManager().moveFile(email.getAbsolutePath(), new ConfigPropertiesManager().getQuarantinePath());
				counterSuspiciousMails++;
			}
		}
		
//		clear mail container after files are moved
		this.mailContainer = new EmailContainer();
		
		String msg = ""; 
		boolean addAdditionalInfoLine = counterSuspiciousMails > 0;
		
		switch (counterSuspiciousMails) {
		case 0:
			msg = "Es wurde keine verdächtige Email gefunden.";
			break;
		case 1:
			msg = "Es wurde " + counterSuspiciousMails + " verdächtige Email gefunden.";
			break;

		default:
			msg = "Es wurden " + counterSuspiciousMails + " verdächtige Emails gefunden.";
			break;
		}
		
		this.logbox.append(msg);
		
		if (addAdditionalInfoLine) {
			msg = "Weitere Details dazu befinden sich in der Logdatei.";
			this.logbox.appendWithoutDelay(msg);
		}
		
	}
	
	/**
	 * Check against blacklist.
	 *
	 * @param email the email
	 * @return the email
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
	 * Gets the blacklist.
	 *
	 * @return the blacklist
	 */
	private List<String> getBlacklist() {
		
        return new FileManager().getBlacklist();
	}

	/**
	 * Gets the mail content.
	 *
	 * @param file the file
	 * @return the mail content
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public List<String> getMailContent(File file) {
		
		return new FileManager().readFile(file.getPath(), "UTF-8", HTML_MAIL_PART_START_IDENTIFIER);
	}

}
