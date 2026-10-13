package com.taekwondoscheduler.model.business;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.taekwondoscheduler.model.domain.Account;
import com.taekwondoscheduler.model.domain.Student;
import com.taekwondoscheduler.model.domain.TaekwondoClass;
import com.taekwondoscheduler.model.enums.Plan;
import com.taekwondoscheduler.model.enums.Rank;

class ScheduleManagerTest {

	ScheduleManager manager;
	
	@BeforeEach
	void setUp() throws Exception {
		
		manager = ScheduleManager.getInstance();
	}

	/**
	 * Tests the load Instructor Classes method
	 */
	@Test
	void testLoadInstructorClasses() {
		
		//All classes should be visible, so length of visible array should be equal to classes array
		
		manager.loadInstructorSchedule();
		
		ArrayList<TaekwondoClass> visibles = new ArrayList<>();
		
		for (TaekwondoClass tClass: manager.getSchedule().getClasses()) {
			
			if (tClass.isVisible()) {
				visibles.add(tClass);
			}
		}
		
		assertEquals(visibles.size(), manager.getSchedule().getClasses().length);
		
		manager.getSchedule().removeVisibility();
		
		System.out.println("testLoadInstructorClasses PASSED");
	}
	
	/**
	 * Tests the load Student Classes method
	 */
	@Test
	void testLoadStudentClasses() {
		
		//Only classes in qualified classes should be set to visible, if any are not, test fails
		Student student = new Student(new Account("Carl", "Sagan", "User", "abc@123.com", "pass", false), (byte) 13,
				Rank.BLACK, Plan.TWO_DAY, true, true, true);
		
		manager.loadStudentSchedule(student);
		
		boolean passed = true;
		
		for (TaekwondoClass tClass: manager.getSchedule().getClasses()) {
			
			if (student.getQualifiedClasses().contains(tClass.getType()) && !tClass.isVisible()) {
				passed = false;
			} else if (!student.getQualifiedClasses().contains(tClass.getType()) && tClass.isVisible()){
				passed = false;
			}
		}
		
		assertTrue(passed);
		
		manager.getSchedule().removeVisibility();
		
		System.out.println("testLoadStudentClasses PASSED");
	}
	
	/**
	 * Tests the set Attendance method under a success condition
	 */
	@Test
	void testSetAttendance() {
		
		//The size of the class list should change after method
		int classSize1 = manager.getSchedule().getClasses()[1].getClasslist().size();
		int classSize3 = manager.getSchedule().getClasses()[3].getClasslist().size();
		int classSize5 = manager.getSchedule().getClasses()[5].getClasslist().size();
		
		ArrayList<Byte> classes = new ArrayList<>();
		classes.add((byte) 1);
		classes.add((byte) 3);
		classes.add((byte) 5);
		
		//Change each test
		manager.setAttendance("Beril Joel");
		
		assertNotEquals(classSize1, manager.getSchedule().getClasses()[1].getClasslist().size(), "index 1");
		assertNotEquals(classSize3, manager.getSchedule().getClasses()[3].getClasslist().size(), "index 3");
		assertNotEquals(classSize5, manager.getSchedule().getClasses()[5].getClasslist().size(), "index 5");
		
		System.out.println("testSetAttendance PASSED");
	}
	

	/**
	 * Tests the set Attendance method under a failure condition
	 */
	@Test
	void testNotSetAttendance() {
		
		//The size of the class list should not change after method
		int classSize1 = manager.getSchedule().getClasses()[1].getClasslist().size();
		int classSize3 = manager.getSchedule().getClasses()[3].getClasslist().size();
		int classSize5 = manager.getSchedule().getClasses()[5].getClasslist().size();
		
		ArrayList<Byte> classes = new ArrayList<>();
		classes.add((byte) 1);
		classes.add((byte) 3);
		classes.add((byte) 5);
		
		manager.setAttendance("Billy Joel");
		
		assertEquals(classSize1, manager.getSchedule().getClasses()[1].getClasslist().size(), "index 1");
		assertEquals(classSize3, manager.getSchedule().getClasses()[3].getClasslist().size(), "index 3");
		assertEquals(classSize5, manager.getSchedule().getClasses()[5].getClasslist().size(), "index 5");
		
		System.out.println("testNotSetAttendance PASSED");
	}
	
	/**
	 * Tests the getAttendance method
	 */
	@Test
	void testGetAttendance() {
		
		//Class list should be the same
		assertEquals(manager.getAttendance((byte) 1), manager.getSchedule().getClasses()[1].getClasslist());
		System.out.println("testGetAttendance PASSED");
	}

}
