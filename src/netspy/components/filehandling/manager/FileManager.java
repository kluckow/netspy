/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.filehandling.manager;

import java.io.File;
import java.io.IOException;
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

	/** The Constant BLACKLIST_FILE. */
	public static final String BLACKLIST_FILE_NAME = "blacklist.txt";
	
	/** The Constant BLACKLSIT_ENCODING. */
	private static final String BLACKLIST_ENCODING = "UTF-8";
	
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
		File destFile = new File(dest + File.separator + srcFile.getName());
		
		if(srcFile.renameTo(destFile)) {
			
			System.out.println(srcFile.getAbsolutePath() + " wurde nach " + destFile.getAbsolutePath() + " verschoben.");
			
		} else if (destFile.exists()){
			
			
			try {
				List<String> srcLines = new FileManager().readFile(srcFile.getAbsolutePath(), "UTF-8");
				List<String> destLines = new FileManager().readFile(destFile.getAbsolutePath(), "UTF-8");
				if (srcLines.equals(destLines)) {
					
					// TODO: Abfrage with JOptionPane, ob bereits existierende Dateien in der Inbox gelöscht werden sollen oder nicht
					// System.out.println("Datei existiert bereits im Quarantäne-Verzeichnis. Außerdem ist der Inhalt gleich");
					
					srcFile.delete();
					System.out.println("content equals is true");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * Write.
	 *
	 * @param relPath the rel path
	 * @param line the line
	 */
	public void write(String relPath, String line) {

		new TextWriter().write(relPath, line);
	}

	/**
	 * Log.
	 *
	 * @param relPath the rel path
	 * @param logLine the log line
	 */
	public void log(String relPath, String logLine) {
		
		new TextWriter().write(relPath, logLine);
	}
	
}
