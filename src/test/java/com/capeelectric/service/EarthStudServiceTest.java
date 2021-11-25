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
import com.capeelectric.model.DownConductorDescription;
import com.capeelectric.model.EarthStudDescription;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.model.SPD;
import com.capeelectric.model.SeperationDistanceDescription;
import com.capeelectric.repository.AirTerminationLpsRepository;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.DownConductorRepository;
import com.capeelectric.repository.EarthStudRepository;
import com.capeelectric.repository.EarthingLpsRepository;
import com.capeelectric.repository.SPDRepository;
import com.capeelectric.repository.SeperationDistanceRepository;
import com.capeelectric.service.impl.EarthStudServiceImpl;
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
	private DownConductorRepository downConductorRepository;

	@MockBean
	private EarthingLpsRepository earthingLpsRepository;

	@MockBean
	private SPDRepository spdRepository;

	@MockBean
	private AirTerminationLpsRepository airTerminationLpsRepository;

	@MockBean
	private SeperationDistanceRepository seperationDistanceRepository;
	
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
	
	private LpsAirDiscription lpsAirDiscription;
	{
		lpsAirDiscription = new LpsAirDiscription();
		lpsAirDiscription.setBasicLpsId(1);
	}
	
	private DownConductorDescription downConductorDescription;
	{
		downConductorDescription = new DownConductorDescription();
		downConductorDescription.setBasicLpsId(1);
	}
	private EarthingLpsDescription earthingLpsDescription;
	{
		earthingLpsDescription = new EarthingLpsDescription();
		earthingLpsDescription.setBasicLpsId(1);
	}
	private SPD sPD;
	{
		sPD = new SPD();
		sPD.setBasicLpsId(1);
	}
	private SeperationDistanceDescription seperationDistanceDescription;
	{
		seperationDistanceDescription = new SeperationDistanceDescription();
		seperationDistanceDescription.setBasicLpsId(1);
	}
	
	
	@Test
	public void testAddEarthStudDetails() throws EarthStudException {
		 
		when(airTerminationLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(lpsAirDiscription));
		when(downConductorRepository
				.findByBasicLpsId(1)).thenReturn(Optional.of(downConductorDescription));
		when(earthingLpsRepository
				.findByBasicLpsId(1)).thenReturn(Optional.of(earthingLpsDescription));
		when( spdRepository.findByBasicLpsId(1)).thenReturn(Optional.of(sPD));
		when(seperationDistanceRepository
				.findByBasicLpsId(1)).thenReturn(Optional.of(seperationDistanceDescription));
		
		when(basicLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(basicLps));
		when(earthStudRepository.findByBasicLpsId(3)).thenReturn(Optional.of(earthStudDescription));
		eartStudServiceImpl.addEarthStudDetails(earthStudDescription);
		
		when(earthStudRepository.findByBasicLpsId(1)).thenReturn(Optional.of(earthStudDescription));
		EarthStudException earthStudException_2 = Assertions.assertThrows(EarthStudException.class,
				() -> eartStudServiceImpl.addEarthStudDetails(earthStudDescription));
		assertEquals(earthStudException_2.getMessage(), "Basic LPS Id Already Available.Create New Basic Id");
		
		when(seperationDistanceRepository
				.findByBasicLpsId(2)).thenReturn(Optional.of(seperationDistanceDescription));
 		EarthStudException earthStudException_3 = Assertions.assertThrows(EarthStudException.class,
				() -> eartStudServiceImpl.addEarthStudDetails(earthStudDescription));
		assertEquals(earthStudException_3.getMessage(), "Basic LPS Id Already Available.Create New Basic Id");

 	
		earthStudDescription.setUserName(null);
		EarthStudException earthStudException_4 = Assertions.assertThrows(EarthStudException.class,
				() -> eartStudServiceImpl.addEarthStudDetails(earthStudDescription));
		assertEquals(earthStudException_4.getMessage(), "Invalid Inputs");
	}
	@Test
	public void testAddEarthStud_SeperationException() throws EarthStudException {
		
		when(basicLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(basicLps));
		
		when(airTerminationLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(lpsAirDiscription));
		when(downConductorRepository
				.findByBasicLpsId(1)).thenReturn(Optional.of(downConductorDescription));
		when(earthingLpsRepository
				.findByBasicLpsId(1)).thenReturn(Optional.of(earthingLpsDescription));
		when(spdRepository.findByBasicLpsId(1)).thenReturn(Optional.of(sPD));
	 
		when(seperationDistanceRepository
				.findByBasicLpsId(2)).thenReturn(Optional.of(seperationDistanceDescription));
		
		EarthStudException earthStudException_1 = Assertions.assertThrows(EarthStudException.class,
				() -> eartStudServiceImpl.addEarthStudDetails(earthStudDescription));
		assertEquals(earthStudException_1.getMessage(), "Please enter Seperation Distance step to proceed further");
 	 
	}
	@Test
	public void testAddEarthStud_SpdException() throws EarthStudException {
		
		when(basicLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(basicLps));
		
		when(airTerminationLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(lpsAirDiscription));
		when(downConductorRepository
				.findByBasicLpsId(1)).thenReturn(Optional.of(downConductorDescription));
		when(earthingLpsRepository
				.findByBasicLpsId(1)).thenReturn(Optional.of(earthingLpsDescription));
		when(spdRepository.findByBasicLpsId(2)).thenReturn(Optional.of(sPD));
		
		 EarthStudException earthStudException_1 = Assertions.assertThrows(EarthStudException.class,
				() -> eartStudServiceImpl.addEarthStudDetails(earthStudDescription));
		assertEquals(earthStudException_1.getMessage(), "Please enter SPD step to proceed further");
		
	}
	
	@Test
	public void testAddEarthStud_EarthingException() throws EarthStudException {
		
		when(basicLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(basicLps));
		
		when(airTerminationLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(lpsAirDiscription));
		when(downConductorRepository
				.findByBasicLpsId(1)).thenReturn(Optional.of(downConductorDescription));
		when( spdRepository.findByBasicLpsId(1)).thenReturn(Optional.of(sPD));
		when(seperationDistanceRepository
				.findByBasicLpsId(1)).thenReturn(Optional.of(seperationDistanceDescription));
		
		
		when(earthingLpsRepository
				.findByBasicLpsId(2)).thenReturn(Optional.of(earthingLpsDescription));
		 
		EarthStudException earthStudException_1 = Assertions.assertThrows(EarthStudException.class,
				() -> eartStudServiceImpl.addEarthStudDetails(earthStudDescription));
		assertEquals(earthStudException_1.getMessage(), "Please enter Earthing step to proceed further");
		
	}
	
	@Test
	public void testAddEarthStud_DownConductorsException() throws EarthStudException {
		
       when(basicLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(basicLps));
		
		when(airTerminationLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(lpsAirDiscription));
		when(downConductorRepository
				.findByBasicLpsId(2)).thenReturn(Optional.of(downConductorDescription));
		when( spdRepository.findByBasicLpsId(1)).thenReturn(Optional.of(sPD));
		when(seperationDistanceRepository
				.findByBasicLpsId(1)).thenReturn(Optional.of(seperationDistanceDescription));
		
		
		when(earthingLpsRepository
				.findByBasicLpsId(1)).thenReturn(Optional.of(earthingLpsDescription));
		 
		EarthStudException earthStudException_1 = Assertions.assertThrows(EarthStudException.class,
				() -> eartStudServiceImpl.addEarthStudDetails(earthStudDescription));
		assertEquals(earthStudException_1.getMessage(), "Please enter Down Conductors step to proceed further");
		
	}
	@Test
	public void testAddEarthStud_AirTerminationException() throws EarthStudException {
		 
		when(basicLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(basicLps));
			
		when(airTerminationLpsRepository.findByBasicLpsId(2)).thenReturn(Optional.of(lpsAirDiscription));
		when(downConductorRepository
				.findByBasicLpsId(1)).thenReturn(Optional.of(downConductorDescription));
		when( spdRepository.findByBasicLpsId(1)).thenReturn(Optional.of(sPD));
		when(seperationDistanceRepository
				.findByBasicLpsId(1)).thenReturn(Optional.of(seperationDistanceDescription));
		
		
		when(earthingLpsRepository
				.findByBasicLpsId(1)).thenReturn(Optional.of(earthingLpsDescription));
		 
		EarthStudException earthStudException_1 = Assertions.assertThrows(EarthStudException.class,
				() -> eartStudServiceImpl.addEarthStudDetails(earthStudDescription));
		assertEquals(earthStudException_1.getMessage(), "Please enter Air Termination step to proceed further");
		
	}
	@Test
	public void testAddEarthStud_BasicException() throws EarthStudException {
		
		when(basicLpsRepository.findByBasicLpsId(2)).thenReturn(Optional.of(basicLps));
		
		when(airTerminationLpsRepository.findByBasicLpsId(1)).thenReturn(Optional.of(lpsAirDiscription));
		when(downConductorRepository
				.findByBasicLpsId(1)).thenReturn(Optional.of(downConductorDescription));
		when( spdRepository.findByBasicLpsId(1)).thenReturn(Optional.of(sPD));
		when(seperationDistanceRepository
				.findByBasicLpsId(1)).thenReturn(Optional.of(seperationDistanceDescription));
		
		
		when(earthingLpsRepository
				.findByBasicLpsId(1)).thenReturn(Optional.of(earthingLpsDescription));
		
		EarthStudException earthStudException_1 = Assertions.assertThrows(EarthStudException.class,
				() -> eartStudServiceImpl.addEarthStudDetails(earthStudDescription));
		assertEquals(earthStudException_1.getMessage(), "Please enter Basic Information step to proceed further");
		
		earthStudDescription.setBasicLpsId(12);
		EarthStudException earthStudException_2 = Assertions.assertThrows(EarthStudException.class,
				() -> eartStudServiceImpl.addEarthStudDetails(earthStudDescription));
		assertEquals(earthStudException_2.getMessage(), "Please enter details for all previous steps to proceed further");
		
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
