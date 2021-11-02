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

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.exception.BasicLpsException;
import com.capeelectric.exception.DownConductorException;
import com.capeelectric.exception.PeriodicTestingException;
import com.capeelectric.model.DownConductorDescription;
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.repository.AirTerminationLpsRepository;
import com.capeelectric.service.impl.AirTerminationLpsServiceImpl;
import com.capeelectric.util.UserFullName;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class AirTerminationLpsServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(AirTerminationLpsServiceTest.class);

	@MockBean
	private UserFullName userFullName;

	@MockBean
	private AirTerminationLpsRepository airTerminationLpsRepository;

	@MockBean
	private AirTerminationException airTerminationException;

	@InjectMocks
	private AirTerminationLpsServiceImpl airTerminationLpsServiceImpl;

	private LpsAirDiscription lpsAirDiscription;

	{
		lpsAirDiscription = new LpsAirDiscription();
		lpsAirDiscription.setBasicLpsId(1);
		lpsAirDiscription.setUserName("LVsystem@gmail.com");
		lpsAirDiscription.setUserName("Inspector@gmail.com");
		lpsAirDiscription.setBasicLpsId(1);
	}

	@Test
	public void testAddAirTerminationLpsDetails() throws AirTerminationException {

		when(airTerminationLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(lpsAirDiscription));

		logger.info("BasicLpsId already Present_flow");
		lpsAirDiscription.setUserName(null);
		AirTerminationException basicLpsException_2 = Assertions.assertThrows(AirTerminationException.class,
				() -> airTerminationLpsServiceImpl.addAirTerminationLpsDetails(lpsAirDiscription));
		assertEquals(basicLpsException_2.getMessage(), "Invalid Inputs");

	}

	@Test
	public void testRetrieveAirTerminationLps() throws AirTerminationException {

		List<LpsAirDiscription> arrayList = new ArrayList<LpsAirDiscription>();
		arrayList.add(lpsAirDiscription);
		when(airTerminationLpsRepository.findByUserNameAndBasicLpsId("LVsystem@gmail.com", 12)).thenReturn(arrayList);

		logger.info("SuccessFlow of Retrieve  AirTerminationLps Obeject");
		airTerminationLpsServiceImpl.retrieveAirTerminationLps("LVsystem@gmail.com", 12);

		logger.info("Invalid Input flow");
		AirTerminationException basicLpsException = Assertions.assertThrows(AirTerminationException.class,
				() -> airTerminationLpsServiceImpl.retrieveAirTerminationLps(null, 12));
		assertEquals(basicLpsException.getMessage(), "Invalid Inputs");

	}

	@Test
	public void testUpdateAirTerminationLps() throws AirTerminationException {

		lpsAirDiscription.setUserName("LVsystem@gmail.com");
		lpsAirDiscription.setLpsAirDescId(1);
		lpsAirDiscription.setBasicLpsId(1);

		when(airTerminationLpsRepository.findById(1)).thenReturn(Optional.of(lpsAirDiscription));
		airTerminationLpsServiceImpl.updateAirTerminationLps(lpsAirDiscription);

		LpsAirDiscription lpsAirDiscription_1 = new LpsAirDiscription();
		lpsAirDiscription_1.setBasicLpsId(1);

		when(airTerminationLpsRepository.findById(1)).thenReturn(Optional.of(lpsAirDiscription));
		lpsAirDiscription.setBasicLpsId(null);
		when(airTerminationLpsRepository.findById(1)).thenReturn(Optional.of(lpsAirDiscription));
		AirTerminationException assertThrows_1 = Assertions.assertThrows(AirTerminationException.class,
				() -> airTerminationLpsServiceImpl.updateAirTerminationLps(lpsAirDiscription));
		assertEquals(assertThrows_1.getMessage(), "Invalid inputs");
	}

}
