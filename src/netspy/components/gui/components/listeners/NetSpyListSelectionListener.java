package netspy.components.gui.components.listeners;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import netspy.components.config.ConfigPropertiesManager;
import netspy.components.filehandling.io.TextReader;
import netspy.components.gui.components.frame.NetSpyFrame;
import netspy.components.gui.components.popups.ErrorNotificationPopup;



public class NetSpyListSelectionListener implements ListSelectionListener{
	
	private NetSpyFrame owner;
	
	private static final String BLACKLIST_ENCODING = "UTF-8";
	
	public NetSpyListSelectionListener(NetSpyFrame owner) {
		this.setOwner(owner);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		// get JList of event
		@SuppressWarnings({ "rawtypes", "unused" })
		JList list = (JList) e.getSource();
		
	}
	
	public NetSpyFrame getOwner() {
		return owner;
	}

	public void setOwner(NetSpyFrame owner) {
		this.owner = owner;
	}
	
	public ArrayList<String> getBlacklist() {
		try {
			ArrayList<String> Blackwords = (ArrayList<String>) new TextReader().readFile(new ConfigPropertiesManager().getBlackwordPath(),
					BLACKLIST_ENCODING);
			return Blackwords;
		} catch (IOException e) {
			new ErrorNotificationPopup("Datei-Lesefehler", "Es ist ein Problem "
					+ "beim Lesen der Blacklist-Datei aufgetreten!");
		}
		return null;
	}
}
