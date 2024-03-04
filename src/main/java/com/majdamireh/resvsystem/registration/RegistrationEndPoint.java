package com.majdamireh.resvsystem.registration;

import com.hotelreservation.user.UserRegistrationRequest;
//import com.hotelreservation.user.UserRegistrationResponse;
import com.majdamireh.resvsystem.exceptions.StatusCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class RegistrationEndPoint {
	@Autowired
	RegistrationService registrationService;
	
	@PayloadRoot(namespace = "http://hotelreservation.com/user", localPart = "UserRegistrationRequest")
	public void registerUser(@RequestPayload UserRegistrationRequest registrationRequest) {
		registrationService.saveCredentials(registrationRequest.getEmail(),
				registrationRequest.getUserName(), registrationRequest.getPassword());
	}
}

//make sure the user logs in to get authenticated




