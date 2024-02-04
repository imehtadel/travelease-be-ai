package com.popeye.rest.webservices.restfulwebservices.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.popeye.rest.webservices.restfulwebservices.repository.EventHotelMappingRepository;
import com.popeye.rest.webservices.restfulwebservices.repository.HotelRepository;
import com.popeye.rest.webservices.restfulwebservices.model.EventHotelMapping;
import com.popeye.rest.webservices.restfulwebservices.model.Hotel;

@Service
public class HotelService {

	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private EventHotelMappingRepository eventHotelMappingRepository;

	public List<Hotel> getAll(){
		return hotelRepository.findAll();
	}
	
	private List<EventHotelMapping> getEventHotelMappings(String eventZipCode){
		return eventHotelMappingRepository.findByEventZipCode(eventZipCode);
	}
	
	public List<Hotel> getEventHotels(String eventZipCode){
		List<Hotel> hotels = getAll();
		List<EventHotelMapping> eventHotelMappings = getEventHotelMappings(eventZipCode);
		List<Hotel> eventHotels = new ArrayList<Hotel>();
		for(Hotel hotel : hotels) {
			for (EventHotelMapping eventHotelMapping : eventHotelMappings) {
				if(eventHotelMapping.getHotelZipCode().equals(hotel.getZipCode())) {
					eventHotels.add(hotel);
				}
			}
		}
		return eventHotels;
	}
}
