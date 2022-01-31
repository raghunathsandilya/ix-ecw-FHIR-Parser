package com.interopx.fhir.parser.model;

import java.util.ArrayList;

/**
 * The location represents a facility used in healthcare 
 * 
 * @author nbashyam
 *
 */
public class Location {
	
	/** The status of the location */
	public enum LocationStatus { active, suspended, inactive }

	/** The id of the Location in the system from where the data is retrieved */
	private String locationId;
	
	/** The Meta data such as versionId, resourceUrl, lastModifiedTimeStamp */
	private MetaData meta;

	/** List of identifiers in the source system */
	private ArrayList<IdentifierElement> identifiers;
	
	/** The Location Status */
	private LocationStatus	status;
	
	/** The physical address of the location */
	private ArrayList<Address> address;
	
	/** The Contact Info for the location */
	private ArrayList<Telecom>  telecom;
	
	/** The name of the Location */
	private String name;

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public MetaData getMeta() {
		return meta;
	}

	public void setMeta(MetaData meta) {
		this.meta = meta;
	}

	public ArrayList<IdentifierElement> getIdentifiers() {
		return identifiers;
	}

	public void setIdentifiers(ArrayList<IdentifierElement> identifiers) {
		this.identifiers = identifiers;
	}

	public LocationStatus getStatus() {
		return status;
	}

	public void setStatus(LocationStatus status) {
		this.status = status;
	}

	public ArrayList<Address> getAddress() {
		return address;
	}

	public void setAddress(ArrayList<Address> address) {
		this.address = address;
	}

	public ArrayList<Telecom> getTelecom() {
		return telecom;
	}

	public void setTelecom(ArrayList<Telecom> telecom) {
		this.telecom = telecom;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
