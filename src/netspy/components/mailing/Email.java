/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.mailing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class Mail.
 */
public class Email {

	/** The sender. */
	private String sender;
	
	/** The receiver. */
	private String receiver;
	
	/** The sending date. */
	private String sendingDate;
	
	/** The subject. */
	private String subject;
	
	/** The lines. */
	private List<String> lines;
	
	/** The is suspicious. */
	private boolean isSuspicious;

	/** The filename. */
	private String filename;

	/** The relative path. */
	private String relativePath;

	/** The index. */
	private int index;

	/** The hit map. */
	private Map<String, Integer> hitMap = new HashMap<>();
	
	/**
	 * Instantiates a new mail.
	 *
	 * @param lines the lines
	 * @param filename the filename
	 */
	public Email(List<String> lines, String filename) {
		
		this.lines = lines;
		this.setFilename(filename);
		this.setSuspicious(false);
	}

	/**
	 * Gets the sender.
	 *
	 * @return the sender
	 */
	public String getSender() {
		return this.sender;
	}

	/**
	 * Sets the sender.
	 *
	 * @param sender the new sender
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * Gets the receiver.
	 *
	 * @return the receiver
	 */
	public String getReceiver() {
		return this.receiver;
	}

	/**
	 * Sets the receiver.
	 *
	 * @param receiver the new receiver
	 */
	public void setReceiver(String receiver) {
		
		this.receiver = receiver;
	}

	/**
	 * Gets the sending date.
	 *
	 * @return the sending date
	 */
	public String getSendingDate() {
		
		return this.sendingDate;
	}

	/**
	 * Sets the sending date.
	 *
	 * @param sendingDate the new sending date
	 */
	public void setSendingDate(String sendingDate) {
		this.sendingDate = sendingDate;
	}

	/**
	 * Gets the subject.
	 *
	 * @return the subject
	 */
	public String getSubject() {
		return this.subject;
	}

	/**
	 * Sets the subject.
	 *
	 * @param subject the new subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	/**
	 * Sets the lines.
	 *
	 * @param lines the new lines
	 */
	public void setLines(List<String> lines) {
		
		this.lines = lines;
	}

	/**
	 * Gets the lines.
	 *
	 * @return the lines
	 */
	public List<String> getLines() {
		
		return this.lines;
	}

	/**
	 * Checks if is suspicious.
	 *
	 * @return true, if is suspicious
	 */
	public boolean isSuspicious() {
		
		return this.isSuspicious;
	}

	/**
	 * Sets the suspicious.
	 *
	 * @param isSuspicious the new suspicious
	 */
	public void setSuspicious(boolean isSuspicious) {
		
		this.isSuspicious = isSuspicious;
	}

	/**
	 * Gets the filename.
	 *
	 * @return the filename
	 */
	public String getFilename() {
		
		return filename;
	}

	/**
	 * Sets the filename and relative path to the file.
	 *
	 * @param filename the new filename
	 */
	public void setFilename(String filename) {
		
		this.relativePath = filename;
		this.filename = filename.substring(filename.indexOf("\\") + 1);
	}

	/**
	 * Gets the relative path.
	 *
	 * @return the relative path
	 */
	public String getRelativePath() {
		
		return relativePath;
	}

	/**
	 * Sets the relative path.
	 *
	 * @param relativePath the new relative path
	 */
	public void setRelativePath(String relativePath) {
		
		this.setFilename(relativePath);
	}

	/**
	 * Gets the index.
	 *
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Sets the index.
	 *
	 * @param index the new index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Sets the hit map.
	 *
	 * @param hitMap the hit map
	 */
	public void setHitMap(Map<String, Integer> hitMap) {
		
		this.hitMap = hitMap;
	}
	
	/**
	 * Gets the hit map.
	 *
	 * @return the hit map
	 */
	public Map<String, Integer> getHitMap() {
		
		return this.hitMap;
	}
}
