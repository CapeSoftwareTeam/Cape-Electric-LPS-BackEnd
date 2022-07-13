package com.capeelectric.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.model.AirTermination;
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.service.impl.AirTerminationLpsServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class AirTerminationLpsControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(AirTerminationLpsControllerTest.class);

	@InjectMocks
	private AirTerminationLpsController airTerminationLpsController;

	@MockBean
	private AirTerminationLpsServiceImpl airTerminationLpsServiceImpl;
	
	private AirTermination airTermination;
	
	{
		airTermination = new AirTermination();
		airTermination.setBasicLpsId(1);
		airTermination.setUserName("LVsystem@gmail.com");
		airTermination.setUserName("Inspector@gmail.com");
		airTermination.setBasicLpsId(1);
	}

	@Test
	public void testAddAirTerminationLpsDetails() throws AirTerminationException {
		logger.info("testAddAirTerminationLpsDetails Function Started");

		doNothing().when(airTerminationLpsServiceImpl).addAirTerminationLpsDetails(airTermination);
		ResponseEntity<String> addAirTerminalsDetails = airTerminationLpsController
				.addAirTerminationLps(airTermination);
		equals(addAirTerminalsDetails.getBody());
		logger.info("testAddAirTerminationLpsDetails Function Ended");
	}

	@Test
	public void testretrieveAirTerminationLps() throws AirTerminationException {
		List<AirTermination> arrayList = new ArrayList<>();
		arrayList.add(airTermination);

		logger.info("testretrieveAirTerminationLps Function Started");

		when(airTerminationLpsServiceImpl.retrieveAirTerminationLps("LVsystem@gmail.com", 12)).thenReturn(arrayList);
		ResponseEntity<List<AirTermination>> retrieveBasicLpsDetails = airTerminationLpsController
				.retrieveAirTerminationLps("LVsystem@gmail.com", 12);
		assertEquals(HttpStatus.OK, retrieveBasicLpsDetails.getStatusCode());

		logger.info("testretrieveAirTerminationLps Function Ended");

	}

	@Test
	public void testUpdateAirTerminationLps() throws AirTerminationException {

		logger.info("testUpdateAirTerminationLps Function Started");
		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>(HttpStatus.OK);
		ResponseEntity<String> actualResponseEntity = airTerminationLpsController
				.updateAirTerminationLps(airTermination);
		assertEquals(actualResponseEntity.getStatusCode(), expectedResponseEntity.getStatusCode());
		logger.info("testUpdateAirTerminationLps Function Ended");
	}

}
