/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.gui.components.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.plaf.metal.MetalFileChooserUI;

import netspy.components.config.ConfigPropertiesManager;
import netspy.components.filehandling.manager.FileManager;
import netspy.components.gui.components.frame.NetSpyFrame;
import netspy.components.gui.components.popups.ErrorPopup;
import netspy.components.mailing.EmailHandler;
import netspy.components.netspy.Netspy;

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
                    
                	this.owner.getInputMailPath().setText(fileMailPath.getAbsolutePath());
                	new ConfigPropertiesManager().setInboxPath(fileMailPath.getAbsolutePath());

                // check with file-names
                } else if (fileMailPath.isFile()) {
                	if (!fileMailPath.getName().endsWith(EmailHandler.EML_FILE_EXTENSION)) {
                		new ErrorPopup("Ungültige Dateierweiterung", "Es sind nur .eml-Dateien erlaubt!");
                		break;
                	} else {
                		this.owner.getInputMailPath().setText(fileMailPath.getAbsolutePath());
                		new ConfigPropertiesManager().setInboxPath(fileMailPath.getAbsolutePath());
                	}
                }
                
            } else if (returnValMailPath == JFileChooser.ERROR_OPTION) {
                new ErrorPopup("Unbekannter Fehler", "Es ist ein unbekannter Fehler aufgetreten!");
            }
            
			break;
			
		case NetSpyFrame.BUTTON_ID_QUARANTINE_PATH:
		    
		    final JFileChooser quarantinePathChooser = new JFileChooser();
		    quarantinePathChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    quarantinePathChooser.setCurrentDirectory(new File(this.owner.getInputQuarantinePath().getText()));
		    MetalFileChooserUI ui = (MetalFileChooserUI) quarantinePathChooser.getUI();
		    Field field = null;
			try {
				field = MetalFileChooserUI.class.getDeclaredField("fileNameTextField");
				field.setAccessible(true);
				JTextField tf = (JTextField) field.get(ui);
				tf.setEditable(false);
			} catch (NoSuchFieldException e1) {
			    // could not fiend textfield inside file chooser
			    new ErrorPopup("Unbekannter Fehler", "Es ist ein unbekannter Fehler aufgetreten!");
			} catch (SecurityException e1) {
			    // could not access access-property of textfield inside file chooser
			    new ErrorPopup("Unbekannter Fehler", "Es ist ein unbekannter Fehler aufgetreten!");
			} catch (IllegalArgumentException e1) {
			    // could not access ui component of text field inside file chooser
			    new ErrorPopup("Unbekannter Fehler", "Es ist ein unbekannter Fehler aufgetreten!");
			} catch (IllegalAccessException e1) {
			    // could not access ui component of text field inside file chooser
			    new ErrorPopup("Unbekannter Fehler", "Es ist ein unbekannter Fehler aufgetreten!");
			}
		    final int returnValQuarantine = quarantinePathChooser.showOpenDialog(null);
		    final File fileQuarantinePath = quarantinePathChooser.getSelectedFile();
		    
		    // Verhindert exception wenn keine dir ausgewählt wird
		    if (returnValQuarantine == JFileChooser.APPROVE_OPTION) {
		        
		        // change text field accordingly after choosing a folder
		        this.owner.getInputQuarantinePath().setText(fileQuarantinePath.getPath());
		        new ConfigPropertiesManager().setQuarantinePath(fileQuarantinePath.getAbsolutePath());

		    } else if (returnValQuarantine == JFileChooser.ERROR_OPTION) {
		        
		        new ErrorPopup("Unbekannter Fehler", "Es ist ein unbekannter Fehler aufgetreten!");
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
		            new ErrorPopup("Falsche Datei", "Datei muss '" + FileManager.BLACKLIST_FILE_NAME + "' heißen!");
		            break;
		        }
		    	
		        // change text field accordingly after choosing a folder
		        this.owner.getInputBlackwordPath().setText(fileBlackwordPath.getPath());
		        new ConfigPropertiesManager().setBlackwordPath(fileBlackwordPath.getAbsolutePath());
		        
		    } else if (returnValBlackword == JFileChooser.ERROR_OPTION) {
		        
		        new ErrorPopup("Unbekannter Fehler", "Es ist ein unbekannter Fehler aufgetreten!");
		    }
			break;
			
		case NetSpyFrame.BUTTON_ID_LOG_PATH:
			
			final JFileChooser logPathChooser = new JFileChooser();
		    logPathChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    logPathChooser.setCurrentDirectory(new File(this.owner.getInputLogPath().getText()));
		    MetalFileChooserUI uiLog = (MetalFileChooserUI) logPathChooser.getUI();
		    Field fieldLog = null;
			try {
				fieldLog = MetalFileChooserUI.class.getDeclaredField("fileNameTextField");
				fieldLog.setAccessible(true);
				JTextField tf = (JTextField) fieldLog.get(uiLog);
				tf.setEditable(false);
			} catch (NoSuchFieldException e1) {
                // could not find textfield inside file chooser
                new ErrorPopup("Unbekannter Fehler", "Es ist ein unbekannter Fehler aufgetreten!");
			} catch (SecurityException e1) {
                // could not access access-property of textfield inside file chooser
                new ErrorPopup("Unbekannter Fehler", "Es ist ein unbekannter Fehler aufgetreten!");
			} catch (IllegalArgumentException e1) {
                // could not access ui component of text field inside file chooser
                new ErrorPopup("Unbekannter Fehler", "Es ist ein unbekannter Fehler aufgetreten!");
			} catch (IllegalAccessException e1) {
                // could not access ui component of text field inside file chooser
                new ErrorPopup("Unbekannter Fehler", "Es ist ein unbekannter Fehler aufgetreten!");
			}
		    final int returnValLog = logPathChooser.showOpenDialog(null);
		    final File fileLogPath = logPathChooser.getSelectedFile();
		    
		    // Verhindert exception wenn keine dir ausgewählt wird
		    if (returnValLog == JFileChooser.APPROVE_OPTION) {
		        
		        // change text field accordingly after choosing a folder
		        this.owner.getInputLogPath().setText(fileLogPath.getPath());
		        new ConfigPropertiesManager().setLogPath(fileLogPath.getAbsolutePath());
		        
		    } else if (returnValLog == JFileChooser.ERROR_OPTION) {
		        
		        new ErrorPopup("Unbekannter Fehler", "Es ist ein unbekannter Fehler aufgetreten!");
		    }
			break;
		
		case NetSpyFrame.BUTTON_ID_START_SCAN:
			
			if (!allPathsAreSet()) {
			    this.owner.getLogBox().append("Scan konnte nicht gestartet werden!");
			    new ErrorPopup("Fehlende Pfadangaben", "Bitte überprüfen Sie Ihre Eingaben bezüglich der Pfade!");
			    break;
			} else {
			    new Netspy().start(this.owner.getLogBox());
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
