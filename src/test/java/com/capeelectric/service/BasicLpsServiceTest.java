package com.capeelectric.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import com.capeelectric.exception.BasicLpsException;
import com.capeelectric.exception.PeriodicTestingException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.BasicLpsDescription;
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.model.TestingReport;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.TestingReportRepository;
import com.capeelectric.service.impl.BasicLpsServiceImpl;
import com.capeelectric.service.impl.PeriodicTestingServiceImpl;
import com.capeelectric.util.UserFullName;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class BasicLpsServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(BasicLpsServiceTest.class);

	@MockBean
	private BasicLpsRepository basicLpsRepository;

	@InjectMocks
	private BasicLpsServiceImpl basicLpsServiceImpl;

	@MockBean
	private BasicLpsException basicLpsException;

	@MockBean
	private UserFullName userFullName;

	private BasicLps basicLps;

	{
		basicLps = new BasicLps();
		basicLps.setBasicLpsId(1);
		basicLps.setUserName("LVsystem@gmail.com");
		basicLps.setClientName("Inspector@gmail.com");
		basicLps.setBasicLpsId(1);
	}

	@Test
	public void testAddBasicLpsDetails() throws BasicLpsException {

		when(basicLpsRepository.findByClientName("Inspector@gmail.com")).thenReturn(Optional.of(basicLps));

		logger.info("BasicLpsId already Present_flow");
		basicLps.setClientName(null);
		BasicLpsException basicLpsException_2 = Assertions.assertThrows(BasicLpsException.class,
				() -> basicLpsServiceImpl.addBasicLpsDetails(basicLps));
		assertEquals(basicLpsException_2.getMessage(), "Invalid Inputs");

	}

	@Test
	public void testRetrieveBasicLpsDetails() throws BasicLpsException {

		List<BasicLps> arrayList = new ArrayList<BasicLps>();
		arrayList.add(basicLps);
		when(basicLpsRepository.findByUserNameAndBasicLpsId("LVsystem@gmail.com", 12)).thenReturn(arrayList);

		logger.info("SuccessFlow of Retrieve LpsBasic Obeject");
		basicLpsServiceImpl.retrieveBasicLpsDetails("LVsystem@gmail.com", 12);

		logger.info("Invalid Input flow");
		BasicLpsException basicLpsException = Assertions.assertThrows(BasicLpsException.class,
				() -> basicLpsServiceImpl.retrieveBasicLpsDetails(null, 12));
		assertEquals(basicLpsException.getMessage(), "Invalid Inputs");

	}

	@Test
	public void testUpdateBasicLpsDetails() throws BasicLpsException {

		basicLps.setUserName("LVsystem@gmail.com");
		basicLps.setBasicLpsId(2);
//		basicLps.setBasicLpsDescription(BasicLpsDescription);

		when(basicLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(basicLps));
//		basicLpsServiceImpl.updateBasicLpsDetails(basicLps);

		LpsAirDiscription lpsAirDiscription_1 = new LpsAirDiscription();
		lpsAirDiscription_1.setBasicLpsId(1);

		basicLps.setBasicLpsId(null);
		when(basicLpsRepository.findById(1)).thenReturn(Optional.of(basicLps));
		BasicLpsException assertThrows_1 = Assertions.assertThrows(BasicLpsException.class,
				() -> basicLpsServiceImpl.updateBasicLpsDetails(basicLps));
		assertEquals(assertThrows_1.getMessage(), "Invalid inputs");
	}

}
