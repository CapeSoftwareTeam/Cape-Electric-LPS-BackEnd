package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.BasicLps;
import com.capeelectric.model.LpsAirDiscription;


/**
 * @author CAPE-SOFTWARE
 *
 */
@Repository
public interface AirTerminationLpsRepository extends CrudRepository<LpsAirDiscription, Integer> {

	List<LpsAirDiscription> findByUserNameAndBasicLpsId(String userName, Integer basicLpsId);
	Optional<LpsAirDiscription> findByBasicLpsId(Integer basicLpsId);
}
