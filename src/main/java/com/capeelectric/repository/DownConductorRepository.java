/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.DownConductorDescription;


/**
 * @author CAPE-SOFTWARE
 *
 */
public interface DownConductorRepository extends CrudRepository<DownConductorDescription, Integer>{

	public List<DownConductorDescription> findByUserNameAndBasicLpsId(String userName, Integer basicLpsId);

	public Optional<DownConductorDescription> findByBasicLpsId(Integer basicLpsId);
}
