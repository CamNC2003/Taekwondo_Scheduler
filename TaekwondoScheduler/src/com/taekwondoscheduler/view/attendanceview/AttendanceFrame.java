package com.taekwondoscheduler.view.attendanceview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.taekwondoscheduler.model.business.ScheduleManager;

/**
 * The JFrame subclass for the Attendance View that displays when instructor
 * presses attendance button
 */
public class AttendanceFrame extends JFrame {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 2159906673382268367L;

	private JLabel title;

	private JPanel content;

	/**
	 * Business layer Manager that handles the schedule
	 */
	private ScheduleManager manager;

	/**
	 * Constructor of AttendanceFrame
	 * 
	 * @param index the index of the Taekwondo class
	 */
	public AttendanceFrame(byte index) {

		manager = ScheduleManager.getInstance();
		this.setPreferredSize(new Dimension(300, 300));
		initComponents(index);

		this.setVisible(true);
	}

	/**
	 * Initializes the components
	 */
	private void initComponents(byte index) {

		this.title = new JLabel();
		this.title.setText("Attendance");
		this.title.setFont(new Font("Serif", Font.BOLD, 24));
		this.title.setHorizontalAlignment(SwingConstants.CENTER);

		this.getContentPane().add(title, BorderLayout.NORTH);

		this.content = new JPanel();

		ArrayList<String> attendance = manager.getAttendance(index);

		if (attendance.isEmpty()) {
			JLabel label = new JLabel();
			label.setText("No attendance marked for this class.");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			this.content.add(label);

		} else {

			String[] names = new String[attendance.size()];

			for (int i = 0; i < attendance.size(); i++) {
				names[i] = attendance.get(i);
			}

			// Attendance comes from classList of corresponding class
			JList<String> list = new JList<String>(names);
			list.setFont(new Font("Serif", Font.BOLD, 20));
			list.setBorder(new LineBorder(Color.BLACK));
			content.add(list);

		}

		this.getContentPane().add(content, BorderLayout.CENTER);
		this.pack();
		this.setLocationRelativeTo(null);

	}
}
