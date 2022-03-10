/**
 * 
 */
package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.DownConductorException;
import com.capeelectric.model.DownConductorDescription;
import com.capeelectric.model.DownConductorReport;


/**
 * @author CAPE-SOFTWARE
 *
 */
public interface DownConductorService {
	
	public void addDownConductorsDetails(DownConductorReport downConductorReport)
			throws DownConductorException;

	public List<DownConductorReport> retrieveDownConductorDetails(String userName, Integer basicLpsId)
			throws DownConductorException;
	
	public void updateDownConductorDetails(DownConductorReport downConductorReport) throws DownConductorException;

}
