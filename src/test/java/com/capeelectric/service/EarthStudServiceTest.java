package com.capeelectric.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.capeelectric.exception.EarthStudException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.EarthStudDescription;
import com.capeelectric.model.EarthStudReport;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.EarthStudRepository;
import com.capeelectric.service.impl.EarthStudServiceImpl;
import com.capeelectric.util.FindNonRemovedObjects;
import com.capeelectric.util.UserFullName;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class EarthStudServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(EarthStudServiceTest.class);

		
	@MockBean
	private EarthStudRepository earthStudRepository;

	@InjectMocks
	private EarthStudServiceImpl eartStudServiceImpl;

	@MockBean
	private EarthStudException earthStudException;

	@MockBean
	private BasicLpsRepository basicLpsRepository;
	
	@MockBean
	private FindNonRemovedObjects findNonRemovedObjects;
	
	@MockBean
	private UserFullName userFullName;

	private EarthStudReport earthStudReport;

	{
		earthStudReport = new EarthStudReport();
		earthStudReport.setBasicLpsId(1);
		earthStudReport.setUserName("Inspector@gmail.com");
		earthStudReport.setEarthStudReportId(2);
		
		List<EarthStudDescription> earthStudDescriptionList = new ArrayList<EarthStudDescription>();
		EarthStudDescription earthStudDescription = new EarthStudDescription();
		earthStudDescription.setFlag("A");
		
		earthStudDescriptionList.add(earthStudDescription);
	}

	private BasicLps basicLps;
	{
		basicLps = new BasicLps();
		basicLps.setUserName("lps@capeindia.net");
		basicLps.setBasicLpsId(1);
	}
	
	
	
	@Test
	public void testAddEarthStudDetails() throws EarthStudException {
		when(basicLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(basicLps));
		when(earthStudRepository.findByBasicLpsId(2)).thenReturn(Optional.of(earthStudReport));
		
		earthStudReport.setEarthStudDescription(null);
		EarthStudException earthStudException_1 = Assertions.assertThrows(EarthStudException.class,
				() -> eartStudServiceImpl.addEarthStudDetails(earthStudReport));
		assertEquals(earthStudException_1.getMessage(), "Please fill all the fields before clicking next button");
		List<EarthStudDescription> earthStudDescriptionList = new ArrayList<EarthStudDescription>();
		earthStudDescriptionList.add(new EarthStudDescription());
		
		EarthStudDescription earthStudDescription = earthStudDescriptionList.get(0);
		earthStudDescription.setFlag("A");
		earthStudReport.setEarthStudDescription(earthStudDescriptionList);
		eartStudServiceImpl.addEarthStudDetails(earthStudReport);
		
		when(earthStudRepository.findByBasicLpsId(1)).thenReturn(Optional.of(earthStudReport));
		EarthStudException earthStudException_2 = Assertions.assertThrows(EarthStudException.class,
				() -> eartStudServiceImpl.addEarthStudDetails(earthStudReport));
		assertEquals(earthStudException_2.getMessage(), "Basic LPS Id Already Available.Create New Basic Id");

		basicLps.setBasicLpsId(5);
		earthStudReport.setBasicLpsId(5);
		when(basicLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(basicLps));
		EarthStudException earthStudException_3 = Assertions.assertThrows(EarthStudException.class,
				() -> eartStudServiceImpl.addEarthStudDetails(earthStudReport));
		assertEquals(earthStudException_3.getMessage(), "Given Basic LPS Id is Not Registered in Basic LPS");	
		
		earthStudReport.setUserName(null);
		EarthStudException earthStudException_4 = Assertions.assertThrows(EarthStudException.class,
				() -> eartStudServiceImpl.addEarthStudDetails(earthStudReport));
		assertEquals(earthStudException_4.getMessage(), "Invalid Inputs");
	}
	
	@Test
	public void testRetrieveEarthingLpsDetails() throws EarthStudException {

		List<EarthStudReport> arrayList = new ArrayList<EarthStudReport>();
		arrayList.add(earthStudReport);
		when(earthStudRepository.findByUserNameAndBasicLpsId("LVsystem@gmail.com", 12)).thenReturn(arrayList);
		eartStudServiceImpl.retrieveEarthStudDetails("LVsystem@gmail.com", 12);

		EarthStudException earthStudException_1 = Assertions.assertThrows(EarthStudException.class,
				() -> eartStudServiceImpl.retrieveEarthStudDetails("abc@gmail.com", 12));
		assertEquals(earthStudException_1.getMessage(), "Given UserName & Id doesn't exist in Earth Stud Report Details");
		
		EarthStudException earthStudException_2 = Assertions.assertThrows(EarthStudException.class,
				() -> eartStudServiceImpl.retrieveEarthStudDetails(null, 12));
		assertEquals(earthStudException_2.getMessage(), "Invalid Inputs");

	}

	@Test
	public void testUpdateEarthStudDetails() throws EarthStudException {

		earthStudReport.setUserName("LVsystem@gmail.com");
		earthStudReport.setEarthStudReportId(1);
		earthStudReport.setBasicLpsId(1);
		when(earthStudRepository.findById(1)).thenReturn(Optional.of(earthStudReport));
		eartStudServiceImpl.updateEarthStudDetails(earthStudReport);

		earthStudReport.setBasicLpsId(2);
		earthStudReport.setEarthStudReportId(50);
		when(earthStudRepository.findById(20)).thenReturn(Optional.of(earthStudReport));
		EarthStudException assertThrows_1 = Assertions.assertThrows(EarthStudException.class,
				() -> eartStudServiceImpl.updateEarthStudDetails(earthStudReport));
		assertEquals(assertThrows_1.getMessage(), "Given Basic LPS Id and Earth Stud Id is Invalid");

		earthStudReport.setBasicLpsId(null);
		when(earthStudRepository.findById(1)).thenReturn(Optional.of(earthStudReport));
		EarthStudException assertThrows_2 = Assertions.assertThrows(EarthStudException.class,
				() -> eartStudServiceImpl.updateEarthStudDetails(earthStudReport));
		assertEquals(assertThrows_2.getMessage(), "Invalid inputs");
	}

}
