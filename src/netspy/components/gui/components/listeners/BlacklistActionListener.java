/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.gui.components.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import netspy.components.config.ConfigPropertiesManager;
import netspy.components.filehandling.io.TextWriter;
import netspy.components.filehandling.manager.FileManager;
import netspy.components.gui.components.frame.NetSpyFrame;
import netspy.components.gui.components.frame.components.LogBox;
import netspy.components.gui.components.popups.ErrorPopup;
import netspy.components.gui.components.popups.InfoPopup;

/**
 * The listener interface for receiving blacklistAction events.
 * The class that is interested in processing a blacklistAction
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addBlacklistActionListener<code> method. When
 * the blacklistAction event occurs, that object's appropriate
 * method is invoked.
 *
 * @see BlacklistActionEvent
 */
public class BlacklistActionListener implements ActionListener {

	/** The blackword list. */
	private JList<String> blackwordList;
	
	/** The dlm. */
	private DefaultListModel<String> dlm;

	/** The logbox. */
	private LogBox logbox;

	/**
	 * Instantiates a new blacklist action listener.
	 *
	 * @param blackWordList the black word list
	 * @param dlm the dlm
	 * @param logbox the logbox
	 */
	public BlacklistActionListener(JList<String> blackWordList, DefaultListModel<String> dlm, LogBox logbox) {
		this.blackwordList = blackWordList;
		this.dlm = dlm;
		this.logbox = logbox;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch ( ((JButton) e.getSource()).getName() ) {
		
		case NetSpyFrame.BUTTON_ID_BLACKWORD_ADD:
			
			String blackwordToAdd = "";
			blackwordToAdd = JOptionPane.showInputDialog(null, "Blackword eingeben:", null);
			
			// handle null value (happening on cancel)
			if (blackwordToAdd == null) break;

			// remove whitespaces
			blackwordToAdd = handleWhitespace(blackwordToAdd);
			
			// process add blackword
			if (dlm.contains(blackwordToAdd.toLowerCase())) {
				new ErrorPopup("Fehlerhafte Eingabe", "Ihr eingegebenes Blackword exitiert bereits!");
				break;
			} else if (isValidToAdd(blackwordToAdd)) {
				
				// add blackword to gui component
				dlm.addElement(blackwordToAdd.toLowerCase());
				
				// refresh alphabetical sort
				dlm = sortAlphabetically(dlm);
				
				// select new entry and scroll to it until it is visible
				int indexAfter = dlm.indexOf(blackwordToAdd);
				blackwordList.setSelectedIndex(indexAfter);
				blackwordList.ensureIndexIsVisible(indexAfter);
				
				// add blackword to file
				new TextWriter().write(new ConfigPropertiesManager().getBlackwordPath(), blackwordToAdd, true, true);
				
				this.logbox.appendWithoutDelay("Blacklist-Wort '" + blackwordToAdd + "' wurde erfolgreich hinzugefügt.");
			}
			break;
			
		case NetSpyFrame.BUTTON_ID_BLACKWORD_EDIT:
			
			int indexBefore = blackwordList.getSelectedIndex();
			String blackwordToEdit = dlm.getElementAt(indexBefore);
			String newBlackword = JOptionPane.showInputDialog(null, "Blackword eingeben:", blackwordToEdit);
			
			// handle null value (happening on cancel)
			if (newBlackword == null) break;
			
			// remove whitespaces
			blackwordToEdit = handleWhitespace(blackwordToEdit);
			
			// process edit blackword
			if (isValidToAdd(newBlackword) && !blackwordToEdit.equals(newBlackword)) {
				
				// update dlm entry
				dlm.set(indexBefore, newBlackword);
				// refresh alphabetical sort
				dlm = sortAlphabetically(dlm);
				
				// select entry and scroll to it until it is visible
				int indexAfter = dlm.indexOf(newBlackword);
				blackwordList.setSelectedIndex(indexAfter);
				blackwordList.ensureIndexIsVisible(indexAfter);
				
				// remove blackword from file
				new FileManager().remove(new ConfigPropertiesManager()
						.getBlackwordPath(), blackwordToEdit);
				// insert blackword to file
				new FileManager().insert(new ConfigPropertiesManager()
						.getBlackwordPath(), newBlackword);
				
				// print info text about edited entry
				this.logbox.appendWithoutDelay("Blacklist-Wort '" + blackwordToEdit + "' wurde erfolgreich in '" + newBlackword + "' geändert.");
			}
			break;
			
		case NetSpyFrame.BUTTON_ID_BLACKWORD_DELETE:
			
			String deleteBlackword = dlm.getElementAt(blackwordList.getSelectedIndex());
			new FileManager().remove(new ConfigPropertiesManager()
					.getBlackwordPath(), deleteBlackword);
			int selectedIndex = blackwordList.getSelectedIndex();
			dlm.remove(selectedIndex);
			
			// print info text about deleted entry
			this.logbox.appendWithoutDelay("Blacklist-Wort '" + deleteBlackword + "' wurde erfolgreich gelöscht.");
			break;
			
		case NetSpyFrame.BUTTON_ID_BLACKWORD_DELETE_ALL:
			
			// delete all blackwords inside file
			new FileManager().removeAll(new ConfigPropertiesManager()
					.getBlackwordPath());
			// delete all blackwords from gui component
			dlm.removeAllElements();
			
			// print info text about delete all
			this.logbox.appendWithoutDelay("Alle Blacklist-Wörter wurden erfolgreich gelöscht.");
			break;

		default:
			// just for development
			new InfoPopup("Keine Funktion", "Event-Handling ist noch nicht implementiert!");
			break;
		}
	}

	/**
	 * Handle whitespace.
	 *
	 * @param str the str
	 * @return the string
	 */
	private String handleWhitespace(String str) {
		
		String twoWhitespacesOrMore = "\\s{2,}";
		String singleWhitespace =  " ";
		
		str = str.trim();
		str = str.replaceAll(twoWhitespacesOrMore, singleWhitespace);
		return str;
	}

	/**
	 * Sort alphabetically.
	 *
	 * @param dlm the dlm
	 * @return the default list model
	 */
	private DefaultListModel<String> sortAlphabetically(DefaultListModel<String> dlm) {
		
		List<String> temp = extractWords(dlm);
		
		Collections.sort(temp, String.CASE_INSENSITIVE_ORDER);
		
		dlm.removeAllElements();
		for (String word : temp) {
			dlm.addElement(word);
		}
		return dlm;
	}

	/**
	 * Extract words.
	 *
	 * @param dlm the dlm
	 * @return the list
	 */
	private List<String> extractWords(DefaultListModel<String> dlm) {
		
		List<String> temp = new ArrayList<String>();
		
		for (int i = 0; i < dlm.size(); i++) {
			temp.add(dlm.getElementAt(i));
		}
		return temp;
	}
	
	/**
	 * Checks if is valid to add.
	 *
	 * @param blackwordToAdd the blackword to add
	 * @return true, if is valid to add
	 */
	private boolean isValidToAdd(String blackwordToAdd) {
		
		Pattern whitespacePattern = Pattern.compile("\\s");
		
		// handle empty/whitespace input
		if ("".equals(blackwordToAdd) || whitespacePattern.equals(blackwordToAdd)) {
			new InfoPopup("Fehlerhafte Eingabe",
					"Bitte überprüfen Sie Ihre Eingabe!");
			return false;
		}
		return true;
	}

}
