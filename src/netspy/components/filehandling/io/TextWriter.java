package netspy.components.filehandling.io;

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
			fWriter = new FileWriter(relativePathOfFile);
			fWriter.write(line + "\n");
			fWriter.close();
		} catch (IOException e) {
			new ConsolePrinter().printLine(WRITE_ERROR_MSG);
		}
	}
}
