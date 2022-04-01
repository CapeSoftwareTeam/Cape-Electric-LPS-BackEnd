package com.capeelectric.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.EarthElectrodeChamber;
import com.capeelectric.model.EarthElectrodeTesting;
import com.capeelectric.model.EarthingClamps;
import com.capeelectric.model.EarthingDescription;
import com.capeelectric.model.EarthingDescriptionList;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.model.EarthingReport;
import com.capeelectric.model.EarthingSystem;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.EarthingLpsRepository;
import com.capeelectric.service.impl.EarthingLpsServiceImpl;
import com.capeelectric.util.FindNonRemovedObjects;
import com.capeelectric.util.UserFullName;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class EarthingLpsServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(EarthingLpsServiceTest.class);

	@MockBean
	private EarthingLpsRepository earthingLpsRepository;

	@InjectMocks
	private EarthingLpsServiceImpl earthingLpsServiceImpl;

	@MockBean
	private EarthingLpsException earthingLpsException;

	@MockBean
	private BasicLpsRepository basicLpsRepository;
	
	@MockBean
	private FindNonRemovedObjects findNonRemovedObjects;
	
	@MockBean
	private UserFullName userFullName;

	private EarthingReport earthingReport;

	{
		earthingReport = new EarthingReport();
		earthingReport.setUserName("LVsystem@gmail.com");
		earthingReport.setBasicLpsId(1);
		earthingReport.setEarthingReportId(2);
		
		List<EarthingLpsDescription> earthingLpsDescriptionList = new ArrayList<EarthingLpsDescription>();
		EarthingLpsDescription earthingLpsDescription = new EarthingLpsDescription();
		earthingLpsDescription.setFlag("A");
		
		List<EarthingDescription> earthingDescriptionList = new ArrayList<EarthingDescription>();
		EarthingDescription earthingDescription = new EarthingDescription();
		earthingDescription.setFlag("A");
		
		List<EarthingDescriptionList> listOfEarthingDescriptionList = new ArrayList<EarthingDescriptionList>();
		EarthingDescriptionList earthingDescriptionLi = new EarthingDescriptionList();
		earthingDescriptionLi.setFlag("A");	
		listOfEarthingDescriptionList.add(earthingDescriptionLi);
		
		earthingDescription.setEarthingDescriptionList(listOfEarthingDescriptionList);
		earthingDescriptionList.add(earthingDescription);
		
		List<EarthingClamps> earthingClampsList = new ArrayList<EarthingClamps>();
		EarthingClamps earthingClamps = new EarthingClamps();
		earthingClamps.setFlag("A");
		earthingClampsList.add(earthingClamps);
		
		List<EarthElectrodeChamber> earthElectrodeChamberList = new ArrayList<EarthElectrodeChamber>();
		EarthElectrodeChamber earthElectrodeChamber = new EarthElectrodeChamber();
		earthElectrodeChamber.setFlag("A");
		earthElectrodeChamberList.add(earthElectrodeChamber);
		
		List<EarthingSystem> earthingSystemList = new ArrayList<EarthingSystem>();
		EarthingSystem earthingSystem = new EarthingSystem();
		earthingSystem.setFlag("A");
		earthingSystemList.add(earthingSystem);
		
		List<EarthElectrodeTesting> earthElectrodeTestingList = new ArrayList<EarthElectrodeTesting>();
		EarthElectrodeTesting earthElectrodeTesting = new EarthElectrodeTesting();
		earthElectrodeTesting.setFlag("A");
		earthElectrodeTestingList.add(earthElectrodeTesting);
		
		earthingLpsDescription.setEarthingDescription(earthingDescriptionList);
		earthingLpsDescription.setEarthingClamps(earthingClampsList);
		earthingLpsDescription.setEarthingElectrodeChamber(earthElectrodeChamberList);
		earthingLpsDescription.setEarthingSystem(earthingSystemList);
		earthingLpsDescription.setEarthElectrodeTesting(earthElectrodeTestingList);
		
		earthingLpsDescriptionList.add(earthingLpsDescription);
	}
	
	private BasicLps basicLps;
	{
		basicLps = new BasicLps();
		basicLps.setBasicLpsId(1);
		basicLps.setClientName("Inspector@gmail.com");
	}

	@Test
	public void testAddEarthingLpsDetails() throws EarthingLpsException, AirTerminationException {

		when(basicLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(basicLps));
		when(earthingLpsRepository.findByBasicLpsId(3)).thenReturn(Optional.of(earthingReport));
		
		earthingReport.setEarthingLpsDescription(null);
		EarthingLpsException earthingLpsException_1 = Assertions.assertThrows(EarthingLpsException.class,
				() -> earthingLpsServiceImpl.addEarthingLpsDetails(earthingReport));
		assertEquals(earthingLpsException_1.getMessage(), "Please fill all the fields before clicking next button");
		
		List<EarthingLpsDescription> earthingLpsDescriptionList = new ArrayList<EarthingLpsDescription>();
		earthingLpsDescriptionList.add(new EarthingLpsDescription());
		
		List<EarthingDescription> earthingDescriptionList = new ArrayList<EarthingDescription>();
		EarthingDescription earthingDescription = new EarthingDescription();
		earthingDescription.setFlag("A");
		
		List<EarthingDescriptionList> listOfEarthingDescriptionList = new ArrayList<EarthingDescriptionList>();
		EarthingDescriptionList earthingDescriptionLi = new EarthingDescriptionList();
		earthingDescriptionLi.setFlag("A");	
		listOfEarthingDescriptionList.add(earthingDescriptionLi);
		
		earthingDescription.setEarthingDescriptionList(listOfEarthingDescriptionList);
		earthingDescriptionList.add(earthingDescription);
		
		List<EarthingClamps> earthingClampsList = new ArrayList<EarthingClamps>();
		EarthingClamps earthingClamps = new EarthingClamps();
		earthingClamps.setFlag("A");
		earthingClampsList.add(earthingClamps);
		
		List<EarthElectrodeChamber> earthElectrodeChamberList = new ArrayList<EarthElectrodeChamber>();
		EarthElectrodeChamber earthElectrodeChamber = new EarthElectrodeChamber();
		earthElectrodeChamber.setFlag("A");
		earthElectrodeChamberList.add(earthElectrodeChamber);
		
		List<EarthingSystem> earthingSystemList = new ArrayList<EarthingSystem>();
		EarthingSystem earthingSystem = new EarthingSystem();
		earthingSystem.setFlag("A");
		earthingSystemList.add(earthingSystem);
		
		List<EarthElectrodeTesting> earthElectrodeTestingList = new ArrayList<EarthElectrodeTesting>();
		EarthElectrodeTesting earthElectrodeTesting = new EarthElectrodeTesting();
		earthElectrodeTesting.setFlag("A");
		earthElectrodeTestingList.add(earthElectrodeTesting);
		
		EarthingLpsDescription earthingLpsDescription = earthingLpsDescriptionList.get(0);	
		earthingLpsDescription.setEarthingDescription(earthingDescriptionList);
		earthingLpsDescription.setEarthingClamps(earthingClampsList);
		earthingLpsDescription.setEarthingElectrodeChamber(earthElectrodeChamberList);
		earthingLpsDescription.setEarthingSystem(earthingSystemList);
		earthingLpsDescription.setEarthElectrodeTesting(earthElectrodeTestingList);
		earthingLpsDescription.setFlag("A");
	
		earthingReport.setEarthingLpsDescription(earthingLpsDescriptionList);		
		earthingLpsServiceImpl.addEarthingLpsDetails(earthingReport);
		
		when(earthingLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(earthingReport));
		EarthingLpsException earthingLpsException_2 = Assertions.assertThrows(EarthingLpsException.class,
				() -> earthingLpsServiceImpl.addEarthingLpsDetails(earthingReport));
		assertEquals(earthingLpsException_2.getMessage(), "Basic LPS Id Already Available.Create New Basic Id");

		basicLps.setBasicLpsId(5);
		earthingReport.setBasicLpsId(5);
		when(basicLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(basicLps));
		EarthingLpsException earthingLpsException_3 = Assertions.assertThrows(EarthingLpsException.class,
				() -> earthingLpsServiceImpl.addEarthingLpsDetails(earthingReport));
		assertEquals(earthingLpsException_3.getMessage(), "Given Basic LPS Id is Not Registered in Basic LPS");	
		
		earthingReport.setUserName(null);
		EarthingLpsException earthingLpsException_4 = Assertions.assertThrows(EarthingLpsException.class,
				() -> earthingLpsServiceImpl.addEarthingLpsDetails(earthingReport));
		assertEquals(earthingLpsException_4.getMessage(), "Invalid Inputs");

	}

	@Test
	public void testRetrieveEarthingLpsDetails() throws EarthingLpsException {

		List<EarthingReport> arrayList = new ArrayList<EarthingReport>();
		arrayList.add(earthingReport);
		when(earthingLpsRepository.findByUserNameAndBasicLpsId("LVsystem@gmail.com", 12)).thenReturn(arrayList);
		logger.info("SuccessFlow of retrieveEarthingLpsDetails ");
		earthingLpsServiceImpl.retrieveEarthingLpsDetails("LVsystem@gmail.com", 12);

		logger.info("Invalid Input flow");
		EarthingLpsException earthingLpsException_1 = Assertions.assertThrows(EarthingLpsException.class,
				() -> earthingLpsServiceImpl.retrieveEarthingLpsDetails(null, 12));
		assertEquals(earthingLpsException_1.getMessage(), "Invalid Inputs");

		assertNotNull(earthingLpsServiceImpl.retrieveEarthingLpsDetails("abc@gmail.com", 12));
	}

	@Test
	public void testUpdateEarthingLpsDetails() throws EarthingLpsException, AirTerminationException {

		earthingReport.setUserName("LVsystem@gmail.com");
		earthingReport.setEarthingReportId(1);
		earthingReport.setBasicLpsId(1);
		when(earthingLpsRepository.findById(1)).thenReturn(Optional.of(earthingReport));
		earthingLpsServiceImpl.updateEarthingLpsDetails(earthingReport);

		earthingReport.setBasicLpsId(2);
		earthingReport.setEarthingReportId(50);
		when(earthingLpsRepository.findById(20)).thenReturn(Optional.of(earthingReport));
		EarthingLpsException assertThrows_1 = Assertions.assertThrows(EarthingLpsException.class,
				() -> earthingLpsServiceImpl.updateEarthingLpsDetails(earthingReport));
		assertEquals(assertThrows_1.getMessage(), "Given Basic LPS Id and Earthing LPS Id is Invalid");
		
		earthingReport.setBasicLpsId(null);
		when(earthingLpsRepository.findById(1)).thenReturn(Optional.of(earthingReport));
		EarthingLpsException assertThrows_2 = Assertions.assertThrows(EarthingLpsException.class,
				() -> earthingLpsServiceImpl.updateEarthingLpsDetails(earthingReport));
		assertEquals(assertThrows_2.getMessage(), "Invalid inputs");
	}

}
