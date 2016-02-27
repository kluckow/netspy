package netspy.components.gui.components.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import netspy.components.config.ConfigPropertiesManager;
import netspy.components.filehandling.io.TextWriter;
import netspy.components.gui.components.frame.NetSpyFrame;
import netspy.components.gui.components.popups.InfoNotificationPopup;

public class ListActionListener implements ActionListener {

	private JList<String> blackwordList;
	private DefaultListModel<String> dlm;

	public ListActionListener(JList<String> blackWordList, DefaultListModel<String> dlm) {
		this.setBlackwordList(blackWordList);
		this.setDlm(dlm);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch ( ((JButton) e.getSource()).getName() ) {
		case NetSpyFrame.BUTTON_ID_BLACKWORD_ADD:
			String blackWordStrAdd = ""; 
			// TODO: no empty string or whitespaces allowed, no words that already exist
			blackWordStrAdd = JOptionPane.showInputDialog(null,"Geben Sie das neue BlackWord ein", null);
			if (blackWordStrAdd != null && !blackWordStrAdd.isEmpty()){
				String BlackWordPath = new ConfigPropertiesManager().getBlackwordPath();
				new TextWriter().write(BlackWordPath, blackWordStrAdd);
				// TODO: update DLM
				dlm.addElement(blackWordStrAdd);
				}
			else {
				// Oder Eingabe fehlerhaft
				new InfoNotificationPopup("Fehler", "Es wurde kein neues BlackWord hinzugefügt!");
			}
			break;

		default:
			break;
		}
	}

	public JList<String> getBlackwordList() {
		return blackwordList;
	}

	public void setBlackwordList(JList<String> blackwordList) {
		this.blackwordList = blackwordList;
	}

	public DefaultListModel<String> getDlm() {
		return dlm;
	}

	public void setDlm(DefaultListModel<String> dlm) {
		this.dlm = dlm;
	}

}
