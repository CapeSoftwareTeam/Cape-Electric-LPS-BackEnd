package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.BasicLpsException;
import com.capeelectric.model.BasicLps;


/**
 **
  * @author capeelectricsoftware
 *
 */
public interface BasicLpsService {

	public BasicLps addBasicLpsDetails(BasicLps basicLps) throws BasicLpsException;

	public List<BasicLps> retrieveBasicLpsDetails(String userName, Integer basicLpsId) throws BasicLpsException;
		
	public void updateBasicLpsDetails(BasicLps basicLps) throws BasicLpsException;
	
	public void updateBasicLpsDetailsStatus(BasicLps basicLps) throws BasicLpsException;

}
