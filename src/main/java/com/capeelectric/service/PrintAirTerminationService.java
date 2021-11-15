package com.capeelectric.service;

import com.capeelectric.exception.AirTerminationException;

public interface PrintAirTerminationService {

	public void printAirTermination(String userName, Integer lpsId) throws AirTerminationException;

}
