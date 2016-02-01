package netspy.components.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import netspy.NetSpy;
import netspy.components.gui.components.LogBox;
import netspy.components.gui.frame.NetSpyFrame;

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
public class NetSpyActionListener implements ActionListener {

	/** The owner. Used for accessing the text fields. */
	private NetSpyFrame owner;
	
	/**
	 * Instantiates a new file chooser action listener.
	 *
	 * @param owner the owner
	 */
	public NetSpyActionListener(NetSpyFrame owner) {
		this.owner = owner;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch ( ((JButton) e.getSource()).getName() ) {
		
		case NetSpyFrame.BUTTON_ID_MAIL_PATH:
			
			final JFileChooser mailPathChooser = new JFileChooser();
            mailPathChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            final int returnVal = mailPathChooser.showOpenDialog(null);
            final File file = mailPathChooser.getSelectedFile();
            // Verhindert exception wenn keine file/dir ausgewählt wird
            if (returnVal == JFileChooser.APPROVE_OPTION) {
            	// TODO: .eml files only
            	// change text field after choosing a file/folder accordingly
            	this.owner.getInputMailPath().setText(file.getPath());
                System.out.println(file.getPath() + " sollte nun im text feld für den mail pfad stehen");
            } else if (returnVal == JFileChooser.CANCEL_OPTION) {
            	// TODO: create JOptionPane class, die nur einen String entgegennimmt
            	// und damit ein Fehlermsg window erstellt
                System.out.println("Es wurde leider kein File ausgewählt!");
            } else if (returnVal == JFileChooser.ERROR_OPTION) {
            	// TODO: create JOptionPane class, die nur einen String entgegennimmt
            	// und damit ein Fehlermsg window erstellt
                System.out.println("Error!");
            }
			break;
			
		case NetSpyFrame.BUTTON_ID_QUARANTINE_PATH:
			
			// TODO: implement choosers
			// TODO: directory only
			break;
			
		case NetSpyFrame.BUTTON_ID_BLACKWORD_PATH:
			
			// TODO: implement choosers
			// TODO: .txt file only
			break;
			
		case NetSpyFrame.BUTTON_ID_LOG_PATH:
			
			// TODO: implement choosers
			// TODO: directory only
			break;
			
		case NetSpyFrame.BUTTON_ID_START_SCAN:
			
			// TODO: implement choosers
			NetSpy.run();
			// TODO: check if everything is ok before starting
			break;
			
		case NetSpyFrame.BUTTON_ID_TOGGLE_LOGBOX:
			
			this.owner.getLogBox().toggleDisplay();
			break;
			
		case NetSpyFrame.BUTTON_ID_CLEAR_LOGBOX:
			
			this.owner.getLogBox().clear();
			break;
			
		default:
			break;
		}

	}

}
