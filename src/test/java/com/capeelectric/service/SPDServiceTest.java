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
import com.capeelectric.exception.SPDException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.EarthStudDescription;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.model.SPD;
import com.capeelectric.repository.BasicLpsRepository;
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
	private BasicLpsRepository basicLpsRepository;

	@MockBean
	private SPDException spdException;

	@MockBean
	private UserFullName userFullName;

	private SPD spd;

	{
		spd = new SPD();
		spd.setBasicLpsId(1);
		spd.setUserName("LVsystem@gmail.com");
		spd.setSpdId(2);
	}

	private BasicLps basicLps;

	{
		basicLps = new BasicLps();
		basicLps.setBasicLpsId(1);
		basicLps.setClientName("Inspector@gmail.com");

	}

	@Test
	public void testAddSPDDetails() throws SPDException {

		when(basicLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(basicLps));
		when(spdRepository.findByBasicLpsId(2)).thenReturn(Optional.of(spd));
		spdServiceImpl.addSPDDetails(spd);

		when(basicLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(basicLps));
		when(spdRepository.findByBasicLpsId(1)).thenReturn(Optional.of(spd));
		SPDException earthStudException_2 = Assertions.assertThrows(SPDException.class,
				() -> spdServiceImpl.addSPDDetails(spd));
		assertEquals(earthStudException_2.getMessage(), "Basic LPS Id Already Available.Create New Basic Id");

		basicLps.setBasicLpsId(5);
		spd.setBasicLpsId(5);
		when(basicLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(basicLps));
		SPDException earthStudException_3 = Assertions.assertThrows(SPDException.class,
				() -> spdServiceImpl.addSPDDetails(spd));
		assertEquals(earthStudException_3.getMessage(), "Given Basic LPS Id is Not Registered in Basic LPS");

		spd.setUserName(null);
		SPDException earthStudException_4 = Assertions.assertThrows(SPDException.class,
				() -> spdServiceImpl.addSPDDetails(spd));
		assertEquals(earthStudException_4.getMessage(), "Invalid Inputs");

	}

	@Test
	public void testRetrieveSPDDetails() throws SPDException {

		List<SPD> arrayList = new ArrayList<SPD>();
		arrayList.add(spd);
		when(spdRepository.findByUserNameAndBasicLpsId("LVsystem@gmail.com", 12)).thenReturn(arrayList);
		spdServiceImpl.retrieveSPDDetails("LVsystem@gmail.com", 12);

		SPDException earthingLpsException = Assertions.assertThrows(SPDException.class,
				() -> spdServiceImpl.retrieveSPDDetails("abc@gmail.com", 12));
		assertEquals(earthingLpsException.getMessage(), "Given UserName & Id doesn't exist in Down Conductor Details");

		SPDException earthingLpsException_1 = Assertions.assertThrows(SPDException.class,
				() -> spdServiceImpl.retrieveSPDDetails(null, 12));
		assertEquals(earthingLpsException_1.getMessage(), "Invalid Inputs");

	}

	@Test
	public void testUpdateSpdDetails() throws SPDException {
		
		spd.setUserName("LVsystem@gmail.com");
		spd.setSpdId(1);
		spd.setBasicLpsId(1);
		when(spdRepository.findById(1)).thenReturn(Optional.of(spd));
		spdServiceImpl.updateSpdDetails(spd);
		
		spd.setBasicLpsId(1);
		spd.setSpdId(20);
		when(spdRepository.findById(30)).thenReturn(Optional.of(spd));
		SPDException assertThrows_1 = Assertions.assertThrows(SPDException.class,
				() -> spdServiceImpl.updateSpdDetails(spd));
		assertEquals(assertThrows_1.getMessage(), "Given Basic LPS Id and SPD Id is Invalid");
		
		spd.setBasicLpsId(null);
		when(spdRepository.findById(1)).thenReturn(Optional.of(spd));
		SPDException assertThrows_2 = Assertions.assertThrows(SPDException.class,
				() -> spdServiceImpl.updateSpdDetails(spd));
		assertEquals(assertThrows_2.getMessage(), "Invalid inputs");
	}

}
