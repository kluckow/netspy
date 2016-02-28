/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import netspy.components.logging.LogManager;

/**
 * The Class DateHelper.
 */
public class DateHelper {
	
	/** The Constant DATE_FORMAT_EMAIL. */
	private static final String DATE_FORMAT_EMAIL = "E, dd MMM yyyy HH:mm:ss ZZZZZ";

	/** The Constant Locale for US. */
	private static final Locale US = Locale.US;

	/**
	 * Format date.
	 *
	 * @param givenDate the given date
	 * @return the string
	 */
	public String formatDate(String givenDate) {
		
		try {
			DateFormat originalFormat = new SimpleDateFormat(DATE_FORMAT_EMAIL, US);
			DateFormat targetFormat = new SimpleDateFormat(LogManager.DATE_FORMAT_LOG_ENTRY);
			Date newDate = originalFormat.parse(givenDate);
			String formattedDate = targetFormat.format(newDate);
			return formattedDate;
		} catch (ParseException e) {
			// just a problem parsing the date format from email, just use String of email instead
			return givenDate;
		}
	}
	
}
