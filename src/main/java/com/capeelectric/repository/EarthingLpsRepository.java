/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.model.EarthingReport;


/**
 * @author CAPE-SOFTWARE
 *
 */
public interface EarthingLpsRepository extends CrudRepository<EarthingReport, Integer>{
	
	public List<EarthingReport> findByUserNameAndBasicLpsId(String userName, Integer basicLpsId);

	public Optional<EarthingReport> findByBasicLpsId(Integer basicLpsId);

}
