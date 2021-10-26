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

import com.capeelectric.exception.DownConductorException;
import com.capeelectric.model.DownConductorDescription;
import com.capeelectric.service.DownConductorService;

/**
 * @author CAPE-SOFTWARE
 *
 */

@RestController
@RequestMapping("/api/v1")
public class DownConductorController {

	private static final Logger logger = LoggerFactory.getLogger(DownConductorController.class);
	
	@Autowired
	private DownConductorService downConductorService;
	
	@PostMapping("/addDownConductor")
	public ResponseEntity<String> addDownConductors(@RequestBody  DownConductorDescription downConductorDesc)
			throws DownConductorException {
		logger.info("called addDownConductors function UserName : {}, SiteId : {}",
				downConductorDesc.getUserName(), downConductorDesc.getBasicLpsId());
		downConductorService.addDownConductorsDetails(downConductorDesc);
		return new ResponseEntity<String>("Down Conductors Details Sucessfully Saved",
				HttpStatus.CREATED);
	}

	@GetMapping("/retrieveDownConductor/{userName}/{basicLpsId}")
	public ResponseEntity<List<DownConductorDescription>> retrieveDownConductor(@PathVariable String userName,
			@PathVariable Integer basicLpsId) throws DownConductorException {
		logger.info("started retrieveDownConductor function UserName : {}, SiteId : {}", userName, basicLpsId);
		return new ResponseEntity<List<DownConductorDescription>>(
				downConductorService.retrieveDownConductorDetails(userName, basicLpsId), HttpStatus.OK);
	}
}
