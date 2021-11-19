/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.EarthingLpsDescription;


/**
 * @author CAPE-SOFTWARE
 *
 */
public interface EarthingLpsRepository extends CrudRepository<EarthingLpsDescription, Integer>{
	
	public List<EarthingLpsDescription> findByUserNameAndBasicLpsId(String userName, Integer basicLpsId);

	public Optional<EarthingLpsDescription> findByBasicLpsId(Integer basicLpsId);

}
