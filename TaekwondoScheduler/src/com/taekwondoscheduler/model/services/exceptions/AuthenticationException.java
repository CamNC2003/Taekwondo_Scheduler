package com.taekwondoscheduler.model.services.exceptions;

/**
 * An Exception that happens whenever something goes wrong with the Authentication Service
 */
public class AuthenticationException extends Exception {



	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -1849866517431646467L;


	/**
	 * Creates a new AuthenticationException with an error message
	 * 
	 * @param message the error message explaining the exception
	 */
	public AuthenticationException(final String message) {
		super(message);

	}


	/**
	 * Creates a new AuthenticationException with an error message and details of
	 * exception cause
	 * 
	 * @param message the error message explaining the exception
	 * @param cause   the Exception that caused the AuthenticationException to be
	 *                thrown
	 */
	public AuthenticationException(final String message, final Throwable cause) {
		super(message, cause);
	}


}
