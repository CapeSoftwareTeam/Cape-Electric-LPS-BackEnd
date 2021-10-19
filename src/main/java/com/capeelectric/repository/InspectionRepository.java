package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.PeriodicInspection;
/**
 * 
 * @author capeelectricsoftware
 *
 */
public interface InspectionRepository extends CrudRepository<PeriodicInspection, Integer>{

	public Optional<PeriodicInspection> findBySiteId(Integer siteId);

	public List<PeriodicInspection> findByUserNameAndSiteId(String userName, Integer siteId);

}