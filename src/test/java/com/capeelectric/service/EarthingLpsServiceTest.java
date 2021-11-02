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

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.exception.BasicLpsException;
import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.EarthingLpsRepository;
import com.capeelectric.service.impl.BasicLpsServiceImpl;
import com.capeelectric.service.impl.EarthingLpsServiceImpl;
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
	private UserFullName userFullName;

	private EarthingLpsDescription earthingLpsDescription;

	{
		earthingLpsDescription = new EarthingLpsDescription();
		earthingLpsDescription.setBasicLpsId(1);
		earthingLpsDescription.setUserName("LVsystem@gmail.com");
		earthingLpsDescription.setUserName("Inspector@gmail.com");
		earthingLpsDescription.setBasicLpsId(1);
	}

	@Test
	public void testAddEarthingLpsDetails() throws EarthingLpsException {

		when(earthingLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(earthingLpsDescription));

		logger.info("BasicLpsId already Present_flow");
		// logger.info("Invalid Present_flow");
		earthingLpsDescription.setUserName(null);
		EarthingLpsException earthingLpsException_2 = Assertions.assertThrows(EarthingLpsException.class,
				() -> earthingLpsServiceImpl.addEarthingLpsDetails(earthingLpsDescription));
		assertEquals(earthingLpsException_2.getMessage(), "Invalid Inputs");

	}

	@Test
	public void testRetrieveEarthingLpsDetails() throws EarthingLpsException {

		List<EarthingLpsDescription> arrayList = new ArrayList<EarthingLpsDescription>();
		arrayList.add(earthingLpsDescription);
		when(earthingLpsRepository.findByUserNameAndBasicLpsId("LVsystem@gmail.com", 12)).thenReturn(arrayList);

		logger.info("SuccessFlow of Retrieve  EarthingLps Obeject");
		earthingLpsServiceImpl.retrieveEarthingLpsDetails("LVsystem@gmail.com", 12);

		logger.info("Invalid Input flow");
		EarthingLpsException earthingLpsException = Assertions.assertThrows(EarthingLpsException.class,
				() -> earthingLpsServiceImpl.retrieveEarthingLpsDetails(null, 12));
		assertEquals(earthingLpsException.getMessage(), "Invalid Inputs");

	}

	@Test
	public void testUpdateEarthingLpsDetails() throws EarthingLpsException {

		earthingLpsDescription.setUserName("LVsystem@gmail.com");
		earthingLpsDescription.setEarthingId(1);
		earthingLpsDescription.setBasicLpsId(1);

		when(earthingLpsRepository.findById(1)).thenReturn(Optional.of(earthingLpsDescription));
		earthingLpsServiceImpl.updateEarthingLpsDetails(earthingLpsDescription);

		EarthingLpsDescription earthingLpsDescription_1 = new EarthingLpsDescription();
		earthingLpsDescription_1.setBasicLpsId(1);

		when(earthingLpsRepository.findById(1)).thenReturn(Optional.of(earthingLpsDescription));
		earthingLpsDescription.setBasicLpsId(null);
		when(earthingLpsRepository.findById(1)).thenReturn(Optional.of(earthingLpsDescription));
		EarthingLpsException assertThrows_1 = Assertions.assertThrows(EarthingLpsException.class,
				() -> earthingLpsServiceImpl.updateEarthingLpsDetails(earthingLpsDescription));
		assertEquals(assertThrows_1.getMessage(), "Invalid inputs");
	}

}
