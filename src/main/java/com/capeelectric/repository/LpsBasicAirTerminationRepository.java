package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.AirBasicDescription;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface LpsBasicAirTerminationRepository
		extends CrudRepository<AirBasicDescription, Integer> {

	public Optional<AirBasicDescription> findByFileId(String fileId);

}
