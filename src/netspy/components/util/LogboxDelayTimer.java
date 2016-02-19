/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.util;

import java.util.Timer;

import netspy.components.gui.components.frame.NetSpyFrame;

/**
 * The Class LogboxDelayTimer.
 */
public class LogboxDelayTimer extends Timer {

	/** The frame. */
	private NetSpyFrame frame;

	/** The delay (in milliseconds). */
	private int delay = 100;
	
	/**
	 * Instantiates a new logbox delay timer.
	 *
	 * @param frame the frame
	 */
	public LogboxDelayTimer(NetSpyFrame frame, int delay) {
		this.frame = frame;
		this.delay = delay;
	}
}
