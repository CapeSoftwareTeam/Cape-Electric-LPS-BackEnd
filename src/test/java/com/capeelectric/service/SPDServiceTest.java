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
import com.capeelectric.exception.SPDException;
import com.capeelectric.model.EarthStudDescription;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.model.SPD;
import com.capeelectric.repository.EarthStudRepository;
import com.capeelectric.repository.SPDRepository;
import com.capeelectric.service.impl.EarthStudServiceImpl;
import com.capeelectric.service.impl.SPDServiceImpl;
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
	private SPDException spdException;

	@MockBean
	private UserFullName userFullName;

	private SPD spd;

	{
		spd = new SPD();
		spd.setBasicLpsId(1);
		spd.setUserName("LVsystem@gmail.com");
		spd.setUserName("Inspector@gmail.com");
		spd.setBasicLpsId(1);
	}

	@Test
	public void testAddSPDDetails() throws SPDException {

		when(spdRepository.findByBasicLpsId(1)).thenReturn(Optional.of(spd));

		logger.info("BasicLpsId already Present_flow");
		logger.info("Invalid Present_flow");
		spd.setUserName(null);
		SPDException earthStudException_2 = Assertions.assertThrows(SPDException.class,
				() -> spdServiceImpl.addSPDDetails(spd));
		assertEquals(earthStudException_2.getMessage(), "Invalid Inputs");

	}

	@Test
	public void testRetrieveSPDDetails() throws SPDException {

		List<SPD> arrayList = new ArrayList<SPD>();
		arrayList.add(spd);
		when(spdRepository.findByUserNameAndBasicLpsId("LVsystem@gmail.com", 12)).thenReturn(arrayList);

		logger.info("SuccessFlow of Retrieve SPDDetails Obeject");
		spdServiceImpl.retrieveSPDDetails("LVsystem@gmail.com", 12);

		logger.info("Invalid Input flow");
		SPDException earthingLpsException = Assertions.assertThrows(SPDException.class,
				() -> spdServiceImpl.retrieveSPDDetails(null, 12));
		assertEquals(earthingLpsException.getMessage(), "Invalid Inputs");

	}

	@Test
	public void testUpdateSpdDetails() throws SPDException {

		spd.setUserName("LVsystem@gmail.com");
		spd.setSpdId(1);
		spd.setBasicLpsId(1);

		when(spdRepository.findByBasicLpsId(1)).thenReturn(Optional.of(spd));
		// spdServiceImpl.updateSpdDetails(spd);

		EarthingLpsDescription earthingLpsDescription_1 = new EarthingLpsDescription();
		earthingLpsDescription_1.setBasicLpsId(1);

		when(spdRepository.findByBasicLpsId(1)).thenReturn(Optional.of(spd));
		spd.setBasicLpsId(null);
		when(spdRepository.findByBasicLpsId(1)).thenReturn(Optional.of(spd));
		SPDException assertThrows_1 = Assertions.assertThrows(SPDException.class,
				() -> spdServiceImpl.updateSpdDetails(spd));
		assertEquals(assertThrows_1.getMessage(), "Invalid inputs");
	}

}
