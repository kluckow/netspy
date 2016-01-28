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

        ASCII_MAP.put("=DC", "�");
        ASCII_MAP.put("=FC", "�");
        ASCII_MAP.put("=C4", "�");
        ASCII_MAP.put("=E4", "�");
        ASCII_MAP.put("=D6", "�");
        ASCII_MAP.put("=F6", "�");
        ASCII_MAP.put("=DF", "�");
        ASCII_MAP.put("=B5", "�");
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
     * Count occurrences.
     *
     * @param lines the lines
     * @param part the part
     * @return the int
     */
    public int countOccurrences(final List<String> lines, final String part) {

        if (lines.isEmpty() || part.isEmpty()) {
            return 0;
        }

        int occurrences = 0;

        for (String line : lines) {

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
    public boolean startsWith(final String target, final String prepend) {

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
    public boolean endsWith(final String target, final String append) {

        if (target.substring(target.length() - (append.length())).equals(append)
            && !(target.isEmpty() || append.isEmpty())) {

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
    public boolean contains(final String haystack, final String needle) {

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
