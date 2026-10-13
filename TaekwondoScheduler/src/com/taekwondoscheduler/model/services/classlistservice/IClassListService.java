package com.taekwondoscheduler.model.services.classlistservice;

import com.taekwondoscheduler.model.domain.*;
import com.taekwondoscheduler.model.services.IService;
import com.taekwondoscheduler.model.services.exceptions.ClassListStorageException;

/**
 * Interface for service that handles class list storage for each class
 */
public interface IClassListService extends IService {

	/**
	 * Service name for properties file
	 */
	public final String NAME = "IClassListService";
	
	/**
	 * Gets list of classes from storage
	 * 
	 * @return the list of classes
	 * 
	 * @throws ClassListStorageException if anything unexpected happens
	 */
	public TaekwondoClass[] getClassList() throws ClassListStorageException;
	
	/**
	 * Sets the list of classes in storage
	 * 
	 * @param classes the list of classes to be stored
	 * 
	 * @return true if successful, false if not
	 * 
	 * @throws ClassListStorageException if anything unexpected happens
	 */
	public boolean setClassList(TaekwondoClass[] classes) throws ClassListStorageException;
}
