package com.taekwondoscheduler.model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import com.taekwondoscheduler.model.enums.*;
import com.taekwondoscheduler.model.services.classlistservice.IClassListService;
import com.taekwondoscheduler.model.services.exceptions.ClassListStorageException;
import com.taekwondoscheduler.model.services.exceptions.ServiceException;
import com.taekwondoscheduler.model.services.factory.ServiceFactory;

/**
 * This class represents the schedule of Mueller's Martial Arts Uses Singleton
 * pattern so that only one instance can exist
 */
public class Schedule implements Serializable {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 1851410889363687903L;

	/**
	 * The Singleton instance of schedule
	 */
	private static Schedule instance;

	/**
	 * Array that contains the class schedule for Mueller's Martial Arts
	 */
	private TaekwondoClass[] CLASSES;

	/**
	 * The total number of classes in the class schedule
	 */
	private final byte NUM_CLASSES = 19;

	/**
	 * Constructor for Schedule Loads the CLASSES array from with the class schedule
	 * from storage Based on the schedule at
	 * https://muellersmartialarts.com/class-schedule/
	 */
	private Schedule() {

		ServiceFactory factory = ServiceFactory.getInstance();

		try { // Gets saved classes with class lists from storage
			IClassListService classListService = (IClassListService) factory.getService(IClassListService.NAME);
			CLASSES = classListService.getClassList();

		} catch (ServiceException | ClassListStorageException e) {

			// If something happens, such as the file being empty,
			// Classes are created from scratch

			CLASSES = new TaekwondoClass[NUM_CLASSES];

			CLASSES[0] = new TaekwondoClass(Day.MONDAY, ClassType.KARATE_KIDS);
			CLASSES[1] = new TaekwondoClass(Day.MONDAY, ClassType.WEAPONS);
			CLASSES[2] = new TaekwondoClass(Day.MONDAY, ClassType.BLACK_BELT);
			CLASSES[3] = new TaekwondoClass(Day.TUESDAY, ClassType.FAMILY);
			CLASSES[4] = new TaekwondoClass(Day.TUESDAY, ClassType.LITTLE_DRAGONS);
			CLASSES[5] = new TaekwondoClass(Day.TUESDAY, ClassType.KARATE_KIDS);
			CLASSES[6] = new TaekwondoClass(Day.TUESDAY, ClassType.TEEN_ADULT);
			CLASSES[7] = new TaekwondoClass(Day.WEDNESDAY, ClassType.LITTLE_DRAGONS);
			CLASSES[8] = new TaekwondoClass(Day.WEDNESDAY, ClassType.KARATE_KIDS);
			CLASSES[9] = new TaekwondoClass(Day.WEDNESDAY, ClassType.NXT);
			CLASSES[10] = new TaekwondoClass(Day.WEDNESDAY, ClassType.BLACK_BELT);
			CLASSES[11] = new TaekwondoClass(Day.THURSDAY, ClassType.FAMILY);
			CLASSES[12] = new TaekwondoClass(Day.THURSDAY, ClassType.LITTLE_DRAGONS);
			CLASSES[13] = new TaekwondoClass(Day.THURSDAY, ClassType.KARATE_KIDS);
			CLASSES[14] = new TaekwondoClass(Day.THURSDAY, ClassType.TEEN_ADULT);
			CLASSES[15] = new TaekwondoClass(Day.FRIDAY, ClassType.FAMILY);
			CLASSES[16] = new TaekwondoClass(Day.FRIDAY, ClassType.SPARRING_A);
			CLASSES[17] = new TaekwondoClass(Day.FRIDAY, ClassType.SPARRING_B);
			CLASSES[18] = new TaekwondoClass(Day.FRIDAY, ClassType.COMP_TEAM);
			
			for (byte i = 0; i < CLASSES.length; i++) {
				
				CLASSES[i].setIndex(i);
			}
			
			
		}
	}

	/**
	 * Gets the singular instance of the Schedule class
	 * 
	 * @return the Schedule instance object
	 */
	public static Schedule getInstance() {

		if (instance == null) {
			instance = new Schedule();
		}

		return instance;
	}

	/**
	 * Gets the CLASSES array of the Schedule
	 * 
	 * @return the CLASSES array
	 */
	public TaekwondoClass[] getClasses() {
		return CLASSES;
	}

	/**
	 * Sets the visibility of the classes based on the Student's qualified classes
	 * 
	 * @param student the Student who will view the schedule
	 */
	public void setStudentClasses(Student student) {

		ArrayList<ClassType> qualifiedClasses = student.getQualifiedClasses();

		// Schedule classes are visible only if they are in the list of the student's
		// qualified classes
		for (TaekwondoClass tClass : CLASSES) {

			if (qualifiedClasses.contains(tClass.getType())) {

				tClass.setVisible(true);
			}
		}
	}

	/**
	 * Sets all classes to visible for instructor use
	 */
	public void setInstructorClasses() {

		// classes are initially invisible
		for (TaekwondoClass tClass : CLASSES) {
			tClass.setVisible(true);
		}
	}

	/**
	 * Removes the visibility for all classes
	 */
	public void removeVisibility() {

		for (TaekwondoClass tClass : CLASSES) {
			tClass.setVisible(false);
		}
	}

	// No hash code or equals methods because there can only be one instance

	/**
	 * Generates a String representation of the Schedule instance
	 */
	@Override
	public String toString() {
		return "Schedule [CLASSES=" + Arrays.toString(CLASSES) + ", NUM_CLASSES=" + NUM_CLASSES + "]";
	}

	/**
	 * Checks if every field has valid data values nulls are invalid
	 * 
	 * @return true if data is valid, false if not
	 */
	public boolean validate() { // Should always return true

		boolean valid = true; // assume valid

		if (this.CLASSES == null) {
			valid = false;
		}

		return valid;
	}

}
