/**
 * 
 */
package com.capeelectric.repository;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.SPD;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface SPDListRepository extends CrudRepository<SPD, Integer>{

	public SPD findByBuildingCount(Integer buildingCount);
}
