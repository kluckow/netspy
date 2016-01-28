package netspy.components.util;

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
