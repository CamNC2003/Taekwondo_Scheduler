package com.taekwondoscheduler.view.scheduleview;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.taekwondoscheduler.model.domain.TaekwondoClass;
import com.taekwondoscheduler.model.enums.ClassType;

/**
 * A JPanel subclass that represents a Taekwondo Class
 */
public class TaekwondoClassPanel extends JPanel{

	
	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 8210348600122573088L;

	
	
	private JLabel classTypeLabel;
	private JLabel classTimeLabel;
	private JLabel lengthLabel;
	
	private JButton attendanceButton;
	
	/**
	 * The corresponding taekwondo class for this panel
	 */
	private TaekwondoClass tClass;
	
	/**
	 * The schedule frame that the panel is displayed in
	 */
	private ScheduleFrame frame;
	
	/**
	 * The constructor for Taekwondo class panel
	 * 
	 * @param tClass the corresponding taekwondo class for this panel
	 * @param scheduleFrame the schedule frame that the panel is displayed in
	 */
	public TaekwondoClassPanel(TaekwondoClass tClass, ScheduleFrame scheduleFrame) {
		
		if (tClass != null && scheduleFrame != null) {
			this.tClass = tClass;
			this.frame = scheduleFrame;
			
			initComponents();
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			
			this.setMaximumSize(new Dimension(115,80));
			this.setBorder(new LineBorder(Color.BLACK));
			
			new TaekwondoClassController(this, frame);
		}
				
	}
	
	/**
	 * Initializes Components
	 */
	private void initComponents() {
		
		this.classTypeLabel = new JLabel();
		this.classTypeLabel.setText(this.tClass.getType().toString().replace("_", " "));
		
		
		this.add(this.classTypeLabel);
		
		this.classTimeLabel = new JLabel();
		this.classTimeLabel.setText(this.tClass.getTime());
		
		
		this.add(this.classTimeLabel);
		
		this.lengthLabel = new JLabel();
		this.lengthLabel.setText((this.tClass.getLength() + " minutes"));
		

		
		this.add(this.lengthLabel);
		
		this.classTypeLabel.setForeground(Color.WHITE);
		this.classTimeLabel.setForeground(Color.WHITE);
		this.lengthLabel.setForeground(Color.WHITE);
		
		this.attendanceButton = new JButton("Attendance");

		
		this.add(this.attendanceButton);
		
		
		//Sets backgrounds colors based on web site schedule colors
		if (this.tClass.getType() == ClassType.KARATE_KIDS) {
			this.setBackground(new Color(75, 175, 235));
		} else if (this.tClass.getType() == ClassType.FAMILY) {
			this.setBackground(new Color(80, 172, 91));
		} else if (this.tClass.getType() == ClassType.LITTLE_DRAGONS) {
			this.setBackground(new Color(255, 254, 84));
			this.classTypeLabel.setForeground(Color.BLACK);
			this.classTimeLabel.setForeground(Color.BLACK);
			this.lengthLabel.setForeground(Color.BLACK);
			
		} else if (this.tClass.getType() == ClassType.BLACK_BELT) {
			this.setBackground(new Color(235, 52, 34));
		} else if (this.tClass.getType() == ClassType.TEEN_ADULT) {
			this.setBackground(new Color(175, 36, 24));
		} else if (this.tClass.getType() == ClassType.COMP_TEAM) {
			this.setBackground(new Color(5, 35, 93));
			
		} else {
			this.setBackground(new Color(246, 193, 65));
			this.setBackground(new Color(255, 254, 84));
			this.classTypeLabel.setForeground(Color.BLACK);
			this.classTimeLabel.setForeground(Color.BLACK);
			this.lengthLabel.setForeground(Color.BLACK);
		}
	}

	/**
	 * @return the tClass
	 */
	public byte getTClass() {
		return tClass.getIndex();
	}

	/**
	 * @return the attendanceButton
	 */
	public JButton getAttendanceButton() {
		return attendanceButton;
	}

	
}
