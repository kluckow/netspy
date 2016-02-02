/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.logging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import netspy.components.filehandling.manager.FileManager;
import netspy.components.mailing.Email;

/**
 * The Class LogManager.
 */
public class LogManager {
	
	/** The date format logging. */
	public final DateFormat DATE_FORMAT_LOGGING = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	/** The Constant LOG_FILE_PREFIX. */
	public final String LOG_FILE_PREFIX = "log_";
	
	/** The Constant LOG_FILE_EXTENSION. */
	public final String LOG_FILE_EXTENSION = ".txt";
	
	/** The Constant LOG_PATH. */
	public final String LOG_PATH = "log/";
	
	/** The Constant LOG_ENTRY_SEPARATOR. */
	private static final String LOG_ENTRY_SEPARATOR = " | ";
	
	/**
	 * Log.
	 *
	 * @param scanResult the scan result
	 */
	@SuppressWarnings("deprecation")
	public void log(Email email) {
		String logLine = "Scan vom: " + new Date().toLocaleString();
		logLine += LOG_ENTRY_SEPARATOR;
		logLine += "Gesendet am: " + email.getSendingDate();
		logLine += LOG_ENTRY_SEPARATOR;
		logLine += "Betreff: " + email.getSubject();
		logLine += LOG_ENTRY_SEPARATOR;
		logLine += "Absender: " + email.getSender();
		logLine += LOG_ENTRY_SEPARATOR;
		logLine += "Empfänger: " + email.getReceiver();
		logLine += LOG_ENTRY_SEPARATOR;
		logLine += "Dateiname: " + email.getFilename();
		
		new FileManager().createLogfile();
		new FileManager().log(FileManager.LOG_FILE, logLine);
	}
	
	/**
	 * Gets the formatted date.
	 *
	 * @param dateFormat the date format
	 * @return the formatted date as String
	 */
}
