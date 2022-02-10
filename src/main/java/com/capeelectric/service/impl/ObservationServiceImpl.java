/**
 * 
 */
package com.capeelectric.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capeelectric.model.AllStepsRemarks;
import com.capeelectric.model.remarks.AirTerminationRemarks;
import com.capeelectric.model.remarks.DownConductorReportRemarks;
import com.capeelectric.model.remarks.EarthStudRemarksReport;
import com.capeelectric.model.remarks.EarthingReportRemarks;
import com.capeelectric.model.remarks.SPDReportRemarks;
import com.capeelectric.model.remarks.SeperationDistanceReportRemarks;
import com.capeelectric.repository.AirTerminationRemarksRepository;
import com.capeelectric.repository.DownConductorRemarksRepository;
import com.capeelectric.repository.EarthStudRemarksRepository;
import com.capeelectric.repository.EarthingLpsRemarksRepository;
import com.capeelectric.repository.SPDRemarksRepository;
import com.capeelectric.repository.SeperationDistanceRemarksRepository;
import com.capeelectric.service.ObservationService;
import com.capeelectric.util.FindNonRemovedRemarksObjects;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Service
public class ObservationServiceImpl implements ObservationService {
	@Autowired
	private AirTerminationRemarksRepository airTerminationRemarksRepository;

	@Autowired
	private DownConductorRemarksRepository downConductorRemarksRepository;

	@Autowired
	private EarthingLpsRemarksRepository earthingLpsRemarksRepository;

	@Autowired
	private SPDRemarksRepository spdRemarksRepository;

	@Autowired
	private SeperationDistanceRemarksRepository seperationDistanceRemarksRepository;

	@Autowired
	private EarthStudRemarksRepository earthStudRemarksRepository;

	@Autowired
	private FindNonRemovedRemarksObjects findNonRemovedRemarksObjects;

	@Override
	public AllStepsRemarks retrieveObservationsInSummary(Integer basicLpsId) {
		AllStepsRemarks allStepsRemarks = new AllStepsRemarks();

		List<AirTerminationRemarks> airTerminationRemarks = airTerminationRemarksRepository
				.findByBasicLpsId(basicLpsId);

		List<DownConductorReportRemarks> downConductorReportRemarks = downConductorRemarksRepository
				.findByBasicLpsId(basicLpsId);
		
		List<EarthingReportRemarks> earthingReportRemarks = earthingLpsRemarksRepository
				.findByBasicLpsId(basicLpsId);
		
		List<SPDReportRemarks> spdReportRemarks = spdRemarksRepository
				.findByBasicLpsId(basicLpsId);

		List<SeperationDistanceReportRemarks> seperationDistanceReportRemarks = seperationDistanceRemarksRepository
				.findByBasicLpsId(basicLpsId);
		
		List<EarthStudRemarksReport> earthStudRemarksReport = earthStudRemarksRepository
				.findByBasicLpsId(basicLpsId);
		
		if (!airTerminationRemarks.isEmpty() && airTerminationRemarks != null) {
			if (airTerminationRemarks.get(0).getLpsAirDiscription().size() > 0) {
				airTerminationRemarks.get(0).setLpsAirDiscription(findNonRemovedRemarksObjects
						.findNonRemovedAirTerminationBuildings(airTerminationRemarks.get(0)));
			}
			allStepsRemarks.setAirTermination(airTerminationRemarks);
		}

		if (!downConductorReportRemarks.isEmpty() && downConductorReportRemarks != null) {
			if (downConductorReportRemarks.get(0).getDownConductorDescription().size() > 0) {
				downConductorReportRemarks.get(0).setDownConductorDescription(findNonRemovedRemarksObjects
						.findNonRemovedDownConductorsBuildings(downConductorReportRemarks.get(0)));
			}
			allStepsRemarks.setDownConductorReport(downConductorReportRemarks);
		}

		if (!earthingReportRemarks.isEmpty() && earthingReportRemarks != null) {
			if (earthingReportRemarks.get(0).getEarthingLpsDescription().size() > 0) {
				earthingReportRemarks.get(0).setEarthingLpsDescription(findNonRemovedRemarksObjects
						.findNonRemovedEarthingLpsBuildings(earthingReportRemarks.get(0)));
			}
			allStepsRemarks.setEarthingReport(earthingReportRemarks);
		}

		if (!spdReportRemarks.isEmpty() && spdReportRemarks != null) {
			if (spdReportRemarks.get(0).getSpd().size() > 0) {
				spdReportRemarks.get(0).setSpd(findNonRemovedRemarksObjects
						.findNonRemovedSpdBuildings(spdReportRemarks.get(0)));
			}
			allStepsRemarks.setSpdReport(spdReportRemarks);
		}

		if (!seperationDistanceReportRemarks.isEmpty() && seperationDistanceReportRemarks != null) {
			if (seperationDistanceReportRemarks.get(0).getSeperationDistanceDescription().size() > 0) {
				seperationDistanceReportRemarks.get(0).setSeperationDistanceDescription(findNonRemovedRemarksObjects
						.findNonRemovedSeperationDistanceBuildings(seperationDistanceReportRemarks.get(0)));
			}
			allStepsRemarks.setSeperationDistanceReport(seperationDistanceReportRemarks);
		}

		if (!earthStudRemarksReport.isEmpty() && earthStudRemarksReport != null) {
			if (earthStudRemarksReport.get(0).getEarthStudDescriptionRemarks().size() > 0) {
				earthStudRemarksReport.get(0).setEarthStudDescriptionRemarks(findNonRemovedRemarksObjects
						.findNonRemovedEarthStudRemarksBuildings(earthStudRemarksReport.get(0)));
			}
			allStepsRemarks.setEarthStudReport(earthStudRemarksReport);
		}
		return allStepsRemarks;
	}

}
