package netspy.components.filehandling.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import netspy.components.gui.components.popups.ErrorNotificationPopup;

/**
 * The Class TextWriter.
 */
public class TextWriter {
	
	/**
	 * Write.
	 *
	 * @param relativePathOfFile the relative path of file
	 * @param line the line
	 */
	public void write(String relativePathOfFile, String line) {
		
		FileWriter fWriter;
		
		try {
			fWriter = new FileWriter(new File(relativePathOfFile).getAbsolutePath(), true);
			BufferedWriter out = new BufferedWriter(fWriter);
			out.write(line + "\n");
			out.close();
		} catch (IOException e) {
		    new ErrorNotificationPopup("Datei-Schreibfehler", "Fehler beim Schreiben in "
		        + new File(relativePathOfFile).getAbsolutePath() + " aufgetreten!");
		}
	}
}
