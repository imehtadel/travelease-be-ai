package com.popeye.rest.webservices.restfulwebservices.model;

import java.util.List;

public class RefinedDetails {
	
	private List<EventDetail> eventDetails; 
	private Double budget;
	private Integer people;
	
	public List<EventDetail> getEventDetails() {
		return eventDetails;
	}
	public void setEventDetails(List<EventDetail> eventDetails) {
		this.eventDetails = eventDetails;
	}
	public Double getBudget() {
		return budget;
	}
	public void setBudget(Double budget) {
		this.budget = budget;
	}
	public Integer getPeople() {
		return people;
	}
	public void setPeople(Integer people) {
		this.people = people;
	}
}
