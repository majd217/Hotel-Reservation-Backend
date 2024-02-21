package com.majdamireh.resvsystem.reservation;

import static com.majdamireh.resvsystem.room.RoomPartition.findAllCombinations;

import org.springframework.beans.factory.annotation.Autowired;
import com.majdamireh.resvsystem.hotel.HotelRepository;
import com.majdamireh.resvsystem.room.RoomRepository;
import org.springframework.stereotype.Component;
import com.majdamireh.resvsystem.room.Partition;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class NewReservationService {
	@Autowired
	HotelRepository hotelRepository;
	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	RoomRepository roomRepository;
	
	public void checkRoomSizesAvailability(int hotelID, int numberOfRooms, int numberOfPeople, Timestamp requestedCheckIn, Timestamp requestedCheckOut) {
		
		List<List<Integer>> validatedRoomIDs = new ArrayList<>();
		ArrayList<Partition> roomPartitions = findAllCombinations(numberOfPeople);
		//a better approach is to not edit the partition list to suggest for users in case the current ones are unavailable
		//iterate through each partition and check for its validity
		Iterator<Partition> roomParitionItr = roomPartitions.iterator();
		while (roomParitionItr.hasNext()) {
			Partition prt = roomParitionItr.next();
			if (prt.getRoomCounts() == numberOfRooms && checkPartitionValidity(hotelID, prt, validatedRoomIDs, requestedCheckIn, requestedCheckOut, numberOfRooms)) {
				//if one partition is valid, create the reservation for it and no need to test the rest of partitions.
				createReservation(hotelID, requestedCheckIn, requestedCheckOut, validatedRoomIDs);
				break;
			}
		}
		//add condition to check if both paritions are invalid
	}
	
	//function to check validity of the partition per hotel
	public Boolean checkPartitionValidity(int hotelID, Partition roomPartition, List<List<Integer>> validatedRoomIDs, Timestamp requestedCheckIn, Timestamp requestedCheckOut, int numberOfRooms) {
		
		
		for (Integer roomSize : roomPartition.getRoomSizes()) {
			int count = 0;
			List<Integer> roomIdsPerSize = roomRepository.findRoomBySizeAndHotelId(roomSize, hotelID);
			//if there aren't enough rooms/no rooms at all for a size in the partition invalidate this partition
			if (roomIdsPerSize.size() < roomPartition.getRoomSizeCount(roomSize) || roomIdsPerSize.isEmpty()) {
				validatedRoomIDs.clear();
				return false;
			}
			List<Integer> roomsPerSize = new ArrayList<>();
			//check for date validity for each roomID
			for (Integer roomID : roomIdsPerSize) {
				if (checkForDates(roomID, hotelID, requestedCheckIn, requestedCheckOut)) //call checkfor dates func
				{
					count++;
					roomsPerSize.add(roomID);
					//if the count of roomIDs matches the count of rooms per size in the partition, add it to the list
					//break to the next roomsize because the count for the current size has been reached
					if (count == roomPartition.getRoomSizeCount(roomSize)) {
						validatedRoomIDs.add(roomsPerSize);
						break;
					}
				}
			}
		}
		//turn it to a list of integers
		List<Integer> roomIDsToRequest = validatedRoomIDs.stream().flatMap(List::stream).collect(Collectors.toList());
		//if the list size does not match the number of rooms clear it and return false for this partition
		if (roomIDsToRequest.size() != numberOfRooms) {
			validatedRoomIDs.clear();
			return false;
		} else {
			return true;
		}
	}
	
	public Boolean checkForDates(Integer roomID, int hotelID, Timestamp requestedCheckIn, Timestamp requestedCheckOut) {
		List<Reservation> reservations = reservationRepository.findReservationByHotelId(hotelID, roomID);
		//on the frontend side you would need to calculate the possible dates and display them
		for (Reservation resv : reservations) {
			Timestamp checkInDate = resv.getCheckInDate();
			Timestamp checkOutDate = resv.getCheckOutDate();
			
			if ((((requestedCheckIn.compareTo(checkInDate) >= 0) && (requestedCheckIn.compareTo(checkOutDate) <= 0)) ||
					((requestedCheckOut.compareTo(checkInDate) >= 0) && (requestedCheckOut.compareTo(checkOutDate) <= 0)) ||
					((requestedCheckIn.compareTo(checkInDate) <= 0) && (requestedCheckOut.compareTo(checkOutDate) >= 0)))) {
				System.out.println("exists");
				return false;
			}
		}
		return true;
	}
	
	//validated [[1][2][3]]
	public String createReservation(int hotelid, Timestamp requestedCheckIn, Timestamp requestedCheckOut, List<List<Integer>> validatedRoomIDsParitions) {
		//this can be enhanced since each list will only contain one element
		//this will create a separate resv for each room, you will combine them on the frontend useing the userid
		List<Integer> roomIDsToRequest = validatedRoomIDsParitions.stream().flatMap(List::stream).collect(Collectors.toList());
		for (Integer roomid : roomIDsToRequest) {
			reservationRepository.save(new Reservation(hotelRepository.findHotelByHotelID(hotelid), roomRepository.findByHotelidAndRoomid(hotelid, roomid), requestedCheckIn, requestedCheckOut));
		}
		return HttpStatus.BAD_REQUEST.toString();
	}
}

//TODO
////make roomIDs array
//make compound pk
//how will
//Suggest alternatives for the user
