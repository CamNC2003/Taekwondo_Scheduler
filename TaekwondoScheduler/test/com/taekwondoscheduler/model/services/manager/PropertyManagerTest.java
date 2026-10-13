package com.taekwondoscheduler.model.services.manager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.taekwondoscheduler.model.services.exceptions.PropertiesException;
import com.taekwondoscheduler.model.services.studentservice.IStudentService;

public class PropertyManagerTest {

	@Test
	void testGetProperties() {
		
		try {
			PropertyManager.loadProperties();
		} catch (PropertiesException e) {
			fail();
		}
		
		assertNotNull(PropertyManager.getProperty(IStudentService.NAME));

		System.out.println("testGetProperties PASSED");
	}

}
