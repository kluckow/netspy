/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class StringHelper.
 */
public class StringHelper {

    /** The Constant ASCII_MAP. */
    private static final Map<String, String> ASCII_MAP = new HashMap<>();

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
     * Count occurrences.
     *
     * @param complete the complete
     * @param part the part
     * @return the int
     */
    public int countOccurrences(String complete, final String part) {

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
     * Split string.
     *
     * @param line the line
     * @param splitter the splitter
     * @return the list
     */
    public List<String> splitString(final String line, final String splitter) {

        final String[] strArr = line.split(splitter);
        final List<String> strList = new ArrayList<>();

        for (final String string : strArr) {
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

        // just check lines that contain "="
        if (!target.contains("=")) {
            return target;
        }

        String key;
        String value;
        for (final Map.Entry<String, String> entry : ASCII_MAP.entrySet()) {

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
    public List<String> replaceAscii(final List<String> lines) {

        final List<String> decodedLines = new ArrayList<>();

        for (final String line : lines) {
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
    public List<String> toLowerCase(final List<String> lines) {

        final List<String> linesToLowerCase = new ArrayList<>();

        for (final String line : lines) {
            linesToLowerCase.add(line.toLowerCase());
        }
        return linesToLowerCase;
    }
}
