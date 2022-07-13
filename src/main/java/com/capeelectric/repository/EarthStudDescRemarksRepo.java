package com.capeelectric.repository;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.EarthStudDescription;
import com.capeelectric.model.remarks.EarthStudDescriptionRemarks;

public interface EarthStudDescRemarksRepo extends CrudRepository<EarthStudDescriptionRemarks, Integer>{

	public EarthStudDescriptionRemarks findByBuildingCount(Integer buildingCount);
}
