/**
 * 
 */
package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.SPDException;
import com.capeelectric.model.SPDDescription;



/**
 * @author CAPE-SOFTWARE
 *
 */
public interface SPDService {
	
	public void addSPDDetails(SPDDescription SPDDesc)
			throws SPDException;

	public List<SPDDescription> retrieveSPDDetails(String userName, Integer basicLpsId)
			throws SPDException;

}
