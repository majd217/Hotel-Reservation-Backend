package com.majdamireh.resvsystem.registration;

import com.hotelreservation.user.UserRegistrationRequest;
import com.hotelreservation.user.UserRegistrationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class RegistrationEndPoint {
    @Autowired
    RegistrationService registrationService;
    @PayloadRoot(namespace = "http://hotelreservation.com/user" , localPart = "UserRegistrationRequest")
    @ResponsePayload
    public UserRegistrationResponse registerUser (@RequestPayload UserRegistrationRequest registrationRequest)
    {
        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setSuccess(registrationService.saveCredentials(registrationRequest.getEmail(),registrationRequest.getUserName(),registrationRequest.getPassword()));
        return response;
    }
}





