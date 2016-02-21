/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.gui.components.frame.components;

import java.awt.Color;

import javax.swing.JTextArea;

import netspy.components.gui.components.popups.InfoNotificationPopup;

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
        setForeground(Color.WHITE);
	}
	
	@Override
	public void append(String str) {
		// TODO: Feature, that logbox is filling with 100ms delay each letter for cool anmiation
		super.append(str + System.lineSeparator());
	}
	
	/**
	 * Clear.
	 */
	public void clear() {
		setText("");
		new InfoNotificationPopup("", "Reportfenster wurde geleert!");
	}

}
