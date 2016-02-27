package netspy.components.gui.components.listeners;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import netspy.components.config.ConfigPropertiesManager;
import netspy.components.filehandling.io.TextReader;
import netspy.components.gui.components.popups.ErrorNotificationPopup;



public class NetSpyListSelectionListener implements ListSelectionListener{
	
	private static final String BLACKLIST_ENCODING = "UTF-8";
	
	public NetSpyListSelectionListener() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		// get JList of event
		JList list = (JList) e.getSource();
		list.update(list.getGraphics());
		list.repaint();
		
	}
	
	public ArrayList<String> getBlacklist() {
		try {
			ArrayList<String> blackwords = (ArrayList<String>) new TextReader().readFile(new ConfigPropertiesManager().getBlackwordPath(),
					BLACKLIST_ENCODING);
			return blackwords;
		} catch (IOException e) {
			new ErrorNotificationPopup("Datei-Lesefehler", "Es ist ein Problem "
					+ "beim Lesen der Blacklist-Datei aufgetreten!");
			return null;
		}
	}
}
