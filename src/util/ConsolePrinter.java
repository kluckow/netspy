package util;

import java.util.List;

/**
 * The Class ConsolePrinter.
 */
public class ConsolePrinter {

    /**
     * Prints the lines.
     *
     * @param lines the lines
     */
    public void printLines(final List<String> lines) {

        for (final String line : lines) {
            printLine(line);
        }
    }

    /**
     * Prints the line.
     *
     * @param line the line
     */
    public void printLine(final String line) {

        System.out.println(line);
    }

    /**
     * Prints the.
     *
     * @param str the str
     */
    public void print(final String str) {

        System.out.print(str);
    }
}
