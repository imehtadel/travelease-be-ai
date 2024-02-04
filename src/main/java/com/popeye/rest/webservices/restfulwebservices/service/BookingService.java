package com.popeye.rest.webservices.restfulwebservices.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.popeye.rest.webservices.restfulwebservices.repository.BookingRepository;
import com.popeye.rest.webservices.restfulwebservices.repository.RoomRepository;
import com.popeye.rest.webservices.restfulwebservices.model.Booking;
import com.popeye.rest.webservices.restfulwebservices.model.Room;

@Service
public class BookingService {

	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	RoomRepository roomRepository;

	
	public Booking saveBooking(Booking booking) {

		Booking savedBooking = bookingRepository.save(booking);
		List<String> roomIds = Arrays.asList(booking.getRoomIds().split(","));
		for(String roomId : roomIds) {
			Optional<Room> existingRoom = roomRepository.findById(Long.parseLong(roomId));
			Room room = existingRoom.orElse(null);
			if (room != null) {
				String existingRoomBookingId = room.getBookingIds();
				if(existingRoomBookingId.equals("0")) {
					//TODO: No room availability check till now as we'll have to get all bookings for all the rooms.
					room.setBookingIds(booking.getId().toString());
				}else {
					room.setBookingIds(existingRoomBookingId + "," + booking.getId());
				}
				roomRepository.save(room);
			} else {
				// The room does not exist, do something else
			}

		}
		return savedBooking;
	}
	
}
