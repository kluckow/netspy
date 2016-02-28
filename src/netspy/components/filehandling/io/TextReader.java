/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.filehandling.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
	 * @throws IOException Signals that an I/O exception has occurred.
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
		} catch (FileNotFoundException e) {
			new ErrorPopup("Datei nicht gefunden", "Es ist ein Fehler beim Lokalisieren einer Datei aufgetreten!");
		} catch (UnsupportedEncodingException e) {
			new ErrorPopup("Datei-Encoding-Fehler", "Es ist ein Encoding-Fehler beim Lesen einer Datei aufgetreten");
		} catch (IOException e) {
			new ErrorPopup("Datei-Lesefehler", "Es ist ein Problem "
					+ "beim Lesen einer Datei aufgetreten!");
		}
		
		return lines;
	}
	
	/**
	 * Read file.
	 *
	 * @param relativePathOfFile the relative path of file
	 * @param encoding the encoding
	 * @param stopper the stopper
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public List<String> readFile(String relativePathOfFile, String encoding, String stopper) throws IOException {
		
		List<String> lines = new ArrayList<>();
		File file = new File(relativePathOfFile);
		FileInputStream fis = new FileInputStream(file);
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
		
		return lines;
	}
}
