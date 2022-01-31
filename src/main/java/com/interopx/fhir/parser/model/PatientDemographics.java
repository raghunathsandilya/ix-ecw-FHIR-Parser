package com.interopx.fhir.parser.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * This POJO contains all the demographic details of a patient such as DateOfBirth, Address, Names etc.
 *  
 * @author nbashyam
 *
 */
public class PatientDemographics {
	
	/** The unique id for the patient in the system from where data was retrieved */
	private String patientId;
	
	/** The Full URL containing the server from where the data was retrieved and id of the resoucer */
	private String resourceUrl;
	
	/** The list of names being used by the patient, it is an array to capture previous names */
	private ArrayList<NameElement> patientNames;
	
	/** The list of identifiers associated with the patient in the system from where the data was retrieved */
	private ArrayList<IdentifierElement> identifiers;
	
	/** The Date of Birth for the Patient */
	private Date dob;
	
	/** The Date of Death for a deceased patient */
	private Date dateOfDeath;
	
	/** The list of addresses associated with the Patient */
	private ArrayList<Address> addresses;
	
	/** The list of languages that the Patient uses for communication */
	private ArrayList<CodeElement> languageCommunication;
	
	/** The Race Category for the Patient */
	private CodeElement raceCategory;
	
	/** The list of detailed race codes associated with the Patient within the raceCategory */
	private ArrayList<CodeElement> detailedRaceCodes;
	
	/** The Ethnicity Category of the Patient */
	private CodeElement ethnicity;
	
	/** The Administrative Gender for the Patient */
	private CodeElement gender;
	
	/** The Birth Sex for the Patient */
	private CodeElement birthSex;
	
	/** The Marital Status for the Patient */
	private CodeElement maritalStatus;
	
	/** The Religious Affiliation of the Patient */
	private CodeElement religiousAffiliationCode;
	
	/** The list of contact information including email, telephone, fax etc */
	private ArrayList<Telecom> telecoms;
	
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public ArrayList<NameElement> getPatientNames() {
		return patientNames;
	}
	public void setPatientNames(ArrayList<NameElement> patientNames) {
		this.patientNames = patientNames;
	}
	public ArrayList<IdentifierElement> getIdentifiers() {
		return identifiers;
	}
	public void setIdentifiers(ArrayList<IdentifierElement> identifiers) {
		this.identifiers = identifiers;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Date getDateOfDeath() {
		return dateOfDeath;
	}
	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}
	public ArrayList<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(ArrayList<Address> addresses) {
		this.addresses = addresses;
	}
	public ArrayList<CodeElement> getLanguageCommunication() {
		return languageCommunication;
	}
	public void setLanguageCommunication(ArrayList<CodeElement> languageCommunication) {
		this.languageCommunication = languageCommunication;
	}
	public CodeElement getRaceCategory() {
		return raceCategory;
	}
	public void setRaceCategory(CodeElement raceCode) {
		this.raceCategory = raceCode;
	}
	public CodeElement getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(CodeElement ethnicity) {
		this.ethnicity = ethnicity;
	}
	public CodeElement getGender() {
		return gender;
	}
	public void setGender(CodeElement gender) {
		this.gender = gender;
	}
	public CodeElement getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(CodeElement maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public CodeElement getReligiousAffiliationCode() {
		return religiousAffiliationCode;
	}
	public void setReligiousAffiliationCode(CodeElement religiousAffiliationCode) {
		this.religiousAffiliationCode = religiousAffiliationCode;
	}
	public String getResourceUrl() {
		return resourceUrl;
	}
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	public ArrayList<CodeElement> getDetailedRaceCodes() {
		return detailedRaceCodes;
	}
	public void setDetailedRaceCodes(ArrayList<CodeElement> detailedRaceCodes) {
		this.detailedRaceCodes = detailedRaceCodes;
	}
	public CodeElement getBirthSex() {
		return birthSex;
	}
	public void setBirthSex(CodeElement birthSex) {
		this.birthSex = birthSex;
	}
	public ArrayList<Telecom> getTelecoms() {
		return telecoms;
	}
	public void setTelecoms(ArrayList<Telecom> telecoms) {
		this.telecoms = telecoms;
	}
	
	

}
