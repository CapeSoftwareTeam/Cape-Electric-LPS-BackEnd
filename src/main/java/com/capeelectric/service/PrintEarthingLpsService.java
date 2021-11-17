package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.model.EarthingLpsDescription;

public interface PrintEarthingLpsService {

	List<EarthingLpsDescription> printEarthingLpsDetails(String userName, Integer basicLpsId) throws EarthingLpsException;

}
