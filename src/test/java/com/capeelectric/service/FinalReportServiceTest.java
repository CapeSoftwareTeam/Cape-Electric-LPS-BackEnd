package com.capeelectric.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capeelectric.exception.FinalReportException;
import com.capeelectric.model.FinalReport;
import com.capeelectric.model.PeriodicInspection;
import com.capeelectric.model.ReportDetails;
import com.capeelectric.model.Site;
import com.capeelectric.model.Summary;
import com.capeelectric.model.SupplyCharacteristics;
import com.capeelectric.model.TestingReport;
import com.capeelectric.repository.InspectionRepository;
import com.capeelectric.repository.InstalReportDetailsRepository;
import com.capeelectric.repository.SiteRepository;
import com.capeelectric.repository.SummaryRepository;
import com.capeelectric.repository.SupplyCharacteristicsRepository;
import com.capeelectric.repository.TestingReportRepository;
import com.capeelectric.service.impl.FinalReportServiceImpl;

/**
 * @author capeelectricsoftware
 *
 */
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class FinalReportServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(FinalReportServiceTest.class);

	@InjectMocks
	private FinalReportServiceImpl finalReportServiceImpl;

	@MockBean
	private SiteRepository siteRepository;

	@MockBean
	private InstalReportDetailsRepository instalReportDetailsRepository;

	@MockBean
	private SupplyCharacteristicsRepository supplyCharacteristicsRepository;

	@MockBean
	private InspectionRepository inspectionRepository;

	@MockBean
	private TestingReportRepository testingReportRepository;

	@MockBean
	private SummaryRepository summaryRepository;

	private Site site;

	private FinalReport finalReport;

	{
		finalReport = new FinalReport();
		finalReport.setUserName("LVsystem@gmail.com");
		finalReport.setSiteId(1);

		finalReport.setReportDetails(retrieveReportDetails());
		finalReport.setSupplyCharacteristics(retrieveSupplyCharacteristics());
		finalReport.setPeriodicInspection(retrievePeriodicInspection());
		finalReport.setTestingReport(retrieveTestingReport());
		finalReport.setSummary(retrieveSummary());

	}

	{
		site = new Site();
		site.setUserName("LVsystem@gmail.com");
		site.setSiteId(1);
	}

	@Test
	public void testRetriveListOfSite() throws FinalReportException {
		logger.info("testRetriveListOfSite method started");
		ArrayList<Site> sites = new ArrayList<Site>();
		sites.add(site);
		when(siteRepository.findByUserName("LVsystem@gmail.com")).thenReturn(sites);
		List<Site> retrieveListOfSite = finalReportServiceImpl.retrieveListOfSite("LVsystem@gmail.com");
		assertTrue(retrieveListOfSite.contains(site));

		FinalReportException finalReportException = Assertions.assertThrows(FinalReportException.class,
				() -> finalReportServiceImpl.retrieveListOfSite(null));
		assertEquals(finalReportException.getMessage(), "Invaild Input");
		logger.info("testRetriveListOfSite method ended");

	}

	@Test
	public void testRetriveFinalReport() throws FinalReportException {

		logger.info("testRetriveListOfSite method started");

		when(instalReportDetailsRepository.findBySiteId(1)).thenReturn(Optional.of(retrieveReportDetails()));
		when(supplyCharacteristicsRepository.findBySiteId(1)).thenReturn(Optional.of(retrieveSupplyCharacteristics()));
		when(inspectionRepository.findBySiteId(1)).thenReturn(Optional.of(retrievePeriodicInspection()));
		when(testingReportRepository.findBySiteId(1)).thenReturn(Optional.of(retrieveTestingReport()));
		when(summaryRepository.findBySiteId(1)).thenReturn(Optional.of(retrieveSummary()));

		Optional<FinalReport> retrieveFinalReport = finalReportServiceImpl.retrieveFinalReport("LVsystem@gmail.com", 1);
		assertNotNull(retrieveFinalReport);

		FinalReportException finalReportException = Assertions.assertThrows(FinalReportException.class,
				() -> finalReportServiceImpl.retrieveFinalReport(null, 1));
		assertEquals(finalReportException.getMessage(), "Invalid Input");
		logger.info("testRetriveListOfSite method ended");

	}
	
	@Test
	public void testRetriveFinalReport_NotHaveingAllStepData() throws FinalReportException {

		logger.info("testRetriveFinalReport_NotHaveingAllStepData method started");

		when(instalReportDetailsRepository.findBySiteId(1)).thenReturn(Optional.of(retrieveReportDetails()));
		when(supplyCharacteristicsRepository.findBySiteId(1)).thenReturn(Optional.of(retrieveSupplyCharacteristics()));

		Optional<FinalReport> retrieveFinalReport = finalReportServiceImpl.retrieveFinalReport("LVsystem@gmail.com", 1);
		assertNotNull(retrieveFinalReport);

	}

	private ReportDetails retrieveReportDetails() {
		ReportDetails reportDetails = new ReportDetails();
		reportDetails.setUserName("LVsystem@gmail.com");
		reportDetails.setSiteId(1);
		return reportDetails;
	}

	private SupplyCharacteristics retrieveSupplyCharacteristics() {
		SupplyCharacteristics supplyCharacteristics = new SupplyCharacteristics();
		supplyCharacteristics.setUserName("LVsystem@gmail.com");
		supplyCharacteristics.setSiteId(1);
		return supplyCharacteristics;
	}

	private PeriodicInspection retrievePeriodicInspection() {
		PeriodicInspection periodicInspection = new PeriodicInspection();
		periodicInspection.setUserName("LVsystem@gmail.com");
		periodicInspection.setSiteId(1);
		return periodicInspection;
	}

	private TestingReport retrieveTestingReport() {
		TestingReport testingReport = new TestingReport();
		testingReport.setUserName("LVsystem@gmail.com");
		testingReport.setSiteId(1);
		return testingReport;
	}

	private Summary retrieveSummary() {
		Summary summary = new Summary();
		summary.setUserName("LVsystem@gmail.com");
		summary.setSiteId(1);
		return summary;
	}
}