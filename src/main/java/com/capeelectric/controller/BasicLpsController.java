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

import com.capeelectric.exception.BasicLpsException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.service.BasicLpsService;

/**
 * @author CAPE-SOFTWARE
 *
 */

@RestController
@RequestMapping("/api/lps/v2")
public class BasicLpsController {

	private static final Logger logger = LoggerFactory.getLogger(BasicLpsController.class);
	
	@Autowired
	private BasicLpsService basicLpsService;
	
	@PostMapping("/addBasicLps")
	public ResponseEntity<BasicLps> addBasicLpsDetails(@RequestBody BasicLps basicLps)
			throws BasicLpsException {
		logger.info("called addBasicLpsDetails function UserName : {}", basicLps.getUserName());
		return new ResponseEntity<BasicLps>(basicLpsService.addBasicLpsDetails(basicLps), HttpStatus.CREATED);
	}

	@GetMapping("/retrieveBasicLps/{userName}/{basicLpsId}")
	public ResponseEntity<List<BasicLps>> retrieveBasicLpsDetails(@PathVariable String userName,
			@PathVariable Integer basicLpsId)
			throws BasicLpsException {
		logger.info("called retrieveBasicLpsDetails function UserName: {}, basicLpsId : {}", userName, basicLpsId);
		return new ResponseEntity<List<BasicLps>>(basicLpsService.retrieveBasicLpsDetails(userName, basicLpsId),
				HttpStatus.OK);
	}
	
	@PutMapping("/updateBasicLps")
	public ResponseEntity<String> updateBasicLpsDetails(@RequestBody BasicLps basicLps)
			throws BasicLpsException {
		logger.info("called updateBasicLpsDetails function UserName : {},BasicLpsId : {}", basicLps.getUserName(),
				basicLps.getBasicLpsId());
		basicLpsService.updateBasicLpsDetails(basicLps);
		return new ResponseEntity<String>("Basic Lps Details Updated Successfully", HttpStatus.OK);
	}
	

	
}
