package com.taekwondoscheduler.model.business;

import com.taekwondoscheduler.model.services.exceptions.PropertiesException;
import com.taekwondoscheduler.model.services.factory.ServiceFactory;
import com.taekwondoscheduler.model.services.manager.PropertyManager;

/**
 * An abstract Manager class all other business managers extend from
 */
public abstract class Manager {

	/**
	 * Static block loads properties from property file when Manager is referenced
	 */
	static {
		try {
			PropertyManager.loadProperties();
		} catch (PropertiesException e) {
			System.out.println("Could not load properties. Taekwondo Scheduler exiting...");
			System.exit(1);
		}
	}

	/**
	 * The service factory that gets the services
	 */
	protected static ServiceFactory factory;

	/**
	 * Abstract constructor used by subclasses
	 */
	protected Manager() {

		factory = ServiceFactory.getInstance();
	}

}
