package com.popeye.rest.webservices.restfulwebservices.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ROOM")
public class Room {

	@Id
    @Column(name = "ROOM_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    @Column(name = "HOTEL_ID")
    private Long hotelId;

    @Column(name = "TYPE_ID")
    private Long typeId;
    
    @Column(name = "BOOKING_IDs")
    private String bookingIds;
    
    @Column(name = "ROOM_PRICE")
    private Double roomPrice;
    
	public Double getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(Double roomPrice) {
		this.roomPrice = roomPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getBookingIds() {
		return bookingIds;
	}

	public void setBookingIds(String bookingIds) {
		this.bookingIds = bookingIds;
	}
}