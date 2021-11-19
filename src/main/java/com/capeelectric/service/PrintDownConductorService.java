package com.capeelectric.service;

import com.capeelectric.exception.DownConductorException;

public interface PrintDownConductorService {

	void printDownConductor(String userName, Integer lpsId)throws DownConductorException;

}
