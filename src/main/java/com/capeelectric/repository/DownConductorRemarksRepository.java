/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.capeelectric.model.remarks.DownConductorReportRemarks;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface DownConductorRemarksRepository extends CrudRepository<DownConductorReportRemarks, Integer> {

	List<DownConductorReportRemarks> findByUserNameAndBasicLpsId(String userName, Integer basicLpsId);
	
	List<DownConductorReportRemarks> findByBasicLpsId(Integer basicLpsId);
}
