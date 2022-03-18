package com.capeelectric.service;

import java.util.Optional;

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.model.AirTermination;

public interface PrintAirTerminationService {

//	public void printAirTermination(String userName, Integer basicLpsId, Optional<AirTermination> lpsAirDisc) throws AirTerminationException;

	public void printAirTermination(String userName, Integer basicLpsId, Optional<AirTermination> lpsAirDisc)
			throws AirTerminationException;

//	public void printAirTermination(String userName, Integer lpsId) throws AirTerminationException;

	
}
