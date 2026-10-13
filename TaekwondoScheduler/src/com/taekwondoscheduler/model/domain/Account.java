package com.taekwondoscheduler.model.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class represents a user Account for Taekwondo Scheduler
 */
public class Account implements Serializable {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 6626815662232972103L;
	
	/**
	 * The first name of the Account holder
	 */
	private String firstName;
	
	/**
	 * The last name of the Account holder
	 */
	private String lastName;
	
	/**
	 * The username of the Account
	 */
	private String username;
	
	/**
	 * The e-mail address of the Account
	 */
	private String email;
	
	/**
	 * The password of the Account
	 */
	private String password;
	
	/**
	 * The boolean value for whether the Account is for an Instructor
	 */
	private boolean isInstructor;

	/**
	 * Default Constructor for Account
	 */
	public Account() {
		
		this.firstName = null;
		this.lastName = null;
		this.username = null;
		this.email = null;
		this.password = null;
		this.isInstructor = false;

	}

	/**
	 * Constructor with Fields for Account
	 * 
	 * @param firstName the first name of the Account holder
	 * @param lastName the last name of the Account holder
	 * @param username the username of the Account
	 * @param email the email address of the Account
	 * @param password the password of the Account
	 * @param isInstructor whether the account is for an instructor or not
	 */
	public Account(String firstName, String lastName, String username, String email, String password, boolean isInstructor) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.isInstructor = isInstructor;
	}
	

	/**
	 * Gets the first name of the Instructor or Student
	 * 
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the Instructor or Student 
	 * 
	 * @param firstName the new first name to be set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name of the Instructor or Student
	 * 
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the Instructor or Student
	 * 
	 * @param lastName the new last name to be set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the username for Account
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
 
	/**
	 * Sets the username for Account
	 * 
	 * @param username the new username to be set
	 */
	public void setUsername(String username) {
		this.username =username;
	}
	
	/**
	 * Gets the email of the Account
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of the Account
	 * 
	 * @param email the new email to be set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the password of the Account
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password of the Account
	 * 
	 * @param password the new password to be set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	/**
	 * @return the isInstructor
	 */
	public boolean isInstructor() {
		return isInstructor;
	}

	/**
	 * @param isInstructor the boolean value for whether the account is for an Instructor
	 */
	public void setInstructor(boolean isInstructor) {
		this.isInstructor = isInstructor;
	}


	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, isInstructor, lastName, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& isInstructor == other.isInstructor && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Account [firstName=" + firstName + ", lastName=" + lastName + ", username=" + username + ", email="
				+ email + ", password=" + password + ", isInstructor=" + isInstructor + "]";
	}
	
	
	/**
	 * Checks if every field has valid data values
	 * nulls and empty strings are invalid
	 * 
	 * @return true if data is valid, false if not
	 */
	public boolean validate() {
		
		boolean valid = true; //assume valid
		
		//Fields evaluated separately for readability
		if (this.firstName == null || this.firstName.equals("")) {
			valid = false;
		} else if (this.lastName == null || this.lastName.equals("")) {
			valid = false;
		} else if (this.username == null || this.username.equals("")) {
			valid = false;
		} else if (this.email == null || this.email.equals("")) {
			valid = false;
		} else if (this.password == null || this.password.equals("")) {
			valid = false;
		}
		
		return valid;
	}

}
