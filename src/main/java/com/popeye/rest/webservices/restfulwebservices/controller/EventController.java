package com.popeye.rest.webservices.restfulwebservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.popeye.rest.webservices.restfulwebservices.model.Event;
import com.popeye.rest.webservices.restfulwebservices.service.EventService;

@RestController
public class EventController {
	
	@Autowired
	private EventService eventService;

	
	@GetMapping("/jpa/events")
	public List<Event> getAllEvents(){
		return eventService.getAll();
	}

}
