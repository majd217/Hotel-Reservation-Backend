package com.majdamireh.resvsystem.room;

import com.majdamireh.resvsystem.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface RoomRepository extends JpaRepository<Room, Integer> {
	
	
	@Query(value = "select * from rooms where hotelid = :hotelid", nativeQuery = true)
	List<Room> findByHotelid(@Param("hotelid") int hotelid);
	
	@Query(value = "select roomid from rooms where roomsize = :roomsize AND hotelid = :hotelid", nativeQuery = true)
	Set<Integer> findRoomBySizeAndHotelId(@Param("roomsize") int roomsize, @Param("hotelid") int hotelid);
}
