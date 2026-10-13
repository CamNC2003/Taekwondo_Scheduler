package com.taekwondoscheduler.model.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class represents a Taekwondo Instructor at Mueller's Martial Arts. It is
 * the Superclass of Student.
 */
public class Instructor implements Serializable {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 6909314318142162364L;
	// protected so that Student subclass can access variables

	/**
	 * The Account of the Instructor or Student
	 */
	protected Account account;

	/**
	 * Default Constructor for Instructor Subclass Student uses this in its
	 * Constructor
	 */
	public Instructor() {

		this.account = null;
	}

	/**
	 * Constructor with fields for Instructor Subclass Student uses this in its
	 * Constructor
	 * 
	 * @param account the Account object of the Instructor or Student
	 */
	public Instructor(Account account) {
		this.account = account;
	}

	/**
	 * Gets the Account object of the Instructor or Student
	 * 
	 * @return the Account object
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * Sets the Account object of the Instructor or Student
	 * 
	 * @param account the new Account object to be set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public int hashCode() {
		return account.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Instructor other = (Instructor) obj;
		return Objects.equals(account, other.account);
	}

	@Override
	public String toString() {
		return "Instructor [account=" + account + "]";
	}

	/**
	 * Checks if every field has valid data values nulls and empty strings are
	 * invalid
	 * 
	 * @return true if data is valid, false if not
	 */
	public boolean validate() {

		boolean valid = true; // assume valid

		if (this.account == null || !this.account.validate()) {
			valid = false;
		}

		return valid;
	}

}
