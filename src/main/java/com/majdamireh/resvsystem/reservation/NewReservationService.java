package com.majdamireh.resvsystem.reservation;

import com.google.common.collect.Sets;
import com.majdamireh.resvsystem.room.Partition;
import org.springframework.beans.factory.annotation.Autowired;
import com.majdamireh.resvsystem.hotel.HotelRepository;
import com.majdamireh.resvsystem.room.RoomRepository;


import org.springframework.stereotype.Component;

import java.util.*;

import static com.majdamireh.resvsystem.room.RoomPartition.findAllCombinations;

@Component
public class NewReservationService {
	@Autowired
	HotelRepository hotelRepository;
	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	RoomRepository roomRepository;
	
	static Set<List<Integer>> result = new HashSet<>();
	
	public Boolean checkRoomSizesAvailability(int hotelID, int numberOfRooms, int numberOfPeople) {
		
		List<Set<Integer>> validatedRoomIDsParitions = new ArrayList<>();
		ArrayList<Partition> roomPartitions = findAllCombinations(numberOfPeople);
		roomPartitions.removeIf(singlePartition -> ((singlePartition.getRoomCounts() != numberOfRooms) ||
				(!checkPartitionValidity(hotelID, singlePartition, validatedRoomIDsParitions))));
		
		
		return false;
	}
	
	
	//function to check validity of the partition per hotel
	public Boolean checkPartitionValidity(int hotelID, Partition roomPartition, List<Set<Integer>> validatedRoomIDsParitions) {
		
		for (Integer roomSize : roomPartition.getRoomSizes()) {
			Set<Integer> roomIdsPerSize = roomRepository.findRoomBySizeAndHotelId(roomSize, hotelID);
			if (roomIdsPerSize.size() < roomPartition.getRoomSizeCount(roomSize) || roomIdsPerSize.isEmpty()) {
				validatedRoomIDsParitions.clear();
				return false;
			}
			validatedRoomIDsParitions.add(roomIdsPerSize);
			if (roomPartition.getRoomSizeCount(roomSize) == validatedRoomIDsParitions.size()) {
				
				return true;
			} else if (roomPartition.getRoomSizeCount(roomSize) > validatedRoomIDsParitions.size()) {
			
			}
		}
		
		result = Sets.cartesianProduct(validatedRoomIDsParitions);
		return true;
	}
	
	
	public void roomIDsToRequest(List<Set<Integer>> validatedRoomIDsParitions) {
	
	}
}


//	public void checkForDates (ArrayList<ArrayList<Integer>> requestedRoomNumbers, Set<List<Integer>> result, int hotelID, Timestamp requestedCheckIn, Timestamp requestedCheckOut)
//	{
//		List<Reservation> reservations = reservationRepository.findReservationByHotelId(hotelID);
//		//on the frontend side you would need to calculate the possible dates and display them
//		for (Reservation resv : reservations) {
//			Timestamp checkInDate = resv.getCheckInDate();
//			Timestamp checkOutDate = resv.getCheckOutDate();
//
//			if ((((requestedCheckIn.compareTo(checkInDate) >= 0) && (requestedCheckIn.compareTo(checkOutDate) <= 0)) ||
//					((requestedCheckOut.compareTo(checkInDate) >= 0) && (requestedCheckOut.compareTo(checkOutDate) <= 0)) ||
//					((requestedCheckIn.compareTo(checkInDate) <= 0) && (requestedCheckOut.compareTo(checkOutDate) >= 0))))
//			{
//
//			}
//		}
//	}
//

//	public String createReservation(int hotelid, Timestamp requestedCheckIn, Timestamp requestedCheckOut) {
//
//		reservationRepository.save(new Reservation(hotelRepository.findHotelByHotelID(hotelid), requestedCheckIn, requestedCheckOut));
//
//		return HttpStatus.BAD_REQUEST.toString();
//	}

