package pattern;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;

import observer.CourseRecord;
import observer.LayoutConstants;

/**
 * This class represents a bar chart view of a vector of data. Uses the Observer
 * pattern.
 */
@SuppressWarnings("serial")
public class BarChartObserver extends JPanel implements Observer {
	/**
	 * Creates a BarChartObserver object
	 * 
	 * @param data
	 *            a CourseData object to observe
	 */
	public BarChartObserver(CourseData data) {
		data.attach(this);
		this.courseData = (Vector<CourseRecord>) data.getUpdate(null);
		this.setPreferredSize(new Dimension(2 * LayoutConstants.xOffset
				+ (LayoutConstants.barSpacing + LayoutConstants.barWidth)
				* this.courseData.size(), LayoutConstants.graphHeight + 2
				* LayoutConstants.yOffset));
		this.setBackground(Color.white);
	}

	/**
	 * Paint method
	 * 
	 * @param g
	 *            a Graphics object on which to paint
	 */
	public void paint(Graphics g) {
		super.paint(g);
		LayoutConstants.paintBarChartOutline(g, this.courseData.size());
		for (int i = 0; i < courseData.size(); i++) {
			CourseRecord record = (CourseRecord) courseData.elementAt(i);
			g.setColor(LayoutConstants.courseColours[i]);
			g.fillRect(
					LayoutConstants.xOffset + (i + 1)
							* LayoutConstants.barSpacing + i
							* LayoutConstants.barWidth, LayoutConstants.yOffset
							+ LayoutConstants.graphHeight
							- LayoutConstants.barHeight
							+ 2
							* (LayoutConstants.maxValue - record
									.getNumOfStudents()),
					LayoutConstants.barWidth, 2 * record.getNumOfStudents());
			g.setColor(Color.red);
			g.drawString(record.getName(),
					LayoutConstants.xOffset + (i + 1)
							* LayoutConstants.barSpacing + i
							* LayoutConstants.barWidth, LayoutConstants.yOffset
							+ LayoutConstants.graphHeight + 20);
		}
	}

	/**
	 * Informs this observer that the observed CourseData object has changed
	 * 
	 * @param o
	 *            the observed CourseData object that has changed
	 */
	public void update(CourseRecord c) {
		CourseRecord curCourse;
		for(CourseRecord course : this.courseData) {
			if(course.getName().contentEquals(c.getName()))
				curCourse = c;
		}
		//this.courseData = c;
		//this.courseData = data.getUpdate();

		this.setPreferredSize(new Dimension(2 * LayoutConstants.xOffset
				+ (LayoutConstants.barSpacing + LayoutConstants.barWidth)
				* this.courseData.size(), LayoutConstants.graphHeight + 2
				* LayoutConstants.yOffset));
		this.revalidate();
		this.repaint();
	}

	private Vector<CourseRecord> courseData;
}