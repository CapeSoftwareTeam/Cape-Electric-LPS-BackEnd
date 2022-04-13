/**
 * 
 */
package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.exception.BasicLpsException;
import com.capeelectric.exception.DownConductorException;
import com.capeelectric.exception.EarthStudException;
import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.exception.SPDException;
import com.capeelectric.exception.SummaryLpsException;
import com.capeelectric.model.SummaryLps;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface SummaryLpsService {

	public String addSummaryLpsDetails(SummaryLps summaryLps, Boolean isSubmitted)
			throws SummaryLpsException, BasicLpsException, AirTerminationException, DownConductorException,
			EarthingLpsException, SPDException, EarthStudException, Exception;

	public String updateSummaryLpsDetails(SummaryLps summaryLps, Boolean isSubmitted)
			throws SummaryLpsException, BasicLpsException, AirTerminationException, DownConductorException,
			EarthingLpsException, SPDException, EarthStudException, Exception;

	public List<SummaryLps> retrieveSummaryLpsDetails(String userName, Integer basicLpsId) throws SummaryLpsException;
}
