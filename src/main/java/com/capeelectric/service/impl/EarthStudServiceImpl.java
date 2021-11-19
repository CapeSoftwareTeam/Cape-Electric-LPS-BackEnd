/**
 * 
 */
package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.EarthStudException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.DownConductorDescription;
import com.capeelectric.model.EarthStudDescription;
import com.capeelectric.model.EarthingLpsDescription;
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.model.LpsFinalReport;
import com.capeelectric.model.SPD;
import com.capeelectric.model.SeperationDistanceDescription;
import com.capeelectric.repository.AirTerminationLpsRepository;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.DownConductorRepository;
import com.capeelectric.repository.EarthStudRepository;
import com.capeelectric.repository.EarthingLpsRepository;
import com.capeelectric.repository.SPDRepository;
import com.capeelectric.repository.SeperationDistanceRepository;
import com.capeelectric.service.EarthStudService;
import com.capeelectric.util.UserFullName;

/**
 * This EarthStudServiceImpl service class doing save and retrieve operation
 * related to EarthStudDetails
 * 
 * @author CAPE-SOFTWARE
 *
 */
@Service
public class EarthStudServiceImpl implements EarthStudService {
	private static final Logger logger = LoggerFactory.getLogger(EarthStudServiceImpl.class);

	@Autowired
	private EarthStudRepository earthStudRepository;

	@Autowired
	private BasicLpsRepository basicLpsRepository;

	private BasicLps basicLps;

	@Autowired
	private UserFullName userFullName;
	
	@Autowired
	private DownConductorRepository downConductorRepository;

	@Autowired
	private EarthingLpsRepository earthingLpsRepository;

	@Autowired
	private SPDRepository spdRepository;

	@Autowired
	private AirTerminationLpsRepository airTerminationLpsRepository;

	@Autowired
	private SeperationDistanceRepository seperationDistanceRepository;

	@Autowired

	private LpsFinalReport lpsFinalReport;

	@Override
	public void addEarthStudDetails(EarthStudDescription earthStudDescription) throws EarthStudException {
		if (earthStudDescription != null && earthStudDescription.getUserName() != null
				&& !earthStudDescription.getUserName().isEmpty() && earthStudDescription.getBasicLpsId() != null
				&& earthStudDescription.getBasicLpsId() != 0) {
			Optional<BasicLps> basicLpsDetails = basicLpsRepository
					.findByBasicLpsId(earthStudDescription.getBasicLpsId());
			Optional<LpsAirDiscription> lpsAirDisc = airTerminationLpsRepository
					.findByBasicLpsId(earthStudDescription.getBasicLpsId());

			Optional<DownConductorDescription> downConductorDetails = downConductorRepository
					.findByBasicLpsId(earthStudDescription.getBasicLpsId());
			Optional<EarthingLpsDescription> earthingLpsDetails = earthingLpsRepository
					.findByBasicLpsId(earthStudDescription.getBasicLpsId());
			Optional<SPD> spdDetails = spdRepository.findByBasicLpsId(earthStudDescription.getBasicLpsId());
			Optional<SeperationDistanceDescription> separateDistanceDetails = seperationDistanceRepository
					.findByBasicLpsId(earthStudDescription.getBasicLpsId());
			Optional<EarthStudDescription> earthStudDetails = earthStudRepository
					.findByBasicLpsId(earthStudDescription.getBasicLpsId());

			if (!basicLpsDetails.isPresent() && !lpsAirDisc.isPresent() && !downConductorDetails.isPresent()
					&& !earthingLpsDetails.isPresent() && !separateDistanceDetails.isPresent()
					&& !spdDetails.isPresent()) {
				throw new EarthStudException("Please enter details for all previous steps to proceed further");
			} else if (!basicLpsDetails.isPresent()) {
				throw new EarthStudException("Please enter Basic Information step to proceed further");
			} else if (!lpsAirDisc.isPresent()) {
				throw new EarthStudException("Please enter Air Termination step to proceed further");
			} else if (!downConductorDetails.isPresent()) {
				throw new EarthStudException("Please enter Down Conductors step to proceed further");
			} else if (!earthingLpsDetails.isPresent()) {
				throw new EarthStudException("Please enter Earthing step to proceed further");
			} else if (!spdDetails.isPresent()) {
				throw new EarthStudException("Please enter SPD step to proceed further");

			} else if (!separateDistanceDetails.isPresent()) {
				throw new EarthStudException("Please enter Seperation Distance step to proceed further");
			}
			Optional<BasicLps> basicLpsRepo = basicLpsRepository.findByBasicLpsId(earthStudDescription.getBasicLpsId());
			if (basicLpsRepo.isPresent()
					&& basicLpsRepo.get().getBasicLpsId().equals(earthStudDescription.getBasicLpsId())) {
				Optional<EarthStudDescription> earthStudRepo = earthStudRepository
						.findByBasicLpsId(earthStudDescription.getBasicLpsId());
				if (!earthStudRepo.isPresent()
						|| !earthStudRepo.get().getBasicLpsId().equals(earthStudDescription.getBasicLpsId())) {

					earthStudDescription.setCreatedDate(LocalDateTime.now());
					earthStudDescription.setUpdatedDate(LocalDateTime.now());
					earthStudDescription.setCreatedBy(userFullName.findByUserName(earthStudDescription.getUserName()));
					earthStudDescription.setUpdatedBy(userFullName.findByUserName(earthStudDescription.getUserName()));
					earthStudRepository.save(earthStudDescription);
					basicLpsRepo = basicLpsRepository.findByBasicLpsId(earthStudDescription.getBasicLpsId());
					if (basicLpsRepo.isPresent()
							&& basicLpsRepo.get().getBasicLpsId().equals(earthStudDescription.getBasicLpsId())) {
						basicLps = basicLpsRepo.get();
						basicLps.setAllStepsCompleted("AllStepCompleted");
						basicLpsRepository.save(basicLps);
					} else {
						throw new EarthStudException("Basic LPS Id Information not Available in Basic LPS Id Details");
					}
				} else {
					throw new EarthStudException("Basic LPS Id Already Available.Create New Basic Id");
				}
			} else {
				throw new EarthStudException("Given Basic LPS Id is Not Registered in Basic LPS");
			}
		} else {
			throw new EarthStudException("Invalid Inputs");
		}

	}

	@Override
	public List<EarthStudDescription> retrieveEarthStudDetails(String userName, Integer basicLpsId)
			throws EarthStudException {
		if (userName != null) {
			List<EarthStudDescription> earthStudRepo = earthStudRepository.findByUserNameAndBasicLpsId(userName,
					basicLpsId);
			if (earthStudRepo != null && !earthStudRepo.isEmpty()) {
				return earthStudRepo;
			} else {
				throw new EarthStudException("Given UserName & Id doesn't exist in Down Conductor Details");
			}
		} else {
			throw new EarthStudException("Invalid Inputs");
		}
	}

	@Override
	public void updateEarthStudDetails(EarthStudDescription earthStudDescription) throws EarthStudException {

		if (earthStudDescription != null && earthStudDescription.getEarthStudDescId() != null
				&& earthStudDescription.getEarthStudDescId() != 0 && earthStudDescription.getBasicLpsId() != null
				&& earthStudDescription.getBasicLpsId() != 0) {
			Optional<EarthStudDescription> earthStudRepo = earthStudRepository
					.findById(earthStudDescription.getEarthStudDescId());
			if (earthStudRepo.isPresent()
					&& earthStudRepo.get().getBasicLpsId().equals(earthStudDescription.getBasicLpsId())) {
				earthStudDescription.setUpdatedDate(LocalDateTime.now());
				earthStudDescription.setUpdatedBy(userFullName.findByUserName(earthStudDescription.getUserName()));
				earthStudRepository.save(earthStudDescription);
			} else {
				throw new EarthStudException("Given Basic LPS Id and Earth Stud Id is Invalid");
			}

		} else {
			throw new EarthStudException("Invalid inputs");
		}
	}
}
