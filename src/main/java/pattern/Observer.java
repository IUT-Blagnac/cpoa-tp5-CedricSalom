package pattern;

import java.util.Vector;

import observer.CourseRecord;

/**
 * An interface for all Observers
 */
public interface Observer {
	/**
	 * Informs this observer that an observed subject has changed
	 * 
	 * @param o
	 *            the observed subject that has changed
	 */
	public void update(CourseRecord o);
}