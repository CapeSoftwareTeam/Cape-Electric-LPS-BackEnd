package com.capeelectric.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.capeelectric.model.AirTermination;
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

}
