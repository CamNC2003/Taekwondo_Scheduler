package com.taekwondoscheduler.model.services;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.taekwondoscheduler.model.services.authenticationservice.AuthenticationServiceImplTest;
import com.taekwondoscheduler.model.services.classlistservice.ClassListServiceImplTest;
import com.taekwondoscheduler.model.services.factory.ServiceFactoryTest;
import com.taekwondoscheduler.model.services.studentservice.StudentServiceImplTest;
import com.taekwondoscheduler.model.services.manager.PropertyManagerTest;


//Service Layer Test Suite
@Suite
@SelectClasses({AuthenticationServiceImplTest.class, ClassListServiceImplTest.class, ServiceFactoryTest.class, StudentServiceImplTest.class, PropertyManagerTest.class})
public class AllServiceTests {

}
