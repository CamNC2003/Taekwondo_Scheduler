package com.taekwondoscheduler.view.scheduleview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.taekwondoscheduler.model.business.AccountManager;
import com.taekwondoscheduler.model.business.ScheduleManager;
import com.taekwondoscheduler.model.domain.Student;
import com.taekwondoscheduler.model.enums.Plan;
import com.taekwondoscheduler.view.allstudentsview.AllStudentsFrame;

/**
 * The singleton controller class for the Schedule View
 */
public class ScheduleController implements ActionListener {

	/**
	 * The singular instance of ScheduleController
	 */
	private static ScheduleController instance;

	/**
	 * The ScheduleFrame that the controller affects
	 */
	private ScheduleFrame frame;

	/**
	 * Business layer manager that handles schedule changes
	 */
	private ScheduleManager scheduleManager;

	/**
	 * Business layer manager that handles students
	 */
	private AccountManager accountManager;

	/**
	 * The current classTotal for a Student
	 */
	private byte classTotal;

	/**
	 * The maximum number of classes a student can take
	 */
	private byte max;

	/**
	 * true if a Student has an unlimited plan, false if not
	 */
	private boolean unlimited;

	/**
	 * The first and last name of a Student
	 */
	private String studentName;

	/**
	 * A student Instance
	 */
	Student student;

	/**
	 * The constructor for ScheduleController
	 * 
	 * @param frame The ScheduleFrame that the controller affects
	 */
	private ScheduleController(ScheduleFrame frame) {

		if (frame != null) {
			this.frame = frame;

			this.scheduleManager = ScheduleManager.getInstance();
			this.accountManager = AccountManager.getInstance();
			this.classTotal = 0;

			// Only sets these fields if a Student is using the application
			if (!this.frame.getAccount().isInstructor()) {

				this.classTotal = 0;
				student = this.accountManager.getStudent(this.frame.getAccount());

				if (student.getPlan() == Plan.ONE_DAY) {
					this.max = 1;
				} else if (student.getPlan() == Plan.TWO_DAY) {
					this.max = 2;
				} else if (student.getPlan() == Plan.UNLIMITED) {
					this.unlimited = true;
				}

				this.studentName = (student.getAccount().getFirstName() + " " + student.getAccount().getLastName());

				if (this.frame.getSubmitButton() != null) {
					this.frame.getSubmitButton().addActionListener(this);
				}

			} else {

				if (this.frame.getViewAllStudentsButton() != null) {
					this.frame.getViewAllStudentsButton().addActionListener(this);
				}
			}
		}
	}

	/**
	 * Gets the instance of Schedule Controller
	 * 
	 * @param frame the ScheduleFrame for constructor in case of first call
	 * 
	 * @return the instance of ScheduleController
	 */
	public static ScheduleController getInstance(ScheduleFrame frame) {

		if (instance == null) {

			instance = new ScheduleController(frame);

		}

		return instance;
	}

	/**
	 * Catches the action events from the view and calls respective methods
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(frame.getSubmitButton())) {
			this.submitAction();
		} else if (e.getSource().equals(frame.getViewAllStudentsButton())) {
			this.viewStudentsAction();
		}
	}

	/**
	 * The method that runs when the submit attendance button is pressed. It submits
	 * the list of selected classes and marks the attendance for each of them
	 */
	private void submitAction() {

		// Uses setAttendance from business layer
		boolean set = scheduleManager.setAttendance(studentName);

		if (set) {
			JOptionPane.showMessageDialog(null, "Attendance has been submitted.");
		} else {
			JOptionPane.showMessageDialog(null,
					"You already marked your attendance for at least one of these classes.");
		}

		// Resets list
		scheduleManager.resetClasses();
		this.classTotal = 0;

		// Resets UI
		if (this.unlimited) {
			this.frame.setClassTotalLabelText(("Class Total: " + this.classTotal));

		} else {

			this.frame.setClassTotalLabelText(("Regular Class Total: " + this.classTotal + " of " + this.max));
		}
	}

	/**
	 * The method that runs when the view Students button is pressed. It loads the
	 * all students view
	 */
	private void viewStudentsAction() {

		new AllStudentsFrame();
	}

	/**
	 * Runs whenever a student selects the attendance button for a class. Adds the
	 * selected class to their list of classes for which to set attendance. If the
	 * button is pressed again, the class will be removed from the list.
	 * 
	 * @param index the index of the selected class
	 */
	void markAttendance(byte index) {

		// Only happens with Students
		if (!this.frame.getAccount().isInstructor()) {

			// Only if the classes have met the maximum
			if (!this.unlimited && (this.classTotal == this.max && !scheduleManager.checkClassAttendance(index))) {

				JOptionPane.showMessageDialog(null, "You have selected your maximum number of classes.");
			} else {

				if (this.unlimited) {
					// Changes class attendance
					this.classTotal = scheduleManager.changeClassAttendanceUnlimited(index);
					this.frame.setClassTotalLabelText(("Class Total: " + this.classTotal));

					// && condition allows selected class to be removed if max is reached
				} else if (this.classTotal < this.max
						|| (this.classTotal == this.max && scheduleManager.checkClassAttendance(index))) {
					this.classTotal = scheduleManager.changeClassAttendance(index);
					this.frame.setClassTotalLabelText(("Regular Class Total: " + this.classTotal + " of " + this.max));
				}
			}
		}
	}
}
