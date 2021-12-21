package com.capeelectric.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.capeelectric.service.impl.AWSEmailService;

@RestController
@RequestMapping("/api/lps/v1")
public class MailPDFController {

	@Autowired
	private AWSEmailService awsEmailService;

	@GetMapping("/sendPDFinMail/{userName}/{lpsId}")
	public ResponseEntity<byte[]> sendFinalPDF(@PathVariable String userName, @PathVariable Integer lpsId)
			throws BasicLpsException, AirTerminationException, DownConductorException, SPDException,
			EarthStudException, EarthingLpsException, Exception {

		String keyname = "Lpsfinalreport.pdf";
		awsEmailService.sendEmailPDF(userName,lpsId, lpsId, keyname);
		
		return null;
	}
}