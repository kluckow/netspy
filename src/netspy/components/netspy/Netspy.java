/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.netspy;

import java.io.File;
import java.util.List;

import netspy.components.filehandling.manager.FileManager;
import netspy.components.gui.components.frame.components.LogBox;
import netspy.components.gui.components.popups.InfoPopup;
import netspy.components.mailing.Email;
import netspy.components.mailing.EmailHandler;

/**
 * The Class Netspy.
 */
public class Netspy {
	
    /** The email handler. */
    private EmailHandler emailHandler;
	
	/** The logbox. */
	private LogBox logbox;
    
    /**
     * Start.
     *
     * @param logbox the logbox
     */
    public void start(LogBox logbox) {
    	
    	this.logbox = logbox;
    	this.emailHandler = new EmailHandler(this.logbox);
    	run();
    }

	/**
	 * Run.
	 */
	private void run() {

		/**
		 * Is actually already checked when configuring paths,
		 * but files could have been moved out of inbox after configuring it
		 */
		if (checkInboxForMails()) {
            processMailsInInbox();
            finishScan();
        }
	}

    /**
     * Process mails in inbox.
     */
    private void processMailsInInbox() {

        emailHandler.scanMails();
        
        // should not be the case though, but to make sure...
        if (!emailHandler.getMailContainer().getMails().isEmpty()) {
            emailHandler.putMailsIntoQuarantine();
        }
    }

    /**
     * Finish scan.
     */
    private void finishScan() {
    	
    	this.logbox.append("Scan abgeschlossen.");
	}

	/**
     * Check inbox for mails.
     *
     * @return true, if successful
     */
    private boolean checkInboxForMails() {

        if (!emailHandler.checkMailbox()) {
            
            new InfoPopup("Keine Emails vorhanden", "Keine Emails in der Mailbox gefunden. Keine Überprüfung notwendig.");
            return false;
            
        } else if (new FileManager().getBlacklist().isEmpty()) {
        	new InfoPopup("Keine Blacklist-Wörter", "Die Blacklist ist leer."
        			+ " Füge zuerst Wörter hinzu nach denen geprüft werden kann!");
        	return false;
        	
        } else {
            this.logbox.clear();
            this.logbox.append("Starte Scan...");
            // get emails as files
            final List<File> mailFiles = emailHandler.getEmlFiles();

            for (final File mailFile : mailFiles) {
            	
                // extract content of emailFile and create an email object with that content
                final Email email = new Email(emailHandler.getMailContent(mailFile),
                    mailFile.getPath());
                // add email object to email container
                emailHandler.getMailContainer().getMails().add(email);
            }
            return true;
        }
    }
}
