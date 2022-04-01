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
import com.capeelectric.exception.SPDException;
import com.capeelectric.model.SpdReport;
import com.capeelectric.service.impl.SPDServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class SPDControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(SPDControllerTest.class);

	@InjectMocks
	private SPDController spdController;

	@MockBean
	private SPDServiceImpl spdServiceImpl;

	private SpdReport spdReport;

	{
		spdReport = new SpdReport();
		spdReport.setBasicLpsId(1);
		spdReport.setUserName("LVsystem@gmail.com");
		spdReport.setUserName("Inspector@gmail.com");
		spdReport.setBasicLpsId(1);
	}

	@Test
	public void testAddSPDDetails() throws SPDException, AirTerminationException {
		logger.info("testAddSPDDetails Function Started");

		doNothing().when(spdServiceImpl).addSPDDetails(spdReport);
		ResponseEntity<String> addSpdDetails = spdController.addSPDDetails(spdReport);
		equals(addSpdDetails.getBody());
		logger.info("testAddSPDDetails Function Ended");
	}

	@Test
	public void testRetrieveSPDDetails() throws SPDException {
		List<SpdReport> arrayList = new ArrayList<>();
		arrayList.add(spdReport);

		logger.info("testRetrieveSPDDetails Function Started");

		when(spdServiceImpl.retrieveSPDDetails("LVsystem@gmail.com", 12)).thenReturn(arrayList);
		ResponseEntity<List<SpdReport>> retrieveSpdReportDetails = spdController.retrieveSPDDetails("LVsystem@gmail.com", 12);
		assertEquals(HttpStatus.OK, retrieveSpdReportDetails.getStatusCode());

		logger.info("testRetrieveSPDDetails Function Ended");

	}

	@Test
	public void testUpdateSPD() throws SPDException, AirTerminationException {

		logger.info("testUpdateSPD Function Started");
		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>(HttpStatus.OK);
		ResponseEntity<String> actualResponseEntity = spdController.updateSpdDetails(spdReport);
		assertEquals(actualResponseEntity.getStatusCode(), expectedResponseEntity.getStatusCode());
		logger.info("testUpdateSPD Function Ended");
	}

}
