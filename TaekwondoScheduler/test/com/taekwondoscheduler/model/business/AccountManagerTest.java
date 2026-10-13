package com.taekwondoscheduler.model.business;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.taekwondoscheduler.model.domain.Account;
import com.taekwondoscheduler.model.domain.Student;
import com.taekwondoscheduler.model.enums.Plan;
import com.taekwondoscheduler.model.enums.Rank;

class AccountManagerTest {

	AccountManager manager;
	Account account;

	@BeforeEach
	void setUp() throws Exception {

		manager = AccountManager.getInstance();
		account = new Account("Carl", "Sagan", "User", "abc@123.com", "pass", false);
	}

	/**
	 * Tests the sign in method under a success condition
	 */
	@Test
	void testSignUp() {

		// Since the account is new, the method should return true

		// Change each test
		account.setUsername("user2");

		assertTrue(manager.signUp(account), "sign up was successful");

		System.out.println("testSignUp PASSED");
	}

	/**
	 * Tests the sign in method under a failure condition
	 */
	@Test
	void testNotSignUp() {

		// Since the account already exists, the method should return false

		assertFalse(manager.signUp(account), "sign up was unsuccessful");

		System.out.println("testNotSignUp PASSED");
	}

	/**
	 * Tests the sign in method under a success condition
	 */
	@Test
	void testSignIn() {

		// Since the credentials are valid, the method should not return null

		assertNotNull(manager.signIn("User", "pass"), "sign in was successful");

		System.out.println("testSignIn PASSED");
	}

	/**
	 * Tests the sign in method under a failure condition
	 */
	@Test
	void testNotSignIn() {

		// Since the credentials are invalid, the method should return null

		assertNull(manager.signIn("username", "pass"), "sign in was unsuccessful");

		System.out.println("testNotSignIn PASSED");
	}

	/**
	 * Tests the add Student method under a success condition
	 */
	@Test
	void testAddStudent() {

		// Change each test
		account.setUsername("user2");
		
		// Since student does not exist, the method should return true
		Student student = new Student(account, (byte) 5, Rank.WHITE, Plan.ONE_DAY, false, false, false);

		assertTrue(manager.addStudent(student));
		System.out.println("testAddStudent PASSED");

	}

	/**
	 * Tests the add Student method under a failure condition
	 */
	@Test
	void testNotAddStudent() {
		// Since student exists, the method should return false
		Student student = new Student(account, (byte) 5, Rank.WHITE, Plan.ONE_DAY, false, false, false);

		assertFalse(manager.addStudent(student));
		System.out.println("testNotAddStudent PASSED");
	}

	/**
	 * Tests the get Student method under a success condition
	 */
	@Test
	void testgetStudent() {

		// Since the corresponding student exists, should return non null student
		assertNotNull(manager.getStudent(account));
		System.out.println("testGetStudent PASSED");
	}

	/**
	 * Tests the get Student method under a failure condition
	 */
	@Test
	void testNotgetStudent() {

		// Since the account does not exist, should return null
		
		account.setFirstName("judge");
		
		assertNull(manager.getStudent(account));
		System.out.println("testGetStudent PASSED");

	}

}
