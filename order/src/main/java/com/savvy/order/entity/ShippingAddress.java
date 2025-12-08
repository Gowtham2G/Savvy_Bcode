package com.savvy.order.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class ShippingAddress {

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getStreet() {
		return Street;
	}
	public void setStreet(String street) {
		Street = street;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	
	private String Name;
	private String Street;
	private String City;
	private String state;
	private String postalCode;
	private String country;
	private String phoneNumber;
	
}
