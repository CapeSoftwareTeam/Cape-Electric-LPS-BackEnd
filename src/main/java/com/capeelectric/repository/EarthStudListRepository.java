/**
 * 
 */
package com.capeelectric.repository;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.EarthStudDescription;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface EarthStudListRepository extends CrudRepository<EarthStudDescription, Integer>{

	public EarthStudDescription findByBuildingCount(Integer buildingCount);
}
