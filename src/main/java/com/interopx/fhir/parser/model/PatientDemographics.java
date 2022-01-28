package com.interopx.fhir.parser.model;

import java.util.ArrayList;
import java.util.Date;


public class PatientDemographics {
	
	private String patientId;
	private ArrayList<NameElement> patientNames;
	private ArrayList<IdentifierElement> identifiers;
	private Date dob;
	private Date dateOfDeath;
	private ArrayList<Address> addresses;
	private ArrayList<CodeElement> languageCommunication;
	private CodeElement raceCode;
	private CodeElement ethnicity;
	private String gender;
	private CodeElement maritalStatus;
	private CodeElement religiousAffiliationCode;
	
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
	public CodeElement getRaceCode() {
		return raceCode;
	}
	public void setRaceCode(CodeElement raceCode) {
		this.raceCode = raceCode;
	}
	public CodeElement getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(CodeElement ethnicity) {
		this.ethnicity = ethnicity;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
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

}
