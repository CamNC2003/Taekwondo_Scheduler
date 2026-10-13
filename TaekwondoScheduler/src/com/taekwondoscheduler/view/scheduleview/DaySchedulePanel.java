package com.taekwondoscheduler.view.scheduleview;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.taekwondoscheduler.model.enums.Day;

/**
 * A JPanel subclass that represents a week day that the classes occur on
 */
public class DaySchedulePanel extends JPanel {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -6234779093729039108L;

	JLabel dayLabel;

	public DaySchedulePanel(Day day) {

		if (day != null) {
			this.dayLabel = new JLabel();
			this.dayLabel.setText(day.toString());
			this.add(this.dayLabel);
			this.setBorder(new LineBorder(Color.BLACK));
			this.setAlignmentY(0f);

			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		}

	}

}
