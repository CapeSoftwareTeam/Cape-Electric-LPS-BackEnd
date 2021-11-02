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

import com.capeelectric.exception.DownConductorException;
import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.model.DownConductorDescription;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.service.impl.DownConductorServiceImpl;
import com.capeelectric.service.impl.EarthingLpsServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class EarthingLpsControllerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(EarthingLpsControllerTest.class);

	@InjectMocks
	private EarthingLpsController earthingLpsController;

	@MockBean
	private EarthingLpsServiceImpl  earthingLpsServiceImpl;
	
	
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
		logger.info("testAddEarthingLpsDetails Function Started");

		doNothing().when(earthingLpsServiceImpl).addEarthingLpsDetails(earthingLpsDescription);
		ResponseEntity<String> addAirTerminalsDetails = earthingLpsController.addEarthingLps(earthingLpsDescription);
		equals(addAirTerminalsDetails.getBody());
        logger.info("testAddEarthingLpsDetails Function Ended");
	}
	
	@Test
	public void testRetrieveEarthingLps() throws  EarthingLpsException {
		List<EarthingLpsDescription> arrayList = new ArrayList<>();
		arrayList.add(earthingLpsDescription);

		logger.info("testRetrieveEarthingLpsDetails Function Started");

		when(earthingLpsServiceImpl.retrieveEarthingLpsDetails("LVsystem@gmail.com", 12)).thenReturn(arrayList);
		ResponseEntity<List<EarthingLpsDescription>> retrieveBasicLpsDetails = earthingLpsController
				.retrieveEarthingLps("LVsystem@gmail.com", 12);
		assertEquals(HttpStatus.OK, retrieveBasicLpsDetails.getStatusCode());

		logger.info("testRetrieveEarthingLpsDetails Function Ended");

	}
	
	@Test
	public void testUpdateEarthingLpsDetails() throws  EarthingLpsException{
		
		logger.info("testUpdateEarthingLpsDetails Function Started");
		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>(HttpStatus.OK);
		ResponseEntity<String> actualResponseEntity = earthingLpsController
				.updateEarthingLps(earthingLpsDescription);
		assertEquals(actualResponseEntity.getStatusCode(), expectedResponseEntity.getStatusCode());
		logger.info("testUpdateEarthingLpsDetails Function Ended");
	}
	
	

}
