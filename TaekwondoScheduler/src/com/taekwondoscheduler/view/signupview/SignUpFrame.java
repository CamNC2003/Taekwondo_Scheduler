package com.taekwondoscheduler.view.signupview;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * The JFrame subclass for the Sign Up View
 */
public class SignUpFrame extends JInternalFrame {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 534731226438414095L;

	private JLabel title;

	private JPanel content;

	private JPanel firstNamePanel;
	private JLabel firstNameLabel;
	private JTextField firstNameText;

	private JPanel lastNamePanel;
	private JLabel lastNameLabel;
	private JTextField lastNameText;

	private JPanel usernamePanel;
	private JLabel usernameLabel;
	private JTextField usernameText;

	private JPanel emailPanel;
	private JLabel emailLabel;
	private JTextField emailText;

	private JPanel passwordPanel;
	private JLabel passwordLabel;
	private JPasswordField passwordText;

	private JPanel password2Panel;
	private JLabel password2Label;
	private JPasswordField password2Text;

	private JCheckBox studentBox;

	private JPanel buttonPanel;
	private JButton cancelButton;
	private JButton enterButton;

	/**
	 * The constructor of SignUpFrame
	 */
	public SignUpFrame() {

		initComponents();
		
		new SignUpController(this);
		
		this.setSize(300, 350);
		this.setVisible(true);
	}

	/**
	 * Initializes the components
	 */
	private void initComponents() {

		this.title = new JLabel();
		this.title.setText("Sign Up");
		this.title.setFont(new Font("Serif", Font.BOLD, 24));
		this.title.setHorizontalAlignment(SwingConstants.CENTER);

		this.getContentPane().add(title, BorderLayout.NORTH);

		this.content = new JPanel();

		this.firstNamePanel = new JPanel();
		this.firstNameLabel = new JLabel();
		this.firstNameText = new JTextField();

		this.firstNameLabel.setText("First Name: ");
		this.firstNameLabel.setLabelFor(this.firstNameText);
		this.firstNameText.setColumns(15);
		this.firstNamePanel.setLayout(new FlowLayout());
		this.firstNamePanel.add(this.firstNameLabel);
		this.firstNamePanel.add(this.firstNameText);

		this.lastNamePanel = new JPanel();
		this.lastNameLabel = new JLabel();
		this.lastNameText = new JTextField();

		this.lastNameLabel.setText("Last Name: ");
		this.lastNameLabel.setLabelFor(this.lastNameText);
		this.lastNameText.setColumns(15);
		this.lastNamePanel.setLayout(new FlowLayout());
		this.lastNamePanel.add(this.lastNameLabel);
		this.lastNamePanel.add(this.lastNameText);

		this.usernamePanel = new JPanel();
		this.usernameLabel = new JLabel();
		this.usernameText = new JTextField();

		this.usernameLabel.setText("Username: ");
		this.usernameLabel.setLabelFor(this.usernameText);
		this.usernameText.setColumns(15);
		this.usernamePanel.setLayout(new FlowLayout());
		this.usernamePanel.add(this.usernameLabel);
		this.usernamePanel.add(this.usernameText);

		this.emailPanel = new JPanel();
		this.emailLabel = new JLabel();
		this.emailText = new JTextField();

		this.emailLabel.setText("E-mail Address: ");
		this.emailLabel.setLabelFor(this.emailText);
		this.emailText.setColumns(15);
		this.emailPanel.setLayout(new FlowLayout());
		this.emailPanel.add(this.emailLabel);
		this.emailPanel.add(this.emailText);

		this.passwordPanel = new JPanel();
		this.passwordLabel = new JLabel();
		this.passwordText = new JPasswordField();

		this.passwordLabel.setText("Password: ");
		this.passwordLabel.setLabelFor(this.passwordText);
		this.passwordText.setColumns(15);
		this.passwordPanel.setLayout(new FlowLayout());
		this.passwordPanel.add(this.passwordLabel);
		this.passwordPanel.add(this.passwordText);

		this.password2Panel = new JPanel();
		this.password2Label = new JLabel();
		this.password2Text = new JPasswordField();

		this.password2Label.setText("Confirm Password: ");
		this.password2Label.setLabelFor(this.password2Text);
		this.password2Text.setColumns(15);
		this.password2Panel.setLayout(new FlowLayout());
		this.password2Panel.add(this.password2Label);
		this.password2Panel.add(this.password2Text);

		this.studentBox = new JCheckBox("Student Account?");

		this.buttonPanel = new JPanel();
		this.cancelButton = new JButton("Cancel");
		this.enterButton = new JButton("Enter");
		this.buttonPanel.setLayout(new FlowLayout());
		this.buttonPanel.add(this.cancelButton);
		this.buttonPanel.add(this.enterButton);

		this.content.setLayout(new BoxLayout(this.content, BoxLayout.Y_AXIS));
		this.content.add(this.firstNamePanel);
		this.content.add(this.lastNamePanel);
		this.content.add(this.usernamePanel);
		this.content.add(this.emailPanel);
		this.content.add(this.passwordPanel);
		this.content.add(this.password2Panel);
		this.content.add(this.studentBox);
		this.content.add(this.buttonPanel);

		this.getContentPane().add(content, BorderLayout.CENTER);
		this.pack();

	}

	/**
	 * @return the firstNameText
	 */
	public String getFirstNameText() {
		return firstNameText.getText();
	}

	/**
	 * @return the lastNameText
	 */
	public String getLastNameText() {
		return lastNameText.getText();
	}

	/**
	 * @return the usernameText
	 */
	public String getUsernameText() {
		return usernameText.getText();
	}

	/**
	 * @return the emailText
	 */
	public String getEmailText() {
		return emailText.getText();
	}

	/**
	 * @return the passwordText
	 */
	public String getPasswordText() {
		return new String(passwordText.getPassword());
	}

	/**
	 * @return the password2Text
	 */
	public String getPassword2Text() {
		return new String(password2Text.getPassword());
	}

	/**
	 * @return the studentBox
	 */
	public boolean getStudentBox() {
		return studentBox.isSelected();
	}

	/**
	 * @return the cancelButton
	 */
	public JButton getCancelButton() {
		return cancelButton;
	}

	/**
	 * @return the enterButton
	 */
	public JButton getEnterButton() {
		return enterButton;
	}
}
