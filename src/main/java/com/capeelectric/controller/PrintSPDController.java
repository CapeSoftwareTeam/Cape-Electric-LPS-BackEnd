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

import com.capeelectric.exception.SPDException;
import com.capeelectric.service.PrintSPDService;

@RestController
@RequestMapping("/api/lps/v1")
public class PrintSPDController {

	private static final Logger logger = LoggerFactory.getLogger(PrintSPDController.class);

	@Autowired
	private PrintSPDService printSPDService;

	@GetMapping("/printSPD/{userName}/{lpsId}")
	public ResponseEntity<String> printSPD(@PathVariable String userName, @PathVariable Integer lpsId)
			throws SPDException {
		logger.info("called printSPD UserName: {},BasicLpsId : {}", userName, lpsId);
//		printSPDService.printSPD1(userName, lpsId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
