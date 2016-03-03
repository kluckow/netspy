/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.PropertiesConfigurationLayout;

import netspy.components.gui.components.frame.components.Logbox;
import netspy.components.gui.components.popups.ErrorPopup;

/**
 * The Class ConfigPropertiesManager.
 */
public class ConfigPropertiesManager implements NetSpyGlobals {

	/** The config properties path. */
	private String configPropertiesPath;

	/** The logbox. */
	private Logbox logbox;
	
	/**
	 * Instantiates a new config properties manager.
	 */
	public ConfigPropertiesManager() {
		
		this.configPropertiesPath = System.getProperty("user.dir") +
				PropertyDefaultValues.CONFIG_PROPERTIES_PATH_RELATIVE;
	}
	
	/**
	 * Instantiates a new config properties manager.
	 *
	 * @param logbox the logbox
	 */
	public ConfigPropertiesManager(Logbox logbox) {
		
		this();
		this.logbox = logbox;
	}
	
	/**
	 * Inits the.
	 */
	public void init() {
		
		createFile();
	}

	/**
	 * Creates the file.
	 */
	private void createFile() {
		try {
			File file = new File(configPropertiesPath);
			if (!file.exists()) {
				file.createNewFile();
				
				this.logbox.appendWithoutDelay("Pfad-Konfigurationsdatei wurde neu erstellt.");
				
				// createDefaultValues for paths
				setupDefaultConfig();
			} 
		} catch (IOException e) {
		    new ErrorPopup("Fehler bei Dateierstellung",
		                    "Die config.properties-Datei konnte nicht erstellt werden!");
		    
        }
	}

	/**
	 * Setup default config.
	 */
	private void setupDefaultConfig() {
		
		setProperty(PropertyKeys.INBOX_PATH, System.getProperty("user.dir") +
				PropertyDefaultValues.INBOX_PATH_RELATIVE);
		setProperty(PropertyKeys.BLACKWORD_PATH, System.getProperty("user.dir") +
				PropertyDefaultValues.BLACKWORD_PATH_RELATIVE);
		setProperty(PropertyKeys.LOG_PATH, System.getProperty("user.dir") +
				PropertyDefaultValues.LOG_PATH_RELATIVE);
		setProperty(PropertyKeys.QUARANTINE_PATH, System.getProperty("user.dir") +
				PropertyDefaultValues.QUARANTINE_PATH_RELATIVE);
	}

	/**
	 * Gets the config properties path.
	 *
	 * @return the config properties path
	 */
	private String getConfigPropertiesPath() {
		return configPropertiesPath;
	}

	/**
	 * Update property.
	 *
	 * @param propKey the prop key
	 * @param propValue the prop value
	 */
	private void setProperty(String propKey, String propValue) {
		
	    File file = new File(getConfigPropertiesPath());
	    
		try (InputStreamReader input = new InputStreamReader(new FileInputStream(file))) {

			PropertiesConfiguration config = new PropertiesConfiguration();
			PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout(config);
			layout.load(input);
			input.close();

			config.setProperty(propKey, propValue);
			layout.save(new FileWriter(file));
		} catch (ConfigurationException e) {
            // could not load config.properties
            new ErrorPopup("Fehler mit Konfigurationsdatei",
                "Es ist ein Fehler beim Laden der Konfigurationsdatei aufgetreten!"); 
        } catch (IOException e) {
            new ErrorPopup("Fehler mit Konfigurationsdatei",
                "Es ist ein Fehler beim Öffnen bzw. Schreiben der Konfigurationsdatei aufgetreten!"); 
        }
		
	}

	/**
	 * Gets the prop.
	 *
	 * @return the prop
	 */
	private Properties getProperties() {
		
		Properties prop = new Properties();
		try (InputStream input = new FileInputStream(configPropertiesPath)) {
            if (input != null) {
                prop.load(input);
                input.close();
            }
            return prop;
        } catch (IOException e) {
            new ErrorPopup("Fehler mit Konfigurationsdatei",
                "Es ist ein Fehler beim Öffnen bzw. Lesen der Konfigurationsdatei aufgetreten!"); 
        }
        return prop;
	}

	/**
	 * Gets the inbox path.
	 *
	 * @return the inbox path
	 */
	public String getInboxPath() {
		
		return getProperties().getProperty(PropertyKeys.INBOX_PATH);
	}
	
	/**
	 * Gets the blackword path.
	 *
	 * @return the blackword path
	 */
	public String getBlackwordPath() {
		
		return getProperties().getProperty(PropertyKeys.BLACKWORD_PATH);
	}
	
	/**
	 * Gets the log path.
	 *
	 * @return the log path
	 */
	public String getLogPath() {
		
		return getProperties().getProperty(PropertyKeys.LOG_PATH);
	}
	
	/**
	 * Gets the quarantine path.
	 *
	 * @return the quarantine path
	 */
	public String getQuarantinePath() {
		
		return getProperties().getProperty(PropertyKeys.QUARANTINE_PATH);
	}
	
	/**
	 * Sets the inbox path.
	 *
	 * @param path the new inbox path
	 */
	public void setInboxPath(String path) {
		setProperty(PropertyKeys.INBOX_PATH, path);
	}
	
	/**
	 * Sets the blackword path.
	 *
	 * @param path the new blackword path
	 */
	public void setBlackwordPath(String path) {
		setProperty(PropertyKeys.BLACKWORD_PATH, path);
	}
	
	/**
	 * Sets the log path.
	 *
	 * @param path the new log path
	 */
	public void setLogPath(String path) {
		setProperty(PropertyKeys.LOG_PATH, path);
	}
	
	/**
	 * Sets the quarantine path.
	 *
	 * @param path the new quarantine path
	 */
	public void setQuarantinePath(String path) {
		setProperty(PropertyKeys.QUARANTINE_PATH, path);
	}
}
