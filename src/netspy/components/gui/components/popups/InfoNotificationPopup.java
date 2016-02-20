/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.gui.components.popups;

import javax.swing.JOptionPane;

/**
 * Class InfoNotificationPopup.
 *
 */
public class InfoNotificationPopup extends NotificationPopup {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -521228479931485901L;

    /**
     * Instantiates a new info notification popup.
     *
     * @param title the title
     * @param message the message
     */
    public InfoNotificationPopup(String title, String message) {
        super();
        showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

}
