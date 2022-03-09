//package com.capeelectric.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.capeelectric.exception.DownConductorException;
//import com.capeelectric.service.PrintDownConductorService;
//
//@RestController()
//@RequestMapping("/api/lps/v1")
//public class PrintDownConductorController {
//	
//	
//	private static final Logger logger = LoggerFactory.getLogger(PrintDownConductorController.class);
//
//	@Autowired
//	private PrintDownConductorService printDownConductorService;
//
//	@GetMapping("/printDownConductor/{userName}/{lpsId}")
//	public ResponseEntity<String> printDownConductor(@PathVariable String userName, @PathVariable Integer lpsId)
//			throws  DownConductorException {
//		logger.info("called printDownConductor UserName: {},BasicLpsId : {}", userName, lpsId);
//		printDownConductorService.printDownConductor1(userName, lpsId);
//		return new ResponseEntity<String>(HttpStatus.OK);
//	}
//
//}
