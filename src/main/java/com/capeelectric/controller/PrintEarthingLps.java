package com.capeelectric.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.service.PrintEarthingLpsService;

@RestController
@RequestMapping("/api/lps/v1")
public class PrintEarthingLps {

	private static final Logger logger = LoggerFactory.getLogger(EarthingLpsController.class);
	
	@Autowired
	private PrintEarthingLpsService printEarthingLpsService;

//	@GetMapping("/printEarthingLps/{userName}/{basicLpsId}")
//	public ResponseEntity<List<EarthingLpsDescription>> printEarthingLps(@PathVariable String userName,
//			@PathVariable Integer basicLpsId) throws EarthingLpsException {
//		logger.info("started printingEarthingLPS function UserName : {}, BasicLpsId : {}", userName, basicLpsId);
//		return new ResponseEntity<List<EarthingLpsDescription>>(
//				printEarthingLpsService.printEarthingLpsDetails(userName, basicLpsId), HttpStatus.OK);
//	}
}
