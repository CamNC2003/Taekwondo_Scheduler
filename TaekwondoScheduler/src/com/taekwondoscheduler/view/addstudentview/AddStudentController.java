package com.taekwondoscheduler.view.addstudentview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.taekwondoscheduler.model.business.AccountManager;
import com.taekwondoscheduler.model.domain.Account;
import com.taekwondoscheduler.model.domain.Student;
import com.taekwondoscheduler.model.enums.Plan;
import com.taekwondoscheduler.model.enums.Rank;
import com.taekwondoscheduler.view.scheduleview.ScheduleFrame;
import com.taekwondoscheduler.view.signupview.SignUpFrame;

/**
 * Controller for the AddStudent View
 */
public class AddStudentController implements ActionListener {

	/**
	 * The Add StudentFrame instance that the controller affects
	 */
	AddStudentFrame frame;

	/**
	 * Business layer Manager for accounts
	 */
	AccountManager accountManager;

	public AddStudentController(AddStudentFrame frame) {

		if (frame != null) {
			this.frame = frame;
			this.accountManager = AccountManager.getInstance();

			// Sets listeners
			this.frame.getCancelButton().addActionListener(this);
			this.frame.getEnterButton().addActionListener(this);
		}

	}

	/**
	 * Catches the action events from the view and calls respective methods
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(frame.getEnterButton())) {
			this.enterAction();
		} else if (e.getSource().equals(frame.getCancelButton())) {
			this.cancelAction();
		}

	}

	/**
	 * The methods that runs when the enter button is pressed. It gets the Student
	 * data, signs up the account, adds the Student, and loads the Schedule View;
	 */
	private void enterAction() {

		try {
			Account account = frame.getAccount();
			byte age = (byte) Integer.parseInt(frame.getAgeText());
			Rank rank = frame.getRankBox();
			Plan plan = frame.getPlanBox();
			boolean nxtInvited = frame.getNxtBox();
			boolean sparringBInvited = frame.getNxtBox();
			boolean compTeamInvited = frame.getCompTeamBox();

			Student student = new Student(account, age, rank, plan, nxtInvited, sparringBInvited, compTeamInvited);

			if (account.validate() && student.validate() && accountManager.signUp(account)
					&& accountManager.addStudent(student)) {

				ScheduleFrame sframe = new ScheduleFrame(account);
				frame.setVisible(false);
				sframe.setVisible(true);
				frame.getParent().add(sframe);
				sframe.setLocation(frame.getParent().getWidth() / 2 - sframe.getWidth() / 2,
						frame.getParent().getHeight() / 2 - sframe.getHeight() / 2);
				frame.dispose();

			} else {
				JOptionPane.showMessageDialog(null, "Could not add Student. Please try again.");
			}

		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Age must be an integer. Please try again.");
		}

	}

	/**
	 * The method that runs when the cancel button is pressed. Goes back to the sign
	 * up frame.
	 */
	private void cancelAction() {

		SignUpFrame sframe = new SignUpFrame();
		frame.setVisible(false);
		frame.getParent().add(sframe);
		sframe.setLocation(frame.getParent().getWidth() / 2 - sframe.getWidth() / 2,
				frame.getParent().getHeight() / 2 - sframe.getHeight() / 2);
		sframe.setVisible(true);
		frame.dispose();
	}

}
