/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.capeelectric.model.remarks.EarthingReportRemarks;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface EarthingLpsRemarksRepository extends CrudRepository<EarthingReportRemarks, Integer> {

	List<EarthingReportRemarks> findByUserNameAndBasicLpsId(String userName, Integer basicLpsId);
	
	List<EarthingReportRemarks> findByBasicLpsId(Integer basicLpsId);
}
