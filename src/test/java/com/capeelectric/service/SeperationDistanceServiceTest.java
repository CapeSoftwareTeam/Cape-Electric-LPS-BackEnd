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

import com.capeelectric.exception.SPDException;
import com.capeelectric.exception.SeperationDistanceException;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.model.SPD;
import com.capeelectric.model.SeperationDistanceDescription;
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
	private UserFullName userFullName;

	private SeperationDistanceDescription seperationDistanceDescription;

	{
		seperationDistanceDescription = new SeperationDistanceDescription();
		seperationDistanceDescription.setBasicLpsId(1);
		seperationDistanceDescription.setUserName("LVsystem@gmail.com");
		seperationDistanceDescription.setUserName("Inspector@gmail.com");
		seperationDistanceDescription.setBasicLpsId(1);
	}
	
	@Test
	public void testAddSeperationDistance() throws SeperationDistanceException {

		when(seperationDistanceRepository.findByBasicLpsId(1)).thenReturn(Optional.of(seperationDistanceDescription));

		logger.info("BasicLpsId already Present_flow");
		logger.info("Invalid Present_flow");
		seperationDistanceDescription.setUserName(null);
		SeperationDistanceException seperationDistanceException_2 = Assertions.assertThrows(SeperationDistanceException.class,
				() -> seperationDistanceServiceImpl.addSeperationDistance(seperationDistanceDescription));
		assertEquals(seperationDistanceException_2.getMessage(), "Basic LPS Id Already Available.Create New Basic Id");

	}
	
	@Test
	public void testRetrieveSeperationDetails() throws  SeperationDistanceException {

		List<SeperationDistanceDescription> arrayList = new ArrayList<SeperationDistanceDescription>();
		arrayList.add(seperationDistanceDescription);
		when(seperationDistanceRepository.findByUserNameAndBasicLpsId("LVsystem@gmail.com", 12)).thenReturn(arrayList);

		logger.info("SuccessFlow of RetrieveSeperationDetails Obeject");
		seperationDistanceServiceImpl.retrieveSeperationDetails("LVsystem@gmail.com", 12);

		logger.info("Invalid Input flow");
		SeperationDistanceException earthingLpsException = Assertions.assertThrows(SeperationDistanceException.class,
				() -> seperationDistanceServiceImpl.retrieveSeperationDetails(null, 12));
		assertEquals(earthingLpsException.getMessage(), "Invalid Inputs");

	}
	
	@Test
	public void testUpdateSeperationDetails() throws SeperationDistanceException {

		seperationDistanceDescription.setUserName("LVsystem@gmail.com");
		seperationDistanceDescription.setSeperationDistanceId(1);
		seperationDistanceDescription.setBasicLpsId(1);

		when(seperationDistanceRepository.findByBasicLpsId(1)).thenReturn(Optional.of(seperationDistanceDescription));
		// spdServiceImpl.updateSpdDetails(spd);

		EarthingLpsDescription earthingLpsDescription_1 = new EarthingLpsDescription();
		earthingLpsDescription_1.setBasicLpsId(1);

		when(seperationDistanceRepository.findByBasicLpsId(1)).thenReturn(Optional.of(seperationDistanceDescription));
		seperationDistanceDescription.setBasicLpsId(null);
		when(seperationDistanceRepository.findByBasicLpsId(1)).thenReturn(Optional.of(seperationDistanceDescription));
		SeperationDistanceException assertThrows_1 = Assertions.assertThrows(SeperationDistanceException.class,
				() -> seperationDistanceServiceImpl.updateSeperationDetails(seperationDistanceDescription));
		assertEquals(assertThrows_1.getMessage(), "Invalid inputs");
	}


}
