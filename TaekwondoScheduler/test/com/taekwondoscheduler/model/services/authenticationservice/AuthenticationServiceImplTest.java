package com.taekwondoscheduler.model.services.authenticationservice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.taekwondoscheduler.model.business.AccountManager;
import com.taekwondoscheduler.model.domain.Account;
import com.taekwondoscheduler.model.services.exceptions.AuthenticationException;
import com.taekwondoscheduler.model.services.exceptions.ServiceException;
import com.taekwondoscheduler.model.services.factory.ServiceFactory;

public class AuthenticationServiceImplTest {

	ServiceFactory factory;
	Account account;
	AccountManager manager;
	
	@BeforeEach
	void setUp() throws Exception {
		
		//Initializes factory and account for tests
		factory = ServiceFactory.getInstance();
		account = new Account("John", "Doe", "username", "example@email.com", "password", false);
		
		manager = AccountManager.getInstance();
	}

	@Test
	void testSignUp() throws Exception {

		//signUp returns true if successful
		//signUp returns false if an account already exists
		//throws exception if anything weird happens
		
		//Since username is different, should return true
		
		//Change each test
		account.setUsername("username131");
		
		try {
			IAuthenticationService authenticationService = (IAuthenticationService) factory.getService(IAuthenticationService.NAME);
			assertTrue(authenticationService.signUp(account)); //Since account already exists in file
			
			System.out.println ("testSignUp PASSED");
			
		} catch (ServiceException | AuthenticationException e) {
			fail();
		}
		
	}
	
	@Test
	void testNotSignUp() {

		//signUp returns true if successful
		//signUp returns false if an account already exists
		//throws exception if anything weird happens
		
		//Since account already exists, should return false
		
		try {
			IAuthenticationService authenticationService = (IAuthenticationService) factory.getService(IAuthenticationService.NAME);
			assertFalse(authenticationService.signUp(account)); //Since account already exists in file
			
			System.out.println ("testNotSignUp PASSED");
			
		} catch (ServiceException | AuthenticationException e) {
			fail();
		}
		
	}
	
	@Test
	void testSignIn() {

		//signIn returns true if an account already exists
		//signUp returns false if an account does not exist
		//throws exception if anything weird happens
		
		//Since account exists, should return true
		
		try {
			IAuthenticationService authenticationService = (IAuthenticationService) factory.getService(IAuthenticationService.NAME);
			assertNotNull(authenticationService.signIn("username", "password"));
			
			System.out.println ("testSignIn PASSED");
			
		} catch (ServiceException | AuthenticationException e) {
			fail();
		}
	}
	
	@Test
	void testNotSignIn() {

		//signIn returns true if an account already exists
		//signUp returns false if an account does not exist
		//throws exception if anything weird happens
		
		//since the password is incorrect, should return false
		
		account.setPassword("password1");
		
		try {
			IAuthenticationService authenticationService = (IAuthenticationService) factory.getService(IAuthenticationService.NAME);
			assertNull(authenticationService.signIn("username", "password1"));
			
			System.out.println ("testSignIn PASSED");
			
		} catch (ServiceException | AuthenticationException e) {
			fail();
		}
	}

}
