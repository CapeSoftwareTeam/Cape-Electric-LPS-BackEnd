package com.capeelectric.service;

import java.util.Optional;

import com.capeelectric.exception.DownConductorException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.DownConductorReport;

public interface PrintDownConductorService {

//	public void printDownConductor(String userName, Integer lpsId,Optional<BasicLps> basicLpsDetails, Optional<DownConductorDescription> downConductorDetails)throws DownConductorException;

	public void printDownConductor(String userName, Integer lpsId,Optional<BasicLps> basicLpsDetails, Optional<DownConductorReport> downConductorDetails)
			throws DownConductorException;

//	public void printDownConductor1(String userName, Integer lpsId)throws DownConductorException;

}
