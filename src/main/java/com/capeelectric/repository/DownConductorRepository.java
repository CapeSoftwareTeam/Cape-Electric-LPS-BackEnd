/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.DownConductorDescription;
import com.capeelectric.model.DownConductorReport;


/**
 * @author CAPE-SOFTWARE
 *
 */
public interface DownConductorRepository extends CrudRepository<DownConductorReport, Integer>{

	public List<DownConductorReport> findByUserNameAndBasicLpsId(String userName, Integer basicLpsId);

	public Optional<DownConductorReport> findByBasicLpsId(Integer basicLpsId);
}
