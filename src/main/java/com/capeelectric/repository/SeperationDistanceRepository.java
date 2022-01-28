/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.SeperationDistanceDescription;
import com.capeelectric.model.SeperationDistanceReport;


/**
 * @author CAPE-SOFTWARE
 *
 */
public interface SeperationDistanceRepository extends CrudRepository<SeperationDistanceReport, Integer>{

	public List<SeperationDistanceReport> findByUserNameAndBasicLpsId(String userName, Integer basicLpsId);

	public Optional<SeperationDistanceReport> findByBasicLpsId(Integer basicLpsId);
}
