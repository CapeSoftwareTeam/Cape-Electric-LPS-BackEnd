package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.SummaryLpsException;
import com.capeelectric.model.SummaryLps;

public interface PrintSummaryLpsService {
	
	 List<SummaryLps> printLpsSummaryDetails(String userName, Integer basicLpsId)throws SummaryLpsException;

}
