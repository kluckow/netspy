package netspy.components.logging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
	public void log(Email email) {
	    // TODO: implement detailed logging, this is just an example
		String logLine = "Gesendet am: " + email.getSendingDate();
		logLine += LOG_ENTRY_SEPARATOR;
		logLine += "Betreff: " + email.getSubject();
		
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
