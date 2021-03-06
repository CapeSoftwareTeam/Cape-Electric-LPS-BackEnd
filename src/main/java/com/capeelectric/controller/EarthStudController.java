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
import com.capeelectric.exception.BasicLpsException;
import com.capeelectric.exception.DownConductorException;
import com.capeelectric.exception.EarthStudException;
import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.exception.SPDException;
import com.capeelectric.model.EarthStudDescription;
import com.capeelectric.service.EarthStudService;



/**
 * @author CAPE-SOFTWARE
 *
 */

@RestController
@RequestMapping("/api/lps/v1")
public class EarthStudController {

	private static final Logger logger = LoggerFactory.getLogger(EarthStudController.class);

	@Autowired
	private EarthStudService earthStudService;
	
	@PostMapping("/addEarthStud")
	public ResponseEntity<String> addEarthStud(@RequestBody  EarthStudDescription earthStudDescription)
			throws EarthStudException, BasicLpsException, AirTerminationException, DownConductorException, EarthingLpsException, SPDException, Exception {
		logger.info("called addEarthStud function UserName : {}, BasicLpsId : {}",
				earthStudDescription.getUserName(), earthStudDescription.getBasicLpsId());
		earthStudService.addEarthStudDetails(earthStudDescription);
		return new ResponseEntity<String>("Earth Stud Details Sucessfully Submitted",
				HttpStatus.CREATED);
	}

	@GetMapping("/retrieveEarthStud/{userName}/{basicLpsId}")
	public ResponseEntity<List<EarthStudDescription>> retrieveEarthStudDetails(@PathVariable String userName,
			@PathVariable Integer basicLpsId) throws EarthStudException {
		logger.info("started retrieveEarthStud function UserName : {}, BasicLpsId : {}", userName, basicLpsId);
		return new ResponseEntity<List<EarthStudDescription>>(
				earthStudService.retrieveEarthStudDetails(userName, basicLpsId), HttpStatus.OK);
	}
	
	@PutMapping("/updateEarthStud")
	public ResponseEntity<String> updateEarthStud(@RequestBody EarthStudDescription earthStudDescription)
			throws EarthStudException {
		logger.info("called updateEarthStud function UserName : {},BasicLpsId : {},SeperationDistanceDescId : {}",
				earthStudDescription.getUserName(), earthStudDescription.getBasicLpsId(),
				earthStudDescription.getEarthStudDescId());
		earthStudService.updateEarthStudDetails(earthStudDescription);
	   return new ResponseEntity<String>("Earth Stud Details successfully Updated", HttpStatus.OK);
	}
}
