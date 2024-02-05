package com.popeye.rest.webservices.restfulwebservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.popeye.rest.webservices.restfulwebservices.model.Hotel;
import com.popeye.rest.webservices.restfulwebservices.model.RefinedDetails;
import com.popeye.rest.webservices.restfulwebservices.service.HotelService;

@RestController
public class HotelController {
	
	@Autowired
	private HotelService hotelService;

	@GetMapping("/jpa/hotels")
	public List<Hotel> getAllHotels(){
		return hotelService.getAll();
	}
	
	@PostMapping("jpa/event/hotels")
	public List<Hotel> getHotels(@RequestBody RefinedDetails details){
		return hotelService.getEventHotels(details);
	}

}
