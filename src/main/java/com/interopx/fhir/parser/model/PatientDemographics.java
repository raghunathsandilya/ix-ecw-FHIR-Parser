package com.interopx.fhir.parser.model;

import java.util.ArrayList;
import java.util.Date;


public class PatientDemographics {
	
	private String patientId;
	private ArrayList<NameElement> patientNames;
	private ArrayList<IdentifierElement> identifiers;
	private String dob;
	private Date dateOfDeath;
	private ArrayList<Address> addresses;
	private ArrayList<PatientLanguage> languageCommunication;
	private CodeElement raceCode;
	private CodeElement ethnicity;
	private CodeElement gender;
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
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
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
	public ArrayList<PatientLanguage> getLanguageCommunication() {
		return languageCommunication;
	}
	public void setLanguageCommunication(ArrayList<PatientLanguage> languageCommunication) {
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

}
