package netspy;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import util.TextReader;

public class NetSpy {

	public static void main(String[] args) {

//		TODO: find way to extract email date format and convert to customized format
//		1. create static final DateFormat for log lines
//		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		
//		2. create a Date from the extracted string
//		Date sendingDate = new Date("mon, 22 aug 2005 20:21:52 +0200");
//		System.out.println("Format des Datums in Emails: " + sendingDate);
		
//		3. format the extracted Date with your log line DateFormat
//		String date = dateFormat.format(sendingDate);
//		4. the Result is a 
//		System.out.println("Format des Datums umgeformt: " + date);
		
//		displayMenu();
		
		// TODO: comment this out
	    JFrame frame = new JFrame("JComboBox Test");
	    frame.setLayout(new FlowLayout());
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JButton button = new JButton("Select File");
	    button.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent ae) {
	        JFileChooser fileChooser = new JFileChooser();
	        int returnValue = fileChooser.showOpenDialog(null);
	        if (returnValue == JFileChooser.APPROVE_OPTION) {
	          File selectedFile = fileChooser.getSelectedFile();
	          System.out.println(selectedFile.getName());
	        }
	      }
	    });
	    frame.add(button);
	    frame.pack();
	    frame.setVisible(true);
	    
	    // TODO: uncomment this
//		if (checkInboxForMails()) {
//			processMailsInInbox();
//			// TODO: implement logging
//			logScanResults();
//		}
	}
	
	private static void logScanResults() {
		
		
	}

	/**
	 * Report.
	 */
	private static void report() {
		
	}

	/**
	 * Process mails in inbox.
	 */
	private static void processMailsInInbox() {
		
		try {
			EmailHandler.getInstance().scanMails();
			if (!EmailHandler.getInstance().getMailContainer().getMails().isEmpty()) {
				EmailHandler.getInstance().putMailsIntoQuarantine();
//				TODO: do we need this if we give an option in menu to re-scan the inbox manually?
//				EmailHandler.getInstance().reset();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void displayMenu() {

		/**
		 *  display options for: 1) Scan Mails 2) Blacklist editor 3) Whitelist editor
		 */
	}

	/**
	 * Check inbox for mails.
	 */
	private static boolean checkInboxForMails() {
		
		if (!EmailHandler.getInstance().checkMailbox()) {
			System.out.println("Keine Emails in der Mailbox gefunden. Keine Überprüfung notwendig.");
			return false;
		} else {
//			get emails as files
			List<File> mailFiles = EmailHandler.getInstance().getEmlFiles();
			
			for (File mailFile: mailFiles) {
				try {
//					extract content of emailFile and create an email object with that content 
					Email email = new Email(EmailHandler.getInstance().getMailContent(mailFile), mailFile.getPath());
					
//					add email object to email container
					EmailHandler.getInstance().getMailContainer().getMails().add(email);
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
			return true;
		}
	}
}
