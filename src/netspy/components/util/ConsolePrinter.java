package netspy.components.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import netspy.components.mailing.Email;

/**
 * The Class ConsolePrinter.
 */
public class ConsolePrinter {

	/**
	 * Prints the.
	 *
	 * @param email the email
	 */
	@SuppressWarnings("rawtypes")
	public void print(Email email) {
		
		System.out.println("Filename: " + email.getFilename());
		System.out.println("Relative Path: " + email.getRelativePath());
		System.out.println("Subject: " + email.getSubject());
		System.out.println("Index: " + email.getIndex());
		System.out.println("Sending Date: " + email.getSendingDate());
		System.out.println("Sender: " + email.getSender());
		System.out.println("Receiver: " + email.getReceiver());
		System.out.println("Lines: " + email.getLines());
		System.out.println("Suspicious: " + email.isSuspicious());
		System.out.println("Wörter = Treffer");
		Iterator<?> it = email.getHitMap().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = ((Map.Entry)it.next());
	        System.out.println(pair.getKey() + " = " + pair.getValue());
	        it.remove(); // avoids a ConcurrentModificationException
	    }
		System.out.println("--------------------------------------------------");
	}
	
	/**
	 * Prints the lines.
	 *
	 * @param lines the lines
	 */
	public void printLines(List<String> lines) {

		for (String line: lines) {
			printLine(line);
		}
	}
	
	/**
	 * Prints the line.
	 *
	 * @param line the line
	 */
	public void printLine(String line) {
		
		System.out.println(line);
	}
	
	/**
	 * Prints the.
	 *
	 * @param str the str
	 */
	public void print(String str) {
		
		System.out.print(str);
	}
}
