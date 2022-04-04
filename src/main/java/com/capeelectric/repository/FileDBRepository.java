package com.capeelectric.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.ResponseFile;

@Repository

public interface FileDBRepository extends CrudRepository<ResponseFile, Integer> {
	List<ResponseFile> findByLpsId(Integer lpsId);

//	ResponseFile findByLpsIdAndComponentName(Integer lpsId, String componentName);

	ResponseFile findByLpsIdAndComponentNameAndIndex(Integer lpsId, String componentName, Integer index);

}
