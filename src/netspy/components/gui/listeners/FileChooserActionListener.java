package netspy.components.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The listener interface for receiving fileChooserAction events.
 * The class that is interested in processing a fileChooserAction
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addFileChooserActionListener<code> method. When
 * the fileChooserAction event occurs, that object's appropriate
 * method is invoked.
 *
 * @see FileChooserActionEvent
 */
public class FileChooserActionListener implements ActionListener {

	/**
	 * Instantiates a new file chooser action listener.
	 */
	public FileChooserActionListener() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
