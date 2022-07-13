package com.capeelectric.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.ResponseFile;

@Repository

public interface FileDBRepository extends CrudRepository<ResponseFile, Integer> {
	public List<ResponseFile> findByLpsId(Integer lpsId);
	
	public ResponseFile findByLpsIdAndComponentNameAndFileName(Integer lpsId, String componentName, String fileName);
	
	@Query(value = "select * FROM file_upload_lps_table where building_count is NULL", 
			  nativeQuery = true)
	public List<ResponseFile> findByLpsIdBuildingCount(Integer basicLpsId,String buildingCount);

	public List<ResponseFile> findByBuildingCount(Integer buildingCount);

}
