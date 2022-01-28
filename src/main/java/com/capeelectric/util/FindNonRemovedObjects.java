package com.capeelectric.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.capeelectric.model.AirTermination;
import com.capeelectric.model.DownConductorDescription;
import com.capeelectric.model.DownConductorReport;
import com.capeelectric.model.EarthStudDescription;
import com.capeelectric.model.EarthStudReport;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.model.EarthingReport;
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.model.SPD;
import com.capeelectric.model.SeperationDistanceDescription;
import com.capeelectric.model.SeperationDistanceReport;
import com.capeelectric.model.SpdReport;

/**
 * This FindNonRemovedObject Util class finding non Removed object for all_steps
 * 
 * @author capeelectricsoftware
 *
 */

@Configuration
public class FindNonRemovedObjects {
	
	public List<LpsAirDiscription> findNonRemovedAirTerminationBuildings(AirTermination airTermination) {

		ArrayList<LpsAirDiscription> lpsAirDiscription = new ArrayList<LpsAirDiscription>();
		List<LpsAirDiscription> findNonRemoveBuildings = airTermination.getLpsAirDescription();
		for (LpsAirDiscription  LpsAirDiscriptionItr: findNonRemoveBuildings) {
			if (LpsAirDiscriptionItr.getFlag()==null || !LpsAirDiscriptionItr.getFlag().equalsIgnoreCase("R")) {
				if(LpsAirDiscriptionItr.getFlag()==null) {
					LpsAirDiscriptionItr.setFlag("N");
				}
				lpsAirDiscription.add(LpsAirDiscriptionItr);
				 
			}
		}
		return lpsAirDiscription;
	}
	
	public List<DownConductorDescription> findNonRemovedDownConductorsBuildings(DownConductorReport downConductorReport) {

		ArrayList<DownConductorDescription> downConductorDescription = new ArrayList<DownConductorDescription>();
		List<DownConductorDescription> findNonRemoveBuildings = downConductorReport.getDownConductorDescription();
		for (DownConductorDescription  downConductorDescriptionItr: findNonRemoveBuildings) {
			if (downConductorDescriptionItr.getFlag()==null || !downConductorDescriptionItr.getFlag().equalsIgnoreCase("R")) {
				if(downConductorDescriptionItr.getFlag()==null) {
					downConductorDescriptionItr.setFlag("N");
				}
				downConductorDescription.add(downConductorDescriptionItr);
				 
			}
		}
		return downConductorDescription;
	}
	
	public List<EarthingLpsDescription> findNonRemovedEarthingLpsBuildings(EarthingReport earthingReport) {

		ArrayList<EarthingLpsDescription> earthingLpsDescription = new ArrayList<EarthingLpsDescription>();
		List<EarthingLpsDescription> findNonRemoveBuildings = earthingReport.getEarthingLpsDescription();
		for (EarthingLpsDescription  earthingLpsDescriptionItr: findNonRemoveBuildings) {
			if (earthingLpsDescriptionItr.getFlag()==null || !earthingLpsDescriptionItr.getFlag().equalsIgnoreCase("R")) {
				if(earthingLpsDescriptionItr.getFlag()==null) {
					earthingLpsDescriptionItr.setFlag("N");
				}
				earthingLpsDescription.add(earthingLpsDescriptionItr);			 
			}
		}
		return earthingLpsDescription;
	}
	
	public List<SPD> findNonRemovedSpdBuildings(SpdReport spdReport) {

		ArrayList<SPD> spd = new ArrayList<SPD>();
		List<SPD> findNonRemoveBuildings = spdReport.getSpd();
		for (SPD  spdItr: findNonRemoveBuildings) {
			if (spdItr.getFlag()==null || !spdItr.getFlag().equalsIgnoreCase("R")) {
				if(spdItr.getFlag()==null) {
					spdItr.setFlag("N");
				}
				spd.add(spdItr);			 
			}
		}
		return spd;
	}
	
	public List<SeperationDistanceDescription> findNonRemovedSeperationDistanceBuildings(SeperationDistanceReport seperationDistanceReport) {

		ArrayList<SeperationDistanceDescription> seperationDistanceDescription = new ArrayList<SeperationDistanceDescription>();
		List<SeperationDistanceDescription> findNonRemoveBuildings = seperationDistanceReport.getSeperationDistanceDescription();
		for (SeperationDistanceDescription  seperationDistanceDescriptionItr: findNonRemoveBuildings) {
			if (seperationDistanceDescriptionItr.getFlag()==null || !seperationDistanceDescriptionItr.getFlag().equalsIgnoreCase("R")) {
				if(seperationDistanceDescriptionItr.getFlag()==null) {
					seperationDistanceDescriptionItr.setFlag("N");
				}
				seperationDistanceDescription.add(seperationDistanceDescriptionItr);			 
			}
		}
		return seperationDistanceDescription;
	}
	
	public List<EarthStudDescription> findNonRemovedEarthStudBuildings(EarthStudReport earthStudReport) {

		ArrayList<EarthStudDescription> earthStudDescription = new ArrayList<EarthStudDescription>();
		List<EarthStudDescription> findNonRemoveBuildings = earthStudReport.getEarthStudDescription();
		for (EarthStudDescription  earthStudDescriptionItr: findNonRemoveBuildings) {
			if (earthStudDescriptionItr.getFlag()==null || !earthStudDescriptionItr.getFlag().equalsIgnoreCase("R")) {
				if(earthStudDescriptionItr.getFlag()==null) {
					earthStudDescriptionItr.setFlag("N");
				}
				earthStudDescription.add(earthStudDescriptionItr);			 
			}
		}
		return earthStudDescription;
	}

}
