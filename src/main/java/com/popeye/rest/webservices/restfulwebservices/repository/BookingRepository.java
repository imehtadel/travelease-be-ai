package com.popeye.rest.webservices.restfulwebservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.popeye.rest.webservices.restfulwebservices.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{
	
	public List<Booking> findByIdAndHotelId(Long bookingId, Long hotelId);
}
