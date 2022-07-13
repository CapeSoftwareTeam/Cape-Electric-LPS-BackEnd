/**
 * 
 */
package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.exception.SeperationDistanceException;
import com.capeelectric.model.SeperationDistanceDescription;
import com.capeelectric.model.SeperationDistanceReport;



/**
 * @author CAPE-SOFTWARE
 *
 */
public interface SeperationDistanceService {

	public void addSeperationDistance(SeperationDistanceReport seperationDistanceReport)
			throws SeperationDistanceException, AirTerminationException;

	public List<SeperationDistanceReport> retrieveSeperationDetails(String userName, Integer basicLpsId)
			throws SeperationDistanceException;
	
	public void updateSeperationDetails(SeperationDistanceReport seperationDistanceReport) throws SeperationDistanceException, AirTerminationException;
}
