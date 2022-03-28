package com.capeelectric.util;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.capeelectric.model.BasicLps;
import com.capeelectric.model.Register;
import com.capeelectric.repository.BasicLpsRepository;
import com.capeelectric.repository.RegistrationRepository;


/**
 * @author capeelectricsoftware
 *
 */
@Configuration
public class UserFullName {

	@Autowired
	private RegistrationRepository registrationRepository;
	
	@Autowired
	private BasicLpsRepository basicLpsRepository;
	
	/**
	 * Method to return Full Name based on UserName
	 * 
	 * @param userName
	 * @return
	 */
	public String findByUserName(String userName) {
		Optional<Register> registerRepo = registrationRepository.findByUsername(userName);

		if (registerRepo.isPresent() && registerRepo.get() != null && registerRepo.get().getName() != null) {
			return registerRepo.get().getName();
		}
		return null;

	}
	public void addUpdatedByandDate(Integer lpsId, String upDatedBy) {
		Optional<BasicLps> basicLpsData = basicLpsRepository.findById(lpsId);

		if (basicLpsData.isPresent()) {
			basicLpsData.get().setUpdatedBy(upDatedBy);
			basicLpsData.get().setUpdatedDate(LocalDateTime.now());
			basicLpsRepository.save(basicLpsData.get());
		}
	}
}
