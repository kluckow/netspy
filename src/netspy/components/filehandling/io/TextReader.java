/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.filehandling.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import netspy.components.gui.components.popups.ErrorPopup;

/**
 * The Class TextParser.
 */
public class TextReader {
	
	/**
	 * Read a file and return lines.
	 *
	 * @param absPath the filename
	 * @param encoding the encoding
	 * @return the list
	 */
	public List<String> readFile(String absPath, String encoding) {
		
		List<String> lines = new ArrayList<>();
		File file = new File(absPath);
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis, encoding));
			
			String line = null;
			
			while ((line = br.readLine()) != null) {
				
				lines.add(line);
			}
			br.close();
		} catch (IOException e) {
			// FileNotFoundException and UnsupportedEncodingException, but not interesting for user
			new ErrorPopup("Datei-Lesefehler", "Es ist ein Problem "
					+ "beim Lesen einer Datei aufgetreten!");
		}
		return lines;
	}
	
	/**
	 * Read file.
	 *
	 * @param absolutePathOfFile the absolute path of file
	 * @param encoding the encoding
	 * @param stopper the stopper
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public List<String> readFile(String absolutePathOfFile, String encoding, String stopper) {
		
		List<String> lines = new ArrayList<>();
		File file = new File(absolutePathOfFile);
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis, encoding));
			
			String line = null;
			
			while ((line = br.readLine()) != null) {
				
				if (line.startsWith(stopper)) {
					break;
				} else {
					lines.add(line);
				}
			}
			br.close();
		} catch (IOException e) {
			// FileNotFoundException and UnsupportedEncodingException, but not interesting for user
			new ErrorPopup("Datei-Lesefehler",
					"Es ist ein Problem beim Lesen einer Datei aufgetreten!");
		}
		return lines;
	}
	
}
