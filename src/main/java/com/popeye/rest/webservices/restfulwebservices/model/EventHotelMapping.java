package com.popeye.rest.webservices.restfulwebservices.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "EVENT_HOTEL_MAPPING")
public class EventHotelMapping {

	@Id
    @Column(name = "EHM_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    @Column(name = "EVENT_ZIP_CODE", length = 10)
    private String eventZipCode;

    @Column(name = "HOTEL_ZIP_CODE", length = 200)
    private String hotelZipCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEventZipCode() {
		return eventZipCode;
	}

	public void setEventZipCode(String eventZipCode) {
		this.eventZipCode = eventZipCode;
	}

	public String getHotelZipCode() {
		return hotelZipCode;
	}

	public void setHotelZipCode(String hotelZipCode) {
		this.hotelZipCode = hotelZipCode;
	}
}
