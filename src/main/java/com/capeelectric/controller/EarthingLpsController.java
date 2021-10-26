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

import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.service.EarthingLpsService;


/**
 * @author CAPE-SOFTWARE
 *
 */
@RestController
@RequestMapping("/api/v1")
public class EarthingLpsController {

private static final Logger logger = LoggerFactory.getLogger(EarthingLpsController.class);
	
	@Autowired
	private EarthingLpsService earthingLpsService;
	
	@PostMapping("/addEarthingLps")
	public ResponseEntity<String> addEarthingLps(@RequestBody   EarthingLpsDescription earthingLpsDescription)
			throws EarthingLpsException {
		logger.info("called addEarthingLps function UserName : {}, SiteId : {}",
				earthingLpsDescription.getUserName(), earthingLpsDescription.getBasicLpsId());
		earthingLpsService.addEarthingLpsDetails(earthingLpsDescription);
		return new ResponseEntity<String>("Earthing Details Sucessfully Saved",
				HttpStatus.CREATED);
	}

	@GetMapping("/retrieveEarthingLps/{userName}/{basicLpsId}")
	public ResponseEntity<List<EarthingLpsDescription>> retrieveEarthingLps(@PathVariable String userName,
			@PathVariable Integer basicLpsId) throws EarthingLpsException {
		logger.info("started retrieveEarthingLps function UserName : {}, SiteId : {}", userName, basicLpsId);
		return new ResponseEntity<List<EarthingLpsDescription>>(
				earthingLpsService.retrieveEarthingLpsDetails(userName, basicLpsId), HttpStatus.OK);
	}
}
