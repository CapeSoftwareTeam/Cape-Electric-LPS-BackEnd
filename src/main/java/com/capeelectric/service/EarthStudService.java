/**
 * 
 */
package com.capeelectric.service;

import java.util.List;


import com.capeelectric.exception.EarthStudException;
import com.capeelectric.model.EarthStudDescription;


/**
 * @author CAPE-SOFTWARE
 *
 */
public interface EarthStudService {

	public void addEarthStudDetails(EarthStudDescription earthStudDescription)
			throws EarthStudException;

	public List<EarthStudDescription> retrieveEarthStudDetails(String userName, Integer basicLpsId)
			throws EarthStudException;
}
