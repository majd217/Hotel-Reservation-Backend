package com.majdamireh.resvsystem.exceptions;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CLIENT)
public class CustomRunTimeException extends RuntimeException {
	public CustomRunTimeException(Integer statusCode) {
		//or should i throw the message instead?
		super(statusCode.toString());
	}
}
