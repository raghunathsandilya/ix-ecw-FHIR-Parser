package com.interopx.fhir.parser.model;

import java.util.ArrayList;


public class Location {

	private String	locationCode;
	private ArrayList<Address> address;
	private ArrayList<DataElement>  telecom;
	private String name;
	
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	public ArrayList<Address> getAddress() {
		return address;
	}
	public void setAddress(ArrayList<Address> address) {
		this.address = address;
	}
	public ArrayList<DataElement> getTelecom() {
		return telecom;
	}
	public void setTelecom(ArrayList<DataElement> telecom) {
		this.telecom = telecom;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
