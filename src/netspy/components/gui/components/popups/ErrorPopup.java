/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.gui.components.popups;

import javax.swing.JOptionPane;

/**
 * Class ErrorNotificationPopup.
 *
 */
public class ErrorPopup extends Popup {

	/** The Constant UNKNOWN_ERROR_MSG. */
	public static final String UNKNOWN_ERROR_MSG = "Es ist ein unbekannter Fehler aufgetreten";
	
	/** The Constant UNKNOWN_ERROR_TITLE. */
	public static final String UNKNOWN_ERROR_TITLE = "Unbekannter Fehler";
	
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -3151242789990015554L;
    
    /**
     * Instantiates a new error notification popup.
     *
     * @param title the title
     * @param message the message
     */
    public ErrorPopup(String title, String message) {
        super();
        showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
    
}
