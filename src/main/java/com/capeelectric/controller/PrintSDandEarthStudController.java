package com.capeelectric.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.EarthStudException;
import com.capeelectric.service.PrintSDandEarthStudService;

@RestController()
@RequestMapping("/api/lps/v1")
public class PrintSDandEarthStudController {
	private static final Logger logger = LoggerFactory.getLogger(PrintSDandEarthStudController.class);

	@Autowired
	private PrintSDandEarthStudService printSDandEarthStudService;

	@GetMapping("/printSDandEarthStud/{userName}/{lpsId}")
	public ResponseEntity<String> printSDandEarthStud(@PathVariable String userName, @PathVariable Integer lpsId)
			throws EarthStudException {
		logger.info("called printBasicLps UserName: {},BasicLpsId : {}", userName, lpsId);
//		printSDandEarthStudService.printSDandEarthStud1(userName, lpsId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
