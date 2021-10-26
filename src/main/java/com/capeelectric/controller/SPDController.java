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

import com.capeelectric.exception.SPDException;
import com.capeelectric.model.SPDDescription;
import com.capeelectric.service.SPDService;



/**
 * @author CAPE-SOFTWARE
 *
 */

@RestController
@RequestMapping("/api/v1")
public class SPDController {
	
	private static final Logger logger = LoggerFactory.getLogger(SPDController.class);

	@Autowired
	private SPDService SPDService;
	
	@PostMapping("/addSPDDetails")
	public ResponseEntity<String> addSPDDetails(@RequestBody  SPDDescription SPDDesc)
			throws SPDException {
		logger.info("called addSPDDetails function UserName : {}, SiteId : {}",
				SPDDesc.getUserName(), SPDDesc.getBasicLpsId());
		SPDService.addSPDDetails(SPDDesc);
		return new ResponseEntity<String>("SPD Details Sucessfully Saved",
				HttpStatus.CREATED);
	}

	@GetMapping("/retrieveSPD/{userName}/{basicLpsId}")
	public ResponseEntity<List<SPDDescription>> retrieveSPDDetails(@PathVariable String userName,
			@PathVariable Integer basicLpsId) throws SPDException {
		logger.info("started retrieveSPDDetails function UserName : {}, SiteId : {}", userName, basicLpsId);
		return new ResponseEntity<List<SPDDescription>>(
				SPDService.retrieveSPDDetails(userName, basicLpsId), HttpStatus.OK);
	}
}
