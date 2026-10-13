package com.taekwondoscheduler.model.services.exceptions;

/**
 * An Exception that happens whenever something goes wrong with the Student
 * Service
 */
public class StudentStorageException extends Exception {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 962136237489374369L;

	/**
	 * Creates a new StudentStorageException with an error message
	 * 
	 * @param message the error message explaining the exception
	 */
	public StudentStorageException(final String message) {
		super(message);
	}

	/**
	 * Creates a new StudentStorageException with an error message and details of
	 * exception cause
	 * 
	 * @param message the error message explaining the exception
	 * @param cause   the Exception that caused the StudentStorageException to be
	 *                thrown
	 */
	public StudentStorageException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
