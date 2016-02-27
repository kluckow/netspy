/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.filehandling.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import netspy.components.gui.components.popups.ErrorPopup;

/**
 * The Class TextWriter.
 */
public class TextWriter {
	
	/**
	 * Write.
	 *
	 * @param absPath the absolute path of file
	 * @param line the line
	 */
	public void write(String absPath, String line, boolean append, boolean lowercase) {
		
		FileWriter fWriter;
		
		try {
			fWriter = new FileWriter(new File(absPath), append);
			BufferedWriter out = new BufferedWriter(fWriter);
			if (lowercase) {
				line = line.toLowerCase();
			}
			if (!line.equals("")) {
				line = line + System.lineSeparator();
			} 
			out.write(line);
			out.close();
		} catch (IOException e) {
		    new ErrorPopup("Datei-Schreibfehler", "Fehler beim Schreiben in "
		        + absPath + " aufgetreten!");
		}
	}
	public void write(String absPath, List<String> lines, boolean append, boolean lowercase) {
		
		FileWriter fWriter;
		
		try {
			fWriter = new FileWriter(new File(absPath), append);
			BufferedWriter out = new BufferedWriter(fWriter);
			for (String line : lines) {
				if (lowercase) {
					line = line.toLowerCase();
				}
				out.write(line + System.lineSeparator());
			}
			out.close();
		} catch (IOException e) {
			new ErrorPopup("Datei-Schreibfehler", "Fehler beim Schreiben in " + absPath + " aufgetreten!");
		}
	}
}
