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
import com.capeelectric.exception.BasicLpsException;
import com.capeelectric.exception.PeriodicTestingException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.model.TestingReport;
import com.capeelectric.service.AirTerminationLpsService;
import com.capeelectric.service.BasicLpsService;
import com.itextpdf.text.log.SysoCounter;

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
	
	@GetMapping("/retrieveAirTerminationLps/{userName}/{basicLpsId}")
	public ResponseEntity<List<LpsAirDiscription>> retrieveAirTerminationLps(@PathVariable String userName,
			@PathVariable Integer basicLpsId)
			throws AirTerminationException {
		logger.info("called retrieveAirTerminationLpsDetails function UserName: {}, basicLpsId : {}", userName, basicLpsId);
		return new ResponseEntity<List<LpsAirDiscription>>(airTerminationLpsService.retrieveAirTerminationLps(userName, basicLpsId),
				HttpStatus.OK);
	}
	

	@PutMapping("/updateAirTerminationLps")
	public ResponseEntity<String> updateAirTerminationLps(@RequestBody LpsAirDiscription lpsAirDescription)
			throws AirTerminationException {
		logger.info("called updateAirTerminationLps function UserName : {},BasicLpsId : {},LpsAirDescId : {}",
				lpsAirDescription.getUserName(), lpsAirDescription.getBasicLpsId(),
				lpsAirDescription.getLpsAirDescId());
		airTerminationLpsService.updateAirTerminationLps(lpsAirDescription);
	   return new ResponseEntity<String>("AirTerminationLps successfully Updated", HttpStatus.OK);
	}

}
