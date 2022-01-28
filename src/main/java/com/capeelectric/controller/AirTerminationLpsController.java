package com.capeelectric.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.model.AirTermination;
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.service.AirTerminationLpsService;

@RestController
@RequestMapping("/api/lps/v1")
public class AirTerminationLpsController {

	private static final Logger logger = LoggerFactory.getLogger(AirTerminationLpsController.class);

	@Autowired
	private AirTerminationLpsService airTerminationLpsService;

	@PostMapping("/addAirTerminationLps")
	public ResponseEntity<String> addAirTerminationLps(@RequestBody AirTermination airTermination)
			throws AirTerminationException {
		logger.info("called addAirTerminationLpsDetails function UserName: {}",airTermination.getUserName());
		airTerminationLpsService.addAirTerminationLpsDetails(airTermination);
		logger.info("Ended addAirTerminationLpsDetails function");
		return new ResponseEntity<String>("Lps Air Terminal Successfully Saved", HttpStatus.CREATED);
	}
	
	@GetMapping("/retrieveAirTerminationLps/{userName}/{basicLpsId}")
	public ResponseEntity<List<AirTermination>> retrieveAirTerminationLps(@PathVariable String userName,
			@PathVariable Integer basicLpsId)
			throws AirTerminationException {
		logger.info("called retrieveAirTerminationLpsDetails function UserName: {}, basicLpsId : {}", userName, basicLpsId);
		return new ResponseEntity<List<AirTermination>>(airTerminationLpsService.retrieveAirTerminationLps(userName, basicLpsId),
				HttpStatus.OK);
	}
	

	@PutMapping("/updateAirTerminationLps")
	public ResponseEntity<String> updateAirTerminationLps(@RequestBody AirTermination airTermination)
			throws AirTerminationException {
		logger.info("called updateAirTerminationLps function UserName : {},BasicLpsId : {},LpsAirDescId : {}",
				airTermination.getUserName(), airTermination.getBasicLpsId(),
				airTermination.getAirTerminationId());
		airTerminationLpsService.updateAirTerminationLps(airTermination);
		logger.info("Ended updateAirTerminationLps function");
	   return new ResponseEntity<String>("AirTerminationLps successfully Updated", HttpStatus.OK);
	}

}
