package com.taekwondoscheduler.model.business;

import com.taekwondoscheduler.model.domain.Account;
import com.taekwondoscheduler.model.domain.Student;
import com.taekwondoscheduler.model.services.authenticationservice.IAuthenticationService;
import com.taekwondoscheduler.model.services.exceptions.*;
import com.taekwondoscheduler.model.services.studentservice.IStudentService;

/**
 * A class that represents a Business Layer Manager that handles the sign in and
 * sign up Use Cases
 */
public class AccountManager extends Manager {

	/**
	 * The Singleton instance of AccountManager
	 */
	private static AccountManager instance;

	/**
	 * The AuthenticationService instance to use service methods
	 */
	private static IAuthenticationService authenticationService;
	
	/**
	 * The StudentService Method to use service methods
	 */
	private static IStudentService studentService;

	/**
	 * Constructor for AccountManager. Initializes properties and service.
	 */
	private AccountManager() {
		super();
		authenticationService = getAuthenticationService();
		studentService = getStudentService();
	}

	/**
	 * Gets the Singleton instance of AccountManager
	 * 
	 * @return the instance of AccountManager
	 */
	public static AccountManager getInstance() {

		if (instance == null) { // If null, a new instance is created
			instance = new AccountManager();
		}

		return instance;

	}

	/**
	 * Signs up a new account with TaekwondoScheduler
	 * 
	 * @param account the new account to be set up
	 * 
	 * @return true if successful, false if not
	 */
	public boolean signUp(Account account) {

		boolean successful = true;

		try { // Calls service method
			successful = authenticationService.signUp(account);

		} catch (AuthenticationException e) {
			System.out.println("Could not sign up Account");
		}

		return successful;
	}

	/**
	 * Signs in and returns an Account based on the provided sign in credentials
	 * 
	 * @param username the username of the account
	 * @param password the password of the account
	 * 
	 * @return the corresponding Account object based on the sign in credentials
	 */
	public Account signIn(String username, String password) {

		Account account = null;

		try { // Calls service method
			account = authenticationService.signIn(username, password);

		} catch (AuthenticationException e) {
			System.out.println("Could not sign in Account");
		}

		return account;
	}
	
	/**
	 * Gets a Student object based on the corresponding Account
	 * 
	 * @param account the Account that corresponds with the Student
	 * 
	 * @return the Student object
	 */
	public Student getStudent(Account account) {

		Student student = null;

		try {
			for (Student stu : AccountManager.studentService.getAllStudents()) {

				if (stu.getAccount().equals(account)) {

					student = stu;
				}

			}
		} catch (StudentStorageException e) {
			System.out.println("Could not get Student");
		}

		return student;
	}

	/**
	 * Adds a Student to the application
	 * 
	 * @param student the student to be added
	 * 
	 * @return true if successful, false if not
	 */
	public boolean addStudent(Student student) {

		boolean successful = false;

		try { // Calls service method

			successful = studentService.addStudent(student);

		} catch (StudentStorageException sse) {
			System.out.println("Could not add Student");
		}

		return successful;
	}
	
	

	/**
	 * Gets the AuthenticationService instance for constructor
	 * 
	 * @return the AuthenticationService instance
	 */
	private IAuthenticationService getAuthenticationService() {

		IAuthenticationService authenticationService = null;

		try {
			authenticationService = (IAuthenticationService) factory.getService(IAuthenticationService.NAME);

		} catch (ServiceException e) {
			System.out.println("Could not load Authentication Service");
		}

		return authenticationService;
	}
	
	/**
	 * Gets the StudentService instance for constructor
	 * 
	 * @return the StudentService instance
	 */
	private IStudentService getStudentService() {

		IStudentService studentService = null;

		try {
			studentService = (IStudentService) factory.getService(IStudentService.NAME);

		} catch (ServiceException se) {
			System.out.println("Could not load Student Service");
		}
		return studentService;
	}

}
