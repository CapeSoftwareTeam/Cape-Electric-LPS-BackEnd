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

import com.capeelectric.exception.EarthStudException;
import com.capeelectric.model.EarthStudDescription;
import com.capeelectric.service.EarthStudService;



/**
 * @author CAPE-SOFTWARE
 *
 */

@RestController
@RequestMapping("/api/v1")
public class EarthStudController {

	private static final Logger logger = LoggerFactory.getLogger(EarthStudController.class);

	@Autowired
	private EarthStudService earthStudService;
	
	@PostMapping("/addEarthStud")
	public ResponseEntity<String> addEarthStud(@RequestBody  EarthStudDescription earthStudDescription)
			throws EarthStudException {
		logger.info("called addEarthStud function UserName : {}, SiteId : {}",
				earthStudDescription.getUserName(), earthStudDescription.getBasicLpsId());
		earthStudService.addEarthStudDetails(earthStudDescription);
		return new ResponseEntity<String>("Earth Stud Details Sucessfully Saved",
				HttpStatus.CREATED);
	}

	@GetMapping("/retrieveEarthStud/{userName}/{basicLpsId}")
	public ResponseEntity<List<EarthStudDescription>> retrieveEarthStudDetails(@PathVariable String userName,
			@PathVariable Integer basicLpsId) throws EarthStudException {
		logger.info("started retrieveEarthStud function UserName : {}, SiteId : {}", userName, basicLpsId);
		return new ResponseEntity<List<EarthStudDescription>>(
				earthStudService.retrieveEarthStudDetails(userName, basicLpsId), HttpStatus.OK);
	}
}
