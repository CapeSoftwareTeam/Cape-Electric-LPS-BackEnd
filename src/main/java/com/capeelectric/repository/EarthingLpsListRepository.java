/**
 * 
 */
package com.capeelectric.repository;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.EarthingLpsDescription;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface EarthingLpsListRepository extends CrudRepository<EarthingLpsDescription, Integer>{

	public EarthingLpsDescription findByBuildingCount(Integer buildingCount);
}
