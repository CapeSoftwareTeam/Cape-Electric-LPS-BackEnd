/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.SPDDescription;


/**
 * @author CAPE-SOFTWARE
 *
 */
public interface SPDRepository extends CrudRepository<SPDDescription, Integer>{

	public List<SPDDescription> findByUserNameAndBasicLpsId(String userName, Integer basicLpsId);

	public Optional<SPDDescription> findByBasicLpsId(Integer basicLpsId);
}
