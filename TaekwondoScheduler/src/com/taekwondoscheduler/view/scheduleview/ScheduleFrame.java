package com.taekwondoscheduler.view.scheduleview;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.taekwondoscheduler.model.business.AccountManager;
import com.taekwondoscheduler.model.business.ScheduleManager;
import com.taekwondoscheduler.model.domain.Account;
import com.taekwondoscheduler.model.domain.Student;
import com.taekwondoscheduler.model.domain.TaekwondoClass;
import com.taekwondoscheduler.model.enums.Day;
import com.taekwondoscheduler.model.enums.Plan;

/**
 * The JFrame subclass for the Schedule View
 */
public class ScheduleFrame extends JInternalFrame {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 582882718226467516L;

	/**
	 * The account that is using the application
	 */
	private Account account;

	/**
	 * Business layer Manager that handles the schedule
	 */
	private ScheduleManager scheduleManager;

	/**
	 * Business layer manager that handles students
	 */
	private AccountManager accountManager;

	private JLabel title;
	private JPanel schedulePanel;

	private DaySchedulePanel mondayClasses;
	private DaySchedulePanel tuesdayClasses;
	private DaySchedulePanel wednesdayClasses;
	private DaySchedulePanel thursdayClasses;
	private DaySchedulePanel fridayClasses;

	private JPanel classTotalpanel;
	private JLabel classTotalLabel;
	private JButton submitButton;

	private JButton viewAllStudentsButton;

	/**
	 * The constructor for ScheduleFrame
	 * 
	 * @param account the account of the user
	 */
	public ScheduleFrame(Account account) {

		if (account != null) {
			this.account = account;

			scheduleManager = ScheduleManager.getInstance();
			accountManager = AccountManager.getInstance();
			
			scheduleManager.clearSchedule();

			initComponents();

			this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			this.setSize(700, 500);

			// Gets singular instance of ScheduleController
			ScheduleController.getInstance(this);

			this.setVisible(true);
		}
	}

	/**
	 * Initializes Components
	 */
	private void initComponents() {

		this.title = new JLabel();

		// Displays either "Schedule" or "StudentSchedule"
		if (this.account.isInstructor()) {
			this.title.setText("Schedule");
		} else {
			this.title.setText("Student Schedule");
		}

		this.title.setFont(new Font("Serif", Font.BOLD, 24));
		this.title.setHorizontalAlignment(SwingConstants.CENTER);

		this.getContentPane().add(title, BorderLayout.NORTH);

		this.schedulePanel = new JPanel();

		// Creates DaySchedulePanels for each day of the week
		this.mondayClasses = new DaySchedulePanel(Day.MONDAY);
		this.tuesdayClasses = new DaySchedulePanel(Day.TUESDAY);
		this.wednesdayClasses = new DaySchedulePanel(Day.WEDNESDAY);
		this.thursdayClasses = new DaySchedulePanel(Day.THURSDAY);
		this.fridayClasses = new DaySchedulePanel(Day.FRIDAY);

		// If instructor, all classes are loaded
		if (this.account.isInstructor()) {
			scheduleManager.loadInstructorSchedule();

		} else if (!this.account.isInstructor()) {

			Student student = accountManager.getStudent(account);

			// If not instructor, only qualified classes are loaded
			scheduleManager.loadStudentSchedule(student);

		}

		// Sets the schedule
		for (TaekwondoClass tClass : scheduleManager.getSchedule().getClasses()) {

			if (tClass.isVisible()) {

				// Panels only made for visible classes set by above load methods
				// Multiple panels also means multiple controllers
				TaekwondoClassPanel classPanel = new TaekwondoClassPanel(tClass, this);

				if (tClass.getDay() == Day.MONDAY) {
					this.mondayClasses.add(classPanel);
				} else if (tClass.getDay() == Day.TUESDAY) {
					this.tuesdayClasses.add(classPanel);
				} else if (tClass.getDay() == Day.WEDNESDAY) {
					this.wednesdayClasses.add(classPanel);
				} else if (tClass.getDay() == Day.THURSDAY) {
					this.thursdayClasses.add(classPanel);
				} else if (tClass.getDay() == Day.FRIDAY) {
					this.fridayClasses.add(classPanel);
				}
			}
		}

		schedulePanel.setLayout(new BoxLayout(schedulePanel, BoxLayout.X_AXIS));

		this.schedulePanel.add(mondayClasses);
		this.schedulePanel.add(tuesdayClasses);
		this.schedulePanel.add(wednesdayClasses);
		this.schedulePanel.add(thursdayClasses);
		this.schedulePanel.add(fridayClasses);

		this.getContentPane().add(this.schedulePanel, BorderLayout.CENTER);

		// Below components are only created and displayed if a student uses the
		// application
		if (!this.account.isInstructor()) {

			Student student = accountManager.getStudent(account);

			this.classTotalLabel = new JLabel();
			if (student.getPlan() == Plan.UNLIMITED) {
				this.classTotalLabel.setText("Class Total: 0");
			} else if (student.getPlan() == Plan.ONE_DAY) {
				this.classTotalLabel.setText("Regular Class Total: 0 of 1");
			} else if (student.getPlan() == Plan.TWO_DAY) {
				this.classTotalLabel.setText("Regular Class Total: 0 of 2");
			}

			this.submitButton = new JButton("Submit Attendance");

			this.classTotalpanel = new JPanel();
			this.classTotalpanel.setLayout(new FlowLayout());
			this.classTotalpanel.add(this.classTotalLabel);
			this.classTotalpanel.add(this.submitButton);
			this.getContentPane().add(this.classTotalpanel, BorderLayout.SOUTH);

		} else {

			this.viewAllStudentsButton = new JButton("View All Students");

			JPanel panel = new JPanel();

			panel.add(viewAllStudentsButton);

			this.getContentPane().add(panel, BorderLayout.SOUTH);
		}

		this.pack();
	}

	/**
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * Sets the text of the class Total Label
	 * 
	 * @param the updated text to set
	 */
	public void setClassTotalLabelText(String text) {

		if (!this.account.isInstructor()) {
			this.classTotalLabel.setText(text);
		}
	}

	/**
	 * @return the viewAllStudentsButton
	 */
	public JButton getViewAllStudentsButton() {
		return viewAllStudentsButton;
	}

	/**
	 * @return the submitButton
	 */
	public JButton getSubmitButton() {
		return submitButton;
	}
}
