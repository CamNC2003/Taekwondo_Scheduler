package com.taekwondoscheduler.model.services.authenticationservice;

import com.taekwondoscheduler.model.domain.Account;
import com.taekwondoscheduler.model.services.IService;
import com.taekwondoscheduler.model.services.exceptions.AuthenticationException;

/**
 * Interface for Authentication Service that handles System Authentication
 */
public interface IAuthenticationService extends IService {

	/**
	 * Name of service for properties file
	 */
	public final String NAME = "IAuthenticationService";

	/**
	 * Sign up a new account by adding it to the stored list of accounts. Does not
	 * add if the account already exists
	 * 
	 * @param account the account to get added
	 * 
	 * @return true if successful, false if not
	 * 
	 * @throws AuthenticationException if something unexpected happens
	 */
	public boolean signUp(Account account) throws AuthenticationException;

	/**
	 * Signs in an account by checking to see if it is in the stored list of
	 * accounts
	 *
	 * @param username the username of the Account
	 * @param password the password of the Account
	 * 
	 * @return the full Account object for the associated sign in credentials
	 * 
	 * @throws AuthenticationException if something unexpected happens
	 */
	public Account signIn(String username, String password) throws AuthenticationException;
}
