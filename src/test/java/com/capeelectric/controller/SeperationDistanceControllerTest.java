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
import com.capeelectric.exception.SeperationDistanceException;
import com.capeelectric.model.SeperationDistanceReport;
import com.capeelectric.service.impl.SeperationDistanceServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class SeperationDistanceControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(SeperationDistanceController.class);

	@InjectMocks
	private SeperationDistanceController seperationDistanceController;

	@MockBean
	private SeperationDistanceServiceImpl seperationDistanceServiceImpl;

	private SeperationDistanceReport seperationDistanceReport;

	{
		seperationDistanceReport = new SeperationDistanceReport();
		seperationDistanceReport.setBasicLpsId(1);
		seperationDistanceReport.setUserName("LVsystem@gmail.com");
		seperationDistanceReport.setUserName("Inspector@gmail.com");
		seperationDistanceReport.setBasicLpsId(1);
	}

	@Test
	public void testAddSeperationDistance() throws SeperationDistanceException, AirTerminationException {
		logger.info("testAddSeperationDistance Function Started");

		doNothing().when(seperationDistanceServiceImpl).addSeperationDistance(seperationDistanceReport);
		ResponseEntity<String> addSeperationDistance = seperationDistanceController
				.addSeperationDistance(seperationDistanceReport);
		equals(addSeperationDistance.getBody());
		logger.info("testAddSeperationDistance Function Ended");
	}

	@Test
	public void testRetrieveSeperationDetails() throws SeperationDistanceException {
		List<SeperationDistanceReport> arrayList = new ArrayList<>();
		arrayList.add(seperationDistanceReport);

		logger.info("testRetrieveSPDDetails Function Started");

		when(seperationDistanceServiceImpl.retrieveSeperationDetails("LVsystem@gmail.com", 12)).thenReturn(arrayList);
		ResponseEntity<List<SeperationDistanceReport>> retrieveSeperationDistance = seperationDistanceController
				.retrieveSeperationDistance("LVsystem@gmail.com", 12);
		assertEquals(HttpStatus.OK, retrieveSeperationDistance.getStatusCode());

		logger.info("testRetrieveSPDDetails Function Ended");

	}

	@Test
	public void testUpdateSeperationDetails() throws SeperationDistanceException, AirTerminationException {

		logger.info("testUpdateSeperationDetails Function Started");
		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>(HttpStatus.OK);
		ResponseEntity<String> actualResponseEntity = seperationDistanceController
				.updateSeperationDistance(seperationDistanceReport);
		assertEquals(actualResponseEntity.getStatusCode(), expectedResponseEntity.getStatusCode());
		logger.info("testUpdateSeperationDetails Function Ended");
	}

}
