/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.gui.components.listeners;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import netspy.components.gui.components.frame.NetSpyFrame;

/**
 * The listener interface for receiving netSpyListSelection events.
 * The class that is interested in processing a netSpyListSelection
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addNetSpyListSelectionListener<code> method. When
 * the netSpyListSelection event occurs, that object's appropriate
 * method is invoked.
 *
 * @see NetSpyListSelectionEvent
 */
public class NetSpyListSelectionListener implements ListSelectionListener {
	
	/** The frame. */
	private NetSpyFrame frame;
	
	/**
	 * Instantiates a new net spy list selection listener.
	 *
	 * @param frame the frame
	 */
	public NetSpyListSelectionListener(NetSpyFrame frame) {
		this.frame = frame;
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		switch ( ((JList<String>) e.getSource()).getSelectedIndex() ) {
		
		// if nothing is selected
		case JList.UNDEFINED_CONDITION:
			
			frame.getBtnEditBlackWord().setEnabled(false);
			frame.getBtnDeleteBlackWord().setEnabled(false);
			// and blacklist is empty
			if (frame.getDlmBlackWord().isEmpty()) {
				frame.getBtnDeleteAllBlackwords().setEnabled(false);
			}
			break;

		// by default there is a selected entry
		default:
			frame.getBtnEditBlackWord().setEnabled(true);
			frame.getBtnDeleteBlackWord().setEnabled(true);
			frame.getBtnDeleteAllBlackwords().setEnabled(true);
			break;
		}
		
	}

}
