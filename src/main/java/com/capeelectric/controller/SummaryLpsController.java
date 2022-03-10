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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.exception.BasicLpsException;
import com.capeelectric.exception.DownConductorException;
import com.capeelectric.exception.EarthStudException;
import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.exception.SPDException;
import com.capeelectric.exception.SummaryLpsException;
import com.capeelectric.model.SummaryLps;
import com.capeelectric.service.SummaryLpsService;

/**
 * @author CAPE-SOFTWARE
 *
 */
@RestController
@RequestMapping("/api/lps/v1")
public class SummaryLpsController {
	
	private static final Logger logger = LoggerFactory.getLogger(SummaryLpsController.class);

	@Autowired
	private SummaryLpsService summaryLpsService;
	
	@PostMapping("/addSummaryLps")
	public ResponseEntity<String> addSummaryLps(@RequestBody  SummaryLps summaryLps)
			throws SummaryLpsException, BasicLpsException, AirTerminationException, DownConductorException, EarthingLpsException, SPDException,EarthStudException, Exception {
		logger.info("called addSummaryLps function UserName : {}, BasicLpsId : {}",
				summaryLps.getUserName(), summaryLps.getBasicLpsId());
		summaryLpsService.addSummaryLpsDetails(summaryLps);
		logger.info("Ended addSummaryLps function");
		return new ResponseEntity<String>("Summary Lps Details Sucessfully Submitted",
				HttpStatus.CREATED);
	}

	@GetMapping("/retrieveSummaryLps/{userName}/{basicLpsId}")
	public ResponseEntity<List<SummaryLps>> retrieveSummaryLps(@PathVariable String userName,
			@PathVariable Integer basicLpsId) throws SummaryLpsException {
		logger.info("started retrieveSummaryLps function UserName : {}, BasicLpsId : {}", userName, basicLpsId);
		return new ResponseEntity<List<SummaryLps>>(
				summaryLpsService.retrieveSummaryLpsDetails(userName, basicLpsId), HttpStatus.OK);
	}

}
