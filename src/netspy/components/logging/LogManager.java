/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.logging;

import java.text.SimpleDateFormat;
import java.util.Date;

import netspy.components.filehandling.manager.FileManager;
import netspy.components.mailing.Email;

/**
 * The Class LogManager.
 */
public class LogManager {
	
	/** The date format logging. */
	public static final String DATE_FORMAT_LOG_ENTRY = "dd.MM.yyyy HH:mm:ss";
	
	/** The log format logfile. */
	public static final String LOG_FORMAT_DATE_LOGFILE = "dd-MM-yyyy";
	
	/** The Constant LOG_ENTRY_SEPARATOR. */
	private static final String LOG_ENTRY_SEPARATOR = " | ";
	
	/**
	 * Log.
	 *
	 * @param email the email
	 */
	public void log(Email email) {
		
		String logLine = "Scan vom: " + new SimpleDateFormat(DATE_FORMAT_LOG_ENTRY).format(new Date());
		logLine += LOG_ENTRY_SEPARATOR;
		logLine += "Gesendet am: " + email.getSendingDate();
		logLine += LOG_ENTRY_SEPARATOR;
		logLine += "Betreff: " + email.getSubject();
		logLine += LOG_ENTRY_SEPARATOR;
		logLine += "Absender: " + email.getSender();
		logLine += LOG_ENTRY_SEPARATOR;
		logLine += "Empfänger: " + email.getReceiver();
		logLine += LOG_ENTRY_SEPARATOR;
		logLine += "Dateiname: " + email.getAbsolutePath();
		logLine += LOG_ENTRY_SEPARATOR;
		logLine += "Gefundene Wörter: " + email.getHitMap().entrySet().toString();
		
		new FileManager().log(new FileManager().createLogfile(), logLine);
	}
	
}
