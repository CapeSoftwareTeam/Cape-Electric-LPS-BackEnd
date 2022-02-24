/**
 * 
 */
package com.capeelectric.util;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Configuration;

import com.capeelectric.model.remarks.AirBasicDescRemarks;
import com.capeelectric.model.remarks.AirClampsRemarks;
import com.capeelectric.model.remarks.AirConnectorsRemarks;
import com.capeelectric.model.remarks.AirExpansionRemarks;
import com.capeelectric.model.remarks.AirHoldersListRemarks;
import com.capeelectric.model.remarks.AirHoldersRemarks;
import com.capeelectric.model.remarks.AirMeshRemarks;
import com.capeelectric.model.remarks.AirTerminationRemarks;
import com.capeelectric.model.remarks.BridgingDescriptionRemarks;
import com.capeelectric.model.remarks.ConnectorRemarks;
import com.capeelectric.model.remarks.DownConductorRemarks;
import com.capeelectric.model.remarks.DownConductorReportRemarks;
import com.capeelectric.model.remarks.DownConductorTestingRemarks;
import com.capeelectric.model.remarks.DownConductorsDescriptionRemarks;
import com.capeelectric.model.remarks.EarthElectrodeChamberRemarks;
import com.capeelectric.model.remarks.EarthElectrodeTestingRemarks;
import com.capeelectric.model.remarks.EarthStudDescriptionRemarks;
import com.capeelectric.model.remarks.EarthStudRemarksReport;
import com.capeelectric.model.remarks.EarthingClampsRemarks;
import com.capeelectric.model.remarks.EarthingDescriptionListRemarks;
import com.capeelectric.model.remarks.EarthingDescriptionRemarks;
import com.capeelectric.model.remarks.EarthingLpsDescriptionRemarks;
import com.capeelectric.model.remarks.EarthingReportRemarks;
import com.capeelectric.model.remarks.EarthingSystemRemarks;
import com.capeelectric.model.remarks.HolderRemarks;
import com.capeelectric.model.remarks.LightningCounterRemarks;
import com.capeelectric.model.remarks.LpsAirDiscriptionRemarks;
import com.capeelectric.model.remarks.LpsVerticalAirTerminationRemarks;
import com.capeelectric.model.remarks.SPDRemarks;
import com.capeelectric.model.remarks.SPDReportRemarks;
import com.capeelectric.model.remarks.SeparateDistanceDownConductRemarks;
import com.capeelectric.model.remarks.SeparateDistanceRemarks;
import com.capeelectric.model.remarks.SeperationDistanceDescriptionRemarks;
import com.capeelectric.model.remarks.SeperationDistanceReportRemarks;
import com.capeelectric.model.remarks.SpdDescriptionRemarks;
import com.capeelectric.model.remarks.TestingJointRemarks;
import com.capeelectric.model.remarks.VerticalAirTerminationListRemarks;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Configuration
public class FindNonRemovedRemarksObjects {
	
	//find non removed values for LPS AIR TERMINATION
		public List<LpsAirDiscriptionRemarks> findNonRemovedAirTerminationBuildings(AirTerminationRemarks airTermination) {

			ArrayList<LpsAirDiscriptionRemarks> lpsAirDiscription = new ArrayList<LpsAirDiscriptionRemarks>();
			List<LpsAirDiscriptionRemarks> findNonRemoveBuildings = airTermination.getLpsAirDiscription();
			for (LpsAirDiscriptionRemarks  LpsAirDiscriptionItr: findNonRemoveBuildings) {
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
		public List<LpsVerticalAirTerminationRemarks> findNonRemovedVAT(List<LpsVerticalAirTerminationRemarks> terminationList) {

			ArrayList<LpsVerticalAirTerminationRemarks> unRemovedVerticalAirTermination = new ArrayList<LpsVerticalAirTerminationRemarks>();
			for (LpsVerticalAirTerminationRemarks lpsVerticalAirTerminationItr : terminationList) {
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
		
		public List<VerticalAirTerminationListRemarks> findNonRemovedVATList(List<VerticalAirTerminationListRemarks> verticalAirTerminationList) {

			ArrayList<VerticalAirTerminationListRemarks> unRemovedVerticalAirTerminationList = new ArrayList<VerticalAirTerminationListRemarks>();
			for (VerticalAirTerminationListRemarks lpsVerticalAirTerminationListItr : verticalAirTerminationList) {
				if (lpsVerticalAirTerminationListItr.getFlag() == null || !lpsVerticalAirTerminationListItr.getFlag().equalsIgnoreCase("R")) {
					if (lpsVerticalAirTerminationListItr.getFlag() == null) {
						lpsVerticalAirTerminationListItr.setFlag("N");
					}
					unRemovedVerticalAirTerminationList.add(lpsVerticalAirTerminationListItr);
				}
			}
			return unRemovedVerticalAirTerminationList;
		}
		
		public List<AirClampsRemarks> findNonRemovedAirClamps(List<AirClampsRemarks> listOfAirClamps) {

			ArrayList<AirClampsRemarks> unRemovedAirClamps = new ArrayList<AirClampsRemarks>();
			for (AirClampsRemarks airClamps : listOfAirClamps) {
				if (airClamps.getFlag() == null || !airClamps.getFlag().equalsIgnoreCase("R")) {
					if (airClamps.getFlag() == null) {
						airClamps.setFlag("N");
					}
					unRemovedAirClamps.add(airClamps);
				}
			}
			return unRemovedAirClamps;
		}
		
		public List<AirConnectorsRemarks> findNonRemovedAirConnectors(List<AirConnectorsRemarks> listOfAirConnectors) {

			ArrayList<AirConnectorsRemarks> unRemovedAirConnectors = new ArrayList<AirConnectorsRemarks>();
			for (AirConnectorsRemarks airConnectors : listOfAirConnectors) {
				if (airConnectors.getFlag() == null || !airConnectors.getFlag().equalsIgnoreCase("R")) {
					if (airConnectors.getFlag() == null) {
						airConnectors.setFlag("N");
					}
					unRemovedAirConnectors.add(airConnectors);
				}
			}
			return unRemovedAirConnectors;
		}
		
		public List<AirExpansionRemarks> findNonRemovedAirExpansion(List<AirExpansionRemarks> listOfAirExpansion) {

			ArrayList<AirExpansionRemarks> unRemovedAirExpansion = new ArrayList<AirExpansionRemarks>();
			for (AirExpansionRemarks airExpansion : listOfAirExpansion) {
				if (airExpansion.getFlag() == null || !airExpansion.getFlag().equalsIgnoreCase("R")) {
					if (airExpansion.getFlag() == null) {
						airExpansion.setFlag("N");
					}
					unRemovedAirExpansion.add(airExpansion);
				}
			}
			return unRemovedAirExpansion;
		}
		
		public List<AirHoldersRemarks> findNonRemovedAirHolder(List<AirHoldersRemarks> listOfAirHolder) {

			ArrayList<AirHoldersRemarks> unRemovedAirHolderDescription = new ArrayList<AirHoldersRemarks>();
			for (AirHoldersRemarks airHolderDescription : listOfAirHolder) {
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
		
		public List<AirHoldersListRemarks> findNonRemovedAirHolderList(List<AirHoldersListRemarks> listOfAirHolder) {

			ArrayList<AirHoldersListRemarks> unRemovedAirHolderList = new ArrayList<AirHoldersListRemarks>();
			for (AirHoldersListRemarks airHolderList : listOfAirHolder) {
				if (airHolderList.getFlag() == null || !airHolderList.getFlag().equalsIgnoreCase("R")) {
					if (airHolderList.getFlag() == null) {
						airHolderList.setFlag("N");
					}
					unRemovedAirHolderList.add(airHolderList);
				}
			}
			return unRemovedAirHolderList;
		}
		
		public List<AirMeshRemarks> findNonRemovedAirMesh(List<AirMeshRemarks> listOfAirMesh) {

			ArrayList<AirMeshRemarks> unRemovedAirMeshDescription = new ArrayList<AirMeshRemarks>();
			for (AirMeshRemarks airMeshDescription : listOfAirMesh) {
				if (airMeshDescription.getFlag() == null || !airMeshDescription.getFlag().equalsIgnoreCase("R")) {
					if (airMeshDescription.getFlag() == null) {
						airMeshDescription.setFlag("N");
					}
					unRemovedAirMeshDescription.add(airMeshDescription);
				}
			}
			return unRemovedAirMeshDescription;
		}
		
		public List<AirBasicDescRemarks> findNonRemovedAirBasic(List<AirBasicDescRemarks> listOfAirBasic) {

			ArrayList<AirBasicDescRemarks> unRemovedAirBasicDescription = new ArrayList<AirBasicDescRemarks>();
			for (AirBasicDescRemarks airBasicDescription : listOfAirBasic) {
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
		public List<DownConductorsDescriptionRemarks> findNonRemovedDownConductorsBuildings(DownConductorReportRemarks downConductorReport) {

			ArrayList<DownConductorsDescriptionRemarks> downConductorDescription = new ArrayList<DownConductorsDescriptionRemarks>();
			List<DownConductorsDescriptionRemarks> findNonRemoveBuildings = downConductorReport.getDownConductorDescription();
			for (DownConductorsDescriptionRemarks  downConductorDescriptionItr: findNonRemoveBuildings) {
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
		public List<BridgingDescriptionRemarks> findNonRemovedBridgingDescription(List<BridgingDescriptionRemarks> bridgingDescriptionList) {

			ArrayList<BridgingDescriptionRemarks> unRemovedBridgingDescription = new ArrayList<BridgingDescriptionRemarks>();
			for (BridgingDescriptionRemarks bridgingDescription : bridgingDescriptionList) {
				if (bridgingDescription.getFlag() == null || !bridgingDescription.getFlag().equalsIgnoreCase("R")) {
					if (bridgingDescription.getFlag() == null) {
						bridgingDescription.setFlag("N");
					}
					unRemovedBridgingDescription.add(bridgingDescription);
				}
			}
			return unRemovedBridgingDescription;
		}
		
		public List<HolderRemarks> findNonRemovedHolder(List<HolderRemarks> listOfHolder) {

			ArrayList<HolderRemarks> unRemovedHolder = new ArrayList<HolderRemarks>();
			for (HolderRemarks holder : listOfHolder) {
				if (holder.getFlag() == null || !holder.getFlag().equalsIgnoreCase("R")) {
					if (holder.getFlag() == null) {
						holder.setFlag("N");
					}
					unRemovedHolder.add(holder);
				}
			}
			return unRemovedHolder;
		}
		
		public List<ConnectorRemarks> findNonRemovedConnectors(List<ConnectorRemarks> listOfConnectors) {

			ArrayList<ConnectorRemarks> unRemovedConnectors = new ArrayList<ConnectorRemarks>();
			for (ConnectorRemarks connectors : listOfConnectors) {
				if (connectors.getFlag() == null || !connectors.getFlag().equalsIgnoreCase("R")) {
					if (connectors.getFlag() == null) {
						connectors.setFlag("N");
					}
					unRemovedConnectors.add(connectors);
				}
			}
			return unRemovedConnectors;
		}
		
		public List<LightningCounterRemarks> findNonRemovedLightningCounter(List<LightningCounterRemarks> listOfLightningCounter) {

			ArrayList<LightningCounterRemarks> unRemovedLightningCounter = new ArrayList<LightningCounterRemarks>();
			for (LightningCounterRemarks lightningCounter : listOfLightningCounter) {
				if (lightningCounter.getFlag() == null || !lightningCounter.getFlag().equalsIgnoreCase("R")) {
					if (lightningCounter.getFlag() == null) {
						lightningCounter.setFlag("N");
					}
					unRemovedLightningCounter.add(lightningCounter);
				}
			}
			return unRemovedLightningCounter;
		}
		
		public List<TestingJointRemarks> findNonRemovedTestingJoint(List<TestingJointRemarks> listOfTestingJoint) {

			ArrayList<TestingJointRemarks> unRemovedTestingJoint = new ArrayList<TestingJointRemarks>();
			for (TestingJointRemarks testingJoint : listOfTestingJoint) {
				if (testingJoint.getFlag() == null || !testingJoint.getFlag().equalsIgnoreCase("R")) {
					if (testingJoint.getFlag() == null) {
						testingJoint.setFlag("N");
					}
					unRemovedTestingJoint.add(testingJoint);
				}
			}
			return unRemovedTestingJoint;
		}
		
		public List<DownConductorRemarks> findNonDownConductor(List<DownConductorRemarks> listOfDownConductor) {

			ArrayList<DownConductorRemarks> unRemovedDownConductor = new ArrayList<DownConductorRemarks>();
			for (DownConductorRemarks downConductor : listOfDownConductor) {
				if (downConductor.getFlag() == null || !downConductor.getFlag().equalsIgnoreCase("R")) {
					if (downConductor.getFlag() == null) {
						downConductor.setFlag("N");
					}
					unRemovedDownConductor.add(downConductor);
				}
			}
			return unRemovedDownConductor;
		}
		
		public List<DownConductorTestingRemarks> findNonRemovedDownConductorTesting(List<DownConductorTestingRemarks> listOfDownConductorTesting) {

			ArrayList<DownConductorTestingRemarks> unRemovedDownConductorTesting = new ArrayList<DownConductorTestingRemarks>();
			for (DownConductorTestingRemarks downConductorTesting : listOfDownConductorTesting) {
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
		public List<EarthingLpsDescriptionRemarks> findNonRemovedEarthingLpsBuildings(EarthingReportRemarks earthingReport) {

			ArrayList<EarthingLpsDescriptionRemarks> earthingLpsDescription = new ArrayList<EarthingLpsDescriptionRemarks>();
			List<EarthingLpsDescriptionRemarks> findNonRemoveBuildings = earthingReport.getEarthingLpsDescription();
			for (EarthingLpsDescriptionRemarks  earthingLpsDescriptionItr: findNonRemoveBuildings) {
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
			public List<EarthingDescriptionRemarks> findNonRemovedEarthingDescription(List<EarthingDescriptionRemarks> earthingDescriptionList) {

				ArrayList<EarthingDescriptionRemarks> unRemovedEarthingDescription = new ArrayList<EarthingDescriptionRemarks>();
				for (EarthingDescriptionRemarks earthingDescription : earthingDescriptionList) {
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
			
			public List<EarthingDescriptionListRemarks> findNonRemovedEarthingDescriptionList(List<EarthingDescriptionListRemarks> earthingDescriptionList) {

				ArrayList<EarthingDescriptionListRemarks> unRemovedEarthingDescriptionList = new ArrayList<EarthingDescriptionListRemarks>();
				for (EarthingDescriptionListRemarks earthingDescriptionListItr : earthingDescriptionList) {
					if (earthingDescriptionListItr.getFlag() == null || !earthingDescriptionListItr.getFlag().equalsIgnoreCase("R")) {
						if (earthingDescriptionListItr.getFlag() == null) {
							earthingDescriptionListItr.setFlag("N");
						}
						unRemovedEarthingDescriptionList.add(earthingDescriptionListItr);
					}
				}
				return unRemovedEarthingDescriptionList;
			}
			
			public List<EarthingClampsRemarks> findNonRemovedEarthingClamps(List<EarthingClampsRemarks> listOfEarthingClamps) {

				ArrayList<EarthingClampsRemarks> unRemovedEarthingClamps = new ArrayList<EarthingClampsRemarks>();
				for (EarthingClampsRemarks earthingClamps : listOfEarthingClamps) {
					if (earthingClamps.getFlag() == null || !earthingClamps.getFlag().equalsIgnoreCase("R")) {
						if (earthingClamps.getFlag() == null) {
							earthingClamps.setFlag("N");
						}
						unRemovedEarthingClamps.add(earthingClamps);
					}
				}
				return unRemovedEarthingClamps;
			}
			
			public List<EarthElectrodeChamberRemarks> findNonRemovedEarthElectrodeChamber(List<EarthElectrodeChamberRemarks> listOfEarthElectrodeChamber) {

				ArrayList<EarthElectrodeChamberRemarks> unRemovedEarthElectrodeChamber = new ArrayList<EarthElectrodeChamberRemarks>();
				for (EarthElectrodeChamberRemarks earthElectrodeChamber : listOfEarthElectrodeChamber) {
					if (earthElectrodeChamber.getFlag() == null || !earthElectrodeChamber.getFlag().equalsIgnoreCase("R")) {
						if (earthElectrodeChamber.getFlag() == null) {
							earthElectrodeChamber.setFlag("N");
						}
						unRemovedEarthElectrodeChamber.add(earthElectrodeChamber);
					}
				}
				return unRemovedEarthElectrodeChamber;
			}
			
			public List<EarthingSystemRemarks> findNonRemovedEarthingSystem(List<EarthingSystemRemarks> listOfEarthingSystem) {

				ArrayList<EarthingSystemRemarks> unRemovedEarthingSystem = new ArrayList<EarthingSystemRemarks>();
				for (EarthingSystemRemarks earthingSystem : listOfEarthingSystem) {
					if (earthingSystem.getFlag() == null || !earthingSystem.getFlag().equalsIgnoreCase("R")) {
						if (earthingSystem.getFlag() == null) {
							earthingSystem.setFlag("N");
						}
						unRemovedEarthingSystem.add(earthingSystem);
					}
				}
				return unRemovedEarthingSystem;
			}
			
			public List<EarthElectrodeTestingRemarks> findNonRemovedEarthElectrodeTesting(List<EarthElectrodeTestingRemarks> listOfEarthElectrodeTesting) {

				ArrayList<EarthElectrodeTestingRemarks> unRemovedEarthElectrodeTesting = new ArrayList<EarthElectrodeTestingRemarks>();
				for (EarthElectrodeTestingRemarks earthElectrodeTesting : listOfEarthElectrodeTesting) {
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
		public List<SPDRemarks> findNonRemovedSpdBuildings(SPDReportRemarks spdReport) {

			ArrayList<SPDRemarks> spd = new ArrayList<SPDRemarks>();
			List<SPDRemarks> findNonRemoveBuildings = spdReport.getSpd();
			for (SPDRemarks spdItr: findNonRemoveBuildings) {
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
		public List<SpdDescriptionRemarks> findNonRemovedSpdDesc(List<SpdDescriptionRemarks> spdDescriptionList) {

			ArrayList<SpdDescriptionRemarks> unRemovedSpdDescription = new ArrayList<SpdDescriptionRemarks>();
			for (SpdDescriptionRemarks spdDescriptionItr : spdDescriptionList) {
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
		public List<SeperationDistanceDescriptionRemarks> findNonRemovedSeperationDistanceBuildings(SeperationDistanceReportRemarks seperationDistanceReport) {

			ArrayList<SeperationDistanceDescriptionRemarks> seperationDistanceDescription = new ArrayList<SeperationDistanceDescriptionRemarks>();
			List<SeperationDistanceDescriptionRemarks> findNonRemoveBuildings = seperationDistanceReport.getSeperationDistanceDescription();
			for (SeperationDistanceDescriptionRemarks  seperationDistanceDescriptionItr: findNonRemoveBuildings) {
				if (seperationDistanceDescriptionItr.getFlag()==null || !seperationDistanceDescriptionItr.getFlag().equalsIgnoreCase("R")) {
					if(seperationDistanceDescriptionItr.getFlag()==null) {
						seperationDistanceDescriptionItr.setFlag("N");
					}
					seperationDistanceDescriptionItr.setSeparateDistance(findNonRemovedSeperateDistance(seperationDistanceDescriptionItr.getSeparateDistance()));
					seperationDistanceDescriptionItr.setSeparateDistanceDownConductors(findNonRemovedSeperateDistanceDownConduct(seperationDistanceDescriptionItr.getSeparateDistanceDownConductors()));
					seperationDistanceDescription.add(seperationDistanceDescriptionItr);			 
				}
			}
			return seperationDistanceDescription;
		}
		
		//find non removed values for SEPERATION DISTANCE childs	
			public List<SeparateDistanceRemarks> findNonRemovedSeperateDistance(List<SeparateDistanceRemarks> separateDistanceList) {

				ArrayList<SeparateDistanceRemarks> unRemovedSeparateDistance = new ArrayList<SeparateDistanceRemarks>();
				for (SeparateDistanceRemarks separateDistance : separateDistanceList) {
					if (separateDistance.getFlag() == null || !separateDistance.getFlag().equalsIgnoreCase("R")) {
						if (separateDistance.getFlag() == null) {
							separateDistance.setFlag("N");
						}
						unRemovedSeparateDistance.add(separateDistance);
					}
				}
				return unRemovedSeparateDistance;
			}
			
			public List<SeparateDistanceDownConductRemarks> findNonRemovedSeperateDistanceDownConduct(List<SeparateDistanceDownConductRemarks> separateDistanceDownList) {

				ArrayList<SeparateDistanceDownConductRemarks> unRemovedSeparateDistanceDown = new ArrayList<SeparateDistanceDownConductRemarks>();
				for (SeparateDistanceDownConductRemarks separateDistanceDownConductors : separateDistanceDownList) {
					if (separateDistanceDownConductors.getFlag() == null || !separateDistanceDownConductors.getFlag().equalsIgnoreCase("R")) {
						if (separateDistanceDownConductors.getFlag() == null) {
							separateDistanceDownConductors.setFlag("N");
						}
						unRemovedSeparateDistanceDown.add(separateDistanceDownConductors);
					}
				}
				return unRemovedSeparateDistanceDown;
			}
		
		//find non removed values for EARTH STUD
		public List<EarthStudDescriptionRemarks> findNonRemovedEarthStudRemarksBuildings(EarthStudRemarksReport earthStudReport) {

			ArrayList<EarthStudDescriptionRemarks> earthStudDescription = new ArrayList<EarthStudDescriptionRemarks>();
			List<EarthStudDescriptionRemarks> findNonRemoveBuildings = earthStudReport.getEarthStudDescription();
			for (EarthStudDescriptionRemarks  earthStudDescriptionItr: findNonRemoveBuildings) {
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
