package util;

import java.io.FileWriter;
import java.io.IOException;

/**
 * The Class TextWriter.
 */
public class TextWriter {
	
	/** The writer. */
	private static TextWriter writer = null;
	
	/** The Constant WRITE_ERROR_MSG. */
	private static final String WRITE_ERROR_MSG = "Schreiben der Datei ist nicht möglich!";
	
	/**
	 * Instantiates a new text writer.
	 */
	private TextWriter() {}
	
	/**
	 * Gets the single instance of TextWriter.
	 *
	 * @return single instance of TextWriter
	 */
	public static TextWriter getInstance() {
		
		if (writer == null) {
			writer = new TextWriter();
		}
		return writer;
	}
	
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
			ConsolePrinter.getInstance().printLine(WRITE_ERROR_MSG);
		}
	}
}
