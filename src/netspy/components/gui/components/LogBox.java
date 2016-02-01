package netspy.components.gui.components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

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
        // TODO: remove
        append("This is the first line!");
        append("Second line alallalallallal!");
	}
	
	@Override
	public void append(String str) {
		str = str + "\n";
        doc = getDocument();
        if (doc != null) {
            try {
                this.doc.insertString(this.doc.getLength(), str, null);
            } catch (BadLocationException e) {
            }
        }
	}
	
	public void clear() {
		setText("");
	}
	/**
	 * Toggle display.
	 */
	public void toggleDisplay() {
		setVisible(!isVisible());
	}

}
