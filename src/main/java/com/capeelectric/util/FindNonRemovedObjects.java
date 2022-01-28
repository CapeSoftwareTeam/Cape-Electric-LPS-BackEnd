package com.capeelectric.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.capeelectric.model.AirTermination;
import com.capeelectric.model.DownConductorDescription;
import com.capeelectric.model.DownConductorReport;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.model.EarthingReport;
import com.capeelectric.model.LpsAirDiscription;

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

}
