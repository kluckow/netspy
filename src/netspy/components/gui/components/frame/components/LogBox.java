/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.gui.components.frame.components;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JTextArea;

/**
 * The Class LogBox.
 */
public class LogBox extends JTextArea {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9108921515991796683L;

	/**
	 * Instantiates a new log box.
	 */
	public LogBox() {
		super();
        setEditable(false);
        setBackground(Color.BLACK);
        setForeground(Color.GREEN);
	}
	
	/**
	 * Prepend timestamp.
	 *
	 * @param str the str
	 * @return the string
	 */
	private String generateTimestampPrefix() {
		
		return "[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "]: ";
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JTextArea#append(java.lang.String)
	 */
	@Override
	public void append(String str) {
		
		super.append(generateTimestampPrefix());
		super.append(str + System.lineSeparator());
		
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
			// TODO e
		}
		this.update(this.getGraphics());
	}

	/**
	 * Append with delay.
	 *
	 * @param str the str
	 * @param delay the delay in ms
	 */
	public void appendWithDelay(String str) {

		super.append(generateTimestampPrefix());
		
		String letter = "";
		for (int i = 0; i < str.length(); i++){
			
			letter = str.substring(i, i+1);
			super.append(letter);
			
			
			if (!letter.equals(" ") && !letter.equals(System.lineSeparator())) {
				try {
					if (i == str.length()) {
						TimeUnit.MILLISECONDS.sleep(1000);
					} else {
						TimeUnit.MILLISECONDS.sleep(200);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
					// TODO e
				}
				this.update(this.getGraphics());
			}
			
		}
		super.append(System.lineSeparator());
	}
	
	/**
	 * Clear.
	 */
	public void clear() {
		setText("");
	}

}
