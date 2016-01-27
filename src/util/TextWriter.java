package util;

import java.io.FileWriter;
import java.io.IOException;

/**
 * The Class TextWriter.
 */
public class TextWriter {

    /** The Constant WRITE_ERROR_MSG. */
    private static final String WRITE_ERROR_MSG = "Schreiben der Datei ist nicht m�glich!";

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
