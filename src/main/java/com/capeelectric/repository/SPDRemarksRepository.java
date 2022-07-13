/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.capeelectric.model.remarks.SPDReportRemarks;
/**
 * @author CAPE-SOFTWARE
 *
 */
public interface SPDRemarksRepository extends CrudRepository<SPDReportRemarks, Integer> {

	List<SPDReportRemarks> findByUserNameAndBasicLpsId(String userName, Integer basicLpsId);
	
	List<SPDReportRemarks> findByBasicLpsId(Integer basicLpsId);
}
