package com.taekwondoscheduler.model.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class AccountTest {

	Account account;

	@BeforeEach
	void setUp() throws Exception {

		// Initializes factory and account for tests
		account = new Account("John", "Doe", "username", "example@email.com", "password", false);
	}

	@Test
	void testValidate() {

		// Fields set with valid data
		// With all fields valid, account should validate

		assertTrue(account.validate(), "account validates");
		System.out.println("testValidate PASSED");

	}

	@Test
	void testNotValidate1() {

		// Default Constructor initializes all fields to null
		// With all fields null, account should not validate
		Account account = new Account();
		assertFalse(account.validate(), "account does not validate");
		System.out.println("testNotValidate1 PASSED");
	}

	@Test
	void testNotValidate2() {

		// All fields set as empty string
		// Since there is no valid data, account should not validate
		Account account = new Account("", "", "", "", "", false);
		assertFalse(account.validate(), "account does not validate");
		System.out.println("testNotValidate2 PASSED");
	}

	@Test
	void testEquals() {

		// account1 and account2 have identical fields
		// account1 and account2 should be equal
		

		Account account2 = new Account("John", "Doe", "username", "example@email.com", "password", false);

		assertTrue(account.equals(account2), "account1 equals account2");

		// Hash code should also be equal
		assertEquals(account.hashCode(), account2.hashCode(), "account1 hash code equals account2 hash code");

		System.out.println("testEquals PASSED");
	}

	@Test
	void testNotEquals() {

		// account1 and account2 have different fields
		// account1 and account2 should be not be equal

		

		Account account2 = new Account("Jon", "Doe", "username", "example@email.com", "password", false);

		assertFalse(account.equals(account2), "account1 does not equal account2");

		// Hash code should also be equal
		assertNotEquals(account.hashCode(), account2.hashCode(),
				"account1 hash code does not equal account2 hash code");

		System.out.println("testNotEquals PASSED");
	}

}
