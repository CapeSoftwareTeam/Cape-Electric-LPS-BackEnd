package com.capeelectric.service;

import com.capeelectric.exception.SPDException;

public interface PrintSPDService {

	public void printSPD(String userName, Integer lpsId)throws SPDException;
}
