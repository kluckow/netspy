/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.gui.components.listeners;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.plaf.metal.MetalFileChooserUI;

import netspy.components.config.ConfigPropertiesManager;
import netspy.components.filehandling.manager.FileManager;
import netspy.components.gui.components.frame.NetSpyFrame;
import netspy.components.gui.components.popups.ErrorPopup;
import netspy.components.netspy.Netspy;

/**
 * Class NetSpyActionListener.
 *
 * @see NetSpyActionEvent
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
	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch ( ((JButton) e.getSource()).getName() ) {
		
		case NetSpyFrame.BUTTON_ID_MAIL_PATH:
			
			final JFileChooser mailPathChooser = new JFileChooser();
            mailPathChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            mailPathChooser.setCurrentDirectory(new File(this.owner.getInputMailPath().getText()));
            
            // set input field inside file chooser editable = false
		    MetalFileChooserUI mailPathUi = (MetalFileChooserUI) mailPathChooser.getUI();
		    Field mailPathInputField = null;
			try {
				mailPathInputField = MetalFileChooserUI.class.getDeclaredField("fileNameTextField");
				mailPathInputField.setAccessible(true);
				JTextField mailPathTextField = (JTextField) mailPathInputField.get(mailPathUi);
				mailPathTextField.setEditable(false);
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
			final int returnValMailPath = mailPathChooser.showOpenDialog(null);
            final File fileMailPath = mailPathChooser.getSelectedFile();
            
            // avoid exception if no file/dir is selected
            if (returnValMailPath == JFileChooser.APPROVE_OPTION) {
                
            	// change text field accordingly after choosing a file/folder
                // but first check if file/folder is valid:
                if (fileMailPath.isDirectory()) {
                    
                	this.owner.getInputMailPath().setText(fileMailPath.getAbsolutePath());
                	new ConfigPropertiesManager().setInboxPath(fileMailPath.getAbsolutePath());

                // check with file-names
                } else if (fileMailPath.isFile()) {
                	if (!fileMailPath.getName().endsWith(FileManager.EML_FILE_EXTENSION)) {
                		new ErrorPopup("Ungültige Dateierweiterung",
                				"Es sind nur .eml-Dateien oder Verzeichnisse erlaubt!");
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
		    
		    // set input field inside file chooser editable = false
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
		    
		    // set input field inside file chooser editable = false
		    MetalFileChooserUI blackwordPathUi = (MetalFileChooserUI) blackwordPathChooser.getUI();
		    Field blackwordField = null;
			try {
				blackwordField = MetalFileChooserUI.class.getDeclaredField("fileNameTextField");
				blackwordField.setAccessible(true);
				JTextField blackwordTextField = (JTextField) blackwordField.get(blackwordPathUi);
				blackwordTextField.setEditable(false);
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
				
			    this.owner.getLogBox().appendWithoutDelay("Scan konnte nicht gestartet werden!");
			    new ErrorPopup("Fehlende Pfadangaben", "Bitte überprüfen Sie Ihre Eingaben bezüglich der Pfade!");
			    break;
			    
			} else {
			    new Netspy().start(this.owner.getLogBox());
			}
			break;
			
		case NetSpyFrame.BUTTON_ID_CLEAR_LOGBOX:
			
			this.owner.getLogBox().clear();
			break;
			
		case NetSpyFrame.BUTTON_ID_SHOW_LOG:
			
			if (Desktop.getDesktop().isDesktopSupported()) {
				
				String logpath = new ConfigPropertiesManager().getLogPath()
						+ File.separator + new FileManager().generateLogfileNameToday();
				File file = new File(logpath);
				
				// already create empty logfile instead of showing error popup (==> better usability)
				if (!file.exists()) new FileManager().createLogfile();
					
				try {
					this.owner.getLogBox().append("Öffne Logdatei...");
					java.awt.Desktop.getDesktop().edit(file);
				} catch (IOException e1) {
					new ErrorPopup("Dateizugriffs-Fehler",
							"Die Logdatei konnte nicht geöffnet werden!");
				}
			} else {
				new ErrorPopup("Kein Zugriff auf Standard-Editor",
						"Diese Komponente wird von Ihrem Betriebssystem nicht unterstützt!");
			}
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
    // might lead into long waiting time when accidently selecting an upper directory, e.g. drives
    @SuppressWarnings("unused")
	private boolean containsDirEmlFiles(File dir) {
        
        if (!dir.isDirectory()) {
            return false;
        }
        for (File fileInDir : dir.listFiles()) {
            if (fileInDir.getName().endsWith(FileManager.EML_FILE_EXTENSION)) {
                return true;    
            } else if (containsDirEmlFiles(fileInDir)) {
                return true;
            }
        }
        return false;
    }

}
