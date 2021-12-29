package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.exception.BasicLpsException;
import com.capeelectric.exception.DownConductorException;
import com.capeelectric.exception.EarthStudException;
import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.exception.SPDException;
import com.capeelectric.model.EarthStudDescription;


/**
 * @author CAPE-SOFTWARE
 *
 */
public interface EarthStudService {

	public void addEarthStudDetails(EarthStudDescription earthStudDescription)
			throws EarthStudException, BasicLpsException, AirTerminationException, DownConductorException, EarthingLpsException, SPDException, Exception;

	public List<EarthStudDescription> retrieveEarthStudDetails(String userName, Integer basicLpsId)
			throws EarthStudException;
	
	public void updateEarthStudDetails(EarthStudDescription earthStudDescription) throws EarthStudException;
}
