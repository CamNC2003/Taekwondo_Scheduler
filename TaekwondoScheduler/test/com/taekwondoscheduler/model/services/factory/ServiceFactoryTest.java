package com.taekwondoscheduler.model.services.factory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.taekwondoscheduler.model.services.authenticationservice.*;
import com.taekwondoscheduler.model.services.classlistservice.*;
import com.taekwondoscheduler.model.services.exceptions.ServiceException;
import com.taekwondoscheduler.model.services.studentservice.*;

public class ServiceFactoryTest {

	ServiceFactory factory;

	@BeforeEach
	void setUp() throws Exception {

		factory = ServiceFactory.getInstance();
	}

	@Test
	void testGetAuthenticationService() {

		// Since AuthenticationServiceImpl implements IAuthenticationService, instanceof
		// should return true

		try {

			IAuthenticationService authenticationService = (IAuthenticationService) factory
					.getService(IAuthenticationService.NAME);
			assertTrue(authenticationService instanceof AuthenticationServiceImpl,
					"authenticationService is an instance of AuthenticationServiceImpl");

			System.out.println("testGetAuthenticationService PASSED");

		} catch (ServiceException e) {
			fail();
		}
	}

	@Test
	void testGetClassListService() {

		// Since ClassListServiceImpl implements IClassListService, instanceof should
		// return true

		try {

			IClassListService classListService = (IClassListService) factory.getService(IClassListService.NAME);
			assertTrue(classListService instanceof ClassListServiceImpl,
					"classListService is an instance of ClassListServiceImpl");

			System.out.println("testGetClassListService PASSED");

		} catch (ServiceException e) {
			fail();
		}

	}

	@Test
	void testGetStudentService() {

		// Since StudentServiceImpl implements IStudentService, instanceof should return
		// true

		try {

			IStudentService studentService = (IStudentService) factory.getService(IStudentService.NAME);
			assertTrue(studentService instanceof StudentServiceImpl,
					"studentService is an instance of StudentServiceImpl");

			System.out.println("testGetStudentService PASSED");

		} catch (ServiceException e) {
			fail();
		}

	}

}
