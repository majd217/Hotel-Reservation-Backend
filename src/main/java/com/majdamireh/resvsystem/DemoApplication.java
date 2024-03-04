package com.majdamireh.resvsystem;

import com.majdamireh.resvsystem.reservation.NewReservationService;
import com.majdamireh.resvsystem.user.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.majdamireh.resvsystem.room.RoomPartition;

import java.util.Collections;

@SpringBootApplication()
@EnableJpaRepositories(basePackages = {"com.majdamireh.resvsystem"})
public class DemoApplication {
	
	
	@Autowired
	NewReservationService newReservationService;
	
	@Autowired
	UserRepository userRepository;
	
	
	@PostConstruct
	public void wtf() {
//	System.out.println(newReservationService.checkAvailability(2,java.sql.Timestamp.valueOf("2023-12-18 12:24:50.914193"),java.sql.Timestamp.valueOf("2023-12-28 13:33:47.999999"),3));
		System.out.println(userRepository.findByUserName("ayooymireh"));

//		newReservationService.checkRoomSizesAvailability(1, 3, 7, java.sql.Timestamp.valueOf("2024-2-14 3:30:00.000000"), java.sql.Timestamp.valueOf("2024-2-17 3:30:00.000000"));
//		newReservationService.checkRoomSizesAvailability(1, 2, 4, java.sql.Timestamp.valueOf("2024-2-25 3:30:00.000000"), java.sql.Timestamp.valueOf("2024-2-29 3:30:00.000000"));
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
	}
	
}
