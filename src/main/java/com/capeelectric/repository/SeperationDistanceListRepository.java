/**
 * 
 */
package com.capeelectric.repository;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.SeperationDistanceDescription;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface SeperationDistanceListRepository extends CrudRepository<SeperationDistanceDescription, Integer>{

	public SeperationDistanceDescription findByBuildingCount(Integer buildingCount);
}
