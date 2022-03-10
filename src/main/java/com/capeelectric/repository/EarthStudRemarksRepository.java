package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.capeelectric.model.remarks.EarthStudRemarksReport;

public interface EarthStudRemarksRepository extends CrudRepository<EarthStudRemarksReport, Integer>{

	public List<EarthStudRemarksReport> findByUserNameAndBasicLpsId(String userName, Integer basicLpsId);

	public List<EarthStudRemarksReport> findByBasicLpsId(Integer basicLpsId);
}
