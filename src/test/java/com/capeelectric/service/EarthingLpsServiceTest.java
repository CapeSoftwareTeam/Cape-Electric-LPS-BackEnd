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

import com.capeelectric.exception.DownConductorException;
import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.exception.SPDException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.EarthingLpsRepository;
import com.capeelectric.service.impl.EarthingLpsServiceImpl;
import com.capeelectric.util.UserFullName;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class EarthingLpsServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(EarthingLpsServiceTest.class);

	@MockBean
	private EarthingLpsRepository earthingLpsRepository;

	@InjectMocks
	private EarthingLpsServiceImpl earthingLpsServiceImpl;

	@MockBean
	private EarthingLpsException earthingLpsException;

	@MockBean
	private BasicLpsRepository basicLpsRepository;
	
	@MockBean
	private UserFullName userFullName;

	private EarthingLpsDescription earthingLpsDescription;

	{
		earthingLpsDescription = new EarthingLpsDescription();
		earthingLpsDescription.setUserName("LVsystem@gmail.com");
		earthingLpsDescription.setBasicLpsId(1);
		earthingLpsDescription.setEarthingId(2);
	}
	
	private BasicLps basicLps;
	{
		basicLps = new BasicLps();
		basicLps.setBasicLpsId(1);
		basicLps.setClientName("Inspector@gmail.com");
	}

	@Test
	public void testAddEarthingLpsDetails() throws EarthingLpsException {

		when(basicLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(basicLps));
		when(earthingLpsRepository.findByBasicLpsId(3)).thenReturn(Optional.of(earthingLpsDescription));
		earthingLpsServiceImpl.addEarthingLpsDetails(earthingLpsDescription);
		
		when(earthingLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(earthingLpsDescription));
		EarthingLpsException earthingLpsException_2 = Assertions.assertThrows(EarthingLpsException.class,
				() -> earthingLpsServiceImpl.addEarthingLpsDetails(earthingLpsDescription));
		assertEquals(earthingLpsException_2.getMessage(), "Basic LPS Id Already Available.Create New Basic Id");

		basicLps.setBasicLpsId(5);
		earthingLpsDescription.setBasicLpsId(5);
		when(basicLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(basicLps));
		EarthingLpsException earthingLpsException_3 = Assertions.assertThrows(EarthingLpsException.class,
				() -> earthingLpsServiceImpl.addEarthingLpsDetails(earthingLpsDescription));
		assertEquals(earthingLpsException_3.getMessage(), "Given Basic LPS Id is Not Registered in Basic LPS");	
		
		earthingLpsDescription.setUserName(null);
		EarthingLpsException earthingLpsException_4 = Assertions.assertThrows(EarthingLpsException.class,
				() -> earthingLpsServiceImpl.addEarthingLpsDetails(earthingLpsDescription));
		assertEquals(earthingLpsException_4.getMessage(), "Invalid Inputs");

	}

	@Test
	public void testRetrieveEarthingLpsDetails() throws EarthingLpsException {

		List<EarthingLpsDescription> arrayList = new ArrayList<EarthingLpsDescription>();
		arrayList.add(earthingLpsDescription);
		when(earthingLpsRepository.findByUserNameAndBasicLpsId("LVsystem@gmail.com", 12)).thenReturn(arrayList);
		earthingLpsServiceImpl.retrieveEarthingLpsDetails("LVsystem@gmail.com", 12);

		EarthingLpsException earthingLpsException = Assertions.assertThrows(EarthingLpsException.class,
				() -> earthingLpsServiceImpl.retrieveEarthingLpsDetails("abc@gmail.com", 12));
		assertEquals(earthingLpsException.getMessage(), "Given UserName & Id doesn't exist in Down Conductor Details");

		EarthingLpsException earthingLpsException_1 = Assertions.assertThrows(EarthingLpsException.class,
				() -> earthingLpsServiceImpl.retrieveEarthingLpsDetails(null, 12));
		assertEquals(earthingLpsException_1.getMessage(), "Invalid Inputs");

	}

	@Test
	public void testUpdateEarthingLpsDetails() throws EarthingLpsException {

		earthingLpsDescription.setUserName("LVsystem@gmail.com");
		earthingLpsDescription.setEarthingId(1);
		earthingLpsDescription.setBasicLpsId(1);
		when(earthingLpsRepository.findById(1)).thenReturn(Optional.of(earthingLpsDescription));
		earthingLpsServiceImpl.updateEarthingLpsDetails(earthingLpsDescription);

		earthingLpsDescription.setBasicLpsId(2);
		earthingLpsDescription.setEarthingId(50);
		when(earthingLpsRepository.findById(20)).thenReturn(Optional.of(earthingLpsDescription));
		EarthingLpsException assertThrows_1 = Assertions.assertThrows(EarthingLpsException.class,
				() -> earthingLpsServiceImpl.updateEarthingLpsDetails(earthingLpsDescription));
		assertEquals(assertThrows_1.getMessage(), "Given Basic LPS Id and Earthing LPS Id is Invalid");
		
		earthingLpsDescription.setBasicLpsId(null);
		when(earthingLpsRepository.findById(1)).thenReturn(Optional.of(earthingLpsDescription));
		EarthingLpsException assertThrows_2 = Assertions.assertThrows(EarthingLpsException.class,
				() -> earthingLpsServiceImpl.updateEarthingLpsDetails(earthingLpsDescription));
		assertEquals(assertThrows_2.getMessage(), "Invalid inputs");
	}

}
