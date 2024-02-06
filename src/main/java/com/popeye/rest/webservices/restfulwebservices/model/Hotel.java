package com.popeye.rest.webservices.restfulwebservices.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "HOTEL")
public class Hotel {

	@Id
    @Column(name = "HOTEL_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    @Column(name = "HOTEL_NAME", length = 50)
    private String name;

    @Column(name = "HOTEL_COUNTRY", length = 20)
    private String country;
    
    @Column(name = "HOTEL_STATE", length = 20)
    private String state;

    @Column(name = "CHECK_IN_TIME", length = 10)
    private String checkInTime;
    
    @Column(name = "CHECK_OUT_TIME", length = 10)
    private String checkOutTime;
    
    @Column(name = "HOTEL_ZIP_CODE", length = 10)
    private String zipCode;

    @Column(name = "HOTEL_DISTANCE")
    private Double distance;
    
    @Transient
    private List<AvailableRoom> availableRooms;

	@Transient
	private String eventZipCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}

	public String getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(String checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public List<AvailableRoom> getAvailableRooms() {
		return availableRooms;
	}

	public void setAvailableRooms(List<AvailableRoom> availableRooms) {
		this.availableRooms = availableRooms;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public String getEventZipCode() {
		return eventZipCode;
	}

	public void setEventZipCode(String eventZipCode) {
		this.eventZipCode = eventZipCode;
	}
}
