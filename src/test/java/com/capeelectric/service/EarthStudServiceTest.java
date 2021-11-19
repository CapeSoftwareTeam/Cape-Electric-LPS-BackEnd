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
import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.EarthStudDescription;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.EarthStudRepository;
import com.capeelectric.repository.EarthingLpsRepository;
import com.capeelectric.service.impl.EarthStudServiceImpl;
import com.capeelectric.service.impl.EarthingLpsServiceImpl;
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
	private UserFullName userFullName;

	private EarthStudDescription earthStudDescription;

	{
		earthStudDescription = new EarthStudDescription();
		earthStudDescription.setBasicLpsId(1);
		earthStudDescription.setUserName("Inspector@gmail.com");
		earthStudDescription.setEarthStudDescId(2);
	}

	private BasicLps basicLps;
	{
		basicLps = new BasicLps();
		basicLps.setBasicLpsId(1);
		basicLps.setClientName("Inspector@gmail.com");
	}
	
	public void testAddEarthStudDetails() throws EarthStudException {

		when(basicLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(basicLps));
		when(earthStudRepository.findByBasicLpsId(3)).thenReturn(Optional.of(earthStudDescription));
		eartStudServiceImpl.addEarthStudDetails(earthStudDescription);
		
		when(earthStudRepository.findByBasicLpsId(1)).thenReturn(Optional.of(earthStudDescription));
		EarthStudException earthStudException_2 = Assertions.assertThrows(EarthStudException.class,
				() -> eartStudServiceImpl.addEarthStudDetails(earthStudDescription));
		assertEquals(earthStudException_2.getMessage(), "Basic LPS Id Already Available.Create New Basic Id");

		basicLps.setBasicLpsId(5);
		earthStudDescription.setBasicLpsId(5);
		when(basicLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(basicLps));
		EarthStudException earthStudException_3 = Assertions.assertThrows(EarthStudException.class,
				() -> eartStudServiceImpl.addEarthStudDetails(earthStudDescription));
		assertEquals(earthStudException_3.getMessage(), "Given Basic LPS Id is Not Registered in Basic LPS");	
		
		earthStudDescription.setUserName(null);
		EarthStudException earthStudException_4 = Assertions.assertThrows(EarthStudException.class,
				() -> eartStudServiceImpl.addEarthStudDetails(earthStudDescription));
		assertEquals(earthStudException_4.getMessage(), "Invalid Inputs");
	}

	@Test
	public void testRetrieveEarthingLpsDetails() throws EarthStudException {

		List<EarthStudDescription> arrayList = new ArrayList<EarthStudDescription>();
		arrayList.add(earthStudDescription);
		when(earthStudRepository.findByUserNameAndBasicLpsId("LVsystem@gmail.com", 12)).thenReturn(arrayList);
		eartStudServiceImpl.retrieveEarthStudDetails("LVsystem@gmail.com", 12);

		EarthStudException earthStudException_1 = Assertions.assertThrows(EarthStudException.class,
				() -> eartStudServiceImpl.retrieveEarthStudDetails("abc@gmail.com", 12));
		assertEquals(earthStudException_1.getMessage(), "Given UserName & Id doesn't exist in Down Conductor Details");
		
		EarthStudException earthStudException_2 = Assertions.assertThrows(EarthStudException.class,
				() -> eartStudServiceImpl.retrieveEarthStudDetails(null, 12));
		assertEquals(earthStudException_2.getMessage(), "Invalid Inputs");

	}

	@Test
	public void testUpdateEarthStudDetails() throws EarthStudException {

		earthStudDescription.setUserName("LVsystem@gmail.com");
		earthStudDescription.setEarthStudDescId(1);
		earthStudDescription.setBasicLpsId(1);
		when(earthStudRepository.findById(1)).thenReturn(Optional.of(earthStudDescription));
		eartStudServiceImpl.updateEarthStudDetails(earthStudDescription);

		earthStudDescription.setBasicLpsId(2);
		earthStudDescription.setEarthStudDescId(50);
		when(earthStudRepository.findById(20)).thenReturn(Optional.of(earthStudDescription));
		EarthStudException assertThrows_1 = Assertions.assertThrows(EarthStudException.class,
				() -> eartStudServiceImpl.updateEarthStudDetails(earthStudDescription));
		assertEquals(assertThrows_1.getMessage(), "Given Basic LPS Id and Earth Stud Id is Invalid");

		earthStudDescription.setBasicLpsId(null);
		when(earthStudRepository.findById(1)).thenReturn(Optional.of(earthStudDescription));
		EarthStudException assertThrows_2 = Assertions.assertThrows(EarthStudException.class,
				() -> eartStudServiceImpl.updateEarthStudDetails(earthStudDescription));
		assertEquals(assertThrows_2.getMessage(), "Invalid inputs");
	}

}
