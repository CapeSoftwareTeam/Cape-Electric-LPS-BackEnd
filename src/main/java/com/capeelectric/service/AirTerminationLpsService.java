package com.capeelectric.service;

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.exception.BasicLpsException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.LpsAirDiscription;

/**
 **
  * @author capeelectricsoftware
 *
 */
public interface AirTerminationLpsService {

	public void addAirTerminationLpsDetails(LpsAirDiscription lpsAirDescription)throws AirTerminationException;


  }
