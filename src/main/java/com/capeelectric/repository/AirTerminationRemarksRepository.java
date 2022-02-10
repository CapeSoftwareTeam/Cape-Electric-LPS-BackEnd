/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.remarks.AirTerminationRemarks;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface AirTerminationRemarksRepository extends CrudRepository<AirTerminationRemarks, Integer> {

	List<AirTerminationRemarks> findByUserNameAndBasicLpsId(String userName, Integer basicLpsId);
	
	List<AirTerminationRemarks> findByBasicLpsId(Integer basicLpsId);
}
