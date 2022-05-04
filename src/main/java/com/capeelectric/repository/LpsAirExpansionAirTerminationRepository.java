package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.AirExpansion;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface LpsAirExpansionAirTerminationRepository
		extends CrudRepository<AirExpansion, Integer> {

	public Optional<AirExpansion> findByFileIdEP(Integer fileId);

}
