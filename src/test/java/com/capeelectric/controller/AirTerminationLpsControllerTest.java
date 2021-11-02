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
import com.capeelectric.exception.BasicLpsException;
import com.capeelectric.exception.SummaryException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.model.TestingReport;
import com.capeelectric.model.TestingReportComment;
import com.capeelectric.service.impl.AirTerminationLpsServiceImpl;
import com.capeelectric.service.impl.BasicLpsServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class AirTerminationLpsControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(AirTerminationLpsControllerTest.class);

	@InjectMocks
	private AirTerminationLpsController airTerminationLpsController;

	@MockBean
	private AirTerminationLpsServiceImpl airTerminationLpsServiceImpl;

	private LpsAirDiscription lpsAirDiscription;

	{
		lpsAirDiscription = new LpsAirDiscription();
		lpsAirDiscription.setBasicLpsId(1);
		lpsAirDiscription.setUserName("LVsystem@gmail.com");
		lpsAirDiscription.setUserName("Inspector@gmail.com");
		lpsAirDiscription.setBasicLpsId(1);
	}

	@Test
	public void testAddAirTerminationLpsDetails() throws AirTerminationException {
		logger.info("testAddAirTerminationLpsDetails Function Started");

		doNothing().when(airTerminationLpsServiceImpl).addAirTerminationLpsDetails(lpsAirDiscription);
		ResponseEntity<String> addAirTerminalsDetails = airTerminationLpsController
				.addAirTerminationLps(lpsAirDiscription);
		equals(addAirTerminalsDetails.getBody());
		logger.info("testAddAirTerminationLpsDetails Function Ended");
	}

	@Test
	public void testretrieveAirTerminationLps() throws AirTerminationException {
		List<LpsAirDiscription> arrayList = new ArrayList<>();
		arrayList.add(lpsAirDiscription);

		logger.info("testAddAirTerminationLpsDetails Function Started");

		when(airTerminationLpsServiceImpl.retrieveAirTerminationLps("LVsystem@gmail.com", 12)).thenReturn(arrayList);
		ResponseEntity<List<LpsAirDiscription>> retrieveBasicLpsDetails = airTerminationLpsController
				.retrieveAirTerminationLps("LVsystem@gmail.com", 12);
		assertEquals(HttpStatus.OK, retrieveBasicLpsDetails.getStatusCode());

		logger.info("testAddAirTerminationLpsDetails Function Ended");

	}

	@Test
	public void testUpdateAirTerminationLps() throws AirTerminationException {

		logger.info("testUpdateAirTerminationLps Function Started");
		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>(HttpStatus.OK);
		ResponseEntity<String> actualResponseEntity = airTerminationLpsController
				.updateAirTerminationLps(lpsAirDiscription);
		assertEquals(actualResponseEntity.getStatusCode(), expectedResponseEntity.getStatusCode());
		logger.info("testUpdateAirTerminationLps Function Ended");
	}

}
