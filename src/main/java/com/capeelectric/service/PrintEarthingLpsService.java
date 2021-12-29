package com.capeelectric.service;

import java.util.List;
import java.util.Optional;

import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.EarthingLpsDescription;

public interface PrintEarthingLpsService {

	List<EarthingLpsDescription> printEarthingLpsDetails(String userName, Integer basicLpsId,Optional<BasicLps> basicLpsDetails, Optional<EarthingLpsDescription> earthingLpsDetails) throws EarthingLpsException;

}
