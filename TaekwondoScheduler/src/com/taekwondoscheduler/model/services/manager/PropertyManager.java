package com.taekwondoscheduler.model.services.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.taekwondoscheduler.model.services.exceptions.PropertiesException;

/**
 * A class that represents a Manager that handles the properties of the
 * application
 */
public class PropertyManager {

	/**
	 * The properties of the application
	 */
	private static Properties properties = null;

	/**
	 * Loads properties from the properties file
	 * 
	 * @throws PropertiesException is something unexpected happens
	 */
	public static void loadProperties() throws PropertiesException {

		if (properties == null) {

			properties = new Properties();
			FileInputStream stream = null;

			try {

				stream = new FileInputStream("config/application.properties");

				properties.load(stream);
			} catch (FileNotFoundException fnfe) {
				throw new PropertiesException("Could not find Properties file", fnfe);

			} catch (IOException ioe) {
				throw new PropertiesException("Could not load Properties", ioe);
			} finally {

				if (stream != null) {
					try {
						stream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}

	/**
	 * Gets the name of the service from properties
	 * 
	 * @param name the name of the service
	 * 
	 * @return the properties name of the service
	 */
	public static String getProperty(String name) {

		return properties.getProperty(name);
	}

}
