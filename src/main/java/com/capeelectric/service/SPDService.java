/**
 * 
 */
package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.SPDException;
import com.capeelectric.model.SPD;
import com.capeelectric.model.SpdReport;



/**
 * @author CAPE-SOFTWARE
 *
 */
public interface SPDService {
	
	public void addSPDDetails(SpdReport spdReport)
			throws SPDException;

	public List<SpdReport> retrieveSPDDetails(String userName, Integer basicLpsId)
			throws SPDException;
	
	void updateSpdDetails(SpdReport spdReport) throws SPDException;

}
