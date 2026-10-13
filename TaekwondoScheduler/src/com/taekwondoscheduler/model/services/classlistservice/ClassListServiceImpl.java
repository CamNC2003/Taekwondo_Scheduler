package com.taekwondoscheduler.model.services.classlistservice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.taekwondoscheduler.model.domain.TaekwondoClass;
import com.taekwondoscheduler.model.services.exceptions.ClassListStorageException;

/**
 * Implementation for service that handles class list storage for each class
 */
public class ClassListServiceImpl implements IClassListService {

	/**
	 * Gets list of classes from storage
	 * 
	 * @return the list of classes
	 * 
	 * @throws ClassListStorageException if anything unexpected happens
	 */
	@Override
	public TaekwondoClass[] getClassList() throws ClassListStorageException {

		TaekwondoClass[] classes = null;

		ObjectInputStream classListInput = null;

		try { // Reads list of classes from ClassList file
			classListInput = new ObjectInputStream(new FileInputStream("persistence/ClassList"));

			classes = (TaekwondoClass[]) classListInput.readObject();

		} catch (FileNotFoundException fnfe) {
			throw new ClassListStorageException("Accounts file not found", fnfe);
		} catch (IOException ioe) {
			throw new ClassListStorageException("Could not read ClassList file", ioe);
		} catch (ClassNotFoundException cnfe) {
			throw new ClassListStorageException("Could not match object from ClassList file", cnfe);
		} finally {
			if (classListInput != null) {

				try {
					classListInput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return classes;
	}

	/**
	 * Sets the list of classes in storage
	 * 
	 * @param classes the list of classes to be stored
	 * 
	 * @return true if successful, false if not
	 * 
	 * @throws ClassListStorageException if anything unexpected happens
	 */
	@Override
	public boolean setClassList(TaekwondoClass[] classes) throws ClassListStorageException {

		boolean successful = false;

		try (ObjectOutputStream classListOutput = new ObjectOutputStream(
				new FileOutputStream("persistence/ClassList"))) { // Writes list of classes to ClassList file

			classListOutput.writeObject(classes);
			classListOutput.flush();

			successful = true;

		} catch (IOException ioe) {
			throw new ClassListStorageException("Could not write to ClassList file", ioe);
		}

		return successful;
	}

}
