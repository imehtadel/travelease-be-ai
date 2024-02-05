package com.popeye.rest.webservices.restfulwebservices.model.request;

import java.util.List;

public class ChatRequest {

    private String message;
    List<Integer> eventIds;
    List<HotelInfo> hotelInfo;
    private Integer noOfPerson;
    private Integer count;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Integer> getEventIds() {
        return eventIds;
    }

    public void setEventIds(List<Integer> eventIds) {
        this.eventIds = eventIds;
    }

    public List<HotelInfo> getHotelInfo() {
        return hotelInfo;
    }

    public void setHotelInfo(List<HotelInfo> hotelInfo) {
        this.hotelInfo = hotelInfo;
    }

    public Integer getNoOfPerson() {
        return noOfPerson;
    }

    public void setNoOfPerson(Integer noOfPerson) {
        this.noOfPerson = noOfPerson;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
