/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.config;

/**
 * The Interface NetSpyGlobals.
 */
interface NetSpyGlobals {

	/**
	 * The Interface PropertyKeys.
	 */
	interface PropertyKeys {
		
		/** The inbox path. */
		String INBOX_PATH = "INBOX_PATH";
		
		/** The blackword path. */
		String BLACKWORD_PATH = "BLACKWORD_PATH";
		
		/** The log path. */
		String LOG_PATH = "LOG_PATH";
		
		/** The quarantine path. */
		String QUARANTINE_PATH = "QUARANTINE_PATH";
	}
	
	/**
	 * The Interface PropertyDefaultValues.
	 */
	interface PropertyDefaultValues {
		
		/** The inbox path relative. */
		String INBOX_PATH_RELATIVE = "\\inbox";
		
		/** The blackword path relative. */
		String BLACKWORD_PATH_RELATIVE = "\\data\\blacklist.txt";
		
		/** The log path relative. */
<<<<<<< HEAD
		String LOG_PATH_RELATIVE = "\\logs\\log.txt";
=======
		String LOG_PATH_RELATIVE = "\\logs";
>>>>>>> 327a0c79d00220564bf883cb9e591a0e3088cc05
		
		/** The quarantine path relative. */
		String QUARANTINE_PATH_RELATIVE = "\\quarantine";
		
		/** The config properties path relative. */
		String CONFIG_PROPERTIES_PATH_RELATIVE = "\\resources\\config.properties";
		
	}
}
