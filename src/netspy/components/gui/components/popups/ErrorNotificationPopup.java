package netspy.components.gui.components.popups;

import javax.swing.JOptionPane;

/**
 * Class ErrorNotificationPopup.
 *
 */
public class ErrorNotificationPopup extends NotificationPopup {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -3151242789990015554L;
    
    /**
     * Instantiates a new error notification popup.
     *
     * @param message the message
     */
    public ErrorNotificationPopup(String title, String message) {
        super();
        showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
    
}
