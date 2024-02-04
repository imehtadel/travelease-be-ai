package com.popeye.rest.webservices.restfulwebservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.popeye.rest.webservices.restfulwebservices.model.EventHotelMapping;


@Repository
public interface EventHotelMappingRepository extends JpaRepository<EventHotelMapping, Long> {

	public List<EventHotelMapping> findByEventZipCode(String eventZipCode);
}
