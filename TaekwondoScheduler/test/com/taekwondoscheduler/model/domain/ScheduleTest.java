package com.taekwondoscheduler.model.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.taekwondoscheduler.model.enums.ClassType;
import com.taekwondoscheduler.model.enums.Plan;
import com.taekwondoscheduler.model.enums.Rank;

class ScheduleTest {

	@Test
	void testValidate() {
		
		//Schedule singleton can only have one instance that initializes fields with set valid data
		//Validate should always pass
		Schedule schedule = Schedule.getInstance();
		
		assertTrue(schedule.validate(), "schedule validates");
		System.out.println("testValidate PASSED");
	}
	
	@Test
	void testEquals() {
		
		//Schedule singleton can only have one instance
		//Two variable should point to same instance
		
		Schedule schedule1 = Schedule.getInstance();
		Schedule schedule2 = Schedule.getInstance();
		
		assertSame(schedule1, schedule2, "schedule1 == schedule2");
		System.out.println("testEquals PASSED");
	}
	
	@Test
	void testScheduleClasses() {
		
		//Each TaekwondoClass object in classes array are initialized with valid data
		//Each should validate
		Schedule schedule = Schedule.getInstance();
		
		TaekwondoClass[] classes = schedule.getClasses();
		
		for (TaekwondoClass taekwondoClass : classes) {
			
			assertTrue(taekwondoClass.validate(), "taekwondoClass is valid");
		}
		
		System.out.println("testEquals PASSED");
	}
	
	@Test
	void testStudentClassesVisible() {
		
		//student qualifies for Karate Kids class
		//Karate Kids should be visible
		Schedule schedule = Schedule.getInstance();
		
		Student student = new Student(new Account("Joke", "Frast", "username", "example@example.com", "password", false), (byte) 10, Rank.YELLOW, Plan.ONE_DAY,
				true, false, true);
		
		schedule.setStudentClasses(student);
		
		TaekwondoClass[] classes = schedule.getClasses();
		
		for (TaekwondoClass taekwondoClass : classes) {
			
			if (taekwondoClass.getType() == ClassType.KARATE_KIDS) {
				
				assertTrue(taekwondoClass.isVisible(), "Karate Kids is visible");
			}
		}
		
		System.out.println("testStudentClassesVisible PASSED");
		
		schedule.removeVisibility();
	}
	
	@Test
	void testStudentClassesNotVisible() {
		
		//student does not qualify for Little Dragons class
		//Little Dragons should not be visible
		Schedule schedule = Schedule.getInstance();
		
		Student student = new Student(new Account("Joke", "Frast", "username", "example@example.com", "password", false), (byte) 10, Rank.YELLOW, Plan.ONE_DAY,
				true, false, true);
		
		schedule.setStudentClasses(student);
		
		TaekwondoClass[] classes = schedule.getClasses();
		
		for (TaekwondoClass taekwondoClass : classes) {
			
			if (taekwondoClass.getType() == ClassType.LITTLE_DRAGONS) {
				
				assertFalse(taekwondoClass.isVisible(), "Little Dragons is not visible");
			}
		}
		
		schedule.removeVisibility();
		
		System.out.println("testStudentClassesNotVisible PASSED");
	}
	
	@Test
	void testGetInstance() {
		
		//Instance should load classes from file
		//So schedule should not be null
		Schedule schedule = Schedule.getInstance();
		
		assertNotNull(schedule.getClasses());
		
		System.out.println("testStudentGetInstance PASSED");
	}

}
