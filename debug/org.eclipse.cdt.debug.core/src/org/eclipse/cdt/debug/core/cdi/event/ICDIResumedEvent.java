/*
 *(c) Copyright QNX Software Systems Ltd. 2002.
 * All Rights Reserved.
 * 
 */
package org.eclipse.cdt.debug.core.cdi.event;

/**
 * 
 * Notifies that the originator has been resumed.
 * The originators:
 * <ul>
 * <li>target (ICDITarget)
 * <li>thread (ICDIThread)
 * </ul>
 * 
 * @since Jul 10, 2002
 */
public interface ICDIResumedEvent extends ICDIEvent {
	final static public int STEP_OVER = 0;
	final static public int STEP_INTO = 1;
	final static public int STEP_OVER_INSTRUCTION = 2;
	final static public int STEP_INTO_INSTRUCTION = 3;
	final static public int STEP_RETURN = 4;

	/**
	 * Returns the stepping type.
	 * 
	 * @return the stepping type
	 */
	int getType();
}
