package com.interopx.fhir.parser.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * The class is used to represent Practitioner information.
 * 
 * @author nbashyam
 *
 */
public class Practitioner extends MetaData{

	/** The id of the Practitioner in the system from where the data is retrieved */
	private String practitionerId;
	
	/** The Meta data such as versionId, resourceUrl, lastModifiedTimeStamp */
	private MetaData meta;

	/** List of identifiers in the source system */
	private ArrayList<IdentifierElement> identifiers;
	
	/** The name of the practitioner */
	private ArrayList<NameElement> name;
	
	/** The contact Info for the Practitioner */
	private ArrayList<Telecom>     telecomInfo;
	
	/** The Practitioner's address */
	private ArrayList<Address>     address;
	
	/** The dob of the Practitioner */
	private Date        birthDate; 
	
	/** The communication languages */
	private ArrayList<CodeElement> languages;
	
	/** The organization where the Practitioner is working */
	private String organizationId;
	
	/** The role of the Practitioner */
	private ArrayList<CodeElement> roles;
	
	/** The specialty of the Practitioner */
	private ArrayList<CodeElement> specialty;
	
	/** The locations where the Practitioner is providing services */
	private ArrayList<Location> locations;
	
	/** The electronic endpoints */
	private ArrayList<String>   endpoints;

	public String getPractitionerId() {
		return practitionerId;
	}

	public void setPractitionerId(String practitionerId) {
		this.practitionerId = practitionerId;
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

	public ArrayList<NameElement> getName() {
		return name;
	}

	public void setName(ArrayList<NameElement> name) {
		this.name = name;
	}

	public ArrayList<Address> getAddress() {
		return address;
	}

	public void setAddress(ArrayList<Address> address) {
		this.address = address;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public ArrayList<CodeElement> getLanguages() {
		return languages;
	}

	public void setLanguages(ArrayList<CodeElement> languages) {
		this.languages = languages;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public ArrayList<CodeElement> getRoles() {
		return roles;
	}

	public void setRoles(ArrayList<CodeElement> roles) {
		this.roles = roles;
	}

	public ArrayList<CodeElement> getSpecialty() {
		return specialty;
	}

	public void setSpecialty(ArrayList<CodeElement> specialty) {
		this.specialty = specialty;
	}

	public ArrayList<Location> getLocations() {
		return locations;
	}

	public void setLocations(ArrayList<Location> locations) {
		this.locations = locations;
	}

	public ArrayList<String> getEndpoints() {
		return endpoints;
	}

	public void setEndpoints(ArrayList<String> endpoints) {
		this.endpoints = endpoints;
	}

	public ArrayList<Telecom> getTelecomInfo() {
		return telecomInfo;
	}

	public void setTelecomInfo(ArrayList<Telecom> telecomInfo) {
		this.telecomInfo = telecomInfo;
	}
	
	
	
}
