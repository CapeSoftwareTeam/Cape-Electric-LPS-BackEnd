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
import com.capeelectric.exception.DownConductorException;
import com.capeelectric.model.DownConductorDescription;
import com.capeelectric.model.DownConductorReport;
import com.capeelectric.service.DownConductorService;

/**
 * @author CAPE-SOFTWARE
 *
 */

@RestController
@RequestMapping("/api/lps/v1")
public class DownConductorController {

	private static final Logger logger = LoggerFactory.getLogger(DownConductorController.class);
	
	@Autowired
	private DownConductorService downConductorService;
	
	@PostMapping("/addDownConductor")
	public ResponseEntity<String> addDownConductors(@RequestBody  DownConductorReport downConductorReport)
			throws DownConductorException, AirTerminationException {
		logger.info("called addDownConductors function UserName : {}, SiteId : {}",
				downConductorReport.getUserName(), downConductorReport.getBasicLpsId());
		downConductorService.addDownConductorsDetails(downConductorReport);
		logger.info("Ended addDownConductors function");
		return new ResponseEntity<String>("Down Conductors Details Sucessfully Saved",
				HttpStatus.CREATED);
	}

	@GetMapping("/retrieveDownConductor/{userName}/{basicLpsId}")
	public ResponseEntity<List<DownConductorReport>> retrieveDownConductor(@PathVariable String userName,
			@PathVariable Integer basicLpsId) throws DownConductorException {
		logger.info("started retrieveDownConductor function UserName : {}, SiteId : {}", userName, basicLpsId);
		return new ResponseEntity<List<DownConductorReport>>(
				downConductorService.retrieveDownConductorDetails(userName, basicLpsId), HttpStatus.OK);
	}
	
	@PutMapping("/updateDownConductor")
	public ResponseEntity<String> updateDownConductor(@RequestBody DownConductorReport downConductorReport)
			throws DownConductorException, AirTerminationException {
		logger.info("called updateDownConductor function UserName : {},BasicLpsId : {},DownConductorReportId : {}",
				downConductorReport.getUserName(), downConductorReport.getBasicLpsId(),
				downConductorReport.getDownConductorReportId());
		downConductorService.updateDownConductorDetails(downConductorReport);
		logger.info("Ended updateDownConductor function");
	   return new ResponseEntity<String>("Down Conductors Details successfully Updated", HttpStatus.OK);
	}
}
