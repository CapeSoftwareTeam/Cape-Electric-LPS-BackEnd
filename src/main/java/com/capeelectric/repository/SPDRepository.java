/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.capeelectric.model.SpdReport;


/**
 * @author CAPE-SOFTWARE
 *
 */
public interface SPDRepository extends CrudRepository<SpdReport, Integer>{

	public List<SpdReport> findByUserNameAndBasicLpsId(String userName, Integer basicLpsId);

	public Optional<SpdReport> findByBasicLpsId(Integer basicLpsId);
}
