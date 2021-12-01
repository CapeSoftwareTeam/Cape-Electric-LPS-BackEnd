package com.capeelectric.service;

import java.util.Optional;

import com.capeelectric.exception.DownConductorException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.DownConductorDescription;

public interface PrintDownConductorService {

	void printDownConductor(String userName, Integer lpsId,Optional<BasicLps> basicLpsDetails, Optional<DownConductorDescription> downConductorDetails)throws DownConductorException;

}
