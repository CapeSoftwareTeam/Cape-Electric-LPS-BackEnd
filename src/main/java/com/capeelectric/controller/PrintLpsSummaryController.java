package com.capeelectric.controller;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.exception.SummaryLpsException;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.model.SummaryLps;
import com.capeelectric.service.PrintEarthingLpsService;
import com.capeelectric.service.PrintFinalPDFService;
import com.capeelectric.service.PrintSummaryLpsService;
import com.capeelectric.service.ReturnPDFService;

@RestController
@RequestMapping("/api/lps/v1")
public class PrintLpsSummaryController {

	private static final Logger logger = LoggerFactory.getLogger(EarthingLpsController.class);

	@Autowired
	private PrintSummaryLpsService printSummaryLpsService;

	private final ReturnPDFService returnPDFService;

	@Autowired
	private PrintFinalPDFService printFinalPDFService;

	@Autowired
	public PrintLpsSummaryController(ReturnPDFService returnPDFService) {
		this.returnPDFService = returnPDFService;
	}

	@GetMapping("/printLpsSummary/{userName}/{basicLpsId}/{projectName}")
	public ResponseEntity<byte[]> printEarthingLps(@PathVariable String userName, @PathVariable Integer basicLpsId,
			@PathVariable String projectName) throws SummaryLpsException, Exception {
		logger.info("started printingEarthingLPS function UserName : {}, BasicLpsId : {}", userName, basicLpsId);

		printSummaryLpsService.printLpsSummaryDetails(userName, basicLpsId);
		printFinalPDFService.printSummaryPDF(userName, basicLpsId, projectName);

		ByteArrayOutputStream downloadInputStream = returnPDFService.printSummaryPDF(userName, basicLpsId, projectName);
		String keyname = projectName + ".pdf";

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