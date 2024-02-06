package com.popeye.rest.webservices.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.popeye.rest.webservices.restfulwebservices.model.Hotel;

import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>{
    public Optional<Hotel> findById(Long hotelId);
}
