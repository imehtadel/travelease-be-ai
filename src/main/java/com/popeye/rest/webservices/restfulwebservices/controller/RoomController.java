package com.popeye.rest.webservices.restfulwebservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.popeye.rest.webservices.restfulwebservices.model.AvailableRoom;
import com.popeye.rest.webservices.restfulwebservices.service.RoomService;

@RestController
public class RoomController {

	@Autowired
	private RoomService roomService;

	@GetMapping("/jpa/hotel/{hotelId}/rooms")
	public List<AvailableRoom> getavailableRooms(@PathVariable Long hotelId, @RequestParam("checkInDate") String checkInDate, @RequestParam("checkOutDate") String checkoutDate){
		return roomService.getAvailableRooms(hotelId, checkInDate, checkoutDate, 120000.00);
	}

}
