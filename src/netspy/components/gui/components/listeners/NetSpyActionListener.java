/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.gui.components.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import netspy.NetSpy;
import netspy.components.filehandling.manager.FileManager;
import netspy.components.gui.components.frame.NetSpyFrame;
import netspy.components.gui.components.popups.ErrorNotificationPopup;
import netspy.components.mailing.EmailHandler;

/**
 * Class NetSpyActionListener.
 *
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
            mailPathChooser.setCurrentDirectory(new File(this.owner.getInputMailPath().getText()));
            final int returnValMailPath = mailPathChooser.showOpenDialog(null);
            final File fileMailPath = mailPathChooser.getSelectedFile();
            
            // Verhindert exception wenn keine file/dir ausgewählt wird
            if (returnValMailPath == JFileChooser.APPROVE_OPTION) {
                
            	// change text field accordingly after choosing a file/folder
                // but first check if file/folder is valid:
                if (fileMailPath.isDirectory()) {
                    
                	this.owner.getInputMailPath().setText(fileMailPath.getPath());

                    
                    // check with file-names
                } else if (fileMailPath.isFile() && !fileMailPath.getName().endsWith(EmailHandler.EML_FILE_EXTENSION)) {
                    new ErrorNotificationPopup("Ungültige Dateierweiterung", "Es sind nur .eml-Dateien erlaubt!");
                }
                
            } else if (returnValMailPath == JFileChooser.ERROR_OPTION) {
                new ErrorNotificationPopup("Unbekannter Fehler", "Es ist ein unbekannter Fehler aufgetreten!");
            }
            
			break;
			
		case NetSpyFrame.BUTTON_ID_QUARANTINE_PATH:
		    
		    final JFileChooser quarantinePathChooser = new JFileChooser();
		    quarantinePathChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    quarantinePathChooser.setCurrentDirectory(new File(this.owner.getInputQuarantinePath().getText()));
		    final int returnValQuarantine = quarantinePathChooser.showOpenDialog(null);
		    final File fileQuarantinePath = quarantinePathChooser.getSelectedFile();
		    
		    // Verhindert exception wenn keine dir ausgewählt wird
		    if (returnValQuarantine == JFileChooser.APPROVE_OPTION) {
		        
		        // change text field accordingly after choosing a folder
		        this.owner.getInputQuarantinePath().setText(fileQuarantinePath.getPath());

		    } else if (returnValQuarantine == JFileChooser.ERROR_OPTION) {
		        
		        new ErrorNotificationPopup("Fehler", "Es ist ein unbekannter Fehler aufgetreten!");
		    }
			break;
			
		case NetSpyFrame.BUTTON_ID_BLACKWORD_PATH:
			 
		    final JFileChooser blackwordPathChooser = new JFileChooser();
		    blackwordPathChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		    blackwordPathChooser.setCurrentDirectory(new File(this.owner.getInputBlackwordPath().getText()));
		    final int returnValBlackword = blackwordPathChooser.showOpenDialog(null);
		    final File fileBlackwordPath = blackwordPathChooser.getSelectedFile();
		    
		    // Verhindert exception wenn keine dir/file ausgewählt wird
		    if (returnValBlackword == JFileChooser.APPROVE_OPTION) {
		        
		    	// only blacklist.txt file accepted
		        if (!fileBlackwordPath.getName().equals(FileManager.BLACKLIST_FILE_NAME)) {
		            new ErrorNotificationPopup("Falsche Datei", "Datei muss '" + FileManager.BLACKLIST_FILE_NAME + "' heißen!");
		            break;
		        }
		    	
		        // change text field accordingly after choosing a folder
		        this.owner.getInputBlackwordPath().setText(fileBlackwordPath.getPath());
		        
		    } else if (returnValBlackword == JFileChooser.ERROR_OPTION) {
		        
		        new ErrorNotificationPopup("Fehler", "Es ist ein unbekannter Fehler aufgetreten!");
		    }
			break;
			
		case NetSpyFrame.BUTTON_ID_LOG_PATH:
			
			final JFileChooser logPathChooser = new JFileChooser();
		    logPathChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    logPathChooser.setCurrentDirectory(new File(this.owner.getInputLogPath().getText()));
		    final int returnValLog = logPathChooser.showOpenDialog(null);
		    final File fileLogPath = logPathChooser.getSelectedFile();
		    
		    // Verhindert exception wenn keine dir ausgewählt wird
		    if (returnValLog == JFileChooser.APPROVE_OPTION) {
		        
		        // change text field accordingly after choosing a folder
		        this.owner.getInputLogPath().setText(fileLogPath.getPath());
		        
		    } else if (returnValLog == JFileChooser.ERROR_OPTION) {
		        
		        new ErrorNotificationPopup("Fehler", "Es ist ein unbekannter Fehler aufgetreten!");
		    }
			break;
			
		case NetSpyFrame.BUTTON_ID_START_SCAN:
			
			if (!allPathsAreSet()) {
			    this.owner.getLogBox().append("Scan konnte nicht gestartet werden!");
			    new ErrorNotificationPopup("Fehlende Pfadangaben", "Bitte überprüfen Sie Ihre Eingaben bezüglich der Pfade!");
			    break;
			} else {
			    NetSpy.run();
			}
			break;
			
		case NetSpyFrame.BUTTON_ID_CLEAR_LOGBOX:
			
			this.owner.getLogBox().clear();
			break;
			
		default:
			break;
		}

	}

    /**
     * All paths are set.
     *
     * @return true, if all paths are set
     */
    private boolean allPathsAreSet() {

        if (this.owner.getInputMailPath().getText().equals("")) {
            return false;
        }
        if (this.owner.getInputBlackwordPath().getText().equals("")) {
            return false;
        }
        if (this.owner.getInputLogPath().getText().equals("")) {
            return false;
        }
        if (this.owner.getInputQuarantinePath().getText().equals("")) {
            return false;
        }
        return true;
    }

    /**
     * Contains dir eml files.
     *
     * @param dir the dir
     * @return true, if successful
     */
    @SuppressWarnings("unused")
	private boolean containsDirEmlFiles(File dir) {
        
        if (!dir.isDirectory()) {
            return false;
        }
        for (File fileInDir : dir.listFiles()) {
            if (fileInDir.getName().endsWith(EmailHandler.EML_FILE_EXTENSION)) {
                return true;    
            } else if (containsDirEmlFiles(fileInDir)) {
                return true;
            }
        }
        return false;
    }

}
