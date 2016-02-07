/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.filehandling.manager;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import netspy.components.config.ConfigPropertiesManager;
import netspy.components.filehandling.io.TextReader;
import netspy.components.filehandling.io.TextWriter;
import netspy.components.gui.components.popups.ErrorNotificationPopup;

/**
 * The Class FileManager.
 */
public class FileManager {

	/** The Constant QUARANTINE_PATH. */
	public static String QUARANTINE_PATH = "quarantine/";
	
	/** The Constant BLACKLIST_FILE. */
	public static final String BLACKLIST_FILE_NAME = "blacklist.txt";
	
	/** The Constant BLACKLSIT_ENCODING. */
	private static final String BLACKLIST_ENCODING = "UTF-8";
	
	/** The Constant DATE_FORMAT_LOGFILE_SUFFIX. */
	public final static DateFormat DATE_FORMAT_LOGFILE_SUFFIX = new SimpleDateFormat("ddMMyyyy_HHmmss");
	
	/**
	 * Gets the files by file extension.
	 *
	 * @param fileExtension the file extension
	 * @return the files by file extension
	 */
	public List<File> getFilesByExtension(String fileExtension, String absPath) {
		
		File folder = new File(absPath);
		File[] arrOfFiles = folder.listFiles();
		List<File> listOfFiles = new ArrayList<>();
		
		if (arrOfFiles.length > 0) {
			
			for(int i = 0; i < arrOfFiles.length; i++) {
				
				// just get the .eml-files
				if (arrOfFiles[i].getName().endsWith(fileExtension)) {
					// add .eml-file to list of files
					listOfFiles.add(arrOfFiles[i]);
				}
			}
		}
		return listOfFiles;
	}

	/**
	 * Read file.
	 *
	 * @param filename the filename
	 * @param encoding the encoding
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public List<String> readFile(String filename, String encoding) throws IOException {
		
		return new TextReader().readFile(filename, encoding);
	}
	
	/**
	 * Read file.
	 *
	 * @param filename the filename
	 * @param encoding the encoding
	 * @param stopper the stopper
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public List<String> readFile(String filename, String encoding, String stopper) throws IOException {
		
		return new TextReader().readFile(filename, encoding, stopper);
	}
	
	/**
	 * Creates the logfile.
	 *
	 * @return the string
	 */
	public String createLogfile() {
		
		File file = new File(new ConfigPropertiesManager().getLogPath());
		File absPathFile = new File(file.getAbsolutePath());
		try {
			new File(file.getParent()).mkdirs();
			file.createNewFile();
		} catch (IOException e) {
		    new ErrorNotificationPopup("Datei konnnte nicht erstellt werden", file.getName() + " konnte nicht erstellt werden in "
		        + "in " + file.getParent() + "!");
		}
		return file.getPath();
	}


	/**
	 * Gets the blacklist.
	 *
	 * @return the blacklist
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public List<String> getBlacklist() {
		
		try {
			return new TextReader().readFile(new ConfigPropertiesManager().getBlackwordPath(),
					BLACKLIST_ENCODING);
		} catch (IOException e) {
			new ErrorNotificationPopup("Datei-Lesefehler", "Es ist ein Problem "
					+ "beim Lesen der Blacklist-Datei aufgetreten!");
		}
		return null;
	}

	/**
	 * Move file.
	 *
	 * @param src the src
	 * @param dest the dest
	 */
	public void moveFile(String src, String dest) {
		
		File srcFile = new File(src);
		File destFile = new File(dest + srcFile.getName());
		
		if (!srcFile.renameTo(destFile)) {
		    new ErrorNotificationPopup("Datei existiert bereits", destFile.getAbsolutePath() +
	    		" existiert bereits im Ziel-Verzeichnis! "
		        + "Daher findet keine Verschiebung statt!");
			System.out.println(destFile.getName() + " existiert bereits im " + destFile.getParent() + "-Verzeichnis!");
		} else {
//			System.out.println(srcFile.getName() + " wurde nach " + destFile.getParent() + " verschoben!");
		}
	}

	public void write(String relPath, String line) {

		new TextWriter().write(relPath, line);
	}

	public void log(String relPath, String logLine) {
		
		new TextWriter().write(relPath, logLine);
	}
	
}
