package com.taekwondoscheduler.model.domain;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ AccountTest.class, InstructorTest.class, ScheduleTest.class, StudentTest.class,
		TaekwondoClassTest.class })
public class AllDomainTests {

}
