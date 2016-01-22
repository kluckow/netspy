package util;

import java.util.List;

/**
 * The Class ConsolePrinter.
 */
public class ConsolePrinter {

	/** The printer. */
	private static ConsolePrinter printer = null;
	
	/**
	 * Instantiates a new console printer.
	 */
	private ConsolePrinter() {}
	
	/**
	 * Gets the single instance of ConsolePrinter.
	 *
	 * @return single instance of ConsolePrinter
	 */
	public static ConsolePrinter getInstance() {
		
		if (printer == null) {
			printer = new ConsolePrinter();
		}
		return printer;
	}
	
	/**
	 * Prints the lines.
	 *
	 * @param lines the lines
	 */
	public void printLines(List<String> lines) {

		for (String line: lines) {
			printLine(line);
		}
	}
	
	/**
	 * Prints the line.
	 *
	 * @param line the line
	 */
	public void printLine(String line) {
		
		System.out.println(line);
	}
	
	/**
	 * Prints the.
	 *
	 * @param str the str
	 */
	public void print(String str) {
		
		System.out.print(str);
	}
}
