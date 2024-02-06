package com.popeye.rest.webservices.restfulwebservices.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.popeye.rest.webservices.restfulwebservices.repository.EventHotelMappingRepository;
import com.popeye.rest.webservices.restfulwebservices.repository.HotelRepository;
import com.popeye.rest.webservices.restfulwebservices.repository.RoomRepository;
import com.popeye.rest.webservices.restfulwebservices.model.AvailableRoom;
import com.popeye.rest.webservices.restfulwebservices.model.EventDetail;
import com.popeye.rest.webservices.restfulwebservices.model.EventHotelMapping;
import com.popeye.rest.webservices.restfulwebservices.model.Hotel;
import com.popeye.rest.webservices.restfulwebservices.model.RefinedDetails;

@Service
public class HotelService {

	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private EventHotelMappingRepository eventHotelMappingRepository;
	
	@Autowired
	private RoomService roomService;

	public List<Hotel> getAll(){
		return hotelRepository.findAll();
	}
	
	private List<EventHotelMapping> getEventHotelMappings(String eventZipCode){
		return eventHotelMappingRepository.findByEventZipCode(eventZipCode);
	}
	
	public List<Hotel> getEventHotels(RefinedDetails details){
		List<Hotel> hotels = getAll();
		List<EventHotelMapping> eventHotelMappings = new ArrayList<EventHotelMapping>();
		List<EventDetail> eventDetails = details.getEventDetails();
		List<Hotel> eventHotels = new ArrayList<Hotel>();
		for(EventDetail e : eventDetails) {
			eventHotelMappings.addAll(getEventHotelMappings(e.getZipCode()));
		}
		
		for(Hotel hotel : hotels) {
			for (EventHotelMapping eventHotelMapping : eventHotelMappings) {
				List<AvailableRoom> availableRooms = new ArrayList<AvailableRoom>();
				if(eventHotelMapping.getHotelZipCode().equals(hotel.getZipCode())) {
					eventHotels.add(hotel);
					hotel.setEventZipCode(eventHotelMapping.getEventZipCode());
					availableRooms = hotel.getAvailableRooms();
					for(EventDetail e : eventDetails) {
						if(!CollectionUtils.isEmpty(availableRooms)) {
							availableRooms.addAll(roomService.getAvailableRooms(hotel.getId(), e.getCheckInDate(), e.getCheckoutDate(), details.getBudget()));
						}else {
							hotel.setAvailableRooms(roomService.getAvailableRooms(hotel.getId(), e.getCheckInDate(), e.getCheckoutDate(), details.getBudget()));;
						}
						
					}
				}
			}
		}
		return eventHotels;
	}
}
