package com.taekwondoscheduler.view.signinview;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * The JFrame subclass for the Sign In View
 */
public class SignInFrame extends JInternalFrame {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 6338029684897828120L;

	private JLabel title;

	private JPanel content;
	private JPanel usernamePanel;
	private JPanel passwordPanel;
	private JPanel buttonPanel;

	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JTextField usernameText;
	private JPasswordField passwordText;
	private JButton signUpButton;
	private JButton enterButton;

	/**
	 * The constructor of SignInFrame
	 */
	public SignInFrame() {

		initComponents();

		new SignInController(this);

		this.setSize(300, 200);
		this.setVisible(true);

	}

	/**
	 * Initializes Components
	 */
	private void initComponents() {

		this.title = new JLabel();
		this.title.setText("Sign-in");
		this.title.setFont(new Font("Serif", Font.BOLD, 24));
		this.title.setHorizontalAlignment(SwingConstants.CENTER);

		this.getContentPane().add(this.title, BorderLayout.NORTH);

		this.content = new JPanel();
		this.usernamePanel = new JPanel();
		this.passwordPanel = new JPanel();
		this.buttonPanel = new JPanel();

		this.usernameLabel = new JLabel();
		this.usernameLabel.setText("Username: ");

		this.passwordLabel = new JLabel();
		this.passwordLabel.setText("Password: ");

		this.usernameText = new JTextField();
		this.usernameText.setColumns(15);

		this.usernameLabel.setLabelFor(usernameText);

		this.passwordText = new JPasswordField();
		this.passwordText.setColumns(15);

		this.passwordLabel.setLabelFor(this.passwordText);

		this.signUpButton = new JButton("Sign up");
		this.enterButton = new JButton("Enter");

		this.usernamePanel.setLayout(new FlowLayout());
		this.usernamePanel.add(this.usernameLabel);
		this.usernamePanel.add(this.usernameText);

		this.passwordPanel.setLayout(new FlowLayout());
		this.passwordPanel.add(this.passwordLabel);
		this.passwordPanel.add(this.passwordText);

		this.buttonPanel.setLayout(new FlowLayout());
		this.buttonPanel.add(this.signUpButton);
		this.buttonPanel.add(this.enterButton);

		this.content.setLayout(new BoxLayout(this.content, BoxLayout.Y_AXIS));
		this.content.add(this.usernamePanel);
		this.content.add(this.passwordPanel);
		this.content.add(this.buttonPanel);

		this.getContentPane().add(this.content, BorderLayout.CENTER);

		this.pack();

	}

	/**
	 * @return the signUpButton
	 */
	public JButton getSignUpButton() {
		return signUpButton;
	}

	/**
	 * @return the enterButton
	 */
	public JButton getEnterButton() {
		return enterButton;
	}

	/**
	 * @return the usernameText
	 */
	public String getUsernameText() {
		return usernameText.getText();
	}

	/**
	 * @return the passwordText
	 */
	public String getPasswordText() {
		return new String(passwordText.getPassword());
	}

}
