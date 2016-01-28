package netspy.components.util;

import java.text.DateFormat;
import java.util.Date;

/**
 * The Class DateHelper.
 */
public class DateHelper {
	
	/** The date helper. */
	private static DateHelper dateHelper = null;
	
	/**
	 * Instantiates a new date helper.
	 */
	private DateHelper() {}
	
	/**
	 * Gets the single instance of DateHelper.
	 *
	 * @return single instance of DateHelper
	 */
	public static DateHelper getInstance() {
		
		if (dateHelper == null) {
			dateHelper = new DateHelper();
		}
		return dateHelper;
	}
	
	/**
	 * Date to string.
	 *
	 * @param date the date
	 * @param dateFormat the date format
	 * @return the string
	 */
	public String dateToString(Date date, DateFormat dateFormat) {
		
		return dateFormat.format(date);
	}
//	TODO: implement (check if you can delete this todo)
	public String convertToGeneralizedDateFormat(Date date) {
		
		return "";
	}
}
