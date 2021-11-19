package com.capeelectric.service;

import com.capeelectric.exception.BasicLpsException;

public interface PrintBasicLpsService {

	public void printBasicLps(String userName, Integer lpsId) throws BasicLpsException;
		
}
