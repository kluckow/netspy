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

	/**
	 * Instantiates a new blacklist action listener.
	 *
	 * @param blackWordList the black word list
	 * @param dlm the dlm
	 */
	public BlacklistActionListener(JList<String> blackWordList, DefaultListModel<String> dlm) {
		this.setBlackwordList(blackWordList);
		this.setDlm(dlm);
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
			if (blackwordToAdd == null) {
				
				break;
			// must not already exist
			} else if (isValidToAdd(blackwordToAdd)) {
				
				// add blackword to file
				new TextWriter().write(new ConfigPropertiesManager().getBlackwordPath(), blackwordToAdd, true, true);
				// add blackword to default list model
				dlm.addElement(blackwordToAdd.toLowerCase());
				// refresh alphabetical sort
				dlm = sortAlphabetically(dlm);
				
				// select new entry and scroll to it until it is visible
				int indexAfter = dlm.indexOf(blackwordToAdd);
				blackwordList.setSelectedIndex(indexAfter);
				blackwordList.ensureIndexIsVisible(indexAfter);
				// TODO: logbox entry: blackwortToAdd wurde hinzugefügt
			}

			break;
		case NetSpyFrame.BUTTON_ID_BLACKWORD_EDIT:
			
			int indexBefore = blackwordList.getSelectedIndex();
			String blackwordToEdit = dlm.getElementAt(indexBefore);
			String newBlackword = JOptionPane.showInputDialog(null, "Blackword eingeben:", null);
			
			// handle null value (happening on cancel)
			if (newBlackword == null) {
				break;
			// TODO: validate input
			} else if (isValidToAdd(newBlackword) && !blackwordToEdit.equals(newBlackword)){
				
				// remove blackword
				new FileManager().remove(new ConfigPropertiesManager()
						.getBlackwordPath(), blackwordToEdit);
				// insert blackword
				new FileManager().insert(new ConfigPropertiesManager()
						.getBlackwordPath(), newBlackword);
				
				// update dlm
				dlm.set(indexBefore, newBlackword);
				// refresh alphabetical sort
				dlm = sortAlphabetically(dlm);
				// select entry and scroll to it until it is visible
				int indexAfter = dlm.indexOf(newBlackword);
				blackwordList.setSelectedIndex(indexAfter);
				blackwordList.ensureIndexIsVisible(indexAfter);
				
				// TODO: logbox entry: blackwordToEdit wurde erfolgreich in newBlackword geändert
			}
			
			break;
		case NetSpyFrame.BUTTON_ID_BLACKWORD_DELETE:
			
			new FileManager().remove(new ConfigPropertiesManager()
					.getBlackwordPath(), dlm.getElementAt(blackwordList.getSelectedIndex()));
			int selectedIndex = blackwordList.getSelectedIndex();
			dlm.remove(selectedIndex);
			// TODO: logbox entry: dlm.getElementAt(selectedIndex) wurde erfolgreich gelöscht 
			break;
		case NetSpyFrame.BUTTON_ID_BLACKWORD_DELETE_ALL:
			
			new FileManager().removeAll(new ConfigPropertiesManager()
					.getBlackwordPath());
			dlm.removeAllElements();
			// TODO: logbox entry: Alle Wörter wurden erfolgreich gelöscht
			break;

		default:
			break;
		}
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
		
		// remove whitespaces
		blackwordToAdd = blackwordToAdd.trim();
		String twoWhitespacesOrMore = "\\s{2,}";
		String singleWhitespace = "\\s";
		blackwordToAdd = blackwordToAdd.replaceAll(twoWhitespacesOrMore, singleWhitespace);
		Pattern whitespacePattern = Pattern.compile("\\s");
		
		// handle empty/whitespace input
		if ("".equals(blackwordToAdd) || whitespacePattern.equals(blackwordToAdd)) {
			new InfoPopup("Fehlerhafte Eingabe", "Bitte überprüfen Sie Ihre Eingabe!");
			return false;
		} else if (dlm.contains(blackwordToAdd.toLowerCase())) {
			new ErrorPopup("Fehlerhafte Eingabe", "Ihr eingegebenes Blackword exitiert bereits!");
			return false;
		}
		return true;
	}

	/**
	 * Gets the blackword list.
	 *
	 * @return the blackword list
	 */
	public JList<String> getBlackwordList() {
		return blackwordList;
	}

	/**
	 * Sets the blackword list.
	 *
	 * @param blackwordList the new blackword list
	 */
	public void setBlackwordList(JList<String> blackwordList) {
		this.blackwordList = blackwordList;
	}

	/**
	 * Gets the dlm.
	 *
	 * @return the dlm
	 */
	public DefaultListModel<String> getDlm() {
		return dlm;
	}

	/**
	 * Sets the dlm.
	 *
	 * @param dlm the new dlm
	 */
	public void setDlm(DefaultListModel<String> dlm) {
		this.dlm = dlm;
	}

}
