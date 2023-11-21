package com.majdamireh.resvsystem.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class LoginEndPoint {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PayloadRoot(namespace = "http://hotelreservation.com/user", localPart = "UserLoginRequest")
    @ResponsePayload
    public com.hotelreservation.user.UserLoginResponse loginUser(@RequestPayload com.hotelreservation.user.UserLoginRequest loginRequest) {
        com.hotelreservation.user.UserLoginResponse response = new com.hotelreservation.user.UserLoginResponse();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        response.setSuccess(HttpStatus.OK.toString());
        return response;
    }
}

