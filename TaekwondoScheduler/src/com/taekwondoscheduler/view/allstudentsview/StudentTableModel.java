package com.taekwondoscheduler.view.allstudentsview;

import javax.swing.table.AbstractTableModel;

import com.taekwondoscheduler.model.business.StudentManager;
import com.taekwondoscheduler.model.domain.Student;
import com.taekwondoscheduler.model.enums.Plan;
import com.taekwondoscheduler.model.enums.Rank;

import java.util.ArrayList;

/**
 * The model for the JTable that shows students
 */
public class StudentTableModel extends AbstractTableModel {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 735433968421712903L;

	/**
	 * The names of the columns of the table
	 */
	private String[] columnNames = { "First Name", "Last Name", "Age", "Rank", "Invited to NXT",
			"Invited to Sparring B", "Invited to Comp Team", "Payment Plan" };

	/**
	 * Business layer Manager that handles Students
	 */
	StudentManager manager;

	/**
	 * The list of Students that fills the table
	 */
	private ArrayList<Student> students;

	/**
	 * The old version of the changed student;
	 */
	private Student oldStudent = null;

	/**
	 * The constructor for StudentTableModel
	 */
	public StudentTableModel() {

		manager = StudentManager.getInstance();
		students = manager.getStudents();
	}

	/**
	 * Refreshes the table after an update
	 */
	public void refreshStudents() {
		students = manager.getStudents();
	}

	/**
	 * Gets the old version of a changed Student
	 * 
	 * @return the old Student
	 */
	public Student getOldStudent() {
		return oldStudent;
	}

	/**
	 * Gets the row count for the table
	 */
	@Override
	public int getRowCount() {

		return students.size();
	}

	/**
	 * Gets the column count for the table
	 */
	@Override
	public int getColumnCount() {

		return columnNames.length;
	}

	/**
	 * Gets the column names for the table
	 */
	@Override
	public String getColumnName(int column) {

		return columnNames[column];
	}

	/**
	 * Gets a value for a cell in the table
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Object value = null;
		Student student = students.get(rowIndex);

		if (columnIndex == 0) {
			value = student.getAccount().getFirstName();
		} else if (columnIndex == 1) {
			value = student.getAccount().getLastName();
		} else if (columnIndex == 2) {
			value = student.getAge();
		} else if (columnIndex == 3) {
			value = student.getRank();
		} else if (columnIndex == 4) {
			value = student.isNxtInvited();
		} else if (columnIndex == 5) {
			value = student.isSparringBInvited();
		} else if (columnIndex == 6) {
			value = student.isCompTeamInvited();
		} else if (columnIndex == 7) {
			value = student.getPlan();
		}

		return value;
	}

	/**
	 * Allows cells to be editable
	 */
	@Override
	public boolean isCellEditable(int row, int col) {
		return true;
	}

	/**
	 * Sets a value at a certain cell
	 */
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {

		Student student = students.get(rowIndex);

		// Saves old Student for update button
		oldStudent = new Student(student.getAccount(), student.getAge(), student.getRank(), student.getPlan(),
				student.isNxtInvited(), student.isSparringBInvited(), student.isCompTeamInvited());

		if (columnIndex == 0) {
			student.getAccount().setFirstName((String) value);
		} else if (columnIndex == 1) {
			student.getAccount().setLastName((String) value);
		} else if (columnIndex == 2) {

			student.setAge((byte) Integer.parseInt((String) value));

		} else if (columnIndex == 3) {

			String val = (String) value;

			for (Rank rank : Rank.values()) {

				if (val.equals(rank.toString())) {

					student.setRank(rank);
				}

			}

		} else if (columnIndex == 4) {

			if (value.equals("true")) {
				student.setNxtInvited(true);
			} else if (value.equals("false")) {
				student.setNxtInvited(false);
			}

		} else if (columnIndex == 5) {

			if (value.equals("true")) {
				student.setSparringBInvited(true);
			} else if (value.equals("false")) {
				student.setSparringBInvited(false);
			}

		} else if (columnIndex == 6) {

			if (value.equals("true")) {
				student.setCompTeamInvited(true);
			} else if (value.equals("false")) {
				student.setCompTeamInvited(false);
			}

		} else if (columnIndex == 7) {

			String val = (String) value;

			for (Plan plan : Plan.values()) {

				if (val.equals(plan.toString())) {
					student.setPlan(plan);
				}

			}

		}

		// Updates cell
		this.fireTableCellUpdated(rowIndex, columnIndex);

	}

}
