/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.EarthStudDescription;
import com.capeelectric.model.EarthStudReport;


/**
 * @author CAPE-SOFTWARE
 *
 */
public interface EarthStudRepository extends CrudRepository<EarthStudReport, Integer>{

	public List<EarthStudReport> findByUserNameAndBasicLpsId(String userName, Integer basicLpsId);

	public Optional<EarthStudReport> findByBasicLpsId(Integer basicLpsId);
}
