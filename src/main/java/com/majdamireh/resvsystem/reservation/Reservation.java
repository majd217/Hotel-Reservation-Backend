package com.majdamireh.resvsystem.reservation;

import com.majdamireh.resvsystem.hotel.Hotel;
import com.majdamireh.resvsystem.user.User;
import com.majdamireh.resvsystem.room.Room;

import jakarta.persistence.*;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.Type;

import java.sql.Array;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "reservations")
public class Reservation {
	public Reservation(Hotel hotel, Timestamp checkInDate, Timestamp checkOutDate, List<Integer> roomids, User user) {
		this.hotel = hotel;
//		this.room = room;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.roomids = roomids;
		this.user = user;
		
	}
	
	public int getResvId() {
		return resvId;
	}
	
	public Hotel getHotel() {
		return hotel;
	}

//	public int getRoomNumber() {
//		return room.getRoomid();
//	}
	
	public Timestamp getCheckInDate() {
		return checkInDate;
	}
	
	public Timestamp getCheckOutDate() {
		return checkOutDate;
	}
	
	public User getUser() {
		return user;
	}
	
	@SequenceGenerator(
			name = "reservations_resvid_seq",
			sequenceName = "reservations_resvid_seq",
			allocationSize = 1
	)
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "reservations_resvid_seq"
	)
	@Column(name = "resvid")
	private int resvId;


//	@ManyToOne
//	@JoinColumn(name = "roomid", referencedColumnName = "roomid")
//	private Room room;
	
	@Column(name = "checkindate")
	private Timestamp checkInDate;
	
	@Column(name = "checkoutdate")
	private Timestamp checkOutDate;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "userid", referencedColumnName = "id")
	private User user;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "hotelid", referencedColumnName = "hotelid")
	private Hotel hotel;
	@Column(name = "roomids", columnDefinition = "int[]")
	private List<Integer> roomids;
	
	public Reservation() {
	
	}
	
}
