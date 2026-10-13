package com.taekwondoscheduler.model.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.taekwondoscheduler.model.enums.ClassType;
import com.taekwondoscheduler.model.enums.Day;

class TaekwondoClassTest {

	@Test
	void testValidate() {
		
		//fields set with valid data combinations
		//taekwondoClass should validate
		TaekwondoClass taekwondoClass = new TaekwondoClass(Day.MONDAY, ClassType.BLACK_BELT);
		
		taekwondoClass.setIndex((byte) 0);
		
		assertTrue(taekwondoClass.validate(), "taekwondoClass validates");
		
		
		
		System.out.println("testValidate PASSED");
	}
	
	@Test
	void testNotValidate() {
		
		//Default Constructor initializes all fields to null
		//With all fields null, taekwondoClass should not validate
		TaekwondoClass taekwondoClass = new TaekwondoClass();
		
		assertFalse(taekwondoClass.validate(), "taekwondoClass does not validate");
		
		System.out.println("testNotValidate PASSED");
	}
	
	@Test
	void testInvalidCombination1() {
		
		//taekwondoClass has invalid day and type
		//time should not be set, so taekwondoClass should not validate
		//All valid combinations tested in ScheduleTest
		TaekwondoClass taekwondoClass = new TaekwondoClass(Day.MONDAY, ClassType.FAMILY);
		
		assertFalse(taekwondoClass.setTime(), "setTime fails");
		assertFalse(taekwondoClass.validate(), "taekwondoClass does not validate");
		
		System.out.println("testInvalidCombination1 PASSED");
	}
	
	@Test
	void testInvalidCombination2() {
		
		//taekwondoClass has invalid day and type
		//time should not be set, so taekwondoClass should not validate
		//All valid combinations tested in ScheduleTest
		TaekwondoClass taekwondoClass = new TaekwondoClass(Day.FRIDAY, ClassType.TEEN_ADULT);
		
		assertFalse(taekwondoClass.setTime(), "setTime fails");
		assertFalse(taekwondoClass.validate(), "taekwondoClass does not validate");
		
		System.out.println("testInvalidCombination2 PASSED");
	}
	
	@Test
	void testInvalidCombination3() {
		
		//taekwondoClass has invalid day and type
		//time should not be set, so taekwondoClass should not validate
		//All valid combinations tested in ScheduleTest
		TaekwondoClass taekwondoClass = new TaekwondoClass(Day.TUESDAY, ClassType.COMP_TEAM);
		
		assertFalse(taekwondoClass.setTime(), "setTime fails");
		assertFalse(taekwondoClass.validate(), "taekwondoClass does not validate");
		
		System.out.println("testInvalidCombination3 PASSED");
	}
	
	@Test
	void testSetTime() {
		
		//Time can only be set if day and type are not null
		//Day and type are not null and a valid combination
		//taekwondoClass should validate
		TaekwondoClass taekwondoClass = new TaekwondoClass();
		taekwondoClass.setDay(Day.MONDAY);
		taekwondoClass.setType(ClassType.BLACK_BELT);

		
		assertTrue(taekwondoClass.setTime(), "setTime succeeds");
		
		taekwondoClass.setLength();
		taekwondoClass.setClasslist(new ArrayList<String>());
		
		taekwondoClass.setIndex((byte) 0);
		assertTrue(taekwondoClass.validate(), "taekwondoClass validates");
		
		System.out.println("testSetTime PASSED");
	}
	
	@Test
	void testNotSetTime() {
		
		//Time can only be set if day and type are not null
		//Day and type are null 
		//taekwondoClass should not validate
		TaekwondoClass taekwondoClass = new TaekwondoClass();

		assertFalse(taekwondoClass.setTime(), "setTime dails");
		
		taekwondoClass.setLength();
		taekwondoClass.setClasslist(new ArrayList<String>());
		
		assertFalse(taekwondoClass.validate(), "taekwondoClass does not validate");
		
		System.out.println("testNotSetTime PASSED");
	}

	@Test
	void testSetLength() {
		
		//Length can only be set if type is not null
		//type is not null
		//taekwondoClass should validate
		TaekwondoClass taekwondoClass = new TaekwondoClass();
		taekwondoClass.setDay(Day.MONDAY);
		taekwondoClass.setType(ClassType.BLACK_BELT);
		taekwondoClass.setIndex((byte) 0);

		assertTrue(taekwondoClass.setLength(), "setLength succeeds");
		
		taekwondoClass.setTime();
		taekwondoClass.setClasslist(new ArrayList<String>());
		
		assertTrue(taekwondoClass.validate(), "taekwondoClass validates");
		
		System.out.println("testSetLength PASSED");
	}
	
	@Test
	void testNotSetLength() {
		
		//Length can only be set if type is not null
		//type is null
		//taekwondoClass should not validate
		TaekwondoClass taekwondoClass = new TaekwondoClass();

		assertFalse(taekwondoClass.setLength(), "setLength fails");
		
		taekwondoClass.setTime();
		taekwondoClass.setClasslist(new ArrayList<String>());
		
		assertFalse(taekwondoClass.validate(), "taekwondoClass does not validate");
		
		System.out.println("testNotSetLength PASSED");
	}
	
	@Test
	void testEquals() {
		
		//taekwondoClass1 and taekwondoClass2 have identical fields
		//taekwondoClass1 and taekwondoClass2 should be equal
		TaekwondoClass taekwondoClass1 = new TaekwondoClass();
		taekwondoClass1.setDay(Day.MONDAY);
		taekwondoClass1.setType(ClassType.BLACK_BELT);
		taekwondoClass1.setTime();
		taekwondoClass1.setLength();
		taekwondoClass1.setClasslist(new ArrayList<String>());
		
		TaekwondoClass taekwondoClass2 = new TaekwondoClass(Day.MONDAY, ClassType.BLACK_BELT);
		
		assertTrue(taekwondoClass1.equals(taekwondoClass2), "taekwondoClass1 equals taekwondoClass2");
		
		//Hash code should also be equal
		assertEquals(taekwondoClass1.hashCode(), taekwondoClass2.hashCode(), 
				"taekwondoClass1 hash code does not equal taekwondoClass2 hash code");
		
		System.out.println("testEquals PASSED");
	}
	
	@Test
	void testNotEquals() {
		
		//taekwondoClass1 and taekwondoClass2 have different fields
		//taekwondoClass1 and taekwondoClass2 should not be equal
		TaekwondoClass taekwondoClass1 = new TaekwondoClass();
		taekwondoClass1.setDay(Day.MONDAY);
		taekwondoClass1.setType(ClassType.BLACK_BELT);
		taekwondoClass1.setTime();
		taekwondoClass1.setLength();
		taekwondoClass1.setClasslist(new ArrayList<String>());
		
		TaekwondoClass taekwondoClass2 = new TaekwondoClass(Day.WEDNESDAY, ClassType.BLACK_BELT);
		
		assertFalse(taekwondoClass1.equals(taekwondoClass2), "taekwondoClass1 does not equal taekwondoClass2");
		
		//Hash code should also not be equal
		assertNotEquals(taekwondoClass1.hashCode(), taekwondoClass2.hashCode(), 
				"taekwondoClass1 hash code does not equal taekwondoClass2 hash code");
		
		System.out.println("testNotEquals PASSED");
	}
	
	@Test
	void testLength60() {
		
		//Comp Team is 60 minutes
		//taekwondoClass is set as Comp Team so should have length of 60
		TaekwondoClass taekwondoClass = new TaekwondoClass(Day.FRIDAY, ClassType.COMP_TEAM);
		
		assertEquals(taekwondoClass.getLength(), 60, "length is 60 minutes");
		
		System.out.println("testLength60 PASSED");
	}
	
	@Test
	void testLength45() {
		
		//Karate Kids is 45 minutes
		//taekwondoClass is set as Karate Kids so should have length of 45
		TaekwondoClass taekwondoClass = new TaekwondoClass(Day.THURSDAY, ClassType.KARATE_KIDS);
		
		assertEquals(taekwondoClass.getLength(), 45, "length is 45 minutes");
		
		System.out.println("testLength45 PASSED");
	}
	
	@Test
	void testLength30() {
		
		//Little Dragons is 30 minutes
		//taekwondoClass is set as Little Dragons so should have length of 30
		TaekwondoClass taekwondoClass = new TaekwondoClass(Day.THURSDAY, ClassType.LITTLE_DRAGONS);
		
		assertEquals(taekwondoClass.getLength(), 30, "length is 30 minutes");
		
		System.out.println("testLength30 PASSED");
	}
}
