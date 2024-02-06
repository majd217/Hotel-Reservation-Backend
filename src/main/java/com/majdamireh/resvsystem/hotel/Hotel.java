package com.majdamireh.resvsystem.hotel;

import jakarta.persistence.*;

@Entity
@Table(name = "hotels")
public class Hotel {
	@SequenceGenerator(
			name = "hotels_hotelid_seq",
			sequenceName = "hotels_hotelid_seq",
			allocationSize = 1
	)
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "hotels_hotelid_seq"
	)
	@Column(name = "hotelid")
	private int hotelID;
	
	@Column(name = "hotelname")
	
	private String hotelName;
	
	protected Hotel() {
	}
	
	public Hotel(int hotelID, String hotelName) {
		this.hotelID = hotelID;
		this.hotelName = hotelName;
	}
	
	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
	}
	
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	
	public int getHotelID() {
		return hotelID;
	}
	
	public String getHotelName() {
		return hotelName;
	}
	
	@Override
	public String toString() {
		return "Hotel{" +
				"hotelID=" + hotelID +
				", hotelName='" + hotelName + '\'' +
				'}';
	}
}
