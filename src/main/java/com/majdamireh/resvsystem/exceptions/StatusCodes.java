package com.majdamireh.resvsystem.exceptions;

public enum StatusCodes {
	Email(101, "User must provide a password"),
	Password(102, "User must provide an email"),
	UserName(103, "User must provide a username"),
	NumberOfUsers(111, "User must provide a number of users"),
	NumberOfRooms(112, "User must provide a number of rooms"),
	CheckInDate(113, "User must provide a check in date"),
	CheckOutDate(114, "User must provide a check out date"),
	UserExists(121, "This user is already registered"),
	Success(200, "Successful"),
	InvalidDates(-131, "This hotel is reserved in this period"),
	ValidDates(201, "Hotel is available in this dates"),
	InvalidPartition(-132, "Partition is invalid"),
	DBError(500, "DB error"),
	ValidPartition(202, "Partition is valid");
	
	
	private int statusCode;
	private String msg;
	
	StatusCodes(int statusCode, String msg) {
		this.statusCode = statusCode;
		this.msg = msg;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
	public String getMessage() {
		return msg;
	}
}

//chaneg msgs to cmments