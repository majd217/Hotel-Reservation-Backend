package com.majdamireh.resvsystem.reservation;

//import com.hotelreservation.user.NewReservationResponse;

import com.hotelreservation.user.NewReservationRequest;
import com.majdamireh.resvsystem.exceptions.CustomRunTimeException;
import com.majdamireh.resvsystem.exceptions.StatusCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.sql.Timestamp;

@Endpoint
public class NewReservationServiceEndPoint {
	
	@Autowired
	NewReservationService newReservationService;
	
	//this receives date type of XMLgregorian from the frontend (you have to convert the user input to xmlgregorian)
	@PayloadRoot(namespace = "http://hotelreservation.com/user", localPart = "NewReservationRequest")
	public void reservationResponse(@RequestPayload NewReservationRequest reservationRequest) {
		newReservationService.checkRoomSizesAvailability(reservationRequest.getHotelid(), reservationRequest.getNumberOfRooms(), reservationRequest.getNumberOfPeople(),
				new Timestamp(reservationRequest.getCheckInDate().toGregorianCalendar().getTimeInMillis()),
				new Timestamp(reservationRequest.getCheckOutDate().toGregorianCalendar().getTimeInMillis()));
	}
}

