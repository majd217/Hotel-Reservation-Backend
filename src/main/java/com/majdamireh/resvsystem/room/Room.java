package com.majdamireh.resvsystem.room;


import com.majdamireh.resvsystem.hotel.Hotel;
import jakarta.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {
	
	public int getRoomid() {
		return roomid;
	}
	
	public int getRoomSize() {
		return roomSize;
	}
	
	public Hotel getHotel() {
		return hotel;
	}
	
	@SequenceGenerator(
			name = "rooms_id_seq",
			sequenceName = "rooms_id_seq",
			allocationSize = 1
	)
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "rooms_id_seq"
	)
	@Column(name = "roomid")
	private int roomid;
	
	@Column(name = "roomsize")
	private int roomSize;
	
	@ManyToOne
	@JoinColumn(name = "hotelid", referencedColumnName = "hotelid")
	private Hotel hotel;
}
