package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.LpsVerticalAirTermination;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface LpsVerticalAirTerminationRepository
		extends CrudRepository<LpsVerticalAirTermination, Integer> {

	public Optional<LpsVerticalAirTermination> findByFileIdVAir(String fileId);

}
