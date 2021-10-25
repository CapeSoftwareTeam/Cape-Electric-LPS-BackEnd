package com.capeelectric.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.exception.BasicLpsException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.service.AirTerminationLpsService;
import com.capeelectric.service.BasicLpsService;

@RestController
@RequestMapping("/api/v1")
public class AirTerminationLpsController {

	private static final Logger logger = LoggerFactory.getLogger(AirTerminationLpsController.class);

	@Autowired
	private AirTerminationLpsService airTerminationLpsService;

	@PostMapping("/addAirTerminationLps")
	public ResponseEntity<String> addAirTerminationLps(@RequestBody LpsAirDiscription lpsAirDescription)
			throws AirTerminationException {
		logger.info("called addAirTerminationLpsDetails function", lpsAirDescription.getUserName());
		airTerminationLpsService.addAirTerminationLpsDetails(lpsAirDescription);
		return new ResponseEntity<String>("Lps Air Terminal Successfully Saved", HttpStatus.CREATED);
	}

}
