package com.capeelectric.service;

import java.util.List;
import java.util.Optional;

import com.capeelectric.exception.SummaryLpsException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.SummaryLps;

public interface PrintSummaryLpsService {

	List<SummaryLps> printLpsSummaryDetails(String userName, Integer basicLpsId, Optional<BasicLps> basicLpsDetails)
			throws SummaryLpsException;

//	List<SummaryLps> printLpsSummaryDetails(String userName, Integer basicLpsId) throws SummaryLpsException;

}
