package com.capeelectric.service;

import java.util.Optional;

import com.capeelectric.exception.BasicLpsException;
import com.capeelectric.model.BasicLps;

public interface PrintBasicLpsService {

	public void printBasicLps(String userName, Integer lpsId, Optional<BasicLps> basicLpsDetails)
			throws BasicLpsException;

//	public void printBasicLps(String userName, Integer lpsId)throws BasicLpsException;;

}
