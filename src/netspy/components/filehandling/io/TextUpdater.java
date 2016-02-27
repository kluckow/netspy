/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.filehandling.io;

import java.util.ArrayList;
import java.util.List;

import netspy.components.filehandling.manager.FileManager;

/**
 * The Class TextUpdater.
 */
public class TextUpdater {

	/**
	 * Insert.
	 *
	 * @param absPath the abs path
	 * @param str the str
	 */
	public void insert(String absPath, String str) {
		
		new TextWriter().write(absPath, str.toLowerCase(), true, true);
	}
	
	/**
	 * Removes the.
	 *
	 * @param absPath the abs path
	 * @param str the str
	 */
	public void remove(String absPath, String str) {
		
		List<String> oldContent = new TextReader().readFile(absPath, FileManager.BLACKLIST_ENCODING);
		List<String> tempContent = new ArrayList<String>();
		
		for (String fileStr : oldContent) {
			if (!str.equals(fileStr.toLowerCase())) {
				tempContent.add(fileStr);
			}
		}
		new TextWriter().write(absPath, tempContent, false, true);
	}

	/**
	 * Clear.
	 *
	 * @param absPath the abs path
	 */
	public void clear(String absPath) {
		new TextWriter().write(absPath, "", false, false);
	}
		
}
