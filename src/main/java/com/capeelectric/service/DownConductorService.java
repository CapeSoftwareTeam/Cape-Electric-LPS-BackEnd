/**
 * 
 */
package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.DownConductorException;
import com.capeelectric.model.DownConductorDescription;


/**
 * @author CAPE-SOFTWARE
 *
 */
public interface DownConductorService {
	
	public void addDownConductorsDetails(DownConductorDescription downConductorDescription)
			throws DownConductorException;

	public List<DownConductorDescription> retrieveDownConductorDetails(String userName, Integer basicLpsId)
			throws DownConductorException;

}
