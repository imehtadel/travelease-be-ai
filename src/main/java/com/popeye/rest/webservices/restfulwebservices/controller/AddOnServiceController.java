package com.popeye.rest.webservices.restfulwebservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.popeye.rest.webservices.restfulwebservices.model.AddOnService;
import com.popeye.rest.webservices.restfulwebservices.service.AddOnServiceService;

@RestController
public class AddOnServiceController {

	@Autowired
	AddOnServiceService addOnServiceService;
	
	@GetMapping("/jpa/hotel/{hotelId}/services")
	public List<AddOnService> getAddOnServices(@PathVariable Long hotelId){
		return addOnServiceService.getByHotelId(hotelId);
	}
}
