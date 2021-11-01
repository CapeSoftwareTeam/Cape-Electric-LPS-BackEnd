/**
 * 
 */
package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.SeperationDistanceException;
import com.capeelectric.model.SeperationDistanceDescription;



/**
 * @author CAPE-SOFTWARE
 *
 */
public interface SeperationDistanceService {

	public void addSeperationDistance(SeperationDistanceDescription seperationDistanceDesc)
			throws SeperationDistanceException;

	public List<SeperationDistanceDescription> retrieveSeperationDetails(String userName, Integer basicLpsId)
			throws SeperationDistanceException;
	
	public void updateSeperationDetails(SeperationDistanceDescription seperationDistanceDesc) throws SeperationDistanceException;
}
