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

import com.capeelectric.exception.SeperationDistanceException;
import com.capeelectric.model.SeperationDistanceDescription;
import com.capeelectric.service.SeperationDistanceService;


/**
 * @author CAPE-SOFTWARE
 *
 */
@RestController
@RequestMapping("/api/lps/v1")
public class SeperationDistanceController {

	private static final Logger logger = LoggerFactory.getLogger(SeperationDistanceController.class);

	@Autowired
	private SeperationDistanceService seperationDistanceService;
	
	@PostMapping("/addSeperationDistance")
	public ResponseEntity<String> addSeperationDistance(@RequestBody  SeperationDistanceDescription seperationDistanceDesc)
			throws SeperationDistanceException {
		logger.info("called addSeperationDistance function UserName : {}, BasicLpsId : {}",
				seperationDistanceDesc.getUserName(), seperationDistanceDesc.getBasicLpsId());
		seperationDistanceService.addSeperationDistance(seperationDistanceDesc);
		logger.info("Ended addSeperationDistance function");
		return new ResponseEntity<String>("Seperation Distance Details Sucessfully Saved",
				HttpStatus.CREATED);
	}

	@GetMapping("/retrieveSeperationDistance/{userName}/{basicLpsId}")
	public ResponseEntity<List<SeperationDistanceDescription>> retrieveSeperationDistance(@PathVariable String userName,
			@PathVariable Integer basicLpsId) throws SeperationDistanceException {
		logger.info("started retrieveSeperationDistance function UserName : {}, SiteId : {}", userName, basicLpsId);
		return new ResponseEntity<List<SeperationDistanceDescription>>(
				seperationDistanceService.retrieveSeperationDetails(userName, basicLpsId), HttpStatus.OK);
	}
	
	@PutMapping("/updateSeperationDistance")
	public ResponseEntity<String> updateSeperationDistance(@RequestBody SeperationDistanceDescription seperationDistanceDesc)
			throws SeperationDistanceException {
		logger.info("called updateSeperationDistance function UserName : {},BasicLpsId : {},SeperationDistanceDescId : {}",
				seperationDistanceDesc.getUserName(), seperationDistanceDesc.getBasicLpsId(),
				seperationDistanceDesc.getSeperationDistanceId());
		seperationDistanceService.updateSeperationDetails(seperationDistanceDesc);
		logger.info("Ended updateSeperationDistance function");
	   return new ResponseEntity<String>("SPD Details successfully Updated", HttpStatus.OK);
	}
}
