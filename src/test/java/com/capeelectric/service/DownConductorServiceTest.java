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
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.DownConductor;
import com.capeelectric.model.DownConductorDescription;
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.DownConductorRepository;
import com.capeelectric.service.impl.BasicLpsServiceImpl;
import com.capeelectric.service.impl.DownConductorServiceImpl;
import com.capeelectric.util.UserFullName;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class DownConductorServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(DownConductorServiceTest.class);

	@MockBean
	private DownConductorRepository downConductorRepository;

	@InjectMocks
	private DownConductorServiceImpl downConductorServiceImpl;

	@MockBean
	private UserFullName userFullName;

	private DownConductorDescription downConductorDescription;

	{
		downConductorDescription = new DownConductorDescription();
		downConductorDescription.setBasicLpsId(1);
		downConductorDescription.setUserName("LVsystem@gmail.com");
		// downConductorDescription.setUserName("Inspector@gmail.com");
		downConductorDescription.setBasicLpsId(1);
	}

	@Test
	public void testAddDownConductorsDetails() throws DownConductorException {

		when(downConductorRepository.findByBasicLpsId(1)).thenReturn(Optional.of(downConductorDescription));

		logger.info("BasicLpsId already Present_flow");
		logger.info("Invalid Present_flow");
		downConductorDescription.setUserName(null);
		DownConductorException basicLpsException_2 = Assertions.assertThrows(DownConductorException.class,
				() -> downConductorServiceImpl.addDownConductorsDetails(downConductorDescription));
		assertEquals(basicLpsException_2.getMessage(), "Invalid Inputs");

	}

	@Test
	public void testRetrieveDownConductorDetails() throws DownConductorException {

		List<DownConductorDescription> arrayList = new ArrayList<DownConductorDescription>();
		arrayList.add(downConductorDescription);
		when(downConductorRepository.findByUserNameAndBasicLpsId("LVsystem@gmail.com", 12)).thenReturn(arrayList);

		logger.info("SuccessFlow of Retrieve DownConductorDetails");
		downConductorServiceImpl.retrieveDownConductorDetails("LVsystem@gmail.com", 12);

		logger.info("Invalid Input flow");
		DownConductorException basicLpsException = Assertions.assertThrows(DownConductorException.class,
				() -> downConductorServiceImpl.retrieveDownConductorDetails(null, 12));
		assertEquals(basicLpsException.getMessage(), "Invalid Inputs");

	}

	@Test
	public void testUpdateDownConductor() throws DownConductorException {
		downConductorDescription.setUserName("LVsystem@gmail.com");
		downConductorDescription.setDownConduDescId(1);
		downConductorDescription.setBasicLpsId(1);

		when(downConductorRepository.findById(1)).thenReturn(Optional.of(downConductorDescription));
		downConductorServiceImpl.updateDownConductorDetails(downConductorDescription);

		downConductorDescription.setBasicLpsId(null);
		when(downConductorRepository.findById(2)).thenReturn(Optional.of(downConductorDescription));
		DownConductorException assertThrows_1 = Assertions.assertThrows(DownConductorException.class,
				() -> downConductorServiceImpl.updateDownConductorDetails(downConductorDescription));
		assertEquals(assertThrows_1.getMessage(), "Invalid inputs");

	}

}
