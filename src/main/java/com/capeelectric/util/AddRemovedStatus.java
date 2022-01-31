/**
 * 
 */
package com.capeelectric.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.model.DownConductorDescription;
import com.capeelectric.model.EarthStudDescription;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.model.SPD;
import com.capeelectric.model.SeperationDistanceDescription;
import com.capeelectric.repository.DownConductorListRepository;
import com.capeelectric.repository.EarthStudListRepository;
import com.capeelectric.repository.EarthingLpsListRepository;
import com.capeelectric.repository.SPDListRepository;
import com.capeelectric.repository.SeperationDistanceListRepository;
import com.capeelectric.service.impl.AirTerminationLpsServiceImpl;

/**
 * @author CAPE-SOFTWARE
 *
 */

@Configuration
public class AddRemovedStatus {
	private static final Logger logger = LoggerFactory.getLogger(AddRemovedStatus.class);

	@Autowired
	private DownConductorListRepository downConductorListRepository;
	
	@Autowired
	private EarthingLpsListRepository earthingLpsListRepository;
	
	@Autowired
	private SPDListRepository spdListRepository;
	
	@Autowired
	private SeperationDistanceListRepository seperationDistanceListRepository;
	
	@Autowired
	private EarthStudListRepository earthStudListRepository;

	//Method for adding R status in Down Conductors
		public void addRemoveStatusInDownConductors(List<LpsAirDiscription> lpsAirDiscription)
				throws AirTerminationException {

			for (LpsAirDiscription lpsAirDiscriptionItr : lpsAirDiscription) {
				if (lpsAirDiscriptionItr != null && lpsAirDiscriptionItr.getBuildingCount() != null
						&& lpsAirDiscriptionItr.getFlag().equalsIgnoreCase("R")) {
					try {
						DownConductorDescription downConductorDescriptionRepo = downConductorListRepository.findByBuildingCount(lpsAirDiscriptionItr.getBuildingCount());
						if (downConductorDescriptionRepo != null
								&& downConductorDescriptionRepo.getBuildingCount().equals(lpsAirDiscriptionItr.getBuildingCount())) {
							downConductorDescriptionRepo.setFlag("R");
							downConductorListRepository.save(downConductorDescriptionRepo);
						}
					} catch (Exception e) {
						logger.error("Please check removed Air Termination Building data not available in Down Conductor"
								+ e.getMessage());
						throw new AirTerminationException(
								"Please check removed Air Termination Building data not available in Down Conductor"
										+ e.getMessage());
					}
				}
			}
		} 
		
		//Method for adding R status in Earthing Lps
		public void addRemoveStatusInEarthingLps(List<LpsAirDiscription> lpsAirDiscription)
				throws AirTerminationException {

			for (LpsAirDiscription lpsAirDiscriptionItr : lpsAirDiscription) {
				if (lpsAirDiscriptionItr != null && lpsAirDiscriptionItr.getBuildingCount() != null
						&& lpsAirDiscriptionItr.getFlag().equalsIgnoreCase("R")) {
					try {
						EarthingLpsDescription earthingLpsDescriptionRepo = earthingLpsListRepository.findByBuildingCount(lpsAirDiscriptionItr.getBuildingCount());
						if (earthingLpsDescriptionRepo != null
								&& earthingLpsDescriptionRepo.getBuildingCount().equals(lpsAirDiscriptionItr.getBuildingCount())) {
							earthingLpsDescriptionRepo.setFlag("R");
							earthingLpsListRepository.save(earthingLpsDescriptionRepo);
						}
					} catch (Exception e) {
						logger.debug("Please check removed Air Termination Building data not available in Earthing Lps"
								+ e.getMessage());
						throw new AirTerminationException(
								"Please check removed Air Termination Building data not available in Earthing Lps"
										+ e.getMessage());
					}
				}
			}
		}
		
		//Method for adding R status in SPD
		public void addRemoveStatusInSpd(List<LpsAirDiscription> lpsAirDiscription)
				throws AirTerminationException {

			for (LpsAirDiscription lpsAirDiscriptionItr : lpsAirDiscription) {
				if (lpsAirDiscriptionItr != null && lpsAirDiscriptionItr.getBuildingCount() != null
						&& lpsAirDiscriptionItr.getFlag().equalsIgnoreCase("R")) {
					try {
						SPD spdRepo = spdListRepository.findByBuildingCount(lpsAirDiscriptionItr.getBuildingCount());
						if (spdRepo != null
								&& spdRepo.getBuildingCount().equals(lpsAirDiscriptionItr.getBuildingCount())) {
							spdRepo.setFlag("R");
							spdListRepository.save(spdRepo);
						}
					} catch (Exception e) {
						logger.debug("Please check removed Air Termination Building data not available in SPD"
								+ e.getMessage());
						throw new AirTerminationException(
								"Please check removed Air Termination Building data not available in SPD"
										+ e.getMessage());
					}
				}
			}
		}
		
		//Method for adding R status in Seperation Distance
		public void addRemoveStatusInSeperationDistance(List<LpsAirDiscription> lpsAirDiscription)
				throws AirTerminationException {

			for (LpsAirDiscription lpsAirDiscriptionItr : lpsAirDiscription) {
				if (lpsAirDiscriptionItr != null && lpsAirDiscriptionItr.getBuildingCount() != null
						&& lpsAirDiscriptionItr.getFlag().equalsIgnoreCase("R")) {
					try {
						SeperationDistanceDescription seperationDistanceDescriptionRepo = seperationDistanceListRepository.findByBuildingCount(lpsAirDiscriptionItr.getBuildingCount());
						if (seperationDistanceDescriptionRepo != null
								&& seperationDistanceDescriptionRepo.getBuildingCount().equals(lpsAirDiscriptionItr.getBuildingCount())) {
							seperationDistanceDescriptionRepo.setFlag("R");
							seperationDistanceListRepository.save(seperationDistanceDescriptionRepo);
						}
					} catch (Exception e) {
						logger.debug("Please check removed Air Termination Building data not available in Seperation Distance"
								+ e.getMessage());
						throw new AirTerminationException(
								"Please check removed Air Termination Building data not available in Seperation Distance"
										+ e.getMessage());
					}
				}
			}
		}
		
		//Method for adding R status in Earth Stud
		public void addRemoveStatusInEarthStud(List<LpsAirDiscription> lpsAirDiscription)
				throws AirTerminationException {

			for (LpsAirDiscription lpsAirDiscriptionItr : lpsAirDiscription) {
				if (lpsAirDiscriptionItr != null && lpsAirDiscriptionItr.getBuildingCount() != null
						&& lpsAirDiscriptionItr.getFlag().equalsIgnoreCase("R")) {
					try {
						EarthStudDescription earthStudDescriptionRepo = earthStudListRepository.findByBuildingCount(lpsAirDiscriptionItr.getBuildingCount());
						if (earthStudDescriptionRepo != null
								&& earthStudDescriptionRepo.getBuildingCount().equals(lpsAirDiscriptionItr.getBuildingCount())) {
							earthStudDescriptionRepo.setFlag("R");
							earthStudListRepository.save(earthStudDescriptionRepo);
						}
					} catch (Exception e) {
						logger.debug("Please check removed Air Termination Building data not available in Earth Stud"
								+ e.getMessage());
						throw new AirTerminationException(
								"Please check removed Air Termination Building data not available in Earth Stud"
										+ e.getMessage());
					}
				}
			}
		}
}
