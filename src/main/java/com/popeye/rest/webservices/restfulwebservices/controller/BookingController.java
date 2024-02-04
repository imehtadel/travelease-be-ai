package com.popeye.rest.webservices.restfulwebservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.popeye.rest.webservices.restfulwebservices.model.Booking;
import com.popeye.rest.webservices.restfulwebservices.service.BookingService;

@RestController
public class BookingController {

	@Autowired
	BookingService bookingService;
	
	@PostMapping("/jpa/hotel/booking")
	public Booking postBook(@RequestBody Booking booking){
		return bookingService.saveBooking(booking);
	}
}
