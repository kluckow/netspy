package netspy.components.logging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import netspy.components.filehandling.manager.FileManager;

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
	/**
	 * Log.
	 *
	 * @param scanResults the email
	 */
	public void log(List<ScanResult> scanResults) {
		
		String logLine = "";
		// TODO: Format of logline of this mail object:
		// TODO: create method in filemanager and textwriter
		FileManager.getInstance().write(logLine);
	}
	public void log(ScanResult scanResult) {
		
		// TODO: implement logging for single result
	}
	
	/**
	 * Gets the formatted date.
	 *
	 * @param dateFormat the date format
	 * @return the formatted date as String
	 */
}
