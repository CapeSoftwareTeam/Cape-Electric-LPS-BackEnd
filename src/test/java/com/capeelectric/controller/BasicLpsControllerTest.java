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

import com.capeelectric.exception.BasicLpsException;
import com.capeelectric.exception.DownConductorException;
import com.capeelectric.exception.PeriodicTestingException;
import com.capeelectric.exception.SummaryException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.Summary;
import com.capeelectric.model.TestingReport;
import com.capeelectric.model.TestingReportComment;
import com.capeelectric.service.BasicLpsService;
import com.capeelectric.service.impl.BasicLpsServiceImpl;
import com.capeelectric.service.impl.LoginServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class BasicLpsControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(BasicLpsControllerTest.class);

	@InjectMocks
	private BasicLpsController basicLpsController;

	@MockBean
	private BasicLpsService basicLpsService;

	@MockBean
	private BasicLpsServiceImpl basicLpsServiceImpl;

	@MockBean
	private BasicLpsException basicLpsException;

	private BasicLps basicLps;

	{
		basicLps = new BasicLps();
		basicLps.setBasicLpsId(null);
		basicLps.setUserName("LVsystem@gmail.com");
		basicLps.setClientName("LVsystem@gmail.com");

	}

//	@Test
//	public void testAddBasicLpsDetails() throws BasicLpsException {
//
////		ResponseEntity<BasicLps> expectedResponseEntity = new ResponseEntity<BasicLps>(HttpStatus.CREATED);
////		ResponseEntity<BasicLps> actualResponseEntity = basicLpsController.addBasicLpsDetails(basicLps);
////		assertEquals(actualResponseEntity, expectedResponseEntity);
//		
//		
//		
//		logger.info("testAddBasicLpaDetails Function Started");
//
//		when(basicLpsServiceImpl.addBasicLpsDetails(basicLps));
//		ResponseEntity<BasicLps> addAirTerminalsDetails = basicLpsController.addBasicLpsDetails(basicLps);
//		equals(addAirTerminalsDetails.getBody());
//        logger.info("testAddBasicLpaDetails Function Ended");
//	
//
//	}

	@Test
	public void testAddBasicLpsDetails() throws BasicLpsException {
		logger.info("testAddDownConductorsDetails Function Started");

		doNothing().when(basicLpsServiceImpl).addBasicLpsDetails(basicLps);
		ResponseEntity<BasicLps> addAirTerminalsDetails = basicLpsController.addBasicLpsDetails(basicLps);
		equals(addAirTerminalsDetails.getBody());
		logger.info("testAddDownConductorsDetails Function Ended");
	}

//	@Test
//	public void testUpdatePeriodicTesting() throws BasicLpsException {
//		logger.info("testUpadateBasicLpaDetails Function Started");
//		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>(HttpStatus.OK);
//		ResponseEntity<String> actualResponseEntity = basicLpsController
//				.updatePeriodicTesting(basicLps);
//		assertEquals(actualResponseEntity.getStatusCode(), expectedResponseEntity.getStatusCode());
//		logger.info("testUpdatePeriodicTesting Function Ended");
//	}

	@Test
	public void testRetrieveBasicLpsDetails() throws BasicLpsException {
		List<BasicLps> arrayList = new ArrayList<>();
		arrayList.add(basicLps);

		logger.info("testRetrieveBasicLpsDetails Function Started");

		when(basicLpsServiceImpl.retrieveBasicLpsDetails("LVsystem@gmail.com", 12)).thenReturn(arrayList);
		ResponseEntity<List<BasicLps>> retrieveBasicLpsDetails = basicLpsController
				.retrieveBasicLpsDetails("LVsystem@gmail.com", 12);
		assertEquals(HttpStatus.OK, retrieveBasicLpsDetails.getStatusCode());

		logger.info("testRetrieveBasicLpsDetails Function Ended");

	}

}
