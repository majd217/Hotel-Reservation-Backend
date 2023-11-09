package com.majdamireh.resvsystem;

import com.hotelreservation.user.UserRegistrationRequest;
import com.hotelreservation.user.UserRegistrationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class applicationEndPoint {

    @Autowired
    LoginService loginService;
    @PayloadRoot(namespace ="http://hotelreservation.com/user" , localPart = "UserLoginRequest")
    @ResponsePayload
    public com.hotelreservation.user.UserLoginResponse loginUser (@RequestPayload com.hotelreservation.user.UserLoginRequest loginRequest){

        com.hotelreservation.user.UserLoginResponse response = new com.hotelreservation.user.UserLoginResponse();
        response.setSuccess(loginService.checkCredentials(loginRequest.getUserName(), loginRequest.getPassword()));
        return response;
    }

    @Autowired
    RegistrationService registrationService;
    @PayloadRoot(namespace = "http://hotelreservation.com/user" , localPart = "UserRegistrationRequest")
    @ResponsePayload
    public UserRegistrationResponse resgisterUser(@RequestPayload UserRegistrationRequest registrationRequest)
    {
        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setSuccess(registrationService.saveCredentials(registrationRequest.getEmail(),registrationRequest.getUserName(),registrationRequest.getPassword()));
        return response;
    }
}
