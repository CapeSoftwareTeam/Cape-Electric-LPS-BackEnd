package com.capeelectric.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Site;
import com.capeelectric.service.SiteService;

@RestController()
@RequestMapping("/api/v1")
public class SiteController {        
	private static final Logger logger = LoggerFactory.getLogger(SiteController.class);

	@Autowired
	private SiteService siteService;

	@PostMapping("/addSite")
	public ResponseEntity<String> addSite(@RequestBody Site site) throws CompanyDetailsException {
		logger.info("called addSite function UserName : {}, Site : {}",
				 site.getSite());
 
		siteService.addSite(site);
		return new ResponseEntity<String>("Site Successfully Saved", HttpStatus.CREATED);

	}

	@PutMapping("/updateSite")
	public ResponseEntity<String> updateSite(@RequestBody Site site) throws CompanyDetailsException {
		logger.info("called updateSite function UserName: {},Site : {}", site.getUserName(), site.getSite());
		siteService.updateSite(site);
		return new ResponseEntity<String>("Site Successfully Updated", HttpStatus.OK);
	}

	@DeleteMapping("/deleteSite/{siteId}")
	public ResponseEntity<String> deleteSite(@PathVariable Integer siteId) throws CompanyDetailsException {
		logger.info("called deleteSite function siteId: {}", siteId);
		siteService.deleteSite(siteId);
		return new ResponseEntity<String>("Site Succesfully Deleted",HttpStatus.OK);
	}

	
	@GetMapping("/retriveSite/{userName}")
	public ResponseEntity<List<Site>> retriveSite(@PathVariable String userName) throws CompanyDetailsException {
		logger.info("called retriveSite function UserName: {}", userName);
		return new ResponseEntity<List<Site>>(siteService.retriveSite(userName), HttpStatus.OK);
	}
	
	@GetMapping("/retrieveSiteByName/{companyName}/{department}/{siteName}")
	public ResponseEntity<Site> retrieveSiteByName(@PathVariable String companyName, 
			@PathVariable String department, @PathVariable String siteName) throws CompanyDetailsException{
		logger.info("called retriveSiteByName function Company Name: {}, Department: {}, Site Name: {}", companyName, department, siteName);
		return new ResponseEntity<Site>(siteService.retrieveSiteByName(companyName, department, siteName), HttpStatus.OK);
	}

}