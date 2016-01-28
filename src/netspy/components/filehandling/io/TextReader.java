package netspy.components.filehandling.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class TextParser.
 */
public class TextReader {
	
	/**
	 * Read a file and return lines.
	 *
	 * @param relativePathOfFile the filename
	 * @param encoding the encoding
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public List<String> readFile(String relativePathOfFile, String encoding) throws IOException {
		
		List<String> lines = new ArrayList<>();
		File file = new File(relativePathOfFile);
		FileInputStream fis = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis, encoding));
		
		String line = null;
		
		while ((line = br.readLine()) != null) {
			
			lines.add(line);
		}
		br.close();
		
		return lines;
	}
	
	public List<String> readFile(String relativePathOfFile, String encoding, String stopper) throws IOException {
		
		List<String> lines = new ArrayList<>();
		File file = new File(relativePathOfFile);
		FileInputStream fis = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis, encoding));
		
		String line = null;
		
		while ((line = br.readLine()) != null) {
			
			if (line.startsWith(stopper)) {
				break;
			} else {
				lines.add(line);
			}
		}
		br.close();
		
		return lines;
	}
}