package com.capeelectric.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.capeelectric.model.AirBasicDescription;
import com.capeelectric.model.AirClamps;
import com.capeelectric.model.AirConnectors;
import com.capeelectric.model.AirExpansion;
import com.capeelectric.model.AirHolderDescription;
import com.capeelectric.model.AirHolderList;
import com.capeelectric.model.AirMeshDescription;
import com.capeelectric.model.AirTermination;
import com.capeelectric.model.BridgingDescription;
import com.capeelectric.model.Connectors;
import com.capeelectric.model.DownConductor;
import com.capeelectric.model.DownConductorDescription;
import com.capeelectric.model.DownConductorReport;
import com.capeelectric.model.DownConductorTesting;
import com.capeelectric.model.EarthElectrodeChamber;
import com.capeelectric.model.EarthElectrodeTesting;
import com.capeelectric.model.EarthStudDescription;
import com.capeelectric.model.EarthStudReport;
import com.capeelectric.model.EarthingClamps;
import com.capeelectric.model.EarthingDescription;
import com.capeelectric.model.EarthingDescriptionList;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.model.EarthingReport;
import com.capeelectric.model.EarthingSystem;
import com.capeelectric.model.Holder;
import com.capeelectric.model.LightningCounter;
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.model.LpsVerticalAirTermination;
import com.capeelectric.model.SPD;
import com.capeelectric.model.SeparateDistance;
import com.capeelectric.model.SeperationDistanceDescription;
import com.capeelectric.model.SeperationDistanceReport;
import com.capeelectric.model.SpdDescription;
import com.capeelectric.model.SpdReport;
import com.capeelectric.model.TestingJoint;
import com.capeelectric.model.VerticalAirTerminationList;

/**
 * This FindNonRemovedObject Util class finding non Removed object for all_steps
 * 
 * @author capeelectricsoftware
 *
 */

@Configuration
public class FindNonRemovedObjects {
	
	//find non removed values for LPS AIR TERMINATION
	public List<LpsAirDiscription> findNonRemovedAirTerminationBuildings(AirTermination airTermination) {

		ArrayList<LpsAirDiscription> lpsAirDiscription = new ArrayList<LpsAirDiscription>();
		List<LpsAirDiscription> findNonRemoveBuildings = airTermination.getLpsAirDescription();
		for (LpsAirDiscription  LpsAirDiscriptionItr: findNonRemoveBuildings) {
			if (LpsAirDiscriptionItr.getFlag()==null || !LpsAirDiscriptionItr.getFlag().equalsIgnoreCase("R")) {
				if(LpsAirDiscriptionItr.getFlag()==null) {
					LpsAirDiscriptionItr.setFlag("N");
				}
				LpsAirDiscriptionItr.setLpsVerticalAirTermination(findNonRemovedVAT(LpsAirDiscriptionItr.getLpsVerticalAirTermination()));
				LpsAirDiscriptionItr.setAirClamps(findNonRemovedAirClamps(LpsAirDiscriptionItr.getAirClamps()));
				LpsAirDiscriptionItr.setAirConnectors(findNonRemovedAirConnectors(LpsAirDiscriptionItr.getAirConnectors()));
				LpsAirDiscriptionItr.setAirExpansion(findNonRemovedAirExpansion(LpsAirDiscriptionItr.getAirExpansion()));
				LpsAirDiscriptionItr.setAirHolderDescription(findNonRemovedAirHolder(LpsAirDiscriptionItr.getAirHolderDescription()));
				LpsAirDiscriptionItr.setAirMeshDescription(findNonRemovedAirMesh(LpsAirDiscriptionItr.getAirMeshDescription()));
				LpsAirDiscriptionItr.setAirBasicDescription(findNonRemovedAirBasic(LpsAirDiscriptionItr.getAirBasicDescription()));
				lpsAirDiscription.add(LpsAirDiscriptionItr);
				 
			}
		}
		return lpsAirDiscription;
	}
	
	//find non removed values for LPS AIR TERMINATION childs	
	public List<LpsVerticalAirTermination> findNonRemovedVAT(List<LpsVerticalAirTermination> terminationList) {

		ArrayList<LpsVerticalAirTermination> unRemovedVerticalAirTermination = new ArrayList<LpsVerticalAirTermination>();
		for (LpsVerticalAirTermination lpsVerticalAirTerminationItr : terminationList) {
			if (lpsVerticalAirTerminationItr.getFlag() == null || !lpsVerticalAirTerminationItr.getFlag().equalsIgnoreCase("R")) {
				if (lpsVerticalAirTerminationItr.getFlag() == null) {
					lpsVerticalAirTerminationItr.setFlag("N");
				}
				lpsVerticalAirTerminationItr.setVerticalAirTerminationList(findNonRemovedVATList(lpsVerticalAirTerminationItr.getVerticalAirTerminationList()));
				unRemovedVerticalAirTermination.add(lpsVerticalAirTerminationItr);
			}
		}
		return unRemovedVerticalAirTermination;
	}
	
	public List<VerticalAirTerminationList> findNonRemovedVATList(List<VerticalAirTerminationList> verticalAirTerminationList) {

		ArrayList<VerticalAirTerminationList> unRemovedVerticalAirTerminationList = new ArrayList<VerticalAirTerminationList>();
		for (VerticalAirTerminationList lpsVerticalAirTerminationListItr : verticalAirTerminationList) {
			if (lpsVerticalAirTerminationListItr.getFlag() == null || !lpsVerticalAirTerminationListItr.getFlag().equalsIgnoreCase("R")) {
				if (lpsVerticalAirTerminationListItr.getFlag() == null) {
					lpsVerticalAirTerminationListItr.setFlag("N");
				}
				unRemovedVerticalAirTerminationList.add(lpsVerticalAirTerminationListItr);
			}
		}
		return unRemovedVerticalAirTerminationList;
	}
	
	public List<AirClamps> findNonRemovedAirClamps(List<AirClamps> listOfAirClamps) {

		ArrayList<AirClamps> unRemovedAirClamps = new ArrayList<AirClamps>();
		for (AirClamps airClamps : listOfAirClamps) {
			if (airClamps.getFlag() == null || !airClamps.getFlag().equalsIgnoreCase("R")) {
				if (airClamps.getFlag() == null) {
					airClamps.setFlag("N");
				}
				unRemovedAirClamps.add(airClamps);
			}
		}
		return unRemovedAirClamps;
	}
	
	public List<AirConnectors> findNonRemovedAirConnectors(List<AirConnectors> listOfAirConnectors) {

		ArrayList<AirConnectors> unRemovedAirConnectors = new ArrayList<AirConnectors>();
		for (AirConnectors airConnectors : listOfAirConnectors) {
			if (airConnectors.getFlag() == null || !airConnectors.getFlag().equalsIgnoreCase("R")) {
				if (airConnectors.getFlag() == null) {
					airConnectors.setFlag("N");
				}
				unRemovedAirConnectors.add(airConnectors);
			}
		}
		return unRemovedAirConnectors;
	}
	
	public List<AirExpansion> findNonRemovedAirExpansion(List<AirExpansion> listOfAirExpansion) {

		ArrayList<AirExpansion> unRemovedAirExpansion = new ArrayList<AirExpansion>();
		for (AirExpansion airExpansion : listOfAirExpansion) {
			if (airExpansion.getFlag() == null || !airExpansion.getFlag().equalsIgnoreCase("R")) {
				if (airExpansion.getFlag() == null) {
					airExpansion.setFlag("N");
				}
				unRemovedAirExpansion.add(airExpansion);
			}
		}
		return unRemovedAirExpansion;
	}
	
	public List<AirHolderDescription> findNonRemovedAirHolder(List<AirHolderDescription> listOfAirHolder) {

		ArrayList<AirHolderDescription> unRemovedAirHolderDescription = new ArrayList<AirHolderDescription>();
		for (AirHolderDescription airHolderDescription : listOfAirHolder) {
			if (airHolderDescription.getFlag() == null || !airHolderDescription.getFlag().equalsIgnoreCase("R")) {
				if (airHolderDescription.getFlag() == null) {
					airHolderDescription.setFlag("N");
				}
				airHolderDescription.setAirHolderList(findNonRemovedAirHolderList(airHolderDescription.getAirHolderList()));
				unRemovedAirHolderDescription.add(airHolderDescription);
			}
		}
		return unRemovedAirHolderDescription;
	}
	
	public List<AirHolderList> findNonRemovedAirHolderList(List<AirHolderList> listOfAirHolder) {

		ArrayList<AirHolderList> unRemovedAirHolderList = new ArrayList<AirHolderList>();
		for (AirHolderList airHolderList : listOfAirHolder) {
			if (airHolderList.getFlag() == null || !airHolderList.getFlag().equalsIgnoreCase("R")) {
				if (airHolderList.getFlag() == null) {
					airHolderList.setFlag("N");
				}
				unRemovedAirHolderList.add(airHolderList);
			}
		}
		return unRemovedAirHolderList;
	}
	
	public List<AirMeshDescription> findNonRemovedAirMesh(List<AirMeshDescription> listOfAirMesh) {

		ArrayList<AirMeshDescription> unRemovedAirMeshDescription = new ArrayList<AirMeshDescription>();
		for (AirMeshDescription airMeshDescription : listOfAirMesh) {
			if (airMeshDescription.getFlag() == null || !airMeshDescription.getFlag().equalsIgnoreCase("R")) {
				if (airMeshDescription.getFlag() == null) {
					airMeshDescription.setFlag("N");
				}
				unRemovedAirMeshDescription.add(airMeshDescription);
			}
		}
		return unRemovedAirMeshDescription;
	}
	
	public List<AirBasicDescription> findNonRemovedAirBasic(List<AirBasicDescription> listOfAirBasic) {

		ArrayList<AirBasicDescription> unRemovedAirBasicDescription = new ArrayList<AirBasicDescription>();
		for (AirBasicDescription airBasicDescription : listOfAirBasic) {
			if (airBasicDescription.getFlag() == null || !airBasicDescription.getFlag().equalsIgnoreCase("R")) {
				if (airBasicDescription.getFlag() == null) {
					airBasicDescription.setFlag("N");
				}
				unRemovedAirBasicDescription.add(airBasicDescription);
			}
		}
		return unRemovedAirBasicDescription;
	}

	//find non removed values for DOWN CONDUCTORS childs	
	public List<DownConductorDescription> findNonRemovedDownConductorsBuildings(DownConductorReport downConductorReport) {

		ArrayList<DownConductorDescription> downConductorDescription = new ArrayList<DownConductorDescription>();
		List<DownConductorDescription> findNonRemoveBuildings = downConductorReport.getDownConductorDescription();
		for (DownConductorDescription  downConductorDescriptionItr: findNonRemoveBuildings) {
			if (downConductorDescriptionItr.getFlag()==null || !downConductorDescriptionItr.getFlag().equalsIgnoreCase("R")) {
				if(downConductorDescriptionItr.getFlag()==null) {
					downConductorDescriptionItr.setFlag("N");
				}
				downConductorDescriptionItr.setBridgingDescription(findNonRemovedBridgingDescription(downConductorDescriptionItr.getBridgingDescription()));
				downConductorDescriptionItr.setHolder(findNonRemovedHolder(downConductorDescriptionItr.getHolder()));
				downConductorDescriptionItr.setConnectors(findNonRemovedConnectors(downConductorDescriptionItr.getConnectors()));
				downConductorDescriptionItr.setLightningCounter(findNonRemovedLightningCounter(downConductorDescriptionItr.getLightningCounter()));
				downConductorDescriptionItr.setTestingJoint(findNonRemovedTestingJoint(downConductorDescriptionItr.getTestingJoint()));
				downConductorDescriptionItr.setDownConductor(findNonDownConductor(downConductorDescriptionItr.getDownConductor()));
				downConductorDescriptionItr.setDownConductorTesting(findNonRemovedDownConductorTesting(downConductorDescriptionItr.getDownConductorTesting()));
				downConductorDescription.add(downConductorDescriptionItr);
				 
			}
		}
		return downConductorDescription;
	}
	
	//find non removed values for DOWN CONDUCTORS childs	
	public List<BridgingDescription> findNonRemovedBridgingDescription(List<BridgingDescription> bridgingDescriptionList) {

		ArrayList<BridgingDescription> unRemovedBridgingDescription = new ArrayList<BridgingDescription>();
		for (BridgingDescription bridgingDescription : bridgingDescriptionList) {
			if (bridgingDescription.getFlag() == null || !bridgingDescription.getFlag().equalsIgnoreCase("R")) {
				if (bridgingDescription.getFlag() == null) {
					bridgingDescription.setFlag("N");
				}
				unRemovedBridgingDescription.add(bridgingDescription);
			}
		}
		return unRemovedBridgingDescription;
	}
	
	public List<Holder> findNonRemovedHolder(List<Holder> listOfHolder) {

		ArrayList<Holder> unRemovedHolder = new ArrayList<Holder>();
		for (Holder holder : listOfHolder) {
			if (holder.getFlag() == null || !holder.getFlag().equalsIgnoreCase("R")) {
				if (holder.getFlag() == null) {
					holder.setFlag("N");
				}
				unRemovedHolder.add(holder);
			}
		}
		return unRemovedHolder;
	}
	
	public List<Connectors> findNonRemovedConnectors(List<Connectors> listOfConnectors) {

		ArrayList<Connectors> unRemovedConnectors = new ArrayList<Connectors>();
		for (Connectors connectors : listOfConnectors) {
			if (connectors.getFlag() == null || !connectors.getFlag().equalsIgnoreCase("R")) {
				if (connectors.getFlag() == null) {
					connectors.setFlag("N");
				}
				unRemovedConnectors.add(connectors);
			}
		}
		return unRemovedConnectors;
	}
	
	public List<LightningCounter> findNonRemovedLightningCounter(List<LightningCounter> listOfLightningCounter) {

		ArrayList<LightningCounter> unRemovedLightningCounter = new ArrayList<LightningCounter>();
		for (LightningCounter lightningCounter : listOfLightningCounter) {
			if (lightningCounter.getFlag() == null || !lightningCounter.getFlag().equalsIgnoreCase("R")) {
				if (lightningCounter.getFlag() == null) {
					lightningCounter.setFlag("N");
				}
				unRemovedLightningCounter.add(lightningCounter);
			}
		}
		return unRemovedLightningCounter;
	}
	
	public List<TestingJoint> findNonRemovedTestingJoint(List<TestingJoint> listOfTestingJoint) {

		ArrayList<TestingJoint> unRemovedTestingJoint = new ArrayList<TestingJoint>();
		for (TestingJoint testingJoint : listOfTestingJoint) {
			if (testingJoint.getFlag() == null || !testingJoint.getFlag().equalsIgnoreCase("R")) {
				if (testingJoint.getFlag() == null) {
					testingJoint.setFlag("N");
				}
				unRemovedTestingJoint.add(testingJoint);
			}
		}
		return unRemovedTestingJoint;
	}
	
	public List<DownConductor> findNonDownConductor(List<DownConductor> listOfDownConductor) {

		ArrayList<DownConductor> unRemovedDownConductor = new ArrayList<DownConductor>();
		for (DownConductor downConductor : listOfDownConductor) {
			if (downConductor.getFlag() == null || !downConductor.getFlag().equalsIgnoreCase("R")) {
				if (downConductor.getFlag() == null) {
					downConductor.setFlag("N");
				}
				unRemovedDownConductor.add(downConductor);
			}
		}
		return unRemovedDownConductor;
	}
	
	public List<DownConductorTesting> findNonRemovedDownConductorTesting(List<DownConductorTesting> listOfDownConductorTesting) {

		ArrayList<DownConductorTesting> unRemovedDownConductorTesting = new ArrayList<DownConductorTesting>();
		for (DownConductorTesting downConductorTesting : listOfDownConductorTesting) {
			if (downConductorTesting.getFlag() == null || !downConductorTesting.getFlag().equalsIgnoreCase("R")) {
				if (downConductorTesting.getFlag() == null) {
					downConductorTesting.setFlag("N");
				}
				unRemovedDownConductorTesting.add(downConductorTesting);
			}
		}
		return unRemovedDownConductorTesting;
	}
	
	//find non removed values for EARTHING LPS	
	public List<EarthingLpsDescription> findNonRemovedEarthingLpsBuildings(EarthingReport earthingReport) {

		ArrayList<EarthingLpsDescription> earthingLpsDescription = new ArrayList<EarthingLpsDescription>();
		List<EarthingLpsDescription> findNonRemoveBuildings = earthingReport.getEarthingLpsDescription();
		for (EarthingLpsDescription  earthingLpsDescriptionItr: findNonRemoveBuildings) {
			if (earthingLpsDescriptionItr.getFlag()==null || !earthingLpsDescriptionItr.getFlag().equalsIgnoreCase("R")) {
				if(earthingLpsDescriptionItr.getFlag()==null) {
					earthingLpsDescriptionItr.setFlag("N");
				}
				earthingLpsDescriptionItr.setEarthingDescription(findNonRemovedEarthingDescription(earthingLpsDescriptionItr.getEarthingDescription()));
				earthingLpsDescriptionItr.setEarthingClamps(findNonRemovedEarthingClamps(earthingLpsDescriptionItr.getEarthingClamps()));
				earthingLpsDescriptionItr.setEarthingElectrodeChamber(findNonRemovedEarthElectrodeChamber(earthingLpsDescriptionItr.getEarthingElectrodeChamber()));
				earthingLpsDescriptionItr.setEarthingSystem(findNonRemovedEarthingSystem(earthingLpsDescriptionItr.getEarthingSystem()));
				earthingLpsDescriptionItr.setEarthElectrodeTesting(findNonRemovedEarthElectrodeTesting(earthingLpsDescriptionItr.getEarthElectrodeTesting()));
				earthingLpsDescription.add(earthingLpsDescriptionItr);			 
			}
		}
		return earthingLpsDescription;
	}
		
		//find non removed values for EARTHING LPS childs	
		public List<EarthingDescription> findNonRemovedEarthingDescription(List<EarthingDescription> earthingDescriptionList) {

			ArrayList<EarthingDescription> unRemovedEarthingDescription = new ArrayList<EarthingDescription>();
			for (EarthingDescription earthingDescription : earthingDescriptionList) {
				if (earthingDescription.getFlag() == null || !earthingDescription.getFlag().equalsIgnoreCase("R")) {
					if (earthingDescription.getFlag() == null) {
						earthingDescription.setFlag("N");
					}
					earthingDescription.setEarthingDescriptionList(findNonRemovedEarthingDescriptionList(earthingDescription.getEarthingDescriptionList()));
					unRemovedEarthingDescription.add(earthingDescription);
				}
			}
			return unRemovedEarthingDescription;
		}
		
		public List<EarthingDescriptionList> findNonRemovedEarthingDescriptionList(List<EarthingDescriptionList> earthingDescriptionList) {

			ArrayList<EarthingDescriptionList> unRemovedEarthingDescriptionList = new ArrayList<EarthingDescriptionList>();
			for (EarthingDescriptionList earthingDescriptionListItr : earthingDescriptionList) {
				if (earthingDescriptionListItr.getFlag() == null || !earthingDescriptionListItr.getFlag().equalsIgnoreCase("R")) {
					if (earthingDescriptionListItr.getFlag() == null) {
						earthingDescriptionListItr.setFlag("N");
					}
					unRemovedEarthingDescriptionList.add(earthingDescriptionListItr);
				}
			}
			return unRemovedEarthingDescriptionList;
		}
		
		public List<EarthingClamps> findNonRemovedEarthingClamps(List<EarthingClamps> listOfEarthingClamps) {

			ArrayList<EarthingClamps> unRemovedEarthingClamps = new ArrayList<EarthingClamps>();
			for (EarthingClamps earthingClamps : listOfEarthingClamps) {
				if (earthingClamps.getFlag() == null || !earthingClamps.getFlag().equalsIgnoreCase("R")) {
					if (earthingClamps.getFlag() == null) {
						earthingClamps.setFlag("N");
					}
					unRemovedEarthingClamps.add(earthingClamps);
				}
			}
			return unRemovedEarthingClamps;
		}
		
		public List<EarthElectrodeChamber> findNonRemovedEarthElectrodeChamber(List<EarthElectrodeChamber> listOfEarthElectrodeChamber) {

			ArrayList<EarthElectrodeChamber> unRemovedEarthElectrodeChamber = new ArrayList<EarthElectrodeChamber>();
			for (EarthElectrodeChamber earthElectrodeChamber : listOfEarthElectrodeChamber) {
				if (earthElectrodeChamber.getFlag() == null || !earthElectrodeChamber.getFlag().equalsIgnoreCase("R")) {
					if (earthElectrodeChamber.getFlag() == null) {
						earthElectrodeChamber.setFlag("N");
					}
					unRemovedEarthElectrodeChamber.add(earthElectrodeChamber);
				}
			}
			return unRemovedEarthElectrodeChamber;
		}
		
		public List<EarthingSystem> findNonRemovedEarthingSystem(List<EarthingSystem> listOfEarthingSystem) {

			ArrayList<EarthingSystem> unRemovedEarthingSystem = new ArrayList<EarthingSystem>();
			for (EarthingSystem earthingSystem : listOfEarthingSystem) {
				if (earthingSystem.getFlag() == null || !earthingSystem.getFlag().equalsIgnoreCase("R")) {
					if (earthingSystem.getFlag() == null) {
						earthingSystem.setFlag("N");
					}
					unRemovedEarthingSystem.add(earthingSystem);
				}
			}
			return unRemovedEarthingSystem;
		}
		
		public List<EarthElectrodeTesting> findNonRemovedEarthElectrodeTesting(List<EarthElectrodeTesting> listOfEarthElectrodeTesting) {

			ArrayList<EarthElectrodeTesting> unRemovedEarthElectrodeTesting = new ArrayList<EarthElectrodeTesting>();
			for (EarthElectrodeTesting earthElectrodeTesting : listOfEarthElectrodeTesting) {
				if (earthElectrodeTesting.getFlag() == null || !earthElectrodeTesting.getFlag().equalsIgnoreCase("R")) {
					if (earthElectrodeTesting.getFlag() == null) {
						earthElectrodeTesting.setFlag("N");
					}
					unRemovedEarthElectrodeTesting.add(earthElectrodeTesting);
				}
			}
			return unRemovedEarthElectrodeTesting;
		}
	
	//find non removed values for SPD
	public List<SPD> findNonRemovedSpdBuildings(SpdReport spdReport) {

		ArrayList<SPD> spd = new ArrayList<SPD>();
		List<SPD> findNonRemoveBuildings = spdReport.getSpd();
		for (SPD  spdItr: findNonRemoveBuildings) {
			if (spdItr.getFlag()==null || !spdItr.getFlag().equalsIgnoreCase("R")) {
				if(spdItr.getFlag()==null) {
					spdItr.setFlag("N");
				}
				spdItr.setSpdDescription(findNonRemovedSpdDesc(spdItr.getSpdDescription()));
				spd.add(spdItr);			 
			}
		}
		return spd;
	}
	
	//find non removed values for SPD childs	
	public List<SpdDescription> findNonRemovedSpdDesc(List<SpdDescription> spdDescriptionList) {

		ArrayList<SpdDescription> unRemovedSpdDescription = new ArrayList<SpdDescription>();
		for (SpdDescription spdDescriptionItr : spdDescriptionList) {
			if (spdDescriptionItr.getFlag() == null || !spdDescriptionItr.getFlag().equalsIgnoreCase("R")) {
				if (spdDescriptionItr.getFlag() == null) {
					spdDescriptionItr.setFlag("N");
				}
				unRemovedSpdDescription.add(spdDescriptionItr);
			}
		}
		return unRemovedSpdDescription;
	}
	
	//find non removed values for SEPERATION DISTANCE	
	public List<SeperationDistanceDescription> findNonRemovedSeperationDistanceBuildings(SeperationDistanceReport seperationDistanceReport) {

		ArrayList<SeperationDistanceDescription> seperationDistanceDescription = new ArrayList<SeperationDistanceDescription>();
		List<SeperationDistanceDescription> findNonRemoveBuildings = seperationDistanceReport.getSeperationDistanceDescription();
		for (SeperationDistanceDescription  seperationDistanceDescriptionItr: findNonRemoveBuildings) {
			if (seperationDistanceDescriptionItr.getFlag()==null || !seperationDistanceDescriptionItr.getFlag().equalsIgnoreCase("R")) {
				if(seperationDistanceDescriptionItr.getFlag()==null) {
					seperationDistanceDescriptionItr.setFlag("N");
				}
				seperationDistanceDescriptionItr.setSeparateDistance(findNonRemovedSeperateDistance(seperationDistanceDescriptionItr.getSeparateDistance()));
				seperationDistanceDescription.add(seperationDistanceDescriptionItr);			 
			}
		}
		return seperationDistanceDescription;
	}
	
	//find non removed values for SEPERATION DISTANCE childs	
		public List<SeparateDistance> findNonRemovedSeperateDistance(List<SeparateDistance> separateDistanceList) {

			ArrayList<SeparateDistance> unRemovedSeparateDistance = new ArrayList<SeparateDistance>();
			for (SeparateDistance separateDistance : separateDistanceList) {
				if (separateDistance.getFlag() == null || !separateDistance.getFlag().equalsIgnoreCase("R")) {
					if (separateDistance.getFlag() == null) {
						separateDistance.setFlag("N");
					}
					unRemovedSeparateDistance.add(separateDistance);
				}
			}
			return unRemovedSeparateDistance;
		}
	
	//find non removed values for EARTH STUD
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
