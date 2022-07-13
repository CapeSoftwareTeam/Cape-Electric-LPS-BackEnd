/**
 * 
 */
package com.capeelectric.repository;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.DownConductorDescription;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface DownConductorListRepository extends CrudRepository<DownConductorDescription, Integer>{

	public DownConductorDescription findByBuildingCount(Integer buildingCount);
}
