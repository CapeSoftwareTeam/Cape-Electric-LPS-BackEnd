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
import com.capeelectric.model.EarthStudDescription;
import com.capeelectric.model.EarthingLpsDescription;
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
	private UserFullName userFullName;

	private EarthStudDescription earthStudDescription;

	{
		earthStudDescription = new EarthStudDescription();
		earthStudDescription.setBasicLpsId(1);
		earthStudDescription.setUserName("LVsystem@gmail.com");
		earthStudDescription.setUserName("Inspector@gmail.com");
		earthStudDescription.setBasicLpsId(1);
	}

	@Test
	public void testAddEarthStudDetails() throws EarthStudException {

		when(earthStudRepository.findByBasicLpsId(1)).thenReturn(Optional.of(earthStudDescription));

		logger.info("BasicLpsId already Present_flow");
		logger.info("Invalid Present_flow");
		earthStudDescription.setUserName(null);
		EarthStudException earthStudException_2 = Assertions.assertThrows(EarthStudException.class,
				() -> eartStudServiceImpl.addEarthStudDetails(earthStudDescription));
		assertEquals(earthStudException_2.getMessage(), "Invalid Inputs");

	}

	@Test
	public void testRetrieveEarthingLpsDetails() throws EarthStudException {

		List<EarthStudDescription> arrayList = new ArrayList<EarthStudDescription>();
		arrayList.add(earthStudDescription);
		when(earthStudRepository.findByUserNameAndBasicLpsId("LVsystem@gmail.com", 12)).thenReturn(arrayList);

		logger.info("SuccessFlow of Retrieve EarthingLps Details");
		eartStudServiceImpl.retrieveEarthStudDetails("LVsystem@gmail.com", 12);

		logger.info("Invalid Input flow");
		EarthStudException earthingLpsException = Assertions.assertThrows(EarthStudException.class,
				() -> eartStudServiceImpl.retrieveEarthStudDetails(null, 12));
		assertEquals(earthingLpsException.getMessage(), "Invalid Inputs");

	}

	@Test
	public void testUpdateEarthStudDetails() throws EarthStudException {

		earthStudDescription.setUserName("LVsystem@gmail.com");
		earthStudDescription.setEarthStudDescId(1);
		earthStudDescription.setBasicLpsId(1);

		when(earthStudRepository.findById(1)).thenReturn(Optional.of(earthStudDescription));
		eartStudServiceImpl.updateEarthStudDetails(earthStudDescription);

		EarthingLpsDescription earthingLpsDescription_1 = new EarthingLpsDescription();
		earthingLpsDescription_1.setBasicLpsId(1);

		when(earthStudRepository.findById(1)).thenReturn(Optional.of(earthStudDescription));
		earthStudDescription.setBasicLpsId(null);
		when(earthStudRepository.findById(1)).thenReturn(Optional.of(earthStudDescription));
		EarthStudException assertThrows_1 = Assertions.assertThrows(EarthStudException.class,
				() -> eartStudServiceImpl.updateEarthStudDetails(earthStudDescription));
		assertEquals(assertThrows_1.getMessage(), "Invalid inputs");
	}

}
