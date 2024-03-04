package com.majdamireh.resvsystem.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.transport.context.TransportContext;
import org.springframework.ws.transport.context.TransportContextHolder;
import org.springframework.ws.transport.http.HttpServletConnection;

@Endpoint
public class LoginEndPoint {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PayloadRoot(namespace = "http://hotelreservation.com/user", localPart = "UserLoginRequest")
	@ResponsePayload
	public void loginUser(@RequestPayload com.hotelreservation.user.UserLoginRequest loginRequest) throws IllegalArgumentException {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		TransportContext ctx = TransportContextHolder.getTransportContext();
		HttpServletRequest req = ((HttpServletConnection) ctx.getConnection()).getHttpServletRequest(); //if ur certain its http
		HttpSession session = req.getSession();
		
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
				SecurityContextHolder.getContext());
	}
}

