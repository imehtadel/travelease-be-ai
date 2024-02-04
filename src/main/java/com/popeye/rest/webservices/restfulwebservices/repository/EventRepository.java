package com.popeye.rest.webservices.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.popeye.rest.webservices.restfulwebservices.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
