package com.taekwondoscheduler.model.services.authenticationservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;

import com.taekwondoscheduler.model.domain.Account;
import com.taekwondoscheduler.model.services.exceptions.AuthenticationException;

/**
 * Implementation for Authentication Service that handles System Authentication
 */
public class AuthenticationServiceImpl implements IAuthenticationService {

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
	@Override
	public boolean signUp(Account account) throws AuthenticationException {

		boolean signedUp = false;
		HashSet<Account> accounts; // list of accounts. HashSet used for efficiency

		accounts = this.getAccounts(); // Gets current list of accounts

		if (accounts == null) { // if list is null, new list is created
			accounts = new HashSet<>();
		}

		if (!accounts.contains(account) && account.validate()) {

			accounts.add(account);

			signedUp = true;

			// Writes HashSet to file
			try (ObjectOutputStream accountOutput = new ObjectOutputStream(
					new FileOutputStream("persistence/Accounts"))) {

				accountOutput.writeObject(accounts);
				accountOutput.flush();

			} catch (IOException ioe) {
				throw new AuthenticationException("Could not write to Accounts file", ioe);
			}
		}

		return signedUp;

	}

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
	@Override
	public Account signIn(String username, String password) throws AuthenticationException {

		Account account = null;

		HashSet<Account> accounts = this.getAccounts(); // Gets current list of accounts

		if (accounts != null) {

			ArrayList<Account> accountsList = new ArrayList<>(accounts);

			for (Account acc : accountsList) {

				// if username and password are equal
				if (acc.getUsername().equals(username) && acc.getPassword().equals(password)) {

					account = acc;

				}
			}
		}

		return account;
	}

	/**
	 * Gets the list of Accounts from the Accounts File
	 * 
	 * @return the list of Accounts
	 * 
	 * @throws AuthenticationException if anything unexpected happens
	 */
	@SuppressWarnings("unchecked")
	private HashSet<Account> getAccounts() throws AuthenticationException {

		HashSet<Account> accounts = null;

		ObjectInputStream accountInput = null;

		try { // Reads list from Accounts file and sets it as HashSet

			File file = new File("persistence/Accounts");

			if (file.length() != 0) { // If file is not empty
				accountInput = new ObjectInputStream(new FileInputStream(file));
				accounts = (HashSet<Account>) accountInput.readObject();
			}

		} catch (FileNotFoundException fnfe) {
			throw new AuthenticationException("Accounts file not found", fnfe);
		} catch (IOException ioe) {
			throw new AuthenticationException("Could not read Accounts file", ioe);
		} catch (ClassNotFoundException cnfe) {
			throw new AuthenticationException("Could not match object from Accounts file", cnfe);
		} finally {
			if (accountInput != null) {

				try {
					accountInput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return accounts;
	}

}
