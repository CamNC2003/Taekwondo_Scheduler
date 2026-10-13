package com.taekwondoscheduler.model.business;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.taekwondoscheduler.model.domain.Account;
import com.taekwondoscheduler.model.domain.Student;
import com.taekwondoscheduler.model.enums.Plan;
import com.taekwondoscheduler.model.enums.Rank;

class StudentManagerTest {

	StudentManager manager;
	Student student;

	@BeforeEach
	void setUp() throws Exception {

		manager = StudentManager.getInstance();
		student = new Student(new Account("Carl", "Sagan", "User", "abc@123.com", "pass", false), (byte) 13, Rank.BLACK,
				Plan.TWO_DAY, true, true, true);
	}

	/**
	 * Tests the update student method under a success condition
	 */
	@Test
	void testUpdateStudent() {

		// Since student exists and stu does not exist, method should return true
		Student stu1 = new Student(new Account("Carll", "Sagan", "User", "abc@123.com", "pass", false), (byte) 13,
				Rank.BLACK, Plan.UNLIMITED, false, true, true);

		// Change each test
		Student stu2 = new Student(new Account("Carll", "Sageadn", "User", "abc@123.com", "pass", false), (byte) 13,
				Rank.BLACK, Plan.UNLIMITED, false, true, true);

		assertTrue(manager.updateStudent(stu1, stu2));

		System.out.println("testUpdateStudent PASSED");

	}

	/**
	 * Tests the update student method under a failure condition because the old
	 * student does not exist
	 */
	@Test
	void testNotUpdateStudent1() {

		student.getAccount().setFirstName("Joel");
		// Since student does not exist, method should return false
		Student stu = new Student(new Account("Carl", "Sagan", "User", "abc@123.com", "pass", false), (byte) 13,
				Rank.BLACK, Plan.UNLIMITED, true, true, true);

		assertFalse(manager.updateStudent(student, stu));

		System.out.println("testNotUpdateStudent1 PASSED");

	}

	/**
	 * Tests the update student method under a failure condition because the new
	 * student already exists
	 */
	@Test
	void testNotUpdateStudent2() {

		student.getAccount().setFirstName("Joe");
		// Since stu exists, method should return false
		Student stu = new Student(new Account("Carl", "Sagan", "User", "abc@123.com", "pass", false), (byte) 13,
				Rank.BLACK, Plan.UNLIMITED, true, true, true);

		assertFalse(manager.updateStudent(student, stu));

		System.out.println("testNotUpdateStudent2 PASSED");

	}

	/**
	 * Tests the remove student method under a success condition
	 */
	@Test
	void testRemoveStudent() {

		// Since student exists, method should return true
		Student stu = new Student(new Account("Lolly", "Sue", "Use", "abcd@123.com", "pass", false), (byte) 13,
				Rank.BLACK, Plan.UNLIMITED, true, true, true);

		assertTrue(manager.removeStudent(stu));

		System.out.println("testRemoveStudent PASSED");

	}

	/**
	 * Tests the remove student method under a failure condition
	 */
	@Test
	void testNotRemoveStudent() {

		// Since student does not exist, method should return false
		Student stu = new Student(new Account("Lol", "Sue", "User", "abc@123.com", "pass", false), (byte) 13,
				Rank.BLACK, Plan.UNLIMITED, true, true, true);

		assertFalse(manager.removeStudent(stu));

		System.out.println("testRemoveStudent PASSED");

	}

	

	/**
	 * Tests the get Students method
	 */
	@Test
	void testGetStudents() {

		// Students field should not be null
		assertNotNull(manager.getStudents());

		System.out.println("testGetStudents PASSED");

	}
	
	

}
