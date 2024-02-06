package com.majdamireh.resvsystem.reservation;

import com.majdamireh.resvsystem.hotel.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;


public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	//return all existing checkindates for this hotel id
	@Query(value = "select resv.checkindate from reservations resv where resv.hotelid = :hotelid", nativeQuery = true)
	List<Timestamp> getCheckInDate(@Param("hotelid") int hotelid);
	
	//return all existing checkoutdates for this hotel id
	@Query(value = "select resv.checkoutdate from reservations resv where resv.hotelid = :hotelid", nativeQuery = true)
	List<Timestamp> getCheckOutDate(@Param("hotelid") int hotelid);
	
	@Query(value = "select * from reservations where hotelid = :hotelid", nativeQuery = true)
	List<Reservation> findReservationByHotelId(@Param("hotelid") int hotelid);
	
	@Query(value = "select * from reservations where hotelid = :hotelid", nativeQuery = true)
	List<Reservation> findReservationByHotelIdAndRoomNumber(@Param("hotelid") int hotelid);
	
	
}
