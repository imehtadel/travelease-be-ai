package com.popeye.rest.webservices.restfulwebservices.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.popeye.rest.webservices.restfulwebservices.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{

	public Optional<Room> findById(Long id);
	public List<Room> findByHotelId(Long hotelId);
}
