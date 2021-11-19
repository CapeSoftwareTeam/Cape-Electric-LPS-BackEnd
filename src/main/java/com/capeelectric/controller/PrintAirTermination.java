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

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.service.PrintAirTerminationService;

@RestController()
@RequestMapping("/api/lps/v1")
public class PrintAirTermination {
	private static final Logger logger = LoggerFactory.getLogger(PrintAirTermination.class);

	@Autowired
	private PrintAirTerminationService printAirTerminationService;

	@GetMapping("/printAirTermination/{userName}/{lpsId}")
	public ResponseEntity<String> printAirTermination(@PathVariable String userName, @PathVariable Integer lpsId)
			throws AirTerminationException {
		logger.info("called printSummary function userName: {},siteId : {}", userName, lpsId);
		printAirTerminationService.printAirTermination(userName, lpsId);
		return new ResponseEntity(HttpStatus.OK);
	}
}
