package com.popeye.rest.webservices.restfulwebservices.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.popeye.rest.webservices.restfulwebservices.repository.BookingRepository;
import com.popeye.rest.webservices.restfulwebservices.repository.RoomRepository;
import com.popeye.rest.webservices.restfulwebservices.repository.RoomTypeRepository;
import com.popeye.rest.webservices.restfulwebservices.model.AvailableRoom;
import com.popeye.rest.webservices.restfulwebservices.model.Booking;
import com.popeye.rest.webservices.restfulwebservices.model.Room;
import com.popeye.rest.webservices.restfulwebservices.model.RoomType;

@Service
public class RoomService {
	
	@Autowired 
	private RoomRepository roomRepository;
	
	@Autowired 
	private RoomTypeRepository roomTypeRepository;
	
	@Autowired 
	private BookingRepository bookingRepository;
	
	public List<AvailableRoom> getAvailableRooms(Long hotelId, String checkInDate, String checkOutDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		List<Room> rooms = roomRepository.findAll();
		List<Long> unAvailableRoomIds = new ArrayList<>(); 
		
		List<AvailableRoom> availableRooms = rooms.stream()
        .map(this::getAvailableRoom)
        .collect(Collectors.toList());
		
		LocalDate customerCheckInDate = LocalDate.parse(checkInDate, formatter);
		LocalDate customerCheckOutDate = LocalDate.parse(checkOutDate, formatter);
		
		for(Room room : rooms) {
			List<String> bookingIds = Arrays.asList(room.getBookingIds().split(","));
			for(String bookingId : bookingIds) {
				List<Booking> booking = new ArrayList<>();
				try{
					booking = bookingRepository.findByIdAndHotelId(Long.parseLong(bookingId), hotelId);
				}catch(NumberFormatException ex) {
					System.out.println("Number format exception accured: " + ex.getMessage());
				}
				
				if(!CollectionUtils.isEmpty(booking)) {
					LocalDate existingCheckInDate = LocalDate.parse(booking.get(0).getCheckInDate(), formatter);
					LocalDate existingCheckOutDate = LocalDate.parse(booking.get(0).getCheckOutDate(), formatter);
					
					if(customerCheckInDate.isBefore(customerCheckOutDate) && (!customerCheckInDate.isAfter(existingCheckInDate) ||  !customerCheckOutDate.isBefore(existingCheckOutDate))) {
						unAvailableRoomIds.add(room.getId());
					}
				}
				
			}
		}
		
		availableRooms.removeIf(availableRoom -> unAvailableRoomIds.contains(availableRoom.getRoomId()));
		
		return availableRooms;
		
	}
	
	private AvailableRoom getAvailableRoom(Room room) {
		AvailableRoom availableRoom = new AvailableRoom();
		availableRoom.setRoomId(room.getId());
		Optional<RoomType> roomType = roomTypeRepository.findById(room.getTypeId());
		availableRoom.setRoomName(roomType.map(RoomType::getRoomName).orElse(null));
		availableRoom.setRoomDesc(roomType.map(RoomType::getRoomDesc).orElse(null));
		availableRoom.setPricePerDay(roomType.map(RoomType::getPricePerDay).orElse(null));
		availableRoom.setCapacity(roomType.map(RoomType::getCapacity).orElse(null));
		return availableRoom;
	}
	
}
