package netspy;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class ScanResult.
 */
public class ScanResult {

	/** The email. */
	private Email email;
	
	/** The hit map. */
	private Map<String, Integer> hitMap = new HashMap<>();
	
	/**
	 * Instantiates a new scan result.
	 *
	 * @param email the email
	 * @param hitMap the hit map
	 */
	public ScanResult(Email email, Map<String, Integer> hitMap) {
		
		this.setEmail(email);
		this.setHitMap(hitMap);
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public Email getEmail() {
		
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(Email email) {
		
		this.email = email;
	}

	/**
	 * Gets the hit map.
	 *
	 * @return the hit map
	 */
	public Map<String, Integer> getHitMap() {
		
		return hitMap;
	}

	/**
	 * Sets the hit map.
	 *
	 * @param hitMap the hit map
	 */
	public void setHitMap(Map<String, Integer> hitMap) {
		
		this.hitMap = hitMap;
	}
}
