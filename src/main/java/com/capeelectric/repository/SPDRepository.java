/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.SPD;


/**
 * @author CAPE-SOFTWARE
 *
 */
public interface SPDRepository extends CrudRepository<SPD, Integer>{

	public List<SPD> findByUserNameAndBasicLpsId(String userName, Integer basicLpsId);

	public Optional<SPD> findByBasicLpsId(Integer basicLpsId);
}
