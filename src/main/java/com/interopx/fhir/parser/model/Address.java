package com.interopx.fhir.parser.model;

import java.sql.Date;

/**
 * The Address data to be used across different resources such as Practitioner, Patient, Organization etc.
 * 
 * @author nbashyam
 *
 */
public class Address {
	
	public enum PostalAddressUse { home, work, temp, old, billing } 
	
	/** The first street address line for the entity */
	private String addressLine1;
	
	/** The second street address line for the entity */
	private String addressLine2;
	
	/** The third street address line for the entity */
	private String addressLine3;
	
	/** The city for the address */
	private String city;
	
	/** The state for the address */
	private String state;
	
	/** The zip code for the address */
	private String postalCode;
	
	/** The Country code for the address */
	private String country;
	
	/** The postal address use */
	private PostalAddressUse postalAddressUse;
	
	/** The name was active from date */
	private Date       startDate;
	
	/** The name was officially changed from date, and hence it is an old/previous name */
	private Date       endDate; 
	
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
	public PostalAddressUse getPostalAddressUse() {
		return postalAddressUse;
	}
	public void setPostalAddressUse(PostalAddressUse postalAddressUse) {
		this.postalAddressUse = postalAddressUse;
	}
	public String getAddressLine3() {
		return addressLine3;
	}
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
