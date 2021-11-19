/**
 * 
 */
package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.model.EarthingLpsDescription;



/**
 * @author CAPE-SOFTWARE
 *
 */
public interface EarthingLpsService {
	
	public void addEarthingLpsDetails(EarthingLpsDescription earthingLpsDescription)
			throws EarthingLpsException;

	public List<EarthingLpsDescription> retrieveEarthingLpsDetails(String userName, Integer basicLpsId)
			throws EarthingLpsException;
	
	public void updateEarthingLpsDetails(EarthingLpsDescription earthingLpsDesc) throws EarthingLpsException;

}
