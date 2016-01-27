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
    private static final String WRITE_ERROR_MSG = "Schreiben der Datei ist nicht m�glich!";

    /**
     * Instantiates a new text writer.
     */
    private TextWriter() {
    }

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
    public void write(final String relativePathOfFile, final String line) {

        FileWriter fWriter;

        try {
            fWriter = new FileWriter(relativePathOfFile);
            fWriter.write(line + "\n");
            fWriter.close();
        } catch (final IOException e) {
            new ConsolePrinter().printLine(WRITE_ERROR_MSG);
        }
    }
}
