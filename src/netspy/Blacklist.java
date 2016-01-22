package netspy;

import java.util.List;

/**
 * The Class Blacklist.
 */
public class Blacklist {

	/** The blacklist. */
	private List<String> blacklist;
	
	/**
	 * Instantiates a new blacklist.
	 *
	 * @param blacklist the blacklist
	 */
	public Blacklist(List<String> blacklist) {
		
		this.blacklist = blacklist;
	}
	
	/**
	 * Gets the blacklist.
	 *
	 * @return the blacklist
	 */
	public List<String> getBlacklist() {
		
		return blacklist;
	}

	/**
	 * Sets the blacklist.
	 *
	 * @param blacklist the new blacklist
	 */
	public void setBlacklist(List<String> blacklist) {
		
		this.blacklist = blacklist;
	}
}