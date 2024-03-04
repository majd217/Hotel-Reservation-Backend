package com.majdamireh.resvsystem.config;

import org.springframework.http.HttpStatus;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.interceptor.EndpointInterceptorAdapter;
import org.springframework.ws.transport.context.TransportContext;
import org.springframework.ws.transport.context.TransportContextHolder;
import org.springframework.ws.transport.http.HttpServletConnection;

public class NoContentInterceptor extends EndpointInterceptorAdapter {
	
	@Override
	public void afterCompletion(MessageContext messageContext, Object o, Exception e) throws Exception {
		if (!messageContext.hasResponse()) {
			TransportContext tc = TransportContextHolder.getTransportContext();
			if (tc != null && tc.getConnection() instanceof HttpServletConnection connection) {
				// First we force the 'statusCodeSet' boolean to true:
				connection.setFaultCode(null);
				// Next we can set our custom status code:
				connection.getHttpServletResponse().setStatus(HttpStatus.OK.value());
			}
		}
	}
}
