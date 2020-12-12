package pattern;

import java.util.Vector;

import observer.CourseRecord;

/**
 * An abstract class for all Observable subjects
 */
public abstract class Observable {
	/**
	 * Constructs an Observable object
	 */
	public Observable() {
		this.nbStudObservers = new Vector<Observer>();
		this.newCourseObservers = new Vector<Observer>();
	}

	/**
	 * Attach to this Subject
	 * 
	 * @param o
	 *            the Observer that wishes to attach
	 */
	public void attach(Observer o) {
		if(o.getClass().equals(PieChartObserver.class) || o.getClass().equals(BarChartObserver.class))
			this.nbStudObservers.addElement(o);
		else if(o.getClass().equals(CourseController.class))
			this.newCourseObservers.addElement(o);
	}

	/**
	 * Detach from this Subject
	 * 
	 * @param o
	 *            the Observer that wishes to detach
	 */
	public void detach(Observer o) {
		for (int i = 0; i < nbStudObservers.size(); i++) {
			if (nbStudObservers.elementAt(i).equals(o))
				nbStudObservers.removeElementAt(i);
		}
		for (int i = 0; i < newCourseObservers.size(); i++) {
			if (newCourseObservers.elementAt(i).equals(o))
				newCourseObservers.removeElementAt(i);
		}
	}

	/**
	 * Notify all Observers that Subject has changed
	 */
	public void notifyObservers(Object element) {
		if(element.getClass().equals(String.class)) {
			for (int i = 0; i < nbStudObservers.size(); i++) {
				Observer observer = nbStudObservers.elementAt(i);
				observer.update((CourseRecord) this.getUpdate((String)element));
			}
		} else if(element.getClass().equals(CourseRecord.class)) {
			for (int i = 0; i < newCourseObservers.size(); i++) {
				Observer observer = newCourseObservers.elementAt(i);
				observer.update((CourseRecord) element);
			}
		}
	}

	/**
	 * Pull updated data from this Subject
	 * 
	 * @return the updated data from the Subject
	 */
	public abstract Object getUpdate(String subjectName);

	protected Vector<Observer> nbStudObservers;
	protected Vector<Observer> newCourseObservers;
}