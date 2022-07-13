package com.capeelectric.service;

import java.util.Optional;

import com.capeelectric.exception.EarthStudException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.EarthStudReport;
import com.capeelectric.model.SeperationDistanceReport;

public interface PrintSDandEarthStudService {

	public void printSDandEarthStud(String userName, Integer lpsId,
			Optional<BasicLps> basicLpsDetails,Optional<SeperationDistanceReport> separateDistanceDetails, Optional<EarthStudReport> earthStudDetails)
			throws EarthStudException;

//	public void printSDandEarthStud(String userName, Integer lpsId) throws EarthStudException;

}
