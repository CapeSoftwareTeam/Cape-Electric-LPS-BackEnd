package com.capeelectric.service;

import java.util.Optional;

import com.capeelectric.exception.SPDException;
import com.capeelectric.model.SpdReport;

public interface PrintSPDService {

	public void printSPD(String userName, Integer lpsId, Optional<SpdReport> spdDetails) throws SPDException;

//	public void printSPD1(String userName, Integer lpsId)throws SPDException;
}
