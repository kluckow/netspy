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
		super.append(str + System.lineSeparator());
	}
	
	public void clear() {
		setText("");
		new InfoNotificationPopup("", "Reportfenster wurde geleert!");
	}
	/**
	 * Toggle display.
	 */
	// TODO: wollen wir die funktion haben?
	public void toggleDisplay() {
		setVisible(!isVisible());
	}

}
