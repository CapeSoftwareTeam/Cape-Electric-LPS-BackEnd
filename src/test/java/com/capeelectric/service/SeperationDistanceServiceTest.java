package com.capeelectric.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.exception.SPDException;
import com.capeelectric.exception.SeperationDistanceException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.model.SPD;
import com.capeelectric.model.SeperationDistanceDescription;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.SPDRepository;
import com.capeelectric.repository.SeperationDistanceRepository;
import com.capeelectric.service.impl.SPDServiceImpl;
import com.capeelectric.service.impl.SeperationDistanceServiceImpl;
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
	private BasicLpsRepository basicLpsRepository;
	
	@MockBean
	private UserFullName userFullName;

	private SeperationDistanceDescription seperationDistanceDescription;

	{
		seperationDistanceDescription = new SeperationDistanceDescription();
		seperationDistanceDescription.setBasicLpsId(1);
		seperationDistanceDescription.setSeperationDistanceId(2);
		seperationDistanceDescription.setUserName("LVsystem@gmail.com");
	}
	private BasicLps basicLps;
	{
		basicLps = new BasicLps();
		basicLps.setBasicLpsId(1);
		basicLps.setClientName("Inspector@gmail.com");
	}
	
	@Test
	public void testAddSeperationDistance() throws SeperationDistanceException {

		when(basicLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(basicLps));
		when(seperationDistanceRepository.findByBasicLpsId(2)).thenReturn(Optional.of(seperationDistanceDescription));
		seperationDistanceServiceImpl.addSeperationDistance(seperationDistanceDescription);
		
		when(seperationDistanceRepository.findByBasicLpsId(1)).thenReturn(Optional.of(seperationDistanceDescription));
		SeperationDistanceException seperationDistanceException = Assertions.assertThrows(SeperationDistanceException.class,
				() -> seperationDistanceServiceImpl.addSeperationDistance(seperationDistanceDescription));
		assertEquals(seperationDistanceException.getMessage(), "Basic LPS Id Already Available.Create New Basic Id");

		basicLps.setBasicLpsId(5);
		seperationDistanceDescription.setBasicLpsId(5);
		when(basicLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(basicLps));
		SeperationDistanceException earthingLpsException_3 = Assertions.assertThrows(SeperationDistanceException.class,
				() -> seperationDistanceServiceImpl.addSeperationDistance(seperationDistanceDescription));
		assertEquals(earthingLpsException_3.getMessage(), "Given Basic LPS Id is Not Registered in Basic LPS");	
		
		seperationDistanceDescription.setUserName(null);
		SeperationDistanceException seperationDistanceException_2 = Assertions.assertThrows(SeperationDistanceException.class,
				() -> seperationDistanceServiceImpl.addSeperationDistance(seperationDistanceDescription));
		assertEquals(seperationDistanceException_2.getMessage(), "Invalid Inputs");

	}
	
	@Test
	public void testRetrieveSeperationDetails() throws  SeperationDistanceException {

		List<SeperationDistanceDescription> arrayList = new ArrayList<SeperationDistanceDescription>();
		arrayList.add(seperationDistanceDescription);
		when(seperationDistanceRepository.findByUserNameAndBasicLpsId("LVsystem@gmail.com", 12)).thenReturn(arrayList);
		seperationDistanceServiceImpl.retrieveSeperationDetails("LVsystem@gmail.com", 12);

		SeperationDistanceException seperationDistanceException_1 = Assertions.assertThrows(SeperationDistanceException.class,
				() -> seperationDistanceServiceImpl.retrieveSeperationDetails("abc@gmail.com", 12));
		assertEquals(seperationDistanceException_1.getMessage(), "Given UserName & Id doesn't exist in Down Conductor Details");
		
		SeperationDistanceException earthingLpsException = Assertions.assertThrows(SeperationDistanceException.class,
				() -> seperationDistanceServiceImpl.retrieveSeperationDetails(null, 12));
		assertEquals(earthingLpsException.getMessage(), "Invalid Inputs");

	}
	
	@Test
	public void testUpdateSeperationDetails() throws SeperationDistanceException {

		
		seperationDistanceDescription.setUserName("LVsystem@gmail.com");
		seperationDistanceDescription.setSeperationDistanceId(1);
		seperationDistanceDescription.setBasicLpsId(1);
		when(seperationDistanceRepository.findById(1)).thenReturn(Optional.of(seperationDistanceDescription));
		seperationDistanceServiceImpl.updateSeperationDetails(seperationDistanceDescription);
		
		seperationDistanceDescription.setBasicLpsId(2);
		seperationDistanceDescription.setSeperationDistanceId(50);
		when(seperationDistanceRepository.findById(20)).thenReturn(Optional.of(seperationDistanceDescription));
		SeperationDistanceException assertThrows_1 = Assertions.assertThrows(SeperationDistanceException.class,
				() -> seperationDistanceServiceImpl.updateSeperationDetails(seperationDistanceDescription));
		assertEquals(assertThrows_1.getMessage(), "Given Basic LPS Id and Seperation Distance Id is Invalid");

		when(seperationDistanceRepository.findByBasicLpsId(1)).thenReturn(Optional.of(seperationDistanceDescription));
		seperationDistanceDescription.setBasicLpsId(null);
		when(seperationDistanceRepository.findByBasicLpsId(1)).thenReturn(Optional.of(seperationDistanceDescription));
		SeperationDistanceException assertThrows_2 = Assertions.assertThrows(SeperationDistanceException.class,
				() -> seperationDistanceServiceImpl.updateSeperationDetails(seperationDistanceDescription));
		assertEquals(assertThrows_2.getMessage(), "Invalid inputs");
	}


}
