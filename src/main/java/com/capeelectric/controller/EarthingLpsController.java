/**
 * 
 */
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
import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.model.EarthingReport;
import com.capeelectric.service.EarthingLpsService;


/**
 * @author CAPE-SOFTWARE
 *
 */
@RestController
@RequestMapping("/api/lps/v1")
public class EarthingLpsController {

private static final Logger logger = LoggerFactory.getLogger(EarthingLpsController.class);
	
	@Autowired
	private EarthingLpsService earthingLpsService;
	
	@PostMapping("/addEarthingLps")
	public ResponseEntity<String> addEarthingLps(@RequestBody   EarthingReport earthingReport)
			throws EarthingLpsException, AirTerminationException {
		logger.info("called addEarthingLps function UserName : {}, BasicLpsId : {}",
				earthingReport.getUserName(), earthingReport.getBasicLpsId());
		earthingLpsService.addEarthingLpsDetails(earthingReport);
		logger.info("Ended addEarthingLps function");
		return new ResponseEntity<String>("Earthing Details Sucessfully Saved",
				HttpStatus.CREATED);
	}

	@GetMapping("/retrieveEarthingLps/{userName}/{basicLpsId}")
	public ResponseEntity<List<EarthingReport>> retrieveEarthingLps(@PathVariable String userName,
			@PathVariable Integer basicLpsId) throws EarthingLpsException {
		logger.info("started retrieveEarthingLps function UserName : {}, BasicLpsId : {}", userName, basicLpsId);
		return new ResponseEntity<List<EarthingReport>>(
				earthingLpsService.retrieveEarthingLpsDetails(userName, basicLpsId), HttpStatus.OK);
	}
	
	@PutMapping("/updateEarthingLps")
	public ResponseEntity<String> updateEarthingLps(@RequestBody EarthingReport earthingReport)
			throws EarthingLpsException, AirTerminationException {
		logger.info("called updateEarthingLps function UserName : {},BasicLpsId : {},EarthingReportId : {}",
				earthingReport.getUserName(), earthingReport.getBasicLpsId(),
				earthingReport.getEarthingReportId());
		earthingLpsService.updateEarthingLpsDetails(earthingReport);
		logger.info("Ended updateEarthingLps function");
	   return new ResponseEntity<String>("Earthing LPS Details successfully Updated", HttpStatus.OK);
	}
}
