package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.model.AirTermination;
import com.capeelectric.model.LpsAirDiscription;

/**
 **
  * @author capeelectricsoftware
 *
 */
public interface AirTerminationLpsService {

	public void addAirTerminationLpsDetails(AirTermination airTermination)throws AirTerminationException;

	public List<AirTermination> retrieveAirTerminationLps(String userName, Integer basicLpsId)throws AirTerminationException;

	public void updateAirTerminationLps(AirTermination airTermination)throws AirTerminationException;


  }
