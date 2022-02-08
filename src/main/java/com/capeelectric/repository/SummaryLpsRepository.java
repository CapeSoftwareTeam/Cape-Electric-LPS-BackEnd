/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.SummaryLps;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface SummaryLpsRepository extends CrudRepository<SummaryLps, Integer>{

	public List<SummaryLps> findByUserNameAndBasicLpsId(String userName, Integer basicLpsId);

	public Optional<SummaryLps> findByBasicLpsId(Integer basicLpsId);
}
