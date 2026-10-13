package com.taekwondoscheduler.model.business;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ AccountManagerTest.class, ScheduleManagerTest.class, StudentManagerTest.class })
public class AllBusinessTests {

}
