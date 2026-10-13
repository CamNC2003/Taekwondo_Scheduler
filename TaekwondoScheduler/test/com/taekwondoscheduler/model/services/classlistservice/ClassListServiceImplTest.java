package com.taekwondoscheduler.model.services.classlistservice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.taekwondoscheduler.model.business.ScheduleManager;
import com.taekwondoscheduler.model.domain.Schedule;
import com.taekwondoscheduler.model.domain.TaekwondoClass;
import com.taekwondoscheduler.model.services.exceptions.ClassListStorageException;
import com.taekwondoscheduler.model.services.exceptions.ServiceException;
import com.taekwondoscheduler.model.services.factory.ServiceFactory;

public class ClassListServiceImplTest {

	ServiceFactory factory;
	Schedule schedule;
	ScheduleManager manager;

	@BeforeEach
	void setUp() throws Exception {

		factory = ServiceFactory.getInstance();
		schedule = Schedule.getInstance();
		manager = ScheduleManager.getInstance();
	}

	@Test
	void testGetClassList() {

		// getClassList returns true if successful

		try {

			IClassListService classListService = (IClassListService) factory.getService(IClassListService.NAME);
			TaekwondoClass[] classes = classListService.getClassList();
			assertNotNull(classes, "class list is not null");

			System.out.println("testGetClassList PASSED");
		} catch (ServiceException | ClassListStorageException e) {
			fail();
		}

	}

	@Test
	void testSetClassList() throws Exception {

		// setClassList returns true if successful

		try {
			IClassListService classListService = (IClassListService) factory.getService(IClassListService.NAME);

			assertTrue(classListService.setClassList(schedule.getClasses()));

			System.out.println("testSetClassList PASSED");
		} catch (ServiceException | ClassListStorageException e) {
			throw e;
		}

	}

}
