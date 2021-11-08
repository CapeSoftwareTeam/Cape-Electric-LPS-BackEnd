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

import com.capeelectric.exception.SPDException;
import com.capeelectric.exception.SeperationDistanceException;
import com.capeelectric.model.SPD;
import com.capeelectric.model.SeperationDistanceDescription;
import com.capeelectric.service.impl.SPDServiceImpl;
import com.capeelectric.service.impl.SeperationDistanceServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class SeperationDistanceControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(SeperationDistanceController.class);

	@InjectMocks
	private SeperationDistanceController seperationDistanceController;

	@MockBean
	private SeperationDistanceServiceImpl seperationDistanceServiceImpl;

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
		logger.info("testAddSeperationDistance Function Started");

		doNothing().when(seperationDistanceServiceImpl).addSeperationDistance(seperationDistanceDescription);
		ResponseEntity<String> addSeperationDistance = seperationDistanceController
				.addSeperationDistance(seperationDistanceDescription);
		equals(addSeperationDistance.getBody());
		logger.info("testAddSeperationDistance Function Ended");
	}

	@Test
	public void testRetrieveSeperationDetails() throws SeperationDistanceException {
		List<SeperationDistanceDescription> arrayList = new ArrayList<>();
		arrayList.add(seperationDistanceDescription);

		logger.info("testRetrieveSPDDetails Function Started");

		when(seperationDistanceServiceImpl.retrieveSeperationDetails("LVsystem@gmail.com", 12)).thenReturn(arrayList);
		ResponseEntity<List<SeperationDistanceDescription>> retrieveSeperationDistance = seperationDistanceController
				.retrieveSeperationDistance("LVsystem@gmail.com", 12);
		assertEquals(HttpStatus.OK, retrieveSeperationDistance.getStatusCode());

		logger.info("testRetrieveSPDDetails Function Ended");

	}

	@Test
	public void testUpdateSeperationDetails() throws SeperationDistanceException {

		logger.info("testUpdateSeperationDetails Function Started");
		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>(HttpStatus.OK);
		ResponseEntity<String> actualResponseEntity = seperationDistanceController
				.updateSeperationDistance(seperationDistanceDescription);
		assertEquals(actualResponseEntity.getStatusCode(), expectedResponseEntity.getStatusCode());
		logger.info("testUpdateSeperationDetails Function Ended");
	}

}
