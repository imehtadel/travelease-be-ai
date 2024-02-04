package com.popeye.rest.webservices.restfulwebservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.popeye.rest.webservices.restfulwebservices.model.AddOnService;

@Repository
public interface AddOnServiceRepository extends JpaRepository<AddOnService, Long>{
	public List<AddOnService> findByHotelId(Long hotelId);
}
