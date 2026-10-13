package com.taekwondoscheduler.view.allstudentsview;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import com.taekwondoscheduler.model.business.StudentManager;
import com.taekwondoscheduler.model.domain.Student;

/**
 * The JFrame subclass for the All Students View
 */
public class AllStudentsFrame extends JFrame {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -6565016199522243709L;

	private JLabel title;

	private JTable studentTable;

	private JScrollPane scroll;

	private JPanel buttonPanel;
	private JButton updateButton;
	private JButton removeButton;

	/**
	 * Table model used to display students
	 */
	private StudentTableModel model;

	/**
	 * The index of the selected row
	 */
	private int selectedRow = -1;

	/**
	 * The index of the selected column
	 */
	private int selectedColumn = -1;

	/**
	 * The student that is selected by a mouse click
	 */
	private Student selectedStudent;

	/**
	 * The student that is updated
	 */
	private Student changedStudent;

	/**
	 * Business layer Manager that handles students
	 */
	private StudentManager studentManager;

	/**
	 * Constructor for AllStudentsFrame
	 */
	public AllStudentsFrame() {

		model = new StudentTableModel();

		studentManager = StudentManager.getInstance();

		initComponents();

		this.setSize(700, 500);

		// Sets controller
		new AllStudentsController(this);

		this.setVisible(true);

	}

	/**
	 * Initializes components
	 */
	private void initComponents() {

		this.title = new JLabel();
		this.title.setText("All Students");
		this.title.setFont(new Font("Serif", Font.BOLD, 24));
		this.title.setHorizontalAlignment(SwingConstants.CENTER);

		this.getContentPane().add(title, BorderLayout.NORTH);

		if (this.studentManager.getStudents() != null && !this.studentManager.getStudents().isEmpty()) {
			studentTable = new JTable();
			studentTable.setModel(model);
			model.fireTableDataChanged();

			studentTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

			studentTable.setColumnSelectionAllowed(true);
			studentTable.setRowSelectionAllowed(true);

			scroll = new JScrollPane();

			scroll.setViewportView(studentTable);

			this.getContentPane().add(scroll, BorderLayout.CENTER);

			buttonPanel = new JPanel();
			buttonPanel.setLayout(new FlowLayout());

			updateButton = new JButton("Update Student");
			removeButton = new JButton("Remove Student");

			buttonPanel.add(updateButton);
			buttonPanel.add(removeButton);

			this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

			// Listener gets selected Student based on mouse click coordinates
			studentTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {

					selectedRow = studentTable.rowAtPoint(event.getPoint());
					selectedColumn = studentTable.columnAtPoint(event.getPoint());
					if (selectedRow >= 0 && selectedColumn >= 0) {
						selectedStudent = studentManager.getStudents().get(selectedRow);
					}
				}
			});

			// Listener gets changed student based on changed attribute
			studentTable.addPropertyChangeListener(new PropertyChangeListener() {

				@Override
				public void propertyChange(PropertyChangeEvent evt) {

					if (selectedRow >= 0 && selectedColumn >= 0) {

						changedStudent = studentManager.getStudents().get(selectedRow);
					}
				}

			});

		} else {
			JLabel label = new JLabel();
			label.setText("Sign up some Students!");
			label.setFont(new Font("Serif", Font.PLAIN, 24));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			this.getContentPane().add(label, BorderLayout.CENTER);
		}

		this.pack();
		this.setLocationRelativeTo(null);

	}

	/**
	 * @return the updateButton
	 */
	public JButton getUpdateButton() {
		return updateButton;
	}

	/**
	 * @return the removeButton
	 */
	public JButton getRemoveButton() {
		return removeButton;
	}

	/**
	 * @return the selectedStudent
	 */
	public Student getSelectedStudent() {
		return selectedStudent;
	}

	/**
	 * @return the changedStudent
	 */
	public Student getChangedStudent() {
		return changedStudent;
	}

	/**
	 * Gets the old Student for updates
	 * 
	 * @return the old Student
	 */
	public Student getOldStudent() {

		return model.getOldStudent();
	}

	/**
	 * Refreshes the table
	 */
	public void refreshTable() {

		this.model.refreshStudents();
		this.model.fireTableDataChanged();
	}
}
