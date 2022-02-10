/**
 * 
 */
package com.capeelectric.service;


import com.capeelectric.model.AllStepsRemarks;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface ObservationService {

	public AllStepsRemarks retrieveObservationsInSummary(Integer basicLpsId);
}
