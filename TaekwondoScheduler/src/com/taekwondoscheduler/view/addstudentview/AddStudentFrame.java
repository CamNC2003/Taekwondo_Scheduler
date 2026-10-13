package com.taekwondoscheduler.view.addstudentview;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.taekwondoscheduler.model.domain.Account;
import com.taekwondoscheduler.model.enums.Plan;
import com.taekwondoscheduler.model.enums.Rank;

/**
 * The JInernalFrame subclass for the Add Student View
 */
public class AddStudentFrame extends JInternalFrame {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -3745305914907771533L;

	private JLabel title;

	private JPanel content;

	private Account account;

	private JPanel agePanel;
	private JLabel ageLabel;
	private JTextField ageText;

	private JPanel rankPanel;
	private JLabel rankLabel;
	private JComboBox<Rank> rankBox;

	private JPanel planPanel;
	private JLabel planLabel;
	private JComboBox<Plan> planBox;

	private JCheckBox nxtBox;

	private JCheckBox sparringBBox;
	private JCheckBox compTeamBox;

	private JPanel buttonPanel;
	private JButton cancelButton;
	private JButton enterButton;

	/**
	 * The constructor for AddStudentView
	 * 
	 * @param account the new account of the student signing up
	 */
	public AddStudentFrame(Account account) {

		if (account != null) {
			
			this.account = account;

			initComponents();

			new AddStudentController(this);

			this.setSize(600, 400);
			this.setVisible(true);
		}
		
	}

	/**
	 * Initializes the components
	 */
	private void initComponents() {

		this.title = new JLabel();
		this.title = new JLabel();
		this.title.setText("Add New Student");
		this.title.setFont(new Font("Serif", Font.BOLD, 24));
		this.title.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(title, BorderLayout.NORTH);

		this.content = new JPanel();

		this.agePanel = new JPanel();
		this.ageLabel = new JLabel();
		this.ageText = new JTextField();

		this.ageLabel.setText("Age: ");
		this.ageLabel.setLabelFor(this.ageText);
		this.ageText.setColumns(2);
		this.agePanel.setLayout(new FlowLayout());
		this.agePanel.add(this.ageLabel);
		this.agePanel.add(this.ageText);

		this.rankPanel = new JPanel();
		this.rankLabel = new JLabel();
		this.rankBox = new JComboBox<Rank>(Rank.values());

		this.rankLabel.setText("Belt Rank: ");
		this.rankLabel.setLabelFor(this.rankBox);
		this.rankPanel.setLayout(new FlowLayout());
		this.rankPanel.add(this.rankLabel);
		this.rankPanel.add(this.rankBox);

		this.planPanel = new JPanel();
		this.planLabel = new JLabel();
		this.planBox = new JComboBox<Plan>(Plan.values());

		this.planLabel.setText("Payment Plan: ");
		this.planLabel.setLabelFor(this.planBox);
		this.planPanel.setLayout(new FlowLayout());
		this.planPanel.add(this.planLabel);
		this.planPanel.add(this.planBox);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel panel2 = new JPanel();
		panel.setLayout(new FlowLayout());

		this.nxtBox = new JCheckBox("Invited to NXT?");
		this.sparringBBox = new JCheckBox("Invited to Sparring B?");
		this.compTeamBox = new JCheckBox("Invited to Comp Team?");

		panel.add(this.nxtBox);
		panel.add(this.sparringBBox);
		panel.add(this.compTeamBox);

		buttonPanel = new JPanel();
		cancelButton = new JButton("Cancel");
		enterButton = new JButton("Enter");
		this.buttonPanel.setLayout(new FlowLayout());
		this.buttonPanel.add(this.cancelButton);
		this.buttonPanel.add(this.enterButton);

		panel2.add(this.agePanel);
		panel2.add(this.rankPanel);
		panel2.add(this.planPanel);

		this.content.setLayout(new BoxLayout(this.content, BoxLayout.Y_AXIS));
		this.content.add(panel2);
		this.content.add(panel);
		this.content.add(buttonPanel);

		this.getContentPane().add(content, BorderLayout.CENTER);
		pack();

	}

	/**
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * @return the ageText value
	 */
	public String getAgeText() {
		return ageText.getText();
	}

	/**
	 * @return the rankBox value
	 */
	public Rank getRankBox() {
		return (Rank) rankBox.getSelectedItem();
	}

	/**
	 * @return the planBox value
	 */
	public Plan getPlanBox() {
		return (Plan) planBox.getSelectedItem();
	}

	/**
	 * @return the nxtBox value
	 */
	public boolean getNxtBox() {
		return nxtBox.isSelected();
	}

	/**
	 * @return the sparringBBox value
	 */
	public boolean getSparringBBox() {
		return sparringBBox.isSelected();
	}

	/**
	 * @return the compTeamBox value
	 */
	public boolean getCompTeamBox() {
		return compTeamBox.isSelected();
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
