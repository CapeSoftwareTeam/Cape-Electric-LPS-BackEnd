package com.capeelectric.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.FinalReportException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.LpsFinalReport;
import com.capeelectric.service.FinalReportService;
import com.capeelectric.service.impl.FinalReportServiceImpl;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@RestController
@RequestMapping("/api/lps/v1")
public class FinalReportController {

	private static final Logger logger = LoggerFactory.getLogger(FinalReportController.class);

	@Autowired
	FinalReportService finalReportService;

	@GetMapping("/retrieveListOfBasicLps/{userName}")
	public ResponseEntity<List<BasicLps>> retrieveListOfBasicLps(@PathVariable String userName)
			throws FinalReportException {
		logger.info("FinalReportAPI_started retrieveListOfBasicLps function UserName: {}", userName);
		return new ResponseEntity<List<BasicLps>>(finalReportService.retrieveListOfBasicLps(userName),
				HttpStatus.OK);

	}

	@GetMapping("/retrieveLpsReport/{userName}/{basicLpsId}")
	public ResponseEntity<Optional<LpsFinalReport>> retrieveLpsReports(@PathVariable String userName,
			@PathVariable Integer basicLpsId) throws FinalReportException {
		logger.info("FinalReportAPI_started retrieveFinalLpsReport function userName: {},basicLpsId : {}", userName,
				basicLpsId);

		return new ResponseEntity<Optional<LpsFinalReport>>(
				finalReportService.retrieveLpsReports(userName, basicLpsId), HttpStatus.OK);

	}
	
	@GetMapping("/retrieveAllBasicLps")
	public ResponseEntity<List<BasicLps>> retrieveAllBasicLps() throws FinalReportException{
		logger.info("FinalReportAPI_started retrieveAllBasicLps");
			
		return new ResponseEntity<List<BasicLps>>(finalReportService.retrieveAllBasicLps(),
						HttpStatus.OK);
	}

}