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
import com.capeelectric.exception.SPDException;
import com.capeelectric.model.DownConductorDescription;
import com.capeelectric.model.SPD;
import com.capeelectric.service.impl.DownConductorServiceImpl;
import com.capeelectric.service.impl.SPDServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class SPDControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(SPDControllerTest.class);

	@InjectMocks
	private SPDController spdController;

	@MockBean
	private SPDServiceImpl  spdServiceImpl;
	
	
private SPD spdDesc;
	

	{
		spdDesc = new SPD();
		spdDesc.setBasicLpsId(1);
		spdDesc.setUserName("LVsystem@gmail.com");
		spdDesc.setUserName("Inspector@gmail.com");
		spdDesc.setBasicLpsId(1);
	}
	

	@Test
	public void testAddSPDDetails() throws  SPDException {
		logger.info("testAddSPDDetails Function Started");

		doNothing().when(spdServiceImpl).addSPDDetails(spdDesc);
		ResponseEntity<String> addAirTerminalsDetails = spdController.addSPDDetails(spdDesc);
		equals(addAirTerminalsDetails.getBody());
        logger.info("testAddSPDDetails Function Ended");
	}
	
	@Test
	public void testRetrieveSPDDetails() throws   SPDException {
		List<SPD> arrayList = new ArrayList<>();
		arrayList.add(spdDesc);

		logger.info("testRetrieveSPDDetails Function Started");

		when(spdServiceImpl.retrieveSPDDetails("LVsystem@gmail.com", 12)).thenReturn(arrayList);
		ResponseEntity<List<SPD>> retrieveBasicLpsDetails = spdController
				.retrieveSPDDetails("LVsystem@gmail.com", 12);
		assertEquals(HttpStatus.OK, retrieveBasicLpsDetails.getStatusCode());

		logger.info("testRetrieveSPDDetails Function Ended");

	}
	
	@Test
	public void testUpdateSPD() throws SPDException{
		
		logger.info("testUpdateSPD Function Started");
		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>(HttpStatus.OK);
		ResponseEntity<String> actualResponseEntity = spdController
				.updateSpdDetails(spdDesc);
		assertEquals(actualResponseEntity.getStatusCode(), expectedResponseEntity.getStatusCode());
		logger.info("testUpdateSPD Function Ended");
	}
	
}
