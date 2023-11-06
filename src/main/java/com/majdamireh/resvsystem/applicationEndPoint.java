package com.majdamireh.resvsystem;


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
}
