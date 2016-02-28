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
		
		try {
			super.append(str + System.lineSeparator());
			this.update(this.getGraphics());
			// timeout for better feel, bad solution though since complete is GUI frozen
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			this.clear();
			super.append("Aufgrund einer Unterbrechung eines Prozesses wird die Ausgabe in dieses Logfenster unterbrochen."
			+ System.lineSeparator());
			super.append("Leere Logfenster...");
			this.clear();
			return;
		}
	}

	/**
	 * Append without delay.
	 *
	 * @param str the str
	 */
	public void appendWithoutDelay(String str) {
		
		super.append(generateTimestampPrefix() + str + System.lineSeparator());
	}
	
	/**
	 * Clear.
	 */
	public void clear() {
		
		setText("");
	}

}
