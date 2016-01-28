package netspy.components.filehandling.manager;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import netspy.components.filehandling.io.TextReader;
import netspy.components.util.DateHelper;

/**
 * The Class FileManager.
 */
public class FileManager {

	/** The file manager. */
	private static FileManager fileManager = null;
	
	/** The Constant INBOX_PATH. */
	public static final String INBOX_PATH = "inbox/";
	
	/** The Constant QUARANTINE_PATH. */
	public static final String QUARANTINE_PATH = "quarantine/";
	
	/** The Constant BLACKLIST_FILE. */
	private static final String BLACKLIST_FILE = "blacklist.txt";
	
	/** The Constant BLACKLIST_PATH. */
	private static final String BLACKLIST_PATH = "data/" + BLACKLIST_FILE;
	
	/** The Constant BLACKLSIT_ENCODING. */
	private static final String BLACKLSIT_ENCODING = "Cp1252";
	
	/** The Constant WHITELIST_FILE. */
	private static final String WHITELIST_FILE = "whitelist.txt"; 
	
	/** The Constant WHITELIST_PATH. */
	private static final String WHITELIST_PATH = "data/" + WHITELIST_FILE;
	
	/** The Constant LOG_PATH. */
	private static final String LOG_PATH = "logs/";

	/** The Constant LOG_FILE_PREFIX. */
	private static final String LOG_FILE_PREFIX = "log_";

	/** The Constant LOG_FILE_EXTENSION. */
	private static final String LOG_FILE_EXTENSION = ".txt";
	
	/** The Constant DATE_FORMAT_LOGFILE_SUFFIX. */
	private final DateFormat DATE_FORMAT_LOGFILE_SUFFIX = new SimpleDateFormat("ddMMyyyy_HHmmss");
	
	/**
	 * Instantiates a new file manager.
	 */
	private FileManager() {}
	
	/**
	 * Gets the single instance of FileManager.
	 *
	 * @return single instance of FileManager
	 */
	public static FileManager getInstance() {
		
		if (fileManager == null) {
			fileManager = new FileManager();
		}
		return fileManager;
	}
	
	/**
	 * Gets the files by file extension.
	 *
	 * @param fileExtension the file extension
	 * @return the files by file extension
	 */
	public List<File> getFilesByExtension(String fileExtension) {
		
		File folder = new File(INBOX_PATH);
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
	 */
	// TODO: use this before logging
	public String createLogfile() {
		
		File file = new File(LOG_PATH 
							+ LOG_FILE_PREFIX
							// TODO: adjust after resolving todo concerned to getFormattedDate(), also fix warning
							+ DateHelper.getInstance().dateToString(new Date(), DATE_FORMAT_LOGFILE_SUFFIX)
							+ LOG_FILE_EXTENSION
		);
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("Could not create file: " + file.getName() + " in " + file.getParent() + "!");
		}
		return file.getPath();
	}


	/**
	 * Write.
	 *
	 * @param line the line
	 */
	public void write(String line) {
//		TODO: add relative path of logfile as first parameter
//		get logfile path from constants from this filemanager here
//		TextWriter.getInstance().write(line);
	}

	/**
	 * Gets the blacklist.
	 *
	 * @return the blacklist
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public List<String> getBlacklist() throws IOException {
		
		return new TextReader().readFile(BLACKLIST_PATH, BLACKLSIT_ENCODING);
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
			System.out.println(destFile.getName() + " existiert bereits im " + destFile.getParent() + "-Verzeichnis!");
		} else {
			System.out.println(srcFile.getName() + " wurde nach " + destFile.getParent() + " verschoben!");
		}
	}
	
}
