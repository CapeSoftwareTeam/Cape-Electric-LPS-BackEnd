/**
 * 
 */
package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.model.EarthingReport;



/**
 * @author CAPE-SOFTWARE
 *
 */
public interface EarthingLpsService {
	
	public void addEarthingLpsDetails(EarthingReport earthingReport)
			throws EarthingLpsException;

	public List<EarthingReport> retrieveEarthingLpsDetails(String userName, Integer basicLpsId)
			throws EarthingLpsException;
	
	public void updateEarthingLpsDetails(EarthingReport earthingReport) throws EarthingLpsException;

}
