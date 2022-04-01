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
import com.capeelectric.exception.SeperationDistanceException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.SeparateDistance;
import com.capeelectric.model.SeparateDistanceDownConductors;
import com.capeelectric.model.SeperationDistanceDescription;
import com.capeelectric.model.SeperationDistanceReport;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.SeperationDistanceRepository;
import com.capeelectric.service.impl.SeperationDistanceServiceImpl;
import com.capeelectric.util.FindNonRemovedObjects;
import com.capeelectric.util.UserFullName;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class SeperationDistanceServiceTest {

	
	private static final Logger logger = LoggerFactory.getLogger(SeperationDistanceServiceTest.class);

	@MockBean
	private SeperationDistanceRepository seperationDistanceRepository;

	@InjectMocks
	private SeperationDistanceServiceImpl seperationDistanceServiceImpl;

	@MockBean
	private SeperationDistanceException seperationDistanceException;
	
	@MockBean
	private FindNonRemovedObjects findNonRemovedObjects;

	@MockBean
	private BasicLpsRepository basicLpsRepository;
	
	@MockBean
	private UserFullName userFullName;

	private SeperationDistanceReport seperationDistanceReport;

	{
		seperationDistanceReport = new SeperationDistanceReport();
		seperationDistanceReport.setBasicLpsId(1);
		seperationDistanceReport.setSeperationDistanceReportId(2);
		seperationDistanceReport.setUserName("LVsystem@gmail.com");
		
		List<SeperationDistanceDescription> seperationDistanceDescriptionList = new ArrayList<SeperationDistanceDescription>();
		SeperationDistanceDescription seperationDistanceDescription = new SeperationDistanceDescription();
		seperationDistanceDescription.setFlag("A");
		
		List<SeparateDistance> separateDistanceList = new ArrayList<SeparateDistance>();
		SeparateDistance separateDistance = new SeparateDistance();
		separateDistance.setFlag("A");
		separateDistanceList.add(separateDistance);
		
		List<SeparateDistanceDownConductors> separateDistanceDownConductorsList = new ArrayList<SeparateDistanceDownConductors>();
		SeparateDistanceDownConductors separateDistanceDownConductors = new SeparateDistanceDownConductors();
		separateDistanceDownConductors.setFlag("A");
		separateDistanceDownConductorsList.add(separateDistanceDownConductors);
		
		seperationDistanceDescription.setSeparateDistance(separateDistanceList);
		seperationDistanceDescription.setSeparateDistanceDownConductors(separateDistanceDownConductorsList);
		
		seperationDistanceDescriptionList.add(seperationDistanceDescription);
	}
	private BasicLps basicLps;
	{
		basicLps = new BasicLps();
		basicLps.setBasicLpsId(1);
		basicLps.setClientName("Inspector@gmail.com");
	}
	
	@Test
	public void testAddSeperationDistance() throws SeperationDistanceException, AirTerminationException {

		when(basicLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(basicLps));
		when(seperationDistanceRepository.findByBasicLpsId(2)).thenReturn(Optional.of(seperationDistanceReport));
		
		seperationDistanceReport.setSeperationDistanceDescription(null);
		SeperationDistanceException seperationDistanceException_1 = Assertions.assertThrows(SeperationDistanceException.class,
				() -> seperationDistanceServiceImpl.addSeperationDistance(seperationDistanceReport));
		assertEquals(seperationDistanceException_1.getMessage(), "Please fill all the fields before clicking next button");
		
		List<SeperationDistanceDescription> seperationDistanceDescriptionList = new ArrayList<SeperationDistanceDescription>();
		seperationDistanceDescriptionList.add(new SeperationDistanceDescription());
		
		List<SeparateDistance> separateDistanceList = new ArrayList<SeparateDistance>();
		SeparateDistance separateDistance = new SeparateDistance();
		separateDistance.setFlag("A");
		separateDistanceList.add(separateDistance);
		
		List<SeparateDistanceDownConductors> separateDistanceDownConductorsList = new ArrayList<SeparateDistanceDownConductors>();
		SeparateDistanceDownConductors separateDistanceDownConductors = new SeparateDistanceDownConductors();
		separateDistanceDownConductors.setFlag("A");
		separateDistanceDownConductorsList.add(separateDistanceDownConductors);
		
		SeperationDistanceDescription seperationDistanceDescription = seperationDistanceDescriptionList.get(0);
		seperationDistanceDescription.setSeparateDistance(separateDistanceList);
		seperationDistanceDescription.setSeparateDistanceDownConductors(separateDistanceDownConductorsList);
		seperationDistanceDescription.setFlag("A");
		
		seperationDistanceReport.setSeperationDistanceDescription(seperationDistanceDescriptionList);
		seperationDistanceServiceImpl.addSeperationDistance(seperationDistanceReport);
		
		when(seperationDistanceRepository.findByBasicLpsId(1)).thenReturn(Optional.of(seperationDistanceReport));
		SeperationDistanceException seperationDistanceException = Assertions.assertThrows(SeperationDistanceException.class,
				() -> seperationDistanceServiceImpl.addSeperationDistance(seperationDistanceReport));
		assertEquals(seperationDistanceException.getMessage(), "Basic LPS Id Already Available.Create New Basic Id");

		basicLps.setBasicLpsId(5);
		seperationDistanceReport.setBasicLpsId(5);
		when(basicLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(basicLps));
		SeperationDistanceException seperationDistanceException_2 = Assertions.assertThrows(SeperationDistanceException.class,
				() -> seperationDistanceServiceImpl.addSeperationDistance(seperationDistanceReport));
		assertEquals(seperationDistanceException_2.getMessage(), "Given Basic LPS Id is Not Registered in Basic LPS");	
		
		seperationDistanceReport.setUserName(null);
		SeperationDistanceException seperationDistanceException_3 = Assertions.assertThrows(SeperationDistanceException.class,
				() -> seperationDistanceServiceImpl.addSeperationDistance(seperationDistanceReport));
		assertEquals(seperationDistanceException_3.getMessage(), "Invalid Inputs");

	}
	
	@Test
	public void testRetrieveSeperationDetails() throws  SeperationDistanceException {

		List<SeperationDistanceReport> arrayList = new ArrayList<SeperationDistanceReport>();
		arrayList.add(seperationDistanceReport);
		when(seperationDistanceRepository.findByUserNameAndBasicLpsId("LVsystem@gmail.com", 12)).thenReturn(arrayList);
		seperationDistanceServiceImpl.retrieveSeperationDetails("LVsystem@gmail.com", 12);

		assertNotNull(seperationDistanceServiceImpl.retrieveSeperationDetails("abc@gmail.com", 12));
		
		SeperationDistanceException seperationDistanceException_2 = Assertions.assertThrows(SeperationDistanceException.class,
				() -> seperationDistanceServiceImpl.retrieveSeperationDetails(null, 12));
		assertEquals(seperationDistanceException_2.getMessage(), "Invalid Inputs");

	}
	
	@Test
	public void testUpdateSeperationDetails() throws SeperationDistanceException, AirTerminationException {

		
		seperationDistanceReport.setUserName("LVsystem@gmail.com");
		seperationDistanceReport.setSeperationDistanceReportId(1);
		seperationDistanceReport.setBasicLpsId(1);
		when(seperationDistanceRepository.findById(1)).thenReturn(Optional.of(seperationDistanceReport));
		seperationDistanceServiceImpl.updateSeperationDetails(seperationDistanceReport);
		
		seperationDistanceReport.setBasicLpsId(2);
		seperationDistanceReport.setSeperationDistanceReportId(50);
		when(seperationDistanceRepository.findById(20)).thenReturn(Optional.of(seperationDistanceReport));
		SeperationDistanceException assertThrows_1 = Assertions.assertThrows(SeperationDistanceException.class,
				() -> seperationDistanceServiceImpl.updateSeperationDetails(seperationDistanceReport));
		assertEquals(assertThrows_1.getMessage(), "Given Basic LPS Id and Seperation Distance Id is Invalid");

		when(seperationDistanceRepository.findByBasicLpsId(1)).thenReturn(Optional.of(seperationDistanceReport));
		seperationDistanceReport.setBasicLpsId(null);
		when(seperationDistanceRepository.findByBasicLpsId(1)).thenReturn(Optional.of(seperationDistanceReport));
		SeperationDistanceException assertThrows_2 = Assertions.assertThrows(SeperationDistanceException.class,
				() -> seperationDistanceServiceImpl.updateSeperationDetails(seperationDistanceReport));
		assertEquals(assertThrows_2.getMessage(), "Invalid inputs");
	}


}
