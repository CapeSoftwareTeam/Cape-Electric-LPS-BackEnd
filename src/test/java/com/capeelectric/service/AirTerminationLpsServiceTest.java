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
import com.capeelectric.exception.EarthingLpsException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.repository.AirTerminationLpsRepository;
import com.capeelectric.repository.BasicLpsRepository;
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
	private BasicLpsRepository basicLpsRepository;
	
	@MockBean
	private AirTerminationException airTerminationException;

	@InjectMocks
	private AirTerminationLpsServiceImpl airTerminationLpsServiceImpl;

	private LpsAirDiscription lpsAirDiscription;

	{
		lpsAirDiscription = new LpsAirDiscription();
		lpsAirDiscription.setBasicLpsId(1);
		lpsAirDiscription.setUserName("Inspector@gmail.com");
		lpsAirDiscription.setLpsAirDescId(2);
		
	}
	private BasicLps basicLps;

	{
		basicLps = new BasicLps();
		basicLps.setBasicLpsId(1);
		basicLps.setClientName("Inspector@gmail.com");
		
	}

	@Test
	public void testAddAirTerminationLpsDetails() throws AirTerminationException {

		when(basicLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(basicLps));
		when(airTerminationLpsRepository.findByBasicLpsId(2)).thenReturn(Optional.of(lpsAirDiscription));
		airTerminationLpsServiceImpl.addAirTerminationLpsDetails(lpsAirDiscription);
		
		when(airTerminationLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(lpsAirDiscription));
		AirTerminationException basicLpsException_3 = Assertions.assertThrows(AirTerminationException.class,
				() -> airTerminationLpsServiceImpl.addAirTerminationLpsDetails(lpsAirDiscription));
		assertEquals(basicLpsException_3.getMessage(), "Given Basic LPS Id is already Available in Air Termination");

		
		basicLps.setBasicLpsId(10);
		AirTerminationException basicLpsException_4 = Assertions.assertThrows(AirTerminationException.class,
				() -> airTerminationLpsServiceImpl.addAirTerminationLpsDetails(lpsAirDiscription));
		assertEquals(basicLpsException_4.getMessage(), "Given Basic LPS Id is Not Registered in Basic LPS");

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
		
		logger.info("Given UserName & Id doesn't exist in Air Termination LPS Details");
		List<LpsAirDiscription> arrayList_1 = new ArrayList<LpsAirDiscription>();
		when(airTerminationLpsRepository.findByUserNameAndBasicLpsId("test@gmail.com", 12)).thenReturn(arrayList_1);
		AirTerminationException basicLpsException_2 = Assertions.assertThrows(AirTerminationException.class,
				() -> airTerminationLpsServiceImpl.retrieveAirTerminationLps("test@gmail.com", 12));
		assertEquals(basicLpsException_2.getMessage(), "Given UserName & Id doesn't exist in Air Termination LPS Details");

	}

	@Test
	public void testUpdateAirTerminationLps() throws AirTerminationException {

		lpsAirDiscription.setUserName("LVsystem@gmail.com");
		lpsAirDiscription.setLpsAirDescId(1);
		lpsAirDiscription.setBasicLpsId(1);
		when(airTerminationLpsRepository.findById(1)).thenReturn(Optional.of(lpsAirDiscription));
		airTerminationLpsServiceImpl.updateAirTerminationLps(lpsAirDiscription);

		lpsAirDiscription.setBasicLpsId(2);
		lpsAirDiscription.setLpsAirDescId(50);
		when(airTerminationLpsRepository.findById(20)).thenReturn(Optional.of(lpsAirDiscription));
		AirTerminationException basicLpsException_2 = Assertions.assertThrows(AirTerminationException.class,
				() -> airTerminationLpsServiceImpl.updateAirTerminationLps(lpsAirDiscription));
		assertEquals(basicLpsException_2.getMessage(), "Given Basic LPS Id and LPS Air Description Id is Invalid");
		
		lpsAirDiscription.setBasicLpsId(null);
		AirTerminationException assertThrows_1 = Assertions.assertThrows(AirTerminationException.class,
				() -> airTerminationLpsServiceImpl.updateAirTerminationLps(lpsAirDiscription));
		assertEquals(assertThrows_1.getMessage(), "Invalid inputs");
		
	}

}
