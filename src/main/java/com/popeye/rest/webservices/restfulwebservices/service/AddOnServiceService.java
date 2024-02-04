package com.popeye.rest.webservices.restfulwebservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.popeye.rest.webservices.restfulwebservices.repository.AddOnServiceRepository;
import com.popeye.rest.webservices.restfulwebservices.model.AddOnService;

@Service
public class AddOnServiceService {
	
	@Autowired
	private AddOnServiceRepository addOnServiceRepository;

	public List<AddOnService> getByHotelId(Long hotelId){
		return addOnServiceRepository.findByHotelId(hotelId);
	}
}
