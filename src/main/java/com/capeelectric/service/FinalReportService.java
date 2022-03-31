package com.capeelectric.service;

import java.util.List;
import java.util.Optional;

import com.capeelectric.exception.FinalReportException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.LpsFinalReport;

/**
 * 
 * @author capeelectricsoftware
 *
 */
public interface FinalReportService {

	List<BasicLps> retrieveListOfBasicLps(String userName) throws FinalReportException;

	Optional<LpsFinalReport> retrieveLpsReports(String userName, Integer basicLpsId) throws FinalReportException;
	
	List<BasicLps> retrieveAllBasicLps() throws FinalReportException;

}