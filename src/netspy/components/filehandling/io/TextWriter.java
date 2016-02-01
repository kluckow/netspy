package netspy.components.filehandling.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import netspy.components.util.ConsolePrinter;

/**
 * The Class TextWriter.
 */
public class TextWriter {
	
	/** The Constant WRITE_ERROR_MSG. */
	private static final String WRITE_ERROR_MSG = "Schreiben der Datei ist nicht möglich!";
	
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
//			TODO: ErrorNotificationPopup 
			new ConsolePrinter().printLine(WRITE_ERROR_MSG);
		}
	}
}
