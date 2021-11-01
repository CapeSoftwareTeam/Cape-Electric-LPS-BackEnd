/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.BasicLps;


/**
 * @author CAPE-SOFTWARE
 *
 */
public interface BasicLpsRepository extends CrudRepository<BasicLps, Integer> {

	List<BasicLps> findByUserNameAndBasicLpsId(String userName, Integer basicLpsId);

	Optional<BasicLps> findByBasicLpsId(Integer basicLpsId);
	
	Optional<BasicLps> findByClientName(String clientName);

}
