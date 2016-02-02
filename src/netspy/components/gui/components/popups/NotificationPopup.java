package netspy.components.gui.components.popups;

import javax.swing.JOptionPane;

/**
 * Class NotificationPopup.
 *
 */
public abstract class NotificationPopup extends JOptionPane {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1325639161519213012L;

    /**
     * Instantiates a new notification popup.
     *
     * @param title the title
     * @param message the message
     */
    public NotificationPopup() {
        super();
        setBounds(300, 200, 500, 200);
    }
}
