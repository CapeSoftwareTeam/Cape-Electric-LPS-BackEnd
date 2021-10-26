/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.SeperationDistanceDescription;


/**
 * @author CAPE-SOFTWARE
 *
 */
public interface SeperationDistanceRepository extends CrudRepository<SeperationDistanceDescription, Integer>{

	public List<SeperationDistanceDescription> findByUserNameAndBasicLpsId(String userName, Integer basicLpsId);

	public Optional<SeperationDistanceDescription> findByBasicLpsId(Integer basicLpsId);
}
