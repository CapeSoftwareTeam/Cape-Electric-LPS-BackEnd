package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.model.LpsAirDiscription;

/**
 **
  * @author capeelectricsoftware
 *
 */
public interface AirTerminationLpsService {

	public void addAirTerminationLpsDetails(LpsAirDiscription lpsAirDescription)throws AirTerminationException;

	public List<LpsAirDiscription> retrieveAirTerminationLps(String userName, Integer basicLpsId)throws AirTerminationException;

	public void updateAirTerminationLps(LpsAirDiscription lpsAirDescription)throws AirTerminationException;


  }
