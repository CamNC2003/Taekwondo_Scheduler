package com.taekwondoscheduler.model.services.studentservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;

import com.taekwondoscheduler.model.domain.*;
import com.taekwondoscheduler.model.services.exceptions.StudentStorageException;

/**
 * Implementation for service that handles Student storage
 */
public class StudentServiceImpl implements IStudentService {

	/**
	 * Adds a Student to the list of Students in storage. Does not add if student
	 * already exists
	 * 
	 * @param student the Student to be added
	 * 
	 * @return true if successful, false if not
	 * 
	 * @throws StudentStorageException if anything unexpected happens
	 */
	@Override
	public boolean addStudent(Student student) throws StudentStorageException {

		boolean added = false;

		// Uses HashSet for efficiency
		HashSet<Student> students = this.getStudents(); // Gets list of students

		if (students == null) { // If null, new HashSet is created

			students = new HashSet<>();
		}

		if (student.validate() && students.isEmpty() || !students.contains(student)) {

			students.add(student);
			added = this.setStudents(students);

		}

		return added;
	}

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
	@Override
	public boolean updateStudent(Student oldStudent, Student newStudent) throws StudentStorageException {

		boolean updated = false;

		// Gets current list of Students
		HashSet<Student> students = this.getStudents();

		if (students != null) {
			
			
			if (students.contains(newStudent)) { // If, somehow, the updated student is in the list // list
				throw new StudentStorageException("updated Student already exists");
			}

			else if (students.contains(oldStudent) && newStudent.validate()) {
			
				students.remove(oldStudent);
				students.add(newStudent);
				updated = this.setStudents(students);
			}

			
		}

		return updated;
	}

	/**
	 * Removes a Student from the list of Students in storage. Does not remove if
	 * the Student does not exist
	 * 
	 * @param student the Student to be removed
	 * 
	 * @return true if successful, false if not
	 * 
	 * @throws StudentStorageException if anything unexpected happens
	 */
	@Override
	public boolean removeStudent(Student student) throws StudentStorageException {

		boolean removed = false;

		// Gets current list of Students
		HashSet<Student> students = this.getStudents();

		if (students != null) {
			if (!students.contains(student)) { // If student is not in list
				throw new StudentStorageException("Student does not exist");
			}

			students.remove(student);

			removed = this.setStudents(students); // Sets the new list of Students
		}

		return removed;
	}

	/**
	 * Gets the list of all Students from storage, with EOFException handling
	 * 
	 * @return the list of Students
	 * 
	 * @throws StudentStorageException if anything unexpected happens
	 */
	@Override
	public ArrayList<Student> getAllStudents() throws StudentStorageException {

		ArrayList<Student> students = null;
		
		if (this.getStudents() != null) {
			students = new ArrayList<>(this.getStudents());
		}

		return students;
	}

	/**
	 * Gets the list of all Students from storage, without EOFException handling
	 * 
	 * @return the list of Students
	 * 
	 * @throws StudentStorageException if anything unexpected happens
	 * @throws EOFException            if Students file is Empty
	 */
	@SuppressWarnings("unchecked")
	private HashSet<Student> getStudents() throws StudentStorageException {

		HashSet<Student> students = null;

		ObjectInputStream studentInput = null;

		try { // Reads object from file. Sets it to HashSet

			File file = new File("persistence/Students");

			if (file.length() != 0) { // If file is not empty
				studentInput = new ObjectInputStream(new FileInputStream(file));
				students = (HashSet<Student>) studentInput.readObject();
			}

		} catch (FileNotFoundException fnfe) {
			throw new StudentStorageException("Students file not found", fnfe);
		} catch (IOException ioe) {
			throw new StudentStorageException("Could not read Students file", ioe);
		} catch (ClassNotFoundException cnfe) {
			throw new StudentStorageException("Could not match object from Student file", cnfe);
		} finally {
			if (studentInput != null) {

				try {
					studentInput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return students;
	}

	/**
	 * Sets the list of Students in Storage
	 * 
	 * @param students the list of Students
	 * 
	 * @return true if successful, false if not
	 * 
	 * @throws StudentStorageException if anything unexpected happens
	 */
	private boolean setStudents(HashSet<Student> students) throws StudentStorageException {

		boolean successful = false;

		// Writes HashSet to file
		try (ObjectOutputStream studentOutput = new ObjectOutputStream(new FileOutputStream("persistence/Students"))) {

			studentOutput.writeObject(students);
			studentOutput.flush();

			successful = true;

		} catch (IOException ioe) {
			throw new StudentStorageException("Could not write to Students file", ioe);
		}

		return successful;
	}

}
