/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.SummaryLpsObservation;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface SummaryLpsObservationRepository extends CrudRepository<SummaryLpsObservation, Integer>{

	public List<SummaryLpsObservation> findByRemarksId(Integer remarksId);
}
