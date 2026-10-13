package com.taekwondoscheduler.model.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InstructorTest {

	Instructor instructor;
	
	@BeforeEach
	void setUp() throws Exception {

		instructor = new Instructor(new Account("Sean", "Mueller", "username", "example@example.com", "password", true));
	}
	
	
	
	@Test
	void testValidate() {
		
		//Fields set with valid data
		//With all fields valid, instructor should validate
	
		assertTrue(instructor.validate(), "instructor validates");
		System.out.println("testValidate PASSED");
	}
	
	@Test
	void testNotValidate1() {
		
		//Default Constructor initializes all fields to null
		//With all fields null, instructor should not validate 
		Instructor instructor = new Instructor();
		assertFalse(instructor.validate(), "instructor does not validate");
		System.out.println("testNotValidate1 PASSED");
	}
	
	@Test
	void testNotValidate2() {
		
		//All string fields set as empty string
		//Since there is no valid string data, instructor should not validate 
		Instructor instructor = new Instructor(new Account("", "", "", "", "", true));
		assertFalse(instructor.validate(), "instructor does not validate");
		System.out.println("testNotValidate2 PASSED");
	}
	
	
	@Test
	void testEquals() {
		
		//instructor1 and instructor2 have identical fields
		//instructor1 and instructor2 should be equal

		
		Instructor instructor2 = new Instructor(new Account("Sean", "Mueller", "username", "example@example.com", "password", true));
		
		assertTrue(instructor.equals(instructor2), "instructor1 equals instructor2");
		
		//Hash code should also be equal
		assertEquals(instructor.hashCode(), instructor2.hashCode(), "instructor1 hash code equals instructor2 hash code");
		System.out.println("testEquals PASSED");
	}
	
	@Test
	void testNotEquals() {
		
		//instructor1 and instructor2 have different fields
		//instructor1 and instructor2 should not be equal
		
		
		Instructor instructor2 = new Instructor(new Account("Cameron", "Christner", "username2", "example@example.com2", "password2", true));
		
		assertFalse(instructor.equals(instructor2), "instructor1 does not equal instructor2");
		
		//Hash code should also be not equal
		assertNotEquals(instructor.hashCode(), instructor2.hashCode(), "instructor1 hash code does not equal instructor2 hash code");
		System.out.println("testNotEquals PASSED");
	}
	

}
