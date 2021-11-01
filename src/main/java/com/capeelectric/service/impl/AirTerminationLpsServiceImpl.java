package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.AirTerminationException;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.LpsAirDiscription;
import com.capeelectric.repository.AirTerminationLpsRepository;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.service.AirTerminationLpsService;
import com.capeelectric.util.UserFullName;

/**
 * This AirTerminationLpsServiceImpl service class doing save and retrieve
 * operation related to AirTerminationLpsDetails
 * 
 * @author capeelectricsoftware
 *
 */

@Service
public class AirTerminationLpsServiceImpl implements AirTerminationLpsService {

	@Autowired
	private AirTerminationLpsRepository airTerminationLpsRepository;
	
	@Autowired
	private BasicLpsRepository basicLpsRepository;	

	@Autowired
	private UserFullName userFullName;

	@Override
	public void addAirTerminationLpsDetails(LpsAirDiscription lpsAirDescription) throws AirTerminationException {
		if (lpsAirDescription != null && lpsAirDescription.getUserName() != null && lpsAirDescription.getUserName() != "") {
			Optional<BasicLps> basicLpsRepo = basicLpsRepository.findByBasicLpsId(lpsAirDescription.getBasicLpsId());
			if(basicLpsRepo.isPresent()
					&& basicLpsRepo.get().getBasicLpsId().equals(lpsAirDescription.getBasicLpsId())) {
				Optional<LpsAirDiscription> airTerminationLpsRepo = airTerminationLpsRepository.findByBasicLpsId(lpsAirDescription.getBasicLpsId());
				if(!airTerminationLpsRepo.isPresent() || !airTerminationLpsRepo.get().getBasicLpsId().equals(lpsAirDescription.getBasicLpsId())) {
					lpsAirDescription.setCreatedDate(LocalDateTime.now());
					lpsAirDescription.setUpdatedDate(LocalDateTime.now());
					lpsAirDescription.setCreatedBy(userFullName.findByUserName(lpsAirDescription.getUserName()));
					lpsAirDescription.setUpdatedBy(userFullName.findByUserName(lpsAirDescription.getUserName()));
					airTerminationLpsRepository.save(lpsAirDescription);
				}
				else {
					throw new AirTerminationException("Given Basic LPS Id is already Available in Air Termination");
				}
			}
			else {
				throw new AirTerminationException("Given Basic LPS Id is Not Registered in Basic LPS");
			}
		}
		 else {
			throw new AirTerminationException("Invalid Inputs");
		}

	}

	@Override
	public List<LpsAirDiscription> retrieveAirTerminationLps(String userName, Integer basicLpsId)
			throws AirTerminationException {
		if (userName != null && basicLpsId != null) {
			List<LpsAirDiscription> airTerminationLpsRepo = airTerminationLpsRepository
					.findByUserNameAndBasicLpsId(userName, basicLpsId);
			if (airTerminationLpsRepo != null && !airTerminationLpsRepo.isEmpty()) {
				return airTerminationLpsRepo;
			} else {
				throw new AirTerminationException("Given UserName & Id doesn't exist in Air Termination LPS Details");
			}
		} else {
			throw new AirTerminationException("Invalid Inputs");
		}

	}

	@Override
	public void updateAirTerminationLps(LpsAirDiscription lpsAirDescription) throws AirTerminationException {

		if (lpsAirDescription != null && lpsAirDescription.getLpsAirDescId() != null
				&& lpsAirDescription.getLpsAirDescId() != 0 && lpsAirDescription.getBasicLpsId() != null
				&& lpsAirDescription.getBasicLpsId() != 0) {
			Optional<LpsAirDiscription> airTerminationLpsRepo = airTerminationLpsRepository
					.findById(lpsAirDescription.getLpsAirDescId());
			if (airTerminationLpsRepo.isPresent()
					&& airTerminationLpsRepo.get().getBasicLpsId().equals(lpsAirDescription.getBasicLpsId())) {
				lpsAirDescription.setUpdatedDate(LocalDateTime.now());
				lpsAirDescription.setUpdatedBy(userFullName.findByUserName(lpsAirDescription.getUserName()));
			    airTerminationLpsRepository.save(lpsAirDescription);
			} else {
				throw new AirTerminationException("Given Basic LPS Id and LPS Air Description Id is Invalid");
			}

		} else {
			throw new AirTerminationException("Invalid inputs");
		}
	}

}
