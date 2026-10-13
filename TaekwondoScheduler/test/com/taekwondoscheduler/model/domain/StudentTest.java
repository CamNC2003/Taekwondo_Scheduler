package com.taekwondoscheduler.model.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.taekwondoscheduler.model.enums.ClassType;
import com.taekwondoscheduler.model.enums.Plan;
import com.taekwondoscheduler.model.enums.Rank;


class StudentTest {

	
	Student student;

	@BeforeEach
	void setUp() throws Exception {

		// Initializes factory and student objects for tests
		student = new Student(new Account("Jack", "Frost", "username", "example@example.com", "password", false), (byte) 8,
				Rank.GREEN, Plan.TWO_DAY, false, true, true);
	}
	
	
	@Test
	void testValidate() {

		//Fields set with valid data
		//With all fields valid, student should validate

		assertTrue(student.validate(), "student validates");
		System.out.println("testValidate PASSED");
	}

	@Test
	void testNotValidate1() {

		//Default Constructor initializes all fields to null or -1
		//With all fields null, student should not validate 
		Student student = new Student();
		
		assertFalse(student.validate(), "student does not validate");
		System.out.println("testNotValidate1 PASSED");	
	}
	
	
	@Test
	void testNotValidate2() {
		
		//Account object fields set as empty string
		//Since account is not valid, student should not validate 
	
		Student student = new Student(new Account("", "", "", "", "", false), (byte) 8,
				Rank.GREEN, Plan.TWO_DAY, false, true, true);
		
		assertFalse(student.validate(), "student does not validate");
		System.out.println("testNotValidate2 PASSED");
	}
		
	@Test
	void testEquals() {
		
		//student1 and student2 have identical fields
		//student1 and student2 should be equal
		
		Student student2 = new Student(new Account("Jack", "Frost", "username", "example@example.com", "password", false), (byte) 8, Rank.GREEN, Plan.TWO_DAY,
				false, true, true);
		
		assertTrue(student.equals(student2), "student1 equals student2");
		
		//Hash code should also be equal
		assertEquals(student.hashCode(), student2.hashCode(), "student1 hash code equals student2 hash code");
		
		System.out.println("testEquals PASSED");
		
	}
	
	@Test
	void testNotEquals() {
		
		//student1 and student2 have different fields
		//student1 and student2 should not be equal
		Student student1 = new Student();

		Student student2 = new Student(new Account("Joke", "Frast", "username2", "example2@example.com", "password2", false), (byte) 10, Rank.YELLOW, Plan.ONE_DAY,
				true, false, true);
		
		assertFalse(student1.equals(student2), "student1 does not equal student2");
		
		//Hash code should also be equal
		assertNotEquals(student1.hashCode(), student2.hashCode(), "student1 hash code does not equal student2 hash code");
		
		System.out.println("testNotEquals PASSED");
		
	}
	
	@Test
	void testSetQualifedClasses() {
		
		//Qualified Classes can only be set if age, rank, and invites are not null
		//Since they are not null, setQualifiedClasses should return true
		//Student should validate
		Student student = new Student();
		
		student.setAccount(new Account("Jack", "Frost", "username", "example@example.com", "password", false));
		student.setAge((byte) 10);
		student.setRank(Rank.GREEN);
		student.setPlan(Plan.TWO_DAY);
		student.setNxtInvited(false);
		student.setSparringBInvited(true);
		student.setCompTeamInvited(true);
		
		assertTrue(student.setQualifiedClasses(), "setQualifiedClasses succeeds");
		assertTrue(student.validate(), "student validates");
		
		System.out.println("testSetQualifedClasses PASSED");
	}
	
	@Test
	void testNotSetQualifedClasses() {
		
		//Qualified Classes can only be set if age, rank, and invites are not null
		//Since they are still null from default constructor, setQualifiedClasses should return false
		//Student should not validate 
		Student student = new Student();
		
		student.setAccount(new Account("Jack", "Frost", "username", "example@example.com", "password", false));
		student.setPlan(Plan.TWO_DAY);
		
		assertFalse(student.setQualifiedClasses(), "setQualifiedClasses fails");
		assertFalse(student.validate(), "student does not validate");
		
		System.out.println("testNotSetQualifedClasses PASSED");
	}
	
	
	
	@Test
	void testQualifiedClassesKarateKids() {
		
		//Ages 8-11 qualify for Karate Kids
		//student is 10, so Karate Kids should be in the list of qualified classes
		Student student = new Student();
		
		student.setAccount(new Account("Jack", "Frost", "username", "example@example.com", "password", false));
		student.setAge((byte) 10);
		student.setRank(Rank.YELLOW);
		student.setPlan(Plan.TWO_DAY);
		student.setNxtInvited(false);
		student.setSparringBInvited(false);
		student.setCompTeamInvited(false);
		student.setQualifiedClasses();
		
		assertTrue(student.getQualifiedClasses().contains(ClassType.KARATE_KIDS), "student qualifies for Karate Kids");
		
		System.out.println("testQualifiedClassesKarateKids PASSED");
	}
	
	@Test
	void testQualifiedClassesLittleDragons() {
		
		//Ages 4-7 qualify for Little Dragons
		//student is 5, so Little Dragons should be in the list of qualified classes
		Student student = new Student();
		
		student.setAccount(new Account("Jack", "Frost", "username", "example@example.com", "password", false));
		student.setAge((byte) 5);
		student.setRank(Rank.YELLOW);
		student.setPlan(Plan.TWO_DAY);
		student.setNxtInvited(false);
		student.setSparringBInvited(false);
		student.setCompTeamInvited(false);
		student.setQualifiedClasses();
		
		assertTrue(student.getQualifiedClasses().contains(ClassType.LITTLE_DRAGONS), "student qualifies for Little Dragons");
		
		System.out.println("testQualifiedClassesLittleDragons PASSED");
	}
	
	@Test
	void testQualifiedClassesTeenAdult() {
		
		//Ages 12+ qualify for Teen/Adult
		//student is 13, so Teen/Adult should be in the list of qualified classes
		Student student = new Student();
		
		student.setAccount(new Account("Jack", "Frost", "username", "example@example.com", "password", false));
		student.setAge((byte) 13);
		student.setRank(Rank.YELLOW);
		student.setPlan(Plan.TWO_DAY);
		student.setNxtInvited(false);
		student.setSparringBInvited(false);
		student.setCompTeamInvited(false);
		student.setQualifiedClasses();
		
		assertTrue(student.getQualifiedClasses().contains(ClassType.TEEN_ADULT), "student qualifies for Teen/Adult");
		
		System.out.println("testQualifiedClassesTeenAdult PASSED");
	}
	
	@Test
	void testQualifiedClassesWeapons() {
		
		//Ages 8+ qualify for Weapons
		//student is 10, so Weapons should be in the list of qualified classes
		Student student = new Student();
		
		student.setAccount(new Account("Jack", "Frost", "username", "example@example.com", "password", false));
		student.setAge((byte) 10);
		student.setRank(Rank.YELLOW);
		student.setPlan(Plan.TWO_DAY);
		student.setNxtInvited(false);
		student.setSparringBInvited(false);
		student.setCompTeamInvited(false);
		student.setQualifiedClasses();
		
		assertTrue(student.getQualifiedClasses().contains(ClassType.WEAPONS), "student qualifies for Weapons");
		
		System.out.println("testQualifiedClassesWeapons PASSED");
	}
	
	@Test
	void testQualifiedClassesBlackBelt() {
		
		//Red-Black and Black Belts qualify for Black Belt class
		//student is Red-Black belt, so Black Belt should be in the list of qualified classes
		Student student = new Student();
		
		student.setAccount(new Account("Jack", "Frost", "username", "example@example.com", "password", false));
		student.setAge((byte) 5);
		student.setRank(Rank.RED_BLACK);
		student.setPlan(Plan.TWO_DAY);
		student.setNxtInvited(false);
		student.setSparringBInvited(false);
		student.setCompTeamInvited(false);
		student.setQualifiedClasses();
		
		assertTrue(student.getQualifiedClasses().contains(ClassType.BLACK_BELT), "student qualifies for Black Belt");
		
		System.out.println("testQualifiedClassesBlackBelt PASSED");
	}
	
	@Test
	void testQualifiedClassesNXT() {
		
		//Students invited qualify for NXT
		//student is invited, so NXT should be in the list of qualified classes
		Student student = new Student();
		
		student.setAccount(new Account("Jack", "Frost", "username", "example@example.com", "password", false));
		student.setAge((byte) 5);
		student.setRank(Rank.RED_BLACK);
		student.setPlan(Plan.TWO_DAY);
		student.setNxtInvited(true);
		student.setSparringBInvited(false);
		student.setCompTeamInvited(false);
		student.setQualifiedClasses();
		
		assertTrue(student.getQualifiedClasses().contains(ClassType.NXT), "student qualifies for NXT");
		
		System.out.println("testQualifiedClassesNXT PASSED");
	}
	
	@Test
	void testQualifiedClassesSparringA() {
		
		//Green belts and above qualify for Sparring A
		//student is green belt, so Sparring A should be in the list of qualified classes
		Student student = new Student();
		student.setAccount(new Account("Jack", "Frost", "username", "example@example.com", "password", false));
		student.setAge((byte) 10);
		student.setRank(Rank.GREEN);
		student.setPlan(Plan.TWO_DAY);
		student.setNxtInvited(false);
		student.setSparringBInvited(false);
		student.setCompTeamInvited(false);
		student.setQualifiedClasses();
				
		assertTrue(student.getQualifiedClasses().contains(ClassType.SPARRING_A), "student qualifies for Sparring A");
		
		System.out.println("testQualifiedClassesSparringA PASSED");
	}
	
	@Test
	void testQualifiedClassesSparringB() {
		
		//Students invited qualify for Sparring B
		//student is invited, so Sparring B should be in the list of qualified classes
		Student student = new Student();
		
		student.setAccount(new Account("Jack", "Frost", "username", "example@example.com", "password", false));
		student.setAge((byte) 5);
		student.setRank(Rank.RED_BLACK);
		student.setPlan(Plan.TWO_DAY);
		student.setNxtInvited(false);
		student.setSparringBInvited(true);
		student.setCompTeamInvited(false);
		student.setQualifiedClasses();
		
		assertTrue(student.getQualifiedClasses().contains(ClassType.SPARRING_B), "student qualifies for Sparring B");
		
		System.out.println("testQualifiedClassesSparringB PASSED");
	}
	
	@Test
	void testQualifiedClassesCompTeam() {
		
		//Students invited qualify for Comp Team
		//student is invited, so Comp Team should be in the list of qualified classes
		Student student = new Student();
		
		student.setAccount(new Account("Jack", "Frost", "username", "example@example.com", "password", false));
		student.setAge((byte) 5);
		student.setRank(Rank.RED_BLACK);
		student.setPlan(Plan.TWO_DAY);
		student.setNxtInvited(false);
		student.setSparringBInvited(false);
		student.setCompTeamInvited(true);
		student.setQualifiedClasses();
		
		assertTrue(student.getQualifiedClasses().contains(ClassType.COMP_TEAM), "student qualifies for Comp Team");
		
		System.out.println("testQualifiedClassesCompTea, PASSED");
	}
}
	


