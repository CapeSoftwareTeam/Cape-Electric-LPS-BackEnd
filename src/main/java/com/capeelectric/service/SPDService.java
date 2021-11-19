/**
 * 
 */
package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.SPDException;
import com.capeelectric.model.SPD;



/**
 * @author CAPE-SOFTWARE
 *
 */
public interface SPDService {
	
	public void addSPDDetails(SPD SPDDesc)
			throws SPDException;

	public List<SPD> retrieveSPDDetails(String userName, Integer basicLpsId)
			throws SPDException;
	
	void updateSpdDetails(SPD spdDesc) throws SPDException;

}
