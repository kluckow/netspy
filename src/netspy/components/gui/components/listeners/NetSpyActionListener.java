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
import netspy.components.gui.components.frame.NetSpyFrame;
import netspy.components.gui.components.popups.ErrorNotificationPopup;
import netspy.components.gui.components.popups.InfoNotificationPopup;
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
            final int returnValMailPath = mailPathChooser.showOpenDialog(null);
            final File fileMailPath = mailPathChooser.getSelectedFile();
            
            // Verhindert exception wenn keine file/dir ausgewählt wird
            if (returnValMailPath == JFileChooser.APPROVE_OPTION) {
                
            	// change text field accordingly after choosing a file/folder
                // but first check if file/folder is valid:
                if (fileMailPath.isDirectory()) {
                    
                    // directory must contain .eml files
                     if (!containsDirEmlFiles(fileMailPath)) {
                         new ErrorNotificationPopup("Keine Emaildateien", "Das Verzeichnis und die Unterverzeichnisse enthalten keine .eml-Dateien");
                         break;
                     } else {
                         this.owner.getInputMailPath().setText(fileMailPath.getPath());
                         System.out.println(fileMailPath.getPath() + " sollte nun im text feld für den mail pfad stehen");
//                         new InfoNotificationPopup("Info", fileMailPath.getPath() + " contains eml file(s)!");
                     }
                    
                    
                    // check with file-names
                } else if (fileMailPath.isFile() && !fileMailPath.getName().endsWith(EmailHandler.EML_FILE_EXTENSION)) {
                    new ErrorNotificationPopup("Ungültige Dateierweiterung", "Es sind nur .eml-Dateien erlaubt!");
//                    TODO: Do we need this?
//                    if (fileMailPath.getName().matches("[^\\p{Punct}&&[.-_]]")) {
//                        new ErrorNotificationPopup("Ungültiger Dateiname", "Dateien dürfen keine Sonderzeichen (außer - und _) enthalten!");
//                    } 
                }
                
            } else if (returnValMailPath == JFileChooser.CANCEL_OPTION) {
                new InfoNotificationPopup("Kein Verzeichnis oder Datei ausgewählt", "Sie müssen ein Verzeichnis"
                                + " oder eine Datei für die zu überprüfenden Emails angeben!");
            } else if (returnValMailPath == JFileChooser.ERROR_OPTION) {
                new ErrorNotificationPopup("Unbekannter Fehler", "Es ist ein unbekannter Fehler aufgetreten!");
            }
            
			break;
			
		case NetSpyFrame.BUTTON_ID_QUARANTINE_PATH:
		    
		    final JFileChooser quarantinePathChooser = new JFileChooser();
		    quarantinePathChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    final int returnValQuarantine = quarantinePathChooser.showOpenDialog(null);
		    final File filequarantinePath = quarantinePathChooser.getSelectedFile();
		    
		    // Verhindert exception wenn keine dir ausgewählt wird
		    if (returnValQuarantine == JFileChooser.APPROVE_OPTION) {
		        
		        // change text field accordingly after choosing a folder
		        this.owner.getInputQuarantinePath().setText(filequarantinePath.getPath());
		        System.out.println(filequarantinePath.getPath() + " sollte nun im Textfeld für den Mail pfad stehen.");
		        
		    } else if (returnValQuarantine == JFileChooser.CANCEL_OPTION) {
		        
		        new InfoNotificationPopup("Kein Verzeichnis ausgewählt", "Sie müssen ein Verzeichnis"
		            + " für den Quarantäne-Ordner angeben!");
		        
		    } else if (returnValQuarantine == JFileChooser.ERROR_OPTION) {
		        
		        new ErrorNotificationPopup("Fehler", "Es ist ein unbekannter Fehler aufgetreten!");
		    }
			
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

    /**
     * Contains dir eml files.
     *
     * @param dir the dir
     * @return true, if successful
     */
    private boolean containsDirEmlFiles(File dir) {
        
        if (!dir.isDirectory()) {
//            new ErrorNotificationPopup("Fehler", dir.getPath() + " is not a directory!");
            return false;
        }
        for (File fileInDir : dir.listFiles()) {
            
            if (fileInDir.getName().endsWith(EmailHandler.EML_FILE_EXTENSION)) {
                
//                new InfoNotificationPopup("Info", fileInDir.getPath() + " is an eml file(s)!");
                return true;    
                
            } else if (containsDirEmlFiles(fileInDir)) {
                
//                new InfoNotificationPopup("Info", fileInDir.getPath() + " contains eml file(s)!");
                return true;
            }
        }
        
//        new ErrorNotificationPopup("Fehler", "No .eml files found in " + dir.getPath());
        return false;
    }

}
