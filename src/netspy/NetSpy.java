/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy;

import java.io.File;
import java.io.IOException;
import java.util.List;

import netspy.components.gui.components.frame.NetSpyFrame;
import netspy.components.gui.components.popups.ErrorNotificationPopup;
import netspy.components.gui.components.popups.InfoNotificationPopup;
import netspy.components.mailing.Email;
import netspy.components.mailing.EmailHandler;

/**
 * The Class NetSpy.
 */
public class NetSpy {

    /** The email handler. */
    static EmailHandler emailHandler = new EmailHandler();
    
    /** The main frame. */
    private static NetSpyFrame mainFrame;

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(final String[] args) {
    	
        mainFrame = new NetSpyFrame();
    }

	public static void run() {
	    // Eigentlich wird die Überprüfung schon beim Auswählen des Pfades durchgeführt,
	    // aber nochmal überprüfen kann nicht schaden.
		if (checkInboxForMails()) {
            processMailsInInbox();
        }
	}

    /**
     * Process mails in inbox.
     */
    private static void processMailsInInbox() {

        emailHandler.scanMails();
        if (!emailHandler.getMailContainer().getMails().isEmpty()) {
            int counterSuspiciousMails = emailHandler.putMailsIntoQuarantine();
            mainFrame.getLogBox().append("Es wurden " + counterSuspiciousMails
            		+ " verdächtige Emails gefunden. "
            		+ "Weitere Details dazu befinden sich der Logdatei.");
        }
    }

    /**
     * Check inbox for mails.
     *
     * @return true, if successful
     */
    private static boolean checkInboxForMails() {

        if (!emailHandler.checkMailbox()) {
            
            new InfoNotificationPopup("Keine Emails vorhanden", "Keine Emails in der Mailbox gefunden. Keine Überprüfung notwendig.");
            return false;
            
        } else {
            
            // get emails as files
            final List<File> mailFiles = emailHandler.getEmlFiles();

            for (final File mailFile : mailFiles) {
                try {
                    // extract content of emailFile and create an email object with that content
                    final Email email = new Email(emailHandler.getMailContent(mailFile),
                        mailFile.getPath());
                    // add email object to email container
                    emailHandler.getMailContainer().getMails().add(email);
                } catch (final IOException e) {
                    new ErrorNotificationPopup("Datei-Lesefehler", "Es ist ein Problem beim Lesen der Email-Datei aufgetreten!");
                }
            }
            // set indices of emails
            for (Email email : emailHandler.getMailContainer().getMails()) {
            	email.setIndex(emailHandler.getMailContainer().getMails().indexOf(email));
            }
            return true;
        }
    }
}
