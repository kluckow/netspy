/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.gui.components.frame.components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import netspy.components.gui.components.popups.ErrorNotificationPopup;
import netspy.components.gui.components.popups.InfoNotificationPopup;

/**
 * The Class LogBox.
 */
public class LogBox extends JTextArea {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9108921515991796683L;

	/** The doc. */
	private Document doc;
	
	/**
	 * Instantiates a new log box.
	 */
	public LogBox() {
		super();
        setEditable(false);
        setPreferredSize(new Dimension(600, 200));
        setBackground(Color.WHITE);
	}
	
	@Override
	public void append(String str) {
		str = str + "\n";
        doc = getDocument();
        if (doc != null) {
            try {
                this.doc.insertString(this.doc.getLength(), str, null);
            } catch (BadLocationException e) {
                new ErrorNotificationPopup("BadLocationException", e.getMessage());
            }
        }
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
