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

import com.capeelectric.exception.DownConductorException;
import com.capeelectric.model.AirTermination;
import com.capeelectric.model.BasicLps;
import com.capeelectric.model.DownConductorDescription;
import com.capeelectric.model.DownConductorReport;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.DownConductorRepository;
import com.capeelectric.service.DownConductorService;
import com.capeelectric.util.FindNonRemovedObjects;
import com.capeelectric.util.UserFullName;

/**
 * 
 *   This DownConductorServiceImpl service class doing save and retrieve operation related to DownConductorDetails
 * 
 * @author CAPE-SOFTWARE
 *
 */

@Service
public class DownConductorServiceImpl implements DownConductorService{
	
	private static final Logger logger = LoggerFactory.getLogger(DownConductorServiceImpl.class);
	
	@Autowired
	private DownConductorRepository downConductorRepository;
	
	@Autowired
	private BasicLpsRepository basicLpsRepository;
	
	@Autowired
	private UserFullName userFullName;
	
	@Autowired
	private FindNonRemovedObjects findNonRemovedObjects;
	
	@Override
	public void addDownConductorsDetails(DownConductorReport downConductorReport)
			throws  DownConductorException{
		if (downConductorReport != null && downConductorReport.getUserName() != null
				&& !downConductorReport.getUserName().isEmpty() && downConductorReport.getBasicLpsId() != null
				&& downConductorReport.getBasicLpsId() != 0) {
			Optional<BasicLps> basicLpsRepo = basicLpsRepository.findByBasicLpsId(downConductorReport.getBasicLpsId());
			if(basicLpsRepo.isPresent()
					&& basicLpsRepo.get().getBasicLpsId().equals(downConductorReport.getBasicLpsId())) {
				Optional<DownConductorReport> downConductorRepo = downConductorRepository
						.findByBasicLpsId(downConductorReport.getBasicLpsId());
				if (!downConductorRepo.isPresent()
						|| !downConductorRepo.get().getBasicLpsId().equals(downConductorReport.getBasicLpsId())) {
					
					List<DownConductorDescription> downConductorDescription = downConductorReport.getDownConductorDescription();
					if(downConductorDescription != null && downConductorDescription.size() > 0) {
						downConductorReport.setCreatedDate(LocalDateTime.now());
						downConductorReport.setUpdatedDate(LocalDateTime.now());
						downConductorReport.setCreatedBy(userFullName.findByUserName(downConductorReport.getUserName()));
						downConductorReport.setUpdatedBy(userFullName.findByUserName(downConductorReport.getUserName()));
						downConductorRepository.save(downConductorReport);
					} else {
						throw new DownConductorException("Please fill all the fields before clicking next button");
					}
				} else {
					throw new DownConductorException("Basic LPS Id Already Available.Create New Basic Id");
				}
			}
			else {
				throw new DownConductorException("Given Basic LPS Id is Not Registered in Basic LPS");
			}
		}
		else {
			throw new DownConductorException("Invalid Inputs");
		}
			
	}
	
	@Override
	public List<DownConductorReport> retrieveDownConductorDetails(String userName, Integer basicLpsId)
			throws DownConductorException {
		if (userName != null) {
			List<DownConductorReport> downConductorRepo = downConductorRepository.findByUserNameAndBasicLpsId(userName,
					basicLpsId);
			if (downConductorRepo != null && !downConductorRepo.isEmpty()) {	
				for(DownConductorReport downConductorReportItr : downConductorRepo) {
					downConductorReportItr.setDownConductorDescription(findNonRemovedObjects.findNonRemovedDownConductorsBuildings(downConductorReportItr));
				}
				return downConductorRepo;
			} else {
				throw new DownConductorException("Given UserName & Id doesn't exist in Down Conductor Details");
			}
		} else {
			throw new DownConductorException("Invalid Inputs");
		}
	}
	
	@Override
	public void updateDownConductorDetails(DownConductorReport downConductorReport) throws DownConductorException {

		if (downConductorReport != null && downConductorReport.getDownConductorReportId() != null
				&& downConductorReport.getDownConductorReportId() != 0 && downConductorReport.getBasicLpsId() != null
				&& downConductorReport.getBasicLpsId() != 0) {
			Optional<DownConductorReport> downConductorRepo = downConductorRepository
					.findById(downConductorReport.getDownConductorReportId());
			if (downConductorRepo.isPresent()
					&& downConductorRepo.get().getBasicLpsId().equals(downConductorReport.getBasicLpsId())) {
				downConductorReport.setUpdatedDate(LocalDateTime.now());
				downConductorReport.setUpdatedBy(userFullName.findByUserName(downConductorReport.getUserName()));
				downConductorRepository.save(downConductorReport);
			} else {
				throw new DownConductorException("Given Basic LPS Id and Down Conductor Id is Invalid");
			}

		} else {
			throw new DownConductorException("Invalid inputs");
		}
	}
	
}
