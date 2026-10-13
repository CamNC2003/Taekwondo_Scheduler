package com.taekwondoscheduler.model.services.factory;

import com.taekwondoscheduler.model.services.IService;
import com.taekwondoscheduler.model.services.exceptions.ServiceException;
import com.taekwondoscheduler.model.services.manager.PropertyManager;

/**
 * Factory class that initializes services Uses singleton pattern so that there
 * is only one instance
 */
public class ServiceFactory {

	/**
	 * Singleton Instance
	 */
	private static ServiceFactory instance;

	/**
	 * Private default constructor so that only this class can access it
	 */
	private ServiceFactory() {
	}

	/**
	 * Gets the singular instance of ServiceFactory
	 * 
	 * @return the instance of ServiceFactory
	 */
	public static ServiceFactory getInstance() {

		if (instance == null) { // Creates new instance if first time called
			instance = new ServiceFactory();
		}
		return instance;
	}

	/**
	 * Gets a service instance based on the names in the properties file.
	 * 
	 * @param name the service name
	 * 
	 * @return an instance of the service interface
	 * 
	 * @throws ServiceException if something unexpected happens when getting
	 *                          services
	 */
	public IService getService(String name) throws ServiceException {

		try {
			Class<?> service = Class.forName(this.getName(name)); // Gets service using name from properties file

			return (IService) service.getDeclaredConstructor().newInstance(); // Returns an instance of the service

		} catch (Exception e) { // Happens if there is a problem with the Properties file

			String message = name + "could not be instantiated";

			throw new ServiceException(message, e);
		}

	}

	/**
	 * Gets the corresponding service name from the properties file.
	 * 
	 * @param name the name of the requested service
	 * 
	 * @return the properties name of the service
	 * 
	 */
	private String getName(String name) {

		return PropertyManager.getProperty(name);
	}
}
