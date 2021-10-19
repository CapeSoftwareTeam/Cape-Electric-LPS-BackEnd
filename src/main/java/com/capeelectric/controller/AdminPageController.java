package com.capeelectric.controller;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capeelectric.exception.RegisterPermissionRequestException;
import com.capeelectric.exception.RegistrationException;
import com.capeelectric.model.Register;
import com.capeelectric.request.RegisterPermissionRequest;
import com.capeelectric.service.RegistrationService;
import com.capeelectric.service.impl.AWSEmailService;
import com.capeelectric.util.Utility;

@RestController
@RequestMapping("/api/v2")
@CrossOrigin(origins = "*")
public class AdminPageController {
	private static final Logger logger = LoggerFactory.getLogger(AdminPageController.class);

	@Autowired
	private AWSEmailService awsEmailService;

	@Autowired
	private RegistrationService registrationService;
	@GetMapping("/retrieveAllRegistration")
	public List<Register> retrieveAllRegistration() throws RegistrationException {
		logger.info("called retrieveAllRegistration function");
		return registrationService.retrieveAllRegistration();
	}
	
	@PutMapping("/updatePermission")
	public ResponseEntity<String> updatePermission(@RequestBody RegisterPermissionRequest registerPermissionRequest)
			throws RegistrationException, RegisterPermissionRequestException, MessagingException, MalformedURLException {
		logger.info("called updatePermission function AdminUserName : {}", registerPermissionRequest.getAdminUserName());
		Register register = registrationService.updatePermission(registerPermissionRequest);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(register.getRegisterId()).toUri();
		String resetUrl = Utility.getSiteURL(uri.toURL());
		if (register != null && register.getPermission().equalsIgnoreCase("YES")) {
			awsEmailService.sendEmail(register.getUsername(),
					"Your request for accessing the Rush App is approved and you can generate OTP with this link"
							+ "\n" + "\n" 
							+ (resetUrl.contains("localhost:5000")
									? resetUrl.replace("http://localhost:5000", "http://localhost:4200")
											: "https://www.rushforsafety.com")
							+ "/generateOtp" + ";email=" + register.getUsername());
		} else {
			awsEmailService.sendEmail(register.getUsername(),
					"Your Registration has not Approved, So You Can't Access Rush for Safety App");
		}
		return new ResponseEntity<String>("Successfully Updated RegisterPermission", HttpStatus.OK);
	}
}