package com.majdamireh.resvsystem.reservation;

import com.majdamireh.resvsystem.hotel.Hotel;
import com.majdamireh.resvsystem.user.User;
import com.majdamireh.resvsystem.room.Room;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "reservations")
public class Reservation {
	public Reservation(Hotel hotel, Room room, Timestamp checkInDate, Timestamp checkOutDate) {
		this.hotel = hotel;
		this.room = room;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		
	}
	
	public int getResvId() {
		return resvId;
	}
	
	public Hotel getHotel() {
		return hotel;
	}
	
	public int getRoomNumber() {
		return room.getRoomid();
	}
	
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
	
	@ManyToOne
	@JoinColumn(name = "hotelid", referencedColumnName = "hotelid")
	private Hotel hotel;
	
	@ManyToOne
	@JoinColumn(name = "roomid", referencedColumnName = "roomid")
	private Room room;
	
	@Column(name = "checkindate")
	private Timestamp checkInDate;
	
	@Column(name = "checkoutdate")
	private Timestamp checkOutDate;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id", referencedColumnName = "id")
	private User user;
	
	public Reservation() {
	
	}
	
}
