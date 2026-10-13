package com.taekwondoscheduler.view.allstudentsview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.taekwondoscheduler.model.business.StudentManager;

/**
 * The controller class for the all students view
 */
public class AllStudentsController implements ActionListener {

	/**
	 * The AllStudentsFrame instance that the controller affects
	 */
	AllStudentsFrame frame;

	/**
	 * Business layer Manager that affects students
	 */
	StudentManager studentManager;

	/**
	 * The constructor for AllStudentsController
	 * 
	 * @param frame the AllStudentsFrame that the controller affects
	 */
	public AllStudentsController(AllStudentsFrame frame) {

		if (frame != null) {
			this.frame = frame;
			studentManager = StudentManager.getInstance();

			// Adds listeners
			
			if (frame.getUpdateButton() != null && frame.getRemoveButton() != null) {
				
				frame.getUpdateButton().addActionListener(this);
				frame.getRemoveButton().addActionListener(this);
			}
			
			
		}
	}

	/**
	 * Catches the action events from the view and calls respective methods
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(frame.getUpdateButton())) {
			this.updateAction();
		} else if (e.getSource().equals(frame.getRemoveButton())) {
			this.removeAction();
		}

	}

	/**
	 * The method that runs when the update button is pressed. It updates the
	 * selected Student in storage
	 */
	private void updateAction() {

		if (frame.getSelectedStudent() == null || frame.getChangedStudent() == null) {
			JOptionPane.showMessageDialog(null, "Please select a Student to update");
		} else {
			studentManager.updateStudent(frame.getOldStudent(), frame.getChangedStudent());
		}

		frame.refreshTable();

	}

	/**
	 * The method that runs when the remove button is pressed. It removes the
	 * selected Student
	 */
	private void removeAction() {

		if (frame.getSelectedStudent() == null) {
			JOptionPane.showMessageDialog(null, "Please select a Student to remove");
		} else {
			// Confirmation Dialog
			int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove the Student?", null,
					JOptionPane.YES_NO_OPTION);

			if (answer == JOptionPane.YES_OPTION) {
				studentManager.removeStudent(frame.getSelectedStudent());
			}

		}

		frame.refreshTable();

	}

}
