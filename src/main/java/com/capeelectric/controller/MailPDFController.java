package com.capeelectric.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.exception.BasicLpsException;
import com.capeelectric.exception.DownConductorException;
import com.capeelectric.exception.EarthStudException;
import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.exception.SPDException;
import com.capeelectric.service.PrintAirTerminationService;
import com.capeelectric.service.PrintBasicLpsService;
import com.capeelectric.service.PrintDownConductorService;
import com.capeelectric.service.PrintEarthingLpsService;
import com.capeelectric.service.PrintSDandEarthStudService;
import com.capeelectric.service.PrintSPDService;
import com.capeelectric.service.impl.AWSEmailService;

@RestController
@RequestMapping("/api/lps/v1")
public class MailPDFController {

	@Autowired
	private PrintBasicLpsService printBasicLpsService;

	@Autowired
	private PrintAirTerminationService printAirTerminationService;

	@Autowired
	private PrintDownConductorService printDownConductorService;

	@Autowired
	private PrintSPDService printSPDService;

	@Autowired
	private PrintEarthingLpsService printEarthingLpsService;

	@Autowired
	private PrintSDandEarthStudService printSDandEarthStudService;

	@Autowired
	private AWSEmailService awsEmailService;

	@GetMapping("/sendPDFinMail/{userName}/{lpsId}")
	public ResponseEntity<String> sendFinalPDF(@PathVariable String userName, @PathVariable Integer lpsId)
			throws BasicLpsException, AirTerminationException, DownConductorException, SPDException,
			EarthStudException, MessagingException, EarthingLpsException {
		printBasicLpsService.printBasicLps(userName, lpsId);
		printAirTerminationService.printAirTermination(userName, lpsId);
		printDownConductorService.printDownConductor(userName, lpsId);
		printEarthingLpsService.printEarthingLpsDetails(userName, lpsId);
		printSPDService.printSPD(userName, lpsId);
		printSDandEarthStudService.printSDandEarthStud(userName, lpsId);
	    awsEmailService.sendEmailPDF(userName);
		return new ResponseEntity<String>("Final LPS-PDF file has been sent to your registered mail id.", HttpStatus.OK);
	}

}
