package com.taekwondoscheduler.model.business;

import java.util.ArrayList;

import com.taekwondoscheduler.model.domain.*;
import com.taekwondoscheduler.model.enums.ClassType;
import com.taekwondoscheduler.model.enums.Day;
import com.taekwondoscheduler.model.services.classlistservice.IClassListService;
import com.taekwondoscheduler.model.services.exceptions.*;

/**
 * A class that represents a Business Layer Manager that handles the schedule
 * Use Cases
 */
public class ScheduleManager extends Manager {

	/**
	 * The Singleton instance of ScheduleManager
	 */
	static ScheduleManager instance;

	/**
	 * The schedule instance that holds the class schedule
	 */
	private Schedule schedule;

	/**
	 * The ClassListService instance to use service methods
	 */
	private IClassListService classListService;

	private ArrayList<Byte> classIndexes;

	private byte classTotal;

	/**
	 * Constructor for ScheduleManager. Initializes properties and fields.
	 */
	private ScheduleManager() {

		super();
		schedule = Schedule.getInstance();
		classListService = getClassListService();
		classIndexes = new ArrayList<>();
		classTotal = 0;
	}

	/**
	 * Gets Singleton instance of ScheduleManager
	 * 
	 * @return the instance of ScheduleManager
	 */
	public static ScheduleManager getInstance() {

		if (instance == null) { // if null, new instance is created
			instance = new ScheduleManager();
		}

		return instance;
	}

	/**
	 * Gets the schedule instance
	 * 
	 * @return the schedule instance
	 */
	public Schedule getSchedule() {
		return schedule;
	}

	/**
	 * Sets all classes in the schedule to visible
	 */
	public void loadInstructorSchedule() {

		this.schedule.setInstructorClasses();

	}

	/**
	 * Sets the visibility of classes in the schedule based on the Student's
	 * qualified classes
	 * 
	 * @param student the Student whose qualified classes on which the visibilities
	 *                are based
	 */
	public void loadStudentSchedule(Student student) {

		this.schedule.setStudentClasses(student);
	}
	

	/**
	 * Resets the visibility of all classes
	 */
	public void clearSchedule() {
		this.schedule.removeVisibility();
	}

	/**
	 * Sets the attendance of a Student for any number of classes based on their
	 * payment plan. If the name is already marked for attendance, it is not marked
	 * again.
	 * 
	 * @param indexes the list of indexes that correspond to the classes on which
	 *                the Student marked their attendance
	 * @param name    the name of the Student
	 * 
	 * @return true if successful, false if not
	 */
	public boolean setAttendance(String name) {

		boolean successful = false;

		if (classIndexes.isEmpty()) {
			successful = true;
		}

		for (byte index : classIndexes) { // If name already is in classList

			if (!schedule.getClasses()[index].getClasslist().contains(name)) {

				schedule.getClasses()[index].getClasslist().add(name);

				try { // Calls service method
					successful = classListService.setClassList(schedule.getClasses());
				} catch (ClassListStorageException e) {
					System.out.println("Could not set classes in storage");
				}
			}

		}

		return successful;
	}

	/**
	 * Gets the attendance of a class that an instructor selected
	 * 
	 * @param index the corresponding index of the class that the instructor
	 *              selected
	 * 
	 * @return the attendance class list
	 */
	public ArrayList<String> getAttendance(byte index) {

		return schedule.getClasses()[index].getClasslist();
	}

	/**
	 * Checks if a class is in the list of classes for which attendance will be
	 * marked
	 * 
	 * @param index the class to be checked
	 * 
	 * @return true if the class is in the list, false if not
	 */
	public boolean checkClassAttendance(byte index) {

		boolean contains = false;

		if (this.classIndexes.contains(index)) {

			contains = true;

		}

		return contains;

	}

	/**
	 * Adds or removes a class to/from the list of classes for which attendance will
	 * be marked. classTotal depends on type of class
	 * 
	 * @param index the class to be added to the list
	 * 
	 * @return the new class total
	 */
	public byte changeClassAttendance(byte index) {

		if (this.classIndexes.contains(index)) {

			int remove = -1;
			boolean flag = false;

			// If class is already in the list, the class is removed
			for (int i = 0; i < classIndexes.size(); i++) {

				if (classIndexes.get(i).equals(index)) {
					remove = i;
					flag = true;
				}

				if (flag) {
					classIndexes.remove(remove);
				}

			}

			if (this.changeTotal(index)) {
				classTotal--; // Total changed only if it is a regular class
			}

		} else { // Otherwise class is added

			classIndexes.add(index);

			if (this.changeTotal(index)) {
				classTotal++; // Total changed only if it is a regular class
			}
		}

		return classTotal;

	}

	/**
	 * Adds or removes a class to/from the list of classes for which attendance will
	 * be marked. Always changes classTotal.
	 * 
	 * @param index the class to be added to the list
	 * 
	 * @return the new class total
	 */
	public byte changeClassAttendanceUnlimited(byte index) {

		if (this.classIndexes.contains(index)) {

			int remove = -1;
			boolean flag = false;

			// If class is already in the list, the class is removed
			for (int i = 0; i < classIndexes.size(); i++) {

				if (classIndexes.get(i).equals(index)) {
					remove = i;
					flag = true;
				}

				if (flag) {
					classIndexes.remove(remove);
				}

			}

			classTotal--;

		} else { // Otherwise class is added

			classIndexes.add(index);

			classTotal++;
		}

		return classTotal;

	}

	/**
	 * Checks if the class total should be changed
	 * 
	 * @param index the corresponding index of the class
	 * 
	 * @return true if class total needs to change, false if not
	 */
	private boolean changeTotal(byte index) {

		boolean change = true;

		// If weapons class, NXT class, or any Friday class, total does not change
		if (this.schedule.getClasses()[index].getType() == ClassType.WEAPONS
				|| this.schedule.getClasses()[index].getType() == ClassType.NXT
				|| this.schedule.getClasses()[index].getDay() == Day.FRIDAY) {
			change = false;
		}

		return change;
	}

	/**
	 * Resets the list of classes and the classTotal
	 */
	public void resetClasses() {

		this.classIndexes.clear();
		this.classTotal = 0;
	}

	/**
	 * Gets the ClassListService for all methods
	 * 
	 * @return the ClassListService instance
	 */
	private IClassListService getClassListService() {

		IClassListService classListService = null;

		try {
			classListService = (IClassListService) factory.getService(IClassListService.NAME);

		} catch (ServiceException se) {
			System.out.println("Could not load Student Service");
		}

		return classListService;
	}

}
