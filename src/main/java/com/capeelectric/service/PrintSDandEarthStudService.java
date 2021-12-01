package com.capeelectric.service;

import java.util.Optional;

import com.capeelectric.exception.EarthStudException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.SeperationDistanceDescription;

public interface PrintSDandEarthStudService {

	public void printSDandEarthStud(String userName, Integer lpsId, Optional<BasicLps> basicLpsDetails,
			Optional<SeperationDistanceDescription> separateDistanceDetails) throws EarthStudException;

}
