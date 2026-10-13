package com.taekwondoscheduler.view.signinview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.taekwondoscheduler.model.business.AccountManager;
import com.taekwondoscheduler.model.domain.Account;
import com.taekwondoscheduler.view.scheduleview.ScheduleFrame;
import com.taekwondoscheduler.view.signupview.SignUpFrame;

/**
 * The controller class for the sign in view
 */
public class SignInController implements ActionListener {

	/**
	 * The SignInFrame instance that the controller affects
	 */
	private SignInFrame frame;

	/**
	 * Business layer Manager for accounts
	 */
	private AccountManager accountManager;

	/**
	 * The constructor for SignInController
	 * 
	 * @param frame the SignInFrame instance that the controller affects
	 */
	public SignInController(SignInFrame frame) {

		if (frame != null) {

			this.frame = frame;
			this.accountManager = AccountManager.getInstance();

			// Sets listeners
			frame.getSignUpButton().addActionListener(this);
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
		} else if (e.getSource().equals(frame.getSignUpButton())) {
			this.signUpAction();
		}

	}

	/**
	 * The method that runs when the enter button is pressed. It attempts to sign in
	 * with the sign in credentials, and loads the Schedule View if successful.
	 */
	private void enterAction() {

		String username = frame.getUsernameText();
		String password = frame.getPasswordText();

		if ((username != null && !username.equals("")) && (password != null && !password.equals(""))) {

			Account account = accountManager.signIn(username, password);

			if (account != null) {

				ScheduleFrame sframe = new ScheduleFrame(account);
				frame.setVisible(false);
				sframe.setVisible(true);
				frame.getParent().add(sframe);

				sframe.setLocation(frame.getParent().getWidth() / 2 - sframe.getWidth() / 2,
						frame.getParent().getHeight() / 2 - sframe.getHeight() / 2);

				frame.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Could not sign in. Please try again.");
			}

		} else {
			JOptionPane.showMessageDialog(null, "Could not sign in. Please try again.");
		}

	}

	/**
	 * The method that runs when the sign up button is pressed. It loads the sign up
	 * view.
	 */
	private void signUpAction() {

		SignUpFrame sframe = new SignUpFrame();
		frame.setVisible(false);
		sframe.setVisible(true);
		frame.getParent().add(sframe);
		sframe.setLocation(frame.getParent().getWidth() / 2 - sframe.getWidth() / 2,
				frame.getParent().getHeight() / 2 - sframe.getHeight() / 2);
		frame.dispose();
	}
}
