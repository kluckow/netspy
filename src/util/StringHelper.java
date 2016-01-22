package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class StringHelper.
 */
public class StringHelper {

	/** The string helper. */
	private static StringHelper stringHelper;
	
	/** The Constant ASCII_MAP. */
	private static final Map<String, String> ASCII_MAP = new HashMap<>();
	
	/**
	 * Instantiates a new string helper.
	 */
	private StringHelper() {}
	
	static {
		
		ASCII_MAP.put("=DC", "Ü");
		ASCII_MAP.put("=FC", "ü");
		ASCII_MAP.put("=C4", "Ä");
		ASCII_MAP.put("=E4", "ä");
		ASCII_MAP.put("=D6", "Ö");
		ASCII_MAP.put("=F6", "ö");
		ASCII_MAP.put("=DF", "ß");
		ASCII_MAP.put("=B5", "µ");
	}
	
	/**
	 * Gets the single instance of StringHelper.
	 *
	 * @return single instance of StringHelper
	 */
	public static StringHelper getInstance() {
		
		if (stringHelper == null) {
			return new StringHelper();
		}
		return stringHelper;
	}
	
	/**
	 * Count occurrences.
	 *
	 * @param complete the complete
	 * @param part the part
	 * @return the int
	 */
	public int countOccurrences(String complete, String part) {
		
		if (complete.isEmpty() || part.isEmpty()) {
			return 0;
		}
		
		int occurrences = 0;
		
		while (complete.contains(part)) {
			complete = complete.replaceFirst(part, "");
			occurrences++;
		}
		return occurrences;
	}
	
	/**
	 * Count occurrences.
	 *
	 * @param lines the lines
	 * @param part the part
	 * @return the int
	 */
	public int countOccurrences(List<String> lines, String part) {
		
		if (lines.isEmpty() || part.isEmpty()) {
			return 0;
		}
		
		int occurrences = 0;
		
		for (String line: lines) {
			
			while (line.contains(part)) {
				line = line.replaceFirst(part, "");
				occurrences++;
			}	
		}
		return occurrences;
	}
	
	/**
	 * Starts with.
	 *
	 * @param target the target
	 * @param prepend the prepend
	 * @return true, if successful
	 */
	public boolean startsWith(String target, String prepend) {

		if (target.substring(0, prepend.length()).equals(prepend) && !(target.isEmpty() || prepend.isEmpty())) {
			return true;
		}
		return false;
	}

	/**
	 * Ends with.
	 *
	 * @param target the target
	 * @param append the append
	 * @return true, if successful
	 */
	public boolean endsWith(String target, String append) {
		
		if (target.substring(target.length() - (append.length())).equals(append) && !(target.isEmpty() || append.isEmpty())) {
			
			return true;
		}
		return false;
	}
	/**
	 * Checks if haystack contains the needle. Also not
	 * accepting empty strings
	 *
	 * @param haystack the haystack
	 * @param needle the needle
	 * @return true, if haystack contains the needle
	 */
	public boolean contains(String haystack, String needle) {
		
		if (haystack.isEmpty() || needle.isEmpty()) {
			return false;
		}
		return haystack.contains(needle);
	}
	
	/**
	 * Split string.
	 *
	 * @param line the line
	 * @param splitter the splitter
	 * @return the list
	 */
	public List<String> splitString(String line, String splitter) {
		
		String[] strArr = line.split(splitter);
		List<String> strList = new ArrayList<>();
		
		for (String string: strArr) {
			strList.add(string);
		}
		return strList;
	}

	/**
	 * Replace ascii inside a string.
	 *
	 * @param target the target string
	 * @return the string
	 */
	public String replaceAscii(String target) {
		
		// just check lines that containt "="
		if (!target.contains("=")) {
			return target;
		}
		
		String key;
		String value;
		for (Map.Entry<String, String> entry : ASCII_MAP.entrySet()) {
			
			key = entry.getKey();
			value = entry.getValue();
			
			if (target.contains(key)) {
				target = target.replace(key, value);
			}
			
		}
		return target;
	}
	
	/**
	 * Replace ascii.
	 *
	 * @param lines the lines
	 * @return the list
	 */
	public List<String> replaceAscii(List<String> lines) {
		
		List<String> decodedLines = new ArrayList<>();
		
		for (String line: lines) {
			decodedLines.add(replaceAscii(line));
		}
		
		return decodedLines;
	}
	
	/**
	 * To lower case.
	 *
	 * @param lines the lines
	 * @return the list
	 */
	public List<String> toLowerCase(List<String> lines) {
		
		List<String> linesToLowerCase = new ArrayList<>();
		
		for (String line: lines) {
			linesToLowerCase.add(line.toLowerCase());
		}
		return linesToLowerCase;
	}
}
