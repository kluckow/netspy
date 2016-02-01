package netspy;

import java.io.File;
import java.io.IOException;
import java.util.List;

import netspy.components.gui.frame.NetSpyFrame;
import netspy.components.mailing.Email;
import netspy.components.mailing.EmailHandler;

/**
 * The Class NetSpy.
 */
public class NetSpy {

    /** The email handler. */
    static EmailHandler emailHandler = new EmailHandler();

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(final String[] args) {
    	
        final NetSpyFrame mainFrame = new NetSpyFrame();
    }

	public static void run() {
		if (checkInboxForMails()) {
            processMailsInInbox();
        }
	}

    /**
     * Process mails in inbox.
     */
    private static void processMailsInInbox() {

        try {
            emailHandler.scanMails();
            if (!emailHandler.getMailContainer().getMails().isEmpty()) {
                emailHandler.putMailsIntoQuarantine();
                // TODO: do we need this if we give an option in menu to re-scan the inbox manually?
                // new EmailHandler().reset();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check inbox for mails.
     *
     * @return true, if successful
     */
    private static boolean checkInboxForMails() {

        if (!emailHandler.checkMailbox()) {
        	// TODO: implement ErrorNotificationPopup
            System.out.println("Keine Emails in der Mailbox gefunden. Keine �berpr�fung notwendig.");
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
                	// TODO: implement ErrorNotificationPopup
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
