package com.popeye.rest.webservices.restfulwebservices.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.popeye.rest.webservices.restfulwebservices.repository.EventRepository;
import com.popeye.rest.webservices.restfulwebservices.model.Event;

@Service
public class EventService {
	
	@Autowired
	private EventRepository eventRepository;

	public List<Event> getAll(){
		List<Event> events = eventRepository.findAll();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		events.sort(Comparator.comparing(event -> LocalDate.parse(event.getDate(), formatter)));

		return events;
	}
}
