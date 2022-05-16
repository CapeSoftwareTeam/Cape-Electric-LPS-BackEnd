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
import com.capeelectric.exception.DownConductorException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.BridgingDescription;
import com.capeelectric.model.Connectors;
import com.capeelectric.model.DownConductor;
import com.capeelectric.model.DownConductorDescription;
import com.capeelectric.model.DownConductorReport;
import com.capeelectric.model.DownConductorTesting;
import com.capeelectric.model.Holder;
import com.capeelectric.model.LightningCounter;
import com.capeelectric.model.TestingJoint;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.DownConductorRepository;
import com.capeelectric.service.impl.DownConductorServiceImpl;
import com.capeelectric.util.AddRemovedStatus;
import com.capeelectric.util.FindNonRemovedObjects;
import com.capeelectric.util.UpdateBuildingCountToFile;
import com.capeelectric.util.UserFullName;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class DownConductorServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(DownConductorServiceTest.class);

	@MockBean
	private DownConductorRepository downConductorRepository;

	@InjectMocks
	private DownConductorServiceImpl downConductorServiceImpl;

	@MockBean
	private BasicLpsRepository basicLpsRepository;
	
	@MockBean
	private UserFullName userFullName;
	
	@MockBean
	private FindNonRemovedObjects findNonRemovedObjects;
	
	@MockBean
	private AddRemovedStatus addRemovedStatus;
	
	@MockBean
	private UpdateBuildingCountToFile updateBuildingCountToFile;

	private DownConductorReport downConductorReport;

	{
		downConductorReport = new DownConductorReport();
		downConductorReport.setBasicLpsId(1);
		downConductorReport.setDownConductorReportId(5);
		downConductorReport.setUserName("LVsystem@gmail.com");
		List<DownConductorDescription> downConductorDescriptionList = new ArrayList<DownConductorDescription>();
		DownConductorDescription downConductorDescription = new DownConductorDescription();
		downConductorDescription.setFlag("A");
		
		List<BridgingDescription> bridgingDescriptionList = new ArrayList<BridgingDescription>();
		BridgingDescription bridgingDescription = new BridgingDescription();
		bridgingDescription.setFlag("A");
		bridgingDescriptionList.add(bridgingDescription);
		
		List<Holder> holderList = new ArrayList<Holder>();
		Holder holder = new Holder();
		holder.setFlag("A");
		holderList.add(holder);
		
		List<Connectors> connectorsList = new ArrayList<Connectors>();
		Connectors connectors = new Connectors();
		connectors.setFlag("A");
		connectorsList.add(connectors);
		
		List<LightningCounter> lightningCounterList = new ArrayList<LightningCounter>();
		LightningCounter lightningCounter = new LightningCounter();
		lightningCounter.setFlag("A");
		lightningCounterList.add(lightningCounter);
		
		List<TestingJoint> testingJointList = new ArrayList<TestingJoint>();
		TestingJoint testingJoint = new TestingJoint();
		testingJoint.setFlag("A");
		testingJointList.add(testingJoint);
		
		List<DownConductor> downConductorList = new ArrayList<DownConductor>();
		DownConductor downConductor = new DownConductor();
		downConductor.setFlag("A");
		downConductorList.add(downConductor);
		
		List<DownConductorTesting> downConductorTestingList = new ArrayList<DownConductorTesting>();
		DownConductorTesting downConductorTesting = new DownConductorTesting();
		downConductorTesting.setFlag("A");
		downConductorTestingList.add(downConductorTesting);
		
		downConductorDescription.setBridgingDescription(bridgingDescriptionList);
		downConductorDescription.setHolder(holderList);
		downConductorDescription.setConnectors(connectorsList);
		downConductorDescription.setLightningCounter(lightningCounterList);
		downConductorDescription.setTestingJoint(testingJointList);
		downConductorDescription.setDownConductor(downConductorList);
		downConductorDescription.setDownConductorTesting(downConductorTestingList);
		
		downConductorDescriptionList.add(downConductorDescription);
	}
	private BasicLps basicLps;

	{
		basicLps = new BasicLps();
		basicLps.setBasicLpsId(1);
		basicLps.setClientName("Inspector@gmail.com");
		
	}
	@Test
	public void testAddDownConductorsDetails() throws DownConductorException, AirTerminationException {

		when(basicLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(basicLps));
		when(downConductorRepository.findByBasicLpsId(2)).thenReturn(Optional.of(downConductorReport));
		
		downConductorReport.setDownConductorDescription(null);
		DownConductorException basicLpsException_4 = Assertions.assertThrows(DownConductorException.class,
				() -> downConductorServiceImpl.addDownConductorsDetails(downConductorReport));
		assertEquals(basicLpsException_4.getMessage(),"Please fill all the fields before clicking next button");
		
		List<DownConductorDescription> downConductorDescriptionList = new ArrayList<DownConductorDescription>();
		downConductorDescriptionList.add(new DownConductorDescription());
		
		List<BridgingDescription> bridgingDescriptionList = new ArrayList<BridgingDescription>();
		BridgingDescription bridgingDescription = new BridgingDescription();
		bridgingDescription.setFlag("A");
		bridgingDescriptionList.add(bridgingDescription);
				
		List<Holder> holderList = new ArrayList<Holder>();
		Holder holder = new Holder();
		holder.setFlag("A");
		holderList.add(holder); 
		
		List<Connectors> connectorsList = new ArrayList<Connectors>();
		Connectors connectors = new Connectors();
		connectors.setFlag("A");
		connectorsList.add(connectors);
		
		List<LightningCounter> lightningCounterList = new ArrayList<LightningCounter>();
		LightningCounter lightningCounter = new LightningCounter();
		lightningCounter.setFlag("A");
		lightningCounterList.add(lightningCounter);
		
		List<TestingJoint> testingJointList = new ArrayList<TestingJoint>();
		TestingJoint testingJoint = new TestingJoint();
		testingJoint.setFlag("A");
		testingJointList.add(testingJoint);
		
		List<DownConductor> downConductorList = new ArrayList<DownConductor>();
		DownConductor downConductor = new DownConductor();
		downConductor.setFlag("A");
		downConductorList.add(downConductor);
		
		List<DownConductorTesting> downConductorTestingList = new ArrayList<DownConductorTesting>();
		DownConductorTesting downConductorTesting = new DownConductorTesting();
		downConductorTesting.setFlag("A");
		downConductorTestingList.add(downConductorTesting);

		DownConductorDescription downConductorDescription = downConductorDescriptionList.get(0);	
		downConductorDescription.setBridgingDescription(bridgingDescriptionList);
		downConductorDescription.setHolder(holderList);
		downConductorDescription.setConnectors(connectorsList);
		downConductorDescription.setLightningCounter(lightningCounterList);
		downConductorDescription.setTestingJoint(testingJointList);
		downConductorDescription.setDownConductor(downConductorList);
		downConductorDescription.setDownConductorTesting(downConductorTestingList);		
		downConductorDescription.setFlag("A");
	
		downConductorReport.setDownConductorDescription(downConductorDescriptionList);		
		downConductorServiceImpl.addDownConductorsDetails(downConductorReport);
		
		when(downConductorRepository.findByBasicLpsId(1)).thenReturn(Optional.of(downConductorReport));
		DownConductorException basicLpsException_1 = Assertions.assertThrows(DownConductorException.class,
				() -> downConductorServiceImpl.addDownConductorsDetails(downConductorReport));
		assertEquals(basicLpsException_1.getMessage(),"Basic LPS Id Already Available.Create New Basic Id");

		downConductorReport.setBasicLpsId(3);
		DownConductorException basicLpsException_2 = Assertions.assertThrows(DownConductorException.class,
				() -> downConductorServiceImpl.addDownConductorsDetails(downConductorReport));
		assertEquals(basicLpsException_2.getMessage(),"Given Basic LPS Id is Not Registered in Basic LPS");
		
		logger.info("Invalid Present_flow");
		downConductorReport.setUserName(null);
		DownConductorException basicLpsException_3 = Assertions.assertThrows(DownConductorException.class,
				() -> downConductorServiceImpl.addDownConductorsDetails(downConductorReport));
		assertEquals(basicLpsException_3.getMessage(), "Invalid Inputs");
		
	}

	@Test
	public void testRetrieveDownConductorDetails() throws DownConductorException {

		List<DownConductorReport> arrayList = new ArrayList<DownConductorReport>();
		arrayList.add(downConductorReport);
		when(downConductorRepository.findByUserNameAndBasicLpsId("LVsystem@gmail.com", 12)).thenReturn(arrayList);

		logger.info("SuccessFlow of Retrieve DownConductorDetails");
		downConductorServiceImpl.retrieveDownConductorDetails("LVsystem@gmail.com", 12);

		logger.info("Invalid Input flow");
		DownConductorException basicLpsException = Assertions.assertThrows(DownConductorException.class,
				() -> downConductorServiceImpl.retrieveDownConductorDetails(null, 12));
		assertEquals(basicLpsException.getMessage(), "Invalid Inputs");

		downConductorReport.setUserName("LVsystem@gmail.com");
		assertNotNull(downConductorServiceImpl.retrieveDownConductorDetails("abc@gmail.com", 12));

	}

	@Test
	public void testUpdateDownConductor() throws DownConductorException, AirTerminationException {
		downConductorReport.setUserName("LVsystem@gmail.com");
		downConductorReport.setDownConductorReportId(1);
		downConductorReport.setBasicLpsId(1);

		when(downConductorRepository.findById(1)).thenReturn(Optional.of(downConductorReport));
		downConductorServiceImpl.updateDownConductorDetails(downConductorReport);

		downConductorReport.setBasicLpsId(null);
		when(downConductorRepository.findById(2)).thenReturn(Optional.of(downConductorReport));
		DownConductorException assertThrows_1 = Assertions.assertThrows(DownConductorException.class,
				() -> downConductorServiceImpl.updateDownConductorDetails(downConductorReport));
		assertEquals(assertThrows_1.getMessage(), "Invalid inputs");
		
		downConductorReport.setBasicLpsId(2);
		downConductorReport.setDownConductorReportId(50);
		when(downConductorRepository.findById(20)).thenReturn(Optional.of(downConductorReport));
		DownConductorException assertThrows_2 = Assertions.assertThrows(DownConductorException.class,
				() -> downConductorServiceImpl.updateDownConductorDetails(downConductorReport));
		assertEquals(assertThrows_2.getMessage(), "Given Basic LPS Id and Down Conductor Id is Invalid");

	}

}
