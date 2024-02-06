package com.majdamireh.resvsystem.hotel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    Hotel findHotelByHotelName(String hotelName);
    Hotel findHotelByHotelID(int hotelID);


}
