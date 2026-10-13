package com.taekwondoscheduler.model.services.exceptions;

/**
 * An Exception that happens whens something goes wrong when trying to load
 * properties
 */
public class PropertiesException extends Exception {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -991871514698722183L;

	/**
	 * Creates a new PropertiesException with an error message
	 * 
	 * @param message the error message explaining the exception
	 */
	public PropertiesException(final String message) {
		super(message);
	}

	/**
	 * Creates a new PropertiesException with an error message and details of exception cause
	 * 
	 * @param message the error message explaining the exception
	 * @param cause the Exception that caused the PropertiesException to be thrown
	 */
	public PropertiesException(final String message, final Throwable cause) {
		super(message, cause);

	}
}
