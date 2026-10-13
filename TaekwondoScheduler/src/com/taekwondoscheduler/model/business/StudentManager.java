package com.taekwondoscheduler.model.business;

import java.util.ArrayList;
import com.taekwondoscheduler.model.domain.Student;
import com.taekwondoscheduler.model.services.exceptions.ServiceException;
import com.taekwondoscheduler.model.services.exceptions.StudentStorageException;
import com.taekwondoscheduler.model.services.studentservice.IStudentService;

/**
 * A class that represents a Business Layer Manager that handles the student
 * related Use Cases
 */
public class StudentManager extends Manager {

	/**
	 * The Singleton instance of StudentManager
	 */
	private static StudentManager instance;

	/**
	 * The studentService instance to use service methods
	 */
	private IStudentService studentService;

	/**
	 * The list of students currently in the application
	 */
	private ArrayList<Student> students;

	/**
	 * Constructor for StudentManager. Initializes properties and fields.
	 */
	private StudentManager() {
		super();
		studentService = getStudentService();
		students = this.getStudentList();
	}

	/**
	 * Gets the Singleton instance of StudentManager
	 * 
	 * @return the instance of StudentManager
	 */
	public static StudentManager getInstance() {

		if (instance == null) { // If null, new instance is created
			instance = new StudentManager();
		}

		return instance;
	}

	/**
	 * Gets the list of students
	 * 
	 * @return the list of students
	 */
	public ArrayList<Student> getStudents() {

		return students;
	}

	/**
	 * Refreshes the list of students after a change in storage is made
	 */
	public void reloadStudents() {

		students = this.getStudentList();
	}

	/**
	 * Gets the list of students from the service layer
	 * 
	 * @return the list of students
	 */
	private ArrayList<Student> getStudentList() {

		ArrayList<Student> students = null;

		try { // Calls service method
			students = studentService.getAllStudents();
		} catch (StudentStorageException e) {
			System.out.println("Could not get Students");
		}

		return students;
	}

	/**
	 * Updates a student in the application
	 * 
	 * @param oldStudent the student getting updated
	 * @param newStudent the updated version of the student
	 * 
	 * @return true if successful, false if not
	 */
	public boolean updateStudent(Student oldStudent, Student newStudent) {

		boolean successful = false;

		try { // Calls service method
			successful = studentService.updateStudent(oldStudent, newStudent);
			this.reloadStudents();

		} catch (StudentStorageException sse) {
			System.out.println("Could not update Student");
		}

		return successful;
	}

	/**
	 * Removes a student from the application
	 * 
	 * @param student the student to be removed
	 * 
	 * @return true if successful, false if not
	 */
	public boolean removeStudent(Student student) {

		boolean successful = false;

		try { // Calls service method
			successful = studentService.removeStudent(student);
			this.reloadStudents();
			students.remove(student);

		} catch (StudentStorageException sse) {
			System.out.println("Could not remove Student");
		}

		return successful;
	}

	/**
	 * Gets the StudentService instance for all methods
	 * 
	 * @return the StudentService instance
	 */
	private IStudentService getStudentService() {

		IStudentService studentService = null;

		try {
			studentService = (IStudentService) factory.getService(IStudentService.NAME);

		} catch (ServiceException se) {
			System.out.println("Could not load Student Service");
		}
		return studentService;
	}

}
