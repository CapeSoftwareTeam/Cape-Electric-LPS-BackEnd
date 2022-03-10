/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.capeelectric.model.remarks.SeperationDistanceReportRemarks;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface SeperationDistanceRemarksRepository extends CrudRepository<SeperationDistanceReportRemarks, Integer> {

	List<SeperationDistanceReportRemarks> findByUserNameAndBasicLpsId(String userName, Integer basicLpsId);
	
	List<SeperationDistanceReportRemarks> findByBasicLpsId(Integer basicLpsId);
}
