package com.taekwondoscheduler.model.services.studentservice;

import java.util.ArrayList;

import com.taekwondoscheduler.model.domain.Student;
import com.taekwondoscheduler.model.services.IService;
import com.taekwondoscheduler.model.services.exceptions.StudentStorageException;

/**
 * Interface for service that handles Student storage
 */
public interface IStudentService extends IService {


	/**
	 * Service name for properties file
	 */
	public final String NAME = "IStudentService";
	
	/**
	 * Adds a Student to the list of Students in storage. Does not add if student already exists
	 * 
	 * @param student the Student to be added
	 * 
	 * @return true if successful, false if not
	 * 
	 * @throws StudentStorageException if anything unexpected happens
	 */
	public boolean addStudent(Student student) throws StudentStorageException;
	
	/**
	 * Updates a Student in the list of Students in storage.
	 * 
	 * @param oldStudent the original student to be updated
	 * @param newStudent the updated Student to replace the original
	 * 
	 * @return true if successful, false if not
	 * 
	 * @throws StudentStorageException if anything unexpected happens
	 */
	public boolean updateStudent(Student oldStudent, Student newStudent) throws StudentStorageException;
	
	/**
	 * Removes a Student from the list of Students in storage. Does not remove if the Student does not exist
	 * 
	 * @param student the Student to be removed
	 * 
	 * @return true if successful, false if not
	 * 
	 * @throws StudentStorageException if anything unexpected happens
	 */
	public boolean removeStudent(Student student) throws StudentStorageException;
	
	/**
	 * Gets the list of Students from storage
	 * 
	 * @return the list of Students
	 * 
	 * @throws StudentStorageException if anything unexpected happens
	 */
	public ArrayList<Student> getAllStudents() throws StudentStorageException;
}
