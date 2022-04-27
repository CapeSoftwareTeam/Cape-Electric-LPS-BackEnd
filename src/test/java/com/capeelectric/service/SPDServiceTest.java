package com.capeelectric.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.exception.SPDException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.SPD;
import com.capeelectric.model.SpdDescription;
import com.capeelectric.model.SpdReport;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.SPDRepository;
import com.capeelectric.service.impl.SPDServiceImpl;
import com.capeelectric.util.AddRemovedStatus;
import com.capeelectric.util.FindNonRemovedObjects;
import com.capeelectric.util.UserFullName;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class SPDServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(SPDServiceTest.class);

	@MockBean
	private SPDRepository spdRepository;

	@InjectMocks
	private SPDServiceImpl spdServiceImpl;

	@MockBean
	private BasicLpsRepository basicLpsRepository;
	
	@MockBean
	private FindNonRemovedObjects findNonRemovedObjects;

	@MockBean
	private SPDException spdException;

	@MockBean
	private UserFullName userFullName;

	private SpdReport spdReport;
	
	@MockBean
	private AddRemovedStatus addRemovedStatus;

	{
		spdReport = new SpdReport();
		spdReport.setBasicLpsId(1);
		spdReport.setUserName("LVsystem@gmail.com");
		spdReport.setSpdReportId(2);
		
		List<SPD> spdList = new ArrayList<SPD>();
		SPD spd = new SPD();
		spd.setFlag("A");
		
		List<SpdDescription> spdDescriptionList = new ArrayList<SpdDescription>();
		SpdDescription spdDescription = new SpdDescription();
		spdDescription.setFlag("A");
		spdDescriptionList.add(spdDescription);
		
		spd.setSpdDescription(spdDescriptionList);
		spdList.add(spd);
		
	}

	private BasicLps basicLps;

	{
		basicLps = new BasicLps();
		basicLps.setBasicLpsId(1);
		basicLps.setClientName("Inspector@gmail.com");

	}

	@Test
	public void testAddSPDDetails() throws SPDException, AirTerminationException {

		when(basicLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(basicLps));
		when(spdRepository.findByBasicLpsId(2)).thenReturn(Optional.of(spdReport));
		spdReport.setSpd(null);
		SPDException earthStudException_1 = Assertions.assertThrows(SPDException.class,
				() -> spdServiceImpl.addSPDDetails(spdReport));
		assertEquals(earthStudException_1.getMessage(), "Please fill all the fields before clicking next button");
		
		List<SPD> spdList = new ArrayList<SPD>();
		spdList.add(new SPD());
		
		List<SpdDescription> spdDescriptionList = new ArrayList<SpdDescription>();
		SpdDescription spdDescription = new SpdDescription();
		spdDescription.setFlag("A");
		spdDescriptionList.add(spdDescription);
		
		SPD spd =spdList.get(0);
		spd.setSpdDescription(spdDescriptionList);
		spd.setFlag("A");
		spdReport.setSpd(spdList);
		spdServiceImpl.addSPDDetails(spdReport);

		when(basicLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(basicLps));
		when(spdRepository.findByBasicLpsId(1)).thenReturn(Optional.of(spdReport));
		SPDException earthStudException_2 = Assertions.assertThrows(SPDException.class,
				() -> spdServiceImpl.addSPDDetails(spdReport));
		assertEquals(earthStudException_2.getMessage(), "Basic LPS Id Already Available.Create New Basic Id");

		basicLps.setBasicLpsId(5);
		spdReport.setBasicLpsId(5);
		when(basicLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(basicLps));
		SPDException earthStudException_3 = Assertions.assertThrows(SPDException.class,
				() -> spdServiceImpl.addSPDDetails(spdReport));
		assertEquals(earthStudException_3.getMessage(), "Given Basic LPS Id is Not Registered in Basic LPS");

		spdReport.setUserName(null);
		SPDException earthStudException_4 = Assertions.assertThrows(SPDException.class,
				() -> spdServiceImpl.addSPDDetails(spdReport));
		assertEquals(earthStudException_4.getMessage(), "Invalid Inputs");

	}

	@Test
	public void testRetrieveSPDDetails() throws SPDException {

		List<SpdReport> arrayList = new ArrayList<SpdReport>();
		arrayList.add(spdReport);
		when(spdRepository.findByUserNameAndBasicLpsId("LVsystem@gmail.com", 12)).thenReturn(arrayList);
		spdServiceImpl.retrieveSPDDetails("LVsystem@gmail.com", 12);
		
		assertNotNull(spdServiceImpl.retrieveSPDDetails("abc@gmail.com", 12));
		
		SPDException earthingLpsException_1 = Assertions.assertThrows(SPDException.class,
				() -> spdServiceImpl.retrieveSPDDetails(null, 12));
		assertEquals(earthingLpsException_1.getMessage(), "Invalid Inputs");

	}

	@Test
	public void testUpdateSpdDetails() throws SPDException, AirTerminationException {
		
		spdReport.setUserName("LVsystem@gmail.com");
		spdReport.setSpdReportId(1);
		spdReport.setBasicLpsId(1);
		when(spdRepository.findById(1)).thenReturn(Optional.of(spdReport));
		spdServiceImpl.updateSpdDetails(spdReport);
		
		spdReport.setBasicLpsId(1);
		spdReport.setSpdReportId(20);
		when(spdRepository.findById(30)).thenReturn(Optional.of(spdReport));
		SPDException assertThrows_1 = Assertions.assertThrows(SPDException.class,
				() -> spdServiceImpl.updateSpdDetails(spdReport));
		assertEquals(assertThrows_1.getMessage(), "Given Basic LPS Id and SPD Id is Invalid");
		
		spdReport.setBasicLpsId(null);
		when(spdRepository.findById(1)).thenReturn(Optional.of(spdReport));
		SPDException assertThrows_2 = Assertions.assertThrows(SPDException.class,
				() -> spdServiceImpl.updateSpdDetails(spdReport));
		assertEquals(assertThrows_2.getMessage(), "Invalid inputs");
	}

}
