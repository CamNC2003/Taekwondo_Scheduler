package com.taekwondoscheduler.view.signupview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.taekwondoscheduler.model.business.AccountManager;
import com.taekwondoscheduler.model.domain.Account;
import com.taekwondoscheduler.view.addstudentview.AddStudentFrame;
import com.taekwondoscheduler.view.scheduleview.ScheduleFrame;
import com.taekwondoscheduler.view.signinview.SignInFrame;

/**
 * The controller class for the Sign Up View
 */
public class SignUpController implements ActionListener {

	/**
	 * The sign up frame instance that the controller affects
	 */
	private SignUpFrame frame;

	/**
	 * Business layer Manager for accounts
	 */
	private AccountManager accountManager;

	/**
	 * The constructor for SignUpController
	 * 
	 * @param frame the sign up frame instance that the controller affects
	 */
	public SignUpController(SignUpFrame frame) {

		if (frame != null) {

			this.frame = frame;
			accountManager = AccountManager.getInstance();

			// Sets listeners
			frame.getCancelButton().addActionListener(this);
			frame.getEnterButton().addActionListener(this);
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
	 * The method that runs when the enter button is pressed. It gets the user info
	 * and signs up a new account. If the account is for a student, then the
	 * AddStudent view is loaded, otherwise the Schedule view is loaded
	 */
	private void enterAction() {

		String password = new String(frame.getPasswordText());
		String password2 = new String(frame.getPassword2Text());

		if (password.equals(password2)) {

			// Data is collected from text fields
			String firstName = frame.getFirstNameText();
			String lastName = frame.getLastNameText();
			String username = frame.getUsernameText();
			String email = frame.getEmailText();
			boolean isInstructor = !frame.getStudentBox();

			Account account = new Account(firstName, lastName, username, email, password, isInstructor);

			if (isInstructor) { // if instructor, skip addStudent
				if (account.validate() && accountManager.signUp(account)) {

					ScheduleFrame sframe = new ScheduleFrame(account);
					frame.setVisible(false);
					frame.getParent().add(sframe);
					sframe.setLocation(frame.getParent().getWidth() / 2 - sframe.getWidth() / 2,
							frame.getParent().getHeight() / 2 - sframe.getHeight() / 2);
					sframe.setVisible(true);
					frame.dispose();

				} else {
					JOptionPane.showMessageDialog(null, "Could not sign up. Please try again.");
				}

			} else { // if not instructor, load addStudent

				if (account.validate()) {
					AddStudentFrame aframe = new AddStudentFrame(account);
					frame.setVisible(false);
					frame.getParent().add(aframe);
					aframe.setLocation(frame.getParent().getWidth() / 2 - aframe.getWidth() / 2,
							frame.getParent().getHeight() / 2 - aframe.getHeight() / 2);
					aframe.setVisible(true);
					frame.dispose();
				}
			}

		} else {
			JOptionPane.showMessageDialog(null, "Password was not the same. Please try again.");
		}
	}

	/**
	 * The method that runs when the cancel button is pressed. Goes back to the sign
	 * in frame
	 */
	private void cancelAction() {

		SignInFrame sframe = new SignInFrame();
		frame.setVisible(false);
		frame.getParent().add(sframe);
		sframe.setLocation(frame.getParent().getWidth() / 2 - sframe.getWidth() / 2,
				frame.getParent().getHeight() / 2 - sframe.getHeight() / 2);
		sframe.setVisible(true);
		frame.dispose();
	}
}
