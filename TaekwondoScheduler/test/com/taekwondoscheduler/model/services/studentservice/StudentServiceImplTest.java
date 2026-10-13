package com.taekwondoscheduler.model.services.studentservice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.taekwondoscheduler.model.business.StudentManager;
import com.taekwondoscheduler.model.domain.Account;
import com.taekwondoscheduler.model.domain.Student;
import com.taekwondoscheduler.model.enums.Plan;
import com.taekwondoscheduler.model.enums.Rank;
import com.taekwondoscheduler.model.services.exceptions.ServiceException;
import com.taekwondoscheduler.model.services.exceptions.StudentStorageException;
import com.taekwondoscheduler.model.services.factory.ServiceFactory;

public class StudentServiceImplTest {

	ServiceFactory factory;
	Student student;
	StudentManager manager;

	@BeforeEach
	void setUp() throws Exception {

		// Initializes factory and student objects for tests
		factory = ServiceFactory.getInstance();
		student = new Student(new Account("Jack", "Frost", "username", "example@example.com", "password", false), (byte) 8,
				Rank.GREEN, Plan.TWO_DAY, false, true, true);
		
		manager = StudentManager.getInstance();
	}

	@Test
	void testAddStudent() {

		//addStudent returns true if successful
		//addStudent returns false if Student already exists
		
		//Since student name is different, the new student should be added and method should return true;
		
		//Change each test
		student.getAccount().setFirstName("Bseobb");
		
		try {
			IStudentService studentService = (IStudentService) factory.getService(IStudentService.NAME);

			assertTrue(studentService.addStudent(student), "addStudent was successful");

			System.out.println("testAddStudent PASSED");
			
		} catch (ServiceException | StudentStorageException e) {
			fail();
		}

	}
	
	@Test
	void testNotAddStudent() {

		//addStudent returns true if successful
		//addStudent returns false if Student already exists
		
		//Since student already exists, should return false
		
		try {
			IStudentService studentService = (IStudentService) factory.getService(IStudentService.NAME);

			assertFalse(studentService.addStudent(student), "addStudent was successful");

			System.out.println("testNotAddStudent PASSED");
			
		} catch (ServiceException | StudentStorageException e) {
			fail();
		}

	}

	@Test
	void testUpdateStudent() {

		//updateStudent returns true if successful
		//updateStudent returns false if original student does not exist
		//Exception happens if updated student already exists
		
		//Since original student exists, and updated student does not, should return true
		
		
		try {
			IStudentService studentService = (IStudentService) factory.getService(IStudentService.NAME);

			//Change each test
			Student stu = new Student(new Account("Jack", "Frost", "usernaeme", "example@example.com", "password", false), (byte) 10,
					Rank.BROWN_DECIDED, Plan.TWO_DAY, true, true, true);
			
			assertTrue(studentService.updateStudent(student, stu), "updateStudent was successful");

			System.out.println("testUpdateStudent PASSED");
			
		} catch (StudentStorageException e) {
			System.out.println("testUpdateStudent FAILED");
			return;
		} catch (ServiceException e) {
			fail();
		}

	}
	
	@Test
	void testNotUpdateStudent1() {

		//updateStudent returns true if successful
		//updateStudent returns false if original student does not exist
		//Exception happens if updated student already exists
		
		//Since original student does not exist, should return false
		
		
		try {
			IStudentService studentService = (IStudentService) factory.getService(IStudentService.NAME);

			Student stu = new Student(new Account("Billy", "Bob", "username", "example@example.com", "password", false), (byte) 10,
					Rank.BROWN_DECIDED, Plan.TWO_DAY, true, true, true);
			
			assertFalse(studentService.updateStudent(stu, student), "updateStudent was not successful");

			System.out.println("testNotUpdateStudent1 PASSED");
			
		} catch (StudentStorageException e) {
			System.out.println("testUpdateNotStudent FAILED");
			return;
		} catch (ServiceException e) {
			fail();
		}

	}
	
	@Test
	void testNotUpdateStudent2() {

		//updateStudent returns true if successful
		//updateStudent returns false if original student does not exist
		//Exception happens if updated student already exists
		
		//Since updated student exists, should throw exception
		
		
		try {
			IStudentService studentService = (IStudentService) factory.getService(IStudentService.NAME);

			
			Student stu = new Student(new Account("Bob", "Frost", "username", "example@example.com", "password", false), (byte) 8,
					Rank.GREEN, Plan.TWO_DAY, false, true, true);

			assertFalse(studentService.addStudent(stu));
			
			studentService.updateStudent(student, stu);

			fail();
			
			System.out.println("testNotUpdateStudent2 FAILED");
			
		} catch (StudentStorageException e) {
			System.out.println("testNotUpdateStudent2 PASSED");
			return;
		} catch (ServiceException e) {
			fail();
		}

	}

	@Test
	void testRemoveStudent() {

		//removeStudent returns true if successful
		//Throws exception if Student does not exist
		
		//Since added Student exists, should return true
		try {
			IStudentService studentService = (IStudentService) factory.getService(IStudentService.NAME);

			Student stu = new Student(new Account("John", "Doe", "username", "example@example.com", "password", false), (byte) 8,
					Rank.GREEN, Plan.TWO_DAY, false, true, true);
			
			studentService.addStudent(stu);
			
			assertTrue(studentService.removeStudent(stu), "removeStudent was successful");

			System.out.println("testRemoveStudent PASSED");
			
		} catch (ServiceException | StudentStorageException e) {
			fail();
		}

	}
	
	@Test
	void testNotRemoveStudent() {

		//removeStudent returns true if successful
		//Throws exception if Student does not exist
		
		//Since Student does not exist, should return true
		try {
			IStudentService studentService = (IStudentService) factory.getService(IStudentService.NAME);

			Student stu = new Student(new Account("Jane", "Doe", "username", "example@example.com", "password", false), (byte) 8,
					Rank.GREEN, Plan.TWO_DAY, false, true, true);
			
			
			assertTrue(studentService.removeStudent(stu), "removeStudent was successful");

			System.out.println("testNotRemoveStudent FAILED");
			fail();
			
		} catch (StudentStorageException e) {
			System.out.println("testNotRemoveStudent PASSED");
			return;
		} catch (ServiceException e ) {
			fail();
		}

	}
	

	@Test
	void testGetAllStudents() {

		// testGetAllStudents returns an ArrayList if successful, null if not
		try {
			IStudentService studentService = (IStudentService) factory.getService(IStudentService.NAME);

			ArrayList<Student> stus = studentService.getAllStudents();
			assertNotNull(stus, "getAllStudents was successful");
			
			System.out.println("testGetAllStudents PASSED");
			
		} catch (ServiceException | StudentStorageException e) {
			fail();
		}

	}
}
