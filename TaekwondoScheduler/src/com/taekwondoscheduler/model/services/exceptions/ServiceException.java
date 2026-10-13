package com.taekwondoscheduler.model.services.exceptions;

/**
 * An Exception that happens whenever something goes wrong with the Service
 * Factory
 */
public class ServiceException extends Exception {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -9014527383034415223L;

	/**
	 * Creates a new ServiceException with an error message
	 * 
	 * @param message the error message explaining the exception
	 */
	public ServiceException(final String message) {
		super(message);
	}

	/**
	 * Creates a new ServiceException with an error message and details of exception
	 * cause
	 * 
	 * @param message the error message explaining the exception
	 * @param cause   the Exception that caused the ServiceException to be thrown
	 */
	public ServiceException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
