package com.taekwondoscheduler.model.services.exceptions;

/**
 * An Exception that happens whenever something goes wrong with the Class List
 * Service
 */
public class ClassListStorageException extends Exception {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -892836032666339740L;

	/**
	 * Creates a new ClassListStorageException with an error message
	 * 
	 * @param message the error message explaining the exception
	 */
	public ClassListStorageException(final String message) {
		super(message);
	}

	/**
	 * Creates a new ClassListStorageException with an error message and details of
	 * exception cause
	 * 
	 * @param message the error message explaining the exception
	 * @param cause   the Exception that caused the ClassListStorageException to be
	 *                thrown
	 */
	public ClassListStorageException(final String message, final Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
