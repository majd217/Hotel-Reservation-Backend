package com.majdamireh.resvsystem.reservation;

import static com.majdamireh.resvsystem.room.RoomPartition.findAllCombinations;

import com.majdamireh.resvsystem.exceptions.CustomRunTimeException;
import com.majdamireh.resvsystem.exceptions.StatusCodes;
import com.majdamireh.resvsystem.user.User;
import com.majdamireh.resvsystem.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import com.majdamireh.resvsystem.hotel.HotelRepository;
import com.majdamireh.resvsystem.room.RoomRepository;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.majdamireh.resvsystem.room.Partition;
import org.springframework.http.HttpStatus;
import org.springframework.ws.transport.context.TransportContext;
import org.springframework.ws.transport.context.TransportContextHolder;
import org.springframework.ws.transport.http.HttpServletConnection;

import java.util.stream.Collectors;
import java.sql.Timestamp;
import java.util.*;


@Component
public class NewReservationService {
	@Autowired
	HotelRepository hotelRepository;
	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	RoomRepository roomRepository;
	@Autowired
	UserRepository userRepository;
	
	public void checkRoomSizesAvailability(int hotelID, int numberOfRooms, int numberOfPeople, Timestamp requestedCheckIn, Timestamp requestedCheckOut) {
		List<List<Integer>> validatedRoomIDs;
		ArrayList<Partition> roomPartitions = findAllCombinations(numberOfPeople);
		//a better approach is to not edit the partition list to suggest for users in case the current ones are unavailable
		//iterate through each partition and check for its validity
		for (Partition prt : roomPartitions) {
			if (prt.getRoomCounts() == numberOfRooms && ((validatedRoomIDs = checkPartitionValidity(hotelID, prt, requestedCheckIn, requestedCheckOut, numberOfRooms)) != null)) {
				//if one partition is valid, create the reservation for it and no need to test the rest of partitions.
				createReservation(hotelID, requestedCheckIn, requestedCheckOut, validatedRoomIDs);
				return;
			}
		}
		throw new CustomRunTimeException("", StatusCodes.InvalidPartition.getStatusCode());
		//add condition to check if both partitions are invalid
	}
	
	//function to check validity of the partition per hotel
	public List<List<Integer>> checkPartitionValidity(int hotelID, Partition roomPartition, Timestamp requestedCheckIn, Timestamp requestedCheckOut, int numberOfRooms) {
		List<List<Integer>> validatedRoomIDs = new ArrayList<>();
		for (Integer roomSize : roomPartition.getRoomSizes()) {
			List<Integer> roomIdsPerSize = roomRepository.findRoomBySizeAndHotelId(roomSize, hotelID);
			//if there aren't enough rooms/no rooms at all for a size in the partition invalidate this partition
			if (roomIdsPerSize.size() < roomPartition.getRoomSizeCount(roomSize) || roomIdsPerSize.isEmpty()) {
				return null;
			}
			List<Integer> roomsPerSize = new ArrayList<>();
			//check for date validity for each roomID
			
			for (Integer roomID : roomIdsPerSize) {
				if (checkForDates(roomID, hotelID, requestedCheckIn, requestedCheckOut)) //call checkfordates func
				{
					roomsPerSize.add(roomID);
					//if the count of roomIDs matches the count of rooms per size in the partition, add it to the list
					//break to the next roomsize because the count for the current size has been reached
					if (roomsPerSize.size() == roomPartition.getRoomSizeCount(roomSize)) {
						validatedRoomIDs.add(roomsPerSize);
						break;
					}
				}
			}
		}
		//turn it to a list of integers
		//if the list size does not match the number of rooms clear it and return false for this partition
		if (validatedRoomIDs.stream().flatMap(List::stream).toList().size() != numberOfRooms) {
			
			return null;
		}
		return validatedRoomIDs;
		
	}
	
	public Boolean checkForDates(Integer roomID, int hotelID, Timestamp requestedCheckIn, Timestamp requestedCheckOut) {
		List<Reservation> reservations = reservationRepository.findReservationByHotelIdtest(hotelID, roomID);
		//on the frontend side you would need to calculate the possible dates and display them
		for (Reservation resv : reservations) {
			Timestamp checkInDate = resv.getCheckInDate();
			Timestamp checkOutDate = resv.getCheckOutDate();
			
			if ((((requestedCheckIn.compareTo(checkInDate) >= 0) && (requestedCheckIn.compareTo(checkOutDate) <= 0)) ||
					((requestedCheckOut.compareTo(checkInDate) >= 0) && (requestedCheckOut.compareTo(checkOutDate) <= 0)) ||
					((requestedCheckIn.compareTo(checkInDate) <= 0) && (requestedCheckOut.compareTo(checkOutDate) >= 0)))) {
				System.out.println("exists");
				//this hotel is reserved in this date
				return false;
			}
		}
		//this hotel is available in these dates
		return true;
	}
	
	public void createReservation(int hotelid, Timestamp requestedCheckIn, Timestamp requestedCheckOut, List<List<Integer>> validatedRoomIDsPartitions) {
		//this can be enhanced since each list will only contain one element
		//this will create a separate resv for each room, you will combine them on the frontend using the userid
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
		try {
			reservationRepository.save(
					new Reservation(
							hotelRepository.findHotelByHotelID(hotelid),
							requestedCheckIn,
							requestedCheckOut,
							validatedRoomIDsPartitions.stream().flatMap(List::stream).collect(Collectors.toList()),
							userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName())
					)
			);
		} catch (IllegalArgumentException | OptimisticLockingFailureException e) {
			throw new CustomRunTimeException("", StatusCodes.DBError.getStatusCode());
		}
	}
}

//TODO
//send userid with the reservations
//location open with google maps?
//pdf for resv
//reviews