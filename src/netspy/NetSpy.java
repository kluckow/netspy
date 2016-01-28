package netspy;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

        // TODO: find way to extract email date format and convert to customized format
        // 1. create static final DateFormat for log lines
        // DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

        // 2. create a Date from the extracted string
        // Date sendingDate = new Date("mon, 22 aug 2005 20:21:52 +0200");
        // System.out.println("Format des Datums in Emails: " + sendingDate);

        // 3. format the extracted Date with your log line DateFormat
        // String date = dateFormat.format(sendingDate);
        // 4. the Result is a
        // System.out.println("Format des Datums umgeformt: " + date);

        if (checkInboxForMails()) {
            processMailsInInbox();
            // // TODO: implement logging
            // logScanResults();
        }
    }

    /**
     * Log scan results.
     */
    @SuppressWarnings("unused")
    private static void logScanResults() {

    }

    /**
     * Report.
     */
    @SuppressWarnings("unused")
    private static void report() {

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
     * Display menu.
     */
    @SuppressWarnings("unused")
    private static void displayMenu() {

        /**
         * display options for: 1) Scan Mails 2) Blacklist editor 3) Whitelist editor
         */
    }

    /**
     * Check inbox for mails.
     *
     * @return true, if successful
     */
    private static boolean checkInboxForMails() {

        if (!emailHandler.checkMailbox()) {
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
                    System.out.println(e.getMessage());
                }
            }
            return true;
        }
    }
}
