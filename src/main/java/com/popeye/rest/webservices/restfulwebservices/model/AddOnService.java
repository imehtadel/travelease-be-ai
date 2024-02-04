package com.popeye.rest.webservices.restfulwebservices.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ADD_ON_SERVICE")
public class AddOnService {

	@Id
    @Column(name = "SERVICE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    @Column(name = "HOTEL_ID")
    private Long hotelId;
    
    @Column(name = "SERVICE_NAME")
    private String serviceName;
    
    @Column(name = "SERVICE_DESC")
    private String serviceDesc;
    
    @Column(name ="SERVICE_CATEGORY")
    private Integer serviceCategory;
    
    @Column(name = "IS_THIRD_PARTY")
    private Integer isthirdParty;
    
    @Column(name = "SERVICE_PRICE")
    private Double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceDesc() {
		return serviceDesc;
	}

	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}

	public Integer getServiceCategory() {
		return serviceCategory;
	}

	public void setServiceCategory(Integer serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	public Integer getIsthirdParty() {
		return isthirdParty;
	}

	public void setIsthirdParty(Integer isthirdParty) {
		this.isthirdParty = isthirdParty;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
