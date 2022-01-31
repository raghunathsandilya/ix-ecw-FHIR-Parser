package com.interopx.fhir.parser.model;

import java.util.ArrayList;

/**
 * The class for representing Organizations in healthcare.
 * 
 * @author nbashyam
 *
 */
public class Organization {

	/** The id of the Organization in the system from where the data is retrieved */
	private String organizationId;
	
	/** The Meta data such as versionId, resourceUrl, lastModifiedTimeStamp */
	private MetaData meta;

	/** List of identifiers in the source system */
	private ArrayList<IdentifierElement> identifiers;
	
	/** The Organization Status */
	private Boolean	active;
	
	/** The physical address of the Organization */
	private ArrayList<Address> address;
	
	/** The Contact Info for the Organization */
	private ArrayList<Telecom>  telecom;
	
	/** The name of the Organization */
	private String name;

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
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
