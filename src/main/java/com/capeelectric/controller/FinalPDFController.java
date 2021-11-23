package com.capeelectric.controller;

import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
import com.capeelectric.service.PrintFinalPDFService;
import com.capeelectric.service.PrintSDandEarthStudService;
import com.capeelectric.service.PrintSPDService;
import com.capeelectric.service.ReturnPDFService;

@RestController
@RequestMapping("/api/lps/v1")
public class FinalPDFController {

	private final ReturnPDFService returnPDFService;

	@Autowired
	public FinalPDFController(ReturnPDFService returnPDFService) {
		this.returnPDFService = returnPDFService;
	}

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
	private PrintFinalPDFService printFinalPDFService;

	@GetMapping("/printFinalPDF/{userName}/{lpsId}")
	@ResponseBody
	public ResponseEntity<byte[]> printFinalPDF(@PathVariable String userName, @PathVariable Integer lpsId)
			throws Exception, BasicLpsException, AirTerminationException, DownConductorException, SPDException,
			EarthStudException, EarthingLpsException {
		printBasicLpsService.printBasicLps(userName, lpsId);
		printAirTerminationService.printAirTermination(userName, lpsId);
		printDownConductorService.printDownConductor(userName, lpsId);
		printEarthingLpsService.printEarthingLpsDetails(userName, lpsId);
		printSPDService.printSPD(userName, lpsId);
		printSDandEarthStudService.printSDandEarthStud(userName, lpsId);
		printFinalPDFService.printFinalPDF(userName, lpsId);

		String keyname = "Lpsfinalreport.pdf";
		ByteArrayOutputStream downloadInputStream = returnPDFService.printFinalPDF(userName, lpsId);

		return ResponseEntity.ok().contentType(contentType(keyname))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + keyname + "\"")
				.body(downloadInputStream.toByteArray());
	}

	private MediaType contentType(String keyname) {
		String[] fileArrSplit = keyname.split("\\.");
		String fileExtension = fileArrSplit[fileArrSplit.length - 1];
		switch (fileExtension) {
		case "txt":
			return MediaType.TEXT_PLAIN;
		case "png":
			return MediaType.IMAGE_PNG;
		case "jpg":
			return MediaType.IMAGE_JPEG;
		case "pdf":
			return MediaType.APPLICATION_PDF;
		default:
			return MediaType.APPLICATION_OCTET_STREAM;
		}
	}
}
