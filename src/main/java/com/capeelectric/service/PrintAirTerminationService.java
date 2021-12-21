package com.capeelectric.service;

import java.util.Optional;

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.LpsAirDiscription;

public interface PrintAirTerminationService {

	public void printAirTermination(String userName, Integer lpsId,Optional<BasicLps> basicLpsDetails, Optional<LpsAirDiscription> lpsAirDisc) throws AirTerminationException;

}
