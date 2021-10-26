/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.EarthStudDescription;


/**
 * @author CAPE-SOFTWARE
 *
 */
public interface EarthStudRepository extends CrudRepository<EarthStudDescription, Integer>{

	public List<EarthStudDescription> findByUserNameAndBasicLpsId(String userName, Integer basicLpsId);

	public Optional<EarthStudDescription> findByBasicLpsId(Integer basicLpsId);
}
