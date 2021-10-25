package com.capeelectric.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.LpsAirDiscription;


/**
 * @author CAPE-SOFTWARE
 *
 */
@Repository
public interface AirTerminationLpsRepository extends CrudRepository<LpsAirDiscription, Integer> {

}
