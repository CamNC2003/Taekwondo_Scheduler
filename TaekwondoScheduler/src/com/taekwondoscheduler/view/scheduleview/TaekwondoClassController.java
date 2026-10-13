package com.taekwondoscheduler.view.scheduleview;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.taekwondoscheduler.view.attendanceview.AttendanceFrame;

/**
 * A controller class for a TaekwondoClassPanel
 */
public class TaekwondoClassController implements ActionListener {

	/**
	 * The TaekwondoClassPanel instance that the controller affects
	 */
	TaekwondoClassPanel classPanel;
	
	/**
	 * The ScheduleFrame instance that holds all of the TaekwondoClassPanels
	 */
	ScheduleFrame scheduleFrame;
	
	/**
	 * The constructor for TaekwondoClassController
	 * 
	 * @param classPanel the TaekwondoClassPanel instance that the controller affects
	 * @param scheduleFrame the ScheduleFrame instance that holds all of the TaekwondoClassPanels
	 */
	public TaekwondoClassController(TaekwondoClassPanel classPanel, ScheduleFrame scheduleFrame) {
		
		if (classPanel != null && scheduleFrame != null) {
			this.classPanel = classPanel;
			this.scheduleFrame = scheduleFrame;
			
			//Sets listener
			this.classPanel.getAttendanceButton().addActionListener(this);
		}
		
		
	}

	/**
	 * Catches the action event from the attendance button. 
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (scheduleFrame.getAccount().isInstructor()) {
			
			viewAttendance();
		} else {
			markAttendance();
		}

	}
	
	/**
	 * If instructor, then the attendance frame for the class is loaded.
	 */
	public void viewAttendance() {
		
		//Displays the attendance of the class
		new AttendanceFrame(classPanel.getTClass());
	}
	
	/**
	 * Marks the attendance of a class by passing control to ScheduleController
	 */
	public void markAttendance() {
		
		//Gets singleton instance of ScheduleController
		ScheduleController scontrol = ScheduleController.getInstance(scheduleFrame);
		
		//MarkAttendance is called from ScheduleController so that ScheduleFrame can be affected
		scontrol.markAttendance(classPanel.getTClass());
	}

}
