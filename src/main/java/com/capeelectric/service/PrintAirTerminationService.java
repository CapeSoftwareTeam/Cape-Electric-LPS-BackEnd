package com.capeelectric.service;

import java.util.Optional;

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.model.AirTermination;
import com.capeelectric.model.BasicLps;

public interface PrintAirTerminationService {


	public void printAirTermination(String userName, Integer basicLpsId,Optional<BasicLps> basicLpsDetails, Optional<AirTermination> lpsAirDisc)
			throws AirTerminationException;

//	public void printAirTermination(String userName, Integer lpsId) throws AirTerminationException;

	
}
