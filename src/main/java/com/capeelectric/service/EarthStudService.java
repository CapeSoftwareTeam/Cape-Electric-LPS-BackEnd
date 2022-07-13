package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.exception.BasicLpsException;
import com.capeelectric.exception.DownConductorException;
import com.capeelectric.exception.EarthStudException;
import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.exception.SPDException;
import com.capeelectric.model.EarthStudDescription;
import com.capeelectric.model.EarthStudReport;


/**
 * @author CAPE-SOFTWARE
 *
 */
public interface EarthStudService {

	public void addEarthStudDetails(EarthStudReport earthStudReport)
			throws EarthStudException, AirTerminationException;

	public List<EarthStudReport> retrieveEarthStudDetails(String userName, Integer basicLpsId)
			throws EarthStudException;
	
	public void updateEarthStudDetails(EarthStudReport earthStudReport) throws EarthStudException, AirTerminationException;
}
