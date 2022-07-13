/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.BasicLps;


/**
 * @author CAPE-SOFTWARE
 *
 */
public interface BasicLpsRepository extends CrudRepository<BasicLps, Integer> {

	List<BasicLps> findByUserNameAndBasicLpsId(String userName, Integer basicLpsId);

	public Optional<BasicLps> findByBasicLpsId(Integer basicLpsId);
	
	public Optional<BasicLps> findByClientNameAndStatus(String clientName,String active);

	List<BasicLps> findByUserName(String userName);

	public Optional<BasicLps> findByClientName(String clientName);

}
