package com.interopx.fhir.parser.model;


public class Address {
	
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String postalCode;
	private String country;
	private String postalAddressUse;
	private PeriodElement periodOfStay;
	
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public String getPostalAddressUse() {
		return postalAddressUse;
	}
	public void setPostalAddressUse(String postalAddressUse) {
		this.postalAddressUse = postalAddressUse;
	}
	public PeriodElement getPeriodOfStay() {
		return periodOfStay;
	}
	public void setPeriodOfStay(PeriodElement periodOfStay) {
		this.periodOfStay = periodOfStay;
	}

}
