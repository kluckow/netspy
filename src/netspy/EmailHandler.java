package netspy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.StringHelper;

/**
 * The Class EmailHandler.
 */
public class EmailHandler {
	
	/** The Constant SECURITY_LEVEL. */
	public static final int SECURITY_LEVEL = 6;
	
	/** The Constant EML_FILE_EXTENSION. */
	private static final String EML_FILE_EXTENSION = ".eml";

	/** The Constant HTML_MAIL_PART_START_IDENTIFIER. */
	private static final String HTML_MAIL_PART_START_IDENTIFIER = "Content-Type: text/html;";
	
//	/** The Constant PLAIN_MAIL_PART_START_IDENTIFIER. */
//	private static final String PLAIN_MAIL_PART_START_IDENTIFIER = "content-type: text/plain;";
	
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
		
	private List<File> emlFiles = null;
	
	/** The blacklist. */
	private Blacklist blacklist;
	
	/** The scan result. */
	private List<ScanResult> scanResults = new ArrayList<>();
	
//	/** The date format mail. */
//	private final DateFormat DATE_FORMAT_MAIL = new SimpleDateFormat("d m yyyy HH:mm:ss");	
	
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
			this.emlFiles = FileManager.getInstance().getFilesByExtension(EML_FILE_EXTENSION);
		}
		return this.emlFiles;
	}
	
	/**
	 * Extract email properties.
	 *
	 * @param email the email
	 * @return 
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
		
		String date = StringHelper.getInstance().splitString(line, ": ").get(1).trim();
		return new Date(date).toLocaleString();
	}

	/**
	 * Extract subject.
	 *
	 * @param line the line
	 * @return the string
	 */
	private String extractSubject(String line) {
		
		return StringHelper.getInstance().splitString(line, ": ").get(1).trim();
	}

	/**
	 * Extract sender.
	 *
	 * @param line the line
	 * @return the string
	 */
	private String extractSender(String line) {
		
		String sender = StringHelper.getInstance().splitString(line, ": ").get(1).trim();
		sender = sender.substring(sender.indexOf("<") + 1, sender.indexOf(">"));
		
		return sender;
	}
	
	/**
	 * Extract receiver.
	 *
	 * @param line the line
	 * @return the string receiver
	 */
	private String extractReceiver(String line) {
		
		String receiver = StringHelper.getInstance().splitString(line, ": ").get(1).trim();
		receiver = receiver.substring(receiver.indexOf("<") + 1, receiver.indexOf(">"));
		
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
	public void scanMails() throws IOException {
		try {
			for (Email email: this.getMailContainer().getMails()) {
				
				List<String> mailContent = null;
				mailContent = email.getLines();
				
//				replace ascii, then to lower case
				mailContent = StringHelper.getInstance().toLowerCase(StringHelper.getInstance().replaceAscii(mailContent));
				email.setLines(mailContent);
				
				email = this.checkAgainstBlacklist(email);
				
				// just if mail is suspicious, get its properties,
				// else remove mail and go to next mail
				if (!email.isSuspicious()) {
					// remove unnecessary emails from the container,
					// since all emails are moved into quarantine later on 
					this.getMailContainer().getMails().remove(email);
					continue;
				}
				email = this.extractEmailProperties(email);
			}	
		} catch (ConcurrentModificationException ignored){};
		
	}
	
	/**
	 * Put mails into quarantine.
	 */
	public void putMailsIntoQuarantine() {
		
		for (Email email: this.getMailContainer().getMails()) {
			
			this.putMailIntoQuarantine(email);
		}
		// clear mail container after files are moved
		this.mailContainer = null;
	}
	
	/**
	 * Put into quarantine.
	 *
	 * @param email the email
	 */
	private void putMailIntoQuarantine(Email email) {
		
		FileManager.getInstance().moveFile(email.getRelativePath(), FileManager.QUARANTINE_PATH);
	}
	
	/**
	 * Check against blacklist.
	 *
	 * @param email the email
	 * @return the email
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private Email checkAgainstBlacklist(Email email) throws IOException {
		
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
					hitsInLine = StringHelper.getInstance().countOccurrences(line, blacklistWord);
					
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
			if (totalHits >= SECURITY_LEVEL) {
				email.setSuspicious(true);
			}
		}
		this.scanResults.add(new ScanResult(email, hitMap));
		
//		System.out.println("-----------------------------------");
//		System.out.println("Report of found words: ");
//		System.out.println("Email-Datei: " + email.getFilename());
//		System.out.println("-----------------------------------");
//		for (String foundWord: hitMap.keySet()) {
//			System.out.println(foundWord + " (" + hitMap.get(foundWord) + ")");
//		}
		return email;
	}
	
	public void logResults() {
		
		new LogManager().log(this.scanResults);
		// reset, just in case you want to re-scan inbox within single runtime
		this.scanResults = null;
	}

	/**
	 * Gets the blacklist.
	 *
	 * @return the blacklist
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private List<String> getBlacklist() throws IOException {
		
//		avoid reading the file over and over again
		if (this.blacklist == null) {
			this.blacklist = new Blacklist(FileManager.getInstance().getBlacklist());
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
		
		return FileManager.getInstance().readFile(file.getPath(), "Cp1252", HTML_MAIL_PART_START_IDENTIFIER);
	}

	/**
	 * Gets the scan results.
	 *
	 * @return the scan results
	 */
	public List<ScanResult> getScanResults() {
		
		return this.scanResults;
	}

	/**
	 * Sets the scan results.
	 *
	 * @param scanResults the new scan results
	 */
	public void setScanResults(List<ScanResult> scanResults) {
		
		this.scanResults = scanResults;
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
