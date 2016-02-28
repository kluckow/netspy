/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.filehandling.manager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import netspy.components.config.ConfigPropertiesManager;
import netspy.components.filehandling.io.TextReader;
import netspy.components.filehandling.io.TextUpdater;
import netspy.components.filehandling.io.TextWriter;
import netspy.components.gui.components.popups.ErrorPopup;
import netspy.components.logging.LogManager;

/**
 * The Class FileManager.
 */
public class FileManager {
	
	/** The Constant EML_FILE_EXTENSION. */
	public static final String EML_FILE_EXTENSION = ".eml";
	
	/** The Constant BLACKLIST_FILE. */
	public static final String BLACKLIST_FILE_NAME = "blacklist.txt";
	
	/** The Constant BLACKLSIT_ENCODING. */
	public static final String BLACKLIST_ENCODING = "UTF-8";
	
	/**
	 * Gets the files by file extension.
	 *
	 * @param fileExtension the file extension
	 * @param absPath the abs path
	 * @return the files by file extension
	 */
	public List<File> getFilesByExtension(String fileExtension, String absPath) {
		
		File fileOrFolder = new File(absPath);
		List<File> listOfFiles = new ArrayList<>();

		// check is necessary, because chosen target to scan can be folder or a single file
		if (fileOrFolder.isDirectory()) {
			
			File[] arrOfFiles = fileOrFolder.listFiles();
			
			if (arrOfFiles.length > 0) {
				
				for(int i = 0; i < arrOfFiles.length; i++) {
					
					// just get the .eml-files
					if (arrOfFiles[i].getName().endsWith(fileExtension)) {
						// add .eml-file to list of files
						listOfFiles.add(arrOfFiles[i]);
					}
				}
			}
		// might be the case when user entered a single .eml-file
		} else if (fileOrFolder.isFile()) {
			listOfFiles.add(fileOrFolder);
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
	public List<String> readFile(String filename, String encoding, String stopper) {
		
		return new TextReader().readFile(filename, encoding, stopper);
	}
	
	/**
	 * Creates the logfile with 'dd-mm-yyyy-log.txt' format.
	 *
	 * @return the string of the logfile
	 */
	public String createLogfile() {
		
		String logFileFinal = new ConfigPropertiesManager().getLogPath() + File.separator + generateLogfileNameToday();
		File logFile = new File(logFileFinal);
		
		if (logFile.exists()) {
			return logFileFinal;
		} else {
			File newLogfile = new File(logFileFinal);
			try {
				newLogfile.createNewFile();
			} catch (IOException e) {
				new ErrorPopup("Datei konnnte nicht erstellt werden", newLogfile.getName() +
						" konnte nicht erstellt werden in " + newLogfile.getParent() + "!");
			}
			return logFileFinal;
		}
	}

	/**
	 * Generate logfile name today.
	 *
	 * @return the string
	 */
	public String generateLogfileNameToday() {
		
		String date = new SimpleDateFormat(LogManager.LOG_FORMAT_DATE_LOGFILE).format(new Date());
		String logFilename = date + "-log.txt";
		return logFilename;
	}


	/**
	 * Gets the blacklist.
	 *
	 * @return the blacklist
	 */
	public List<String> getBlacklist() {
		
		return new TextReader().readFile(new ConfigPropertiesManager().getBlackwordPath(),
				BLACKLIST_ENCODING);
	}

	/**
	 * Move file.
	 *
	 * @param src the src
	 * @param dest the dest
	 */
	public void moveFile(String src, String dest) {
		
		File srcFile = new File(src);
		File destFile = new File(dest + File.separator + srcFile.getName());
		
		if (srcFile.renameTo(destFile)) {
			
		} else if (destFile.exists()){
			
			try {
				List<String> srcLines = new FileManager().readFile(srcFile.getAbsolutePath(), "UTF-8");
				List<String> destLines = new FileManager().readFile(destFile.getAbsolutePath(), "UTF-8");
				if (srcLines.equals(destLines)) {
					
					srcFile.delete();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * Log.
	 *
	 * @param absPath the abs path
	 * @param logLine the log line
	 */
	public void log(String absPath, String logLine) {
		
		new TextWriter().write(absPath, logLine, true, false);
	}
	
	/**
	 * Removes the all.
	 *
	 * @param absPath the abs path
	 */
	public void removeAll(String absPath) {
		
		new TextUpdater().clear(absPath);
	}
	
	/**
	 * Removes the.
	 *
	 * @param absPath the abs path
	 * @param str the str
	 */
	public void remove(String absPath, String str) {
		
		new TextUpdater().remove(absPath, str);
	}
	
	/**
	 * Update.
	 *
	 * @param absPath the abs path
	 * @param str the str
	 */
	public void insert(String absPath, String str) {
		
		new TextUpdater().insert(absPath, str);
	}
	
}
