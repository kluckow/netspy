/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.logging;

import java.text.SimpleDateFormat;
import java.util.Date;

import netspy.components.config.ConfigPropertiesManager;
import netspy.components.filehandling.manager.FileManager;
import netspy.components.mailing.Email;

/**
 * The Class LogManager.
 */
public class LogManager {
	
	/** The date format logging. */
	private final String LOG_FORMAT = "dd-MM-yyyy HH:mm:ss";
	
	/** The Constant LOG_ENTRY_SEPARATOR. */
	private static final String LOG_ENTRY_SEPARATOR = " | ";
	
	/**
	 * Log.
	 *
	 * @param scanResult the scan result
	 */
	public void log(Email email) {
		String logLine = "Scan vom: " + new SimpleDateFormat(LOG_FORMAT).format(new Date());
		logLine += LOG_ENTRY_SEPARATOR;
		logLine += "Gesendet am: " + email.getSendingDate();
		logLine += LOG_ENTRY_SEPARATOR;
		logLine += "Betreff: " + email.getSubject();
		logLine += LOG_ENTRY_SEPARATOR;
		logLine += "Absender: " + email.getSender();
		logLine += LOG_ENTRY_SEPARATOR;
		logLine += "Empfänger: " + email.getReceiver();
		logLine += LOG_ENTRY_SEPARATOR;
		logLine += "Dateiname: " + email.getRelativePath();
		logLine += LOG_ENTRY_SEPARATOR;
		logLine += "Gefundene Wörter: " + email.getHitMap().entrySet().toString();
		
		
		new FileManager().createLogfile();
		new FileManager().log(new ConfigPropertiesManager().getLogPath(), logLine);
	}
	
	/**
	 * Gets the formatted date.
	 *
	 * @param dateFormat the date format
	 * @return the formatted date as String
	 */
}
