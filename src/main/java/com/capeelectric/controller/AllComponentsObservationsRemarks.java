/**
 * 
 */
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

import com.capeelectric.model.AllStepsRemarks;
import com.capeelectric.service.ObservationService;
import com.capeelectric.service.impl.FinalReportServiceImpl;


/**
 * @author CAPE-SOFTWARE
 *
 */
@RestController
@RequestMapping("/api/lps/v1")
public class AllComponentsObservationsRemarks {
	private static final Logger logger = LoggerFactory.getLogger(AllComponentsObservationsRemarks.class);

	@Autowired
	private ObservationService observationService;

	@GetMapping("/retrieveObservationsInSummary/{basicLpsId}")
	public ResponseEntity<AllStepsRemarks> retrieveObservationsInSummary(@PathVariable Integer basicLpsId) {
		logger.info("called retrieveObservationsInSummaryLps function");
		return new ResponseEntity<AllStepsRemarks>(
				observationService.retrieveObservationsInSummary(basicLpsId), HttpStatus.OK);
	}
}
