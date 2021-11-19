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
import com.capeelectric.exception.DownConductorException;
import com.capeelectric.model.DownConductorDescription;
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.service.impl.BasicLpsServiceImpl;
import com.capeelectric.service.impl.DownConductorServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class DownConductorControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(DownConductorControllerTest.class);

	@InjectMocks
	private DownConductorController downConductorController;

	@MockBean
	private DownConductorServiceImpl downConductorServiceImpl;

	private DownConductorDescription downConductorDescription;

	{
		downConductorDescription = new DownConductorDescription();
		downConductorDescription.setBasicLpsId(1);
		downConductorDescription.setUserName("LVsystem@gmail.com");
		downConductorDescription.setUserName("Inspector@gmail.com");
		downConductorDescription.setBasicLpsId(1);
	}

	@Test
	public void testAddDownConductorsDetails() throws DownConductorException {
		logger.info("testAddDownConductorsDetails Function Started");

		doNothing().when(downConductorServiceImpl).addDownConductorsDetails(downConductorDescription);
		ResponseEntity<String> addAirTerminalsDetails = downConductorController
				.addDownConductors(downConductorDescription);
		equals(addAirTerminalsDetails.getBody());
		logger.info("testAddDownConductorsDetails Function Ended");
	}

	@Test
	public void testRetrieveDownConductor() throws DownConductorException {
		List<DownConductorDescription> arrayList = new ArrayList<>();
		arrayList.add(downConductorDescription);

		logger.info("testRetrieveDownConductor Function Started");

		when(downConductorServiceImpl.retrieveDownConductorDetails("LVsystem@gmail.com", 12)).thenReturn(arrayList);
		ResponseEntity<List<DownConductorDescription>> retrieveBasicLpsDetails = downConductorController
				.retrieveDownConductor("LVsystem@gmail.com", 12);
		assertEquals(HttpStatus.OK, retrieveBasicLpsDetails.getStatusCode());

		logger.info("testRetrieveDownConductor Function Ended");

	}

	@Test
	public void testUpdateDownConductor() throws DownConductorException {

		logger.info("testUpdateDownConductor Function Started");
		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>(HttpStatus.OK);
		ResponseEntity<String> actualResponseEntity = downConductorController
				.updateDownConductor(downConductorDescription);
		assertEquals(actualResponseEntity.getStatusCode(), expectedResponseEntity.getStatusCode());
		logger.info("testUpdateDownConductor Function Ended");
	}

}
