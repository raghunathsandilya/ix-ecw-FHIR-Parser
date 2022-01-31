package com.interopx.fhir.parser.model;

import java.util.ArrayList;
/**
 * This is the top level POJO containing all the data related to a specific Patient.
 * 
 * @author nbashyam
 * 
 */
public class Patient {
	
	/** The PatientId within the eCW Practice */
	private String ecwPatientId;
	
	/** The PracticeId requesting the data */
	private String ecwPracticeId;
	
	/** The Data Sources from where the data is being retrieved */
	private ArrayList<String> serverUrls;
	
	/** The Data that was retrieved based on the request represented by the RequestId */
	private String requestId; 
	
	// Specific data domains.
	
	/** The Patient Demographics information, Each instance of PatientDemographics is data received from a different data source.
	 * If there is only one data source from where data is retrieved, then there would be only entry of PatientDemographics. 
	 */
	private PatientDemographics patientDemographics;
	
	/** The List of immunizations retrieved from the data sources */
	private ArrayList<Immunization> immunizations;
	
	/** The List of Allergies and Intolerances retrieved from the data sources */
	private ArrayList<AllergyIntolerance> allergyIntolerance;
	
	/** The List of Conditions (Problems, Disorders, Diagnosis) retrieved from the data sources */
	private ArrayList<Condition> conditions;
	
	/** The List of Encounters retrieved from the data sources */
	private ArrayList<Encounter> encounters;
	
	/** The List of MedicationRequests retrieved from the data sources */
	private ArrayList<MedicationRequest> medicationRequests;
	
	/** The list of all practitioners referenced in the various resources */
	private ArrayList<Practitioner>  practitioners;
	
	/** The List of locations where the service was provided */
	private ArrayList<Location>      location;
	
	/** The List of Organizations who provided the services */
	private ArrayList<Organization>  organizations;

	public PatientDemographics getPatientDemographics() {
		return patientDemographics;
	}

	public void setPatientDemographics(PatientDemographics patientDemographics) {
		this.patientDemographics = patientDemographics;
	}

	public ArrayList<AllergyIntolerance> getAllergyIntolerance() {
		return allergyIntolerance;
	}

	public void setAllergyIntolerance(ArrayList<AllergyIntolerance> allergyIntolerance) {
		this.allergyIntolerance = allergyIntolerance;
	}

	public ArrayList<Condition> getCondition() {
		return conditions;
	}

	public void setCondition(ArrayList<Condition> condition) {
		this.conditions = condition;
	}

	public ArrayList<Encounter> getEncounters() {
		return encounters;
	}

	public void setEncounters(ArrayList<Encounter> encounters) {
		this.encounters = encounters;
	}

	public ArrayList<MedicationRequest> getMedicationRequests() {
		return medicationRequests;
	}

	public void setMedicationRequests(ArrayList<MedicationRequest> medicationRequests) {
		this.medicationRequests = medicationRequests;
	}

	public String getEcwPatientId() {
		return ecwPatientId;
	}

	public void setEcwPatientId(String ecwPatientId) {
		this.ecwPatientId = ecwPatientId;
	}

	public String getEcwPracticeId() {
		return ecwPracticeId;
	}

	public void setEcwPracticeId(String ecwPracticeId) {
		this.ecwPracticeId = ecwPracticeId;
	}

	public ArrayList<String> getServerUrls() {
		return serverUrls;
	}

	public void setServerUrls(ArrayList<String> serverUrls) {
		this.serverUrls = serverUrls;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public ArrayList<Practitioner> getPractitioners() {
		return practitioners;
	}

	public void setPractitioners(ArrayList<Practitioner> practitioners) {
		this.practitioners = practitioners;
	}

	public ArrayList<Location> getLocation() {
		return location;
	}

	public void setLocation(ArrayList<Location> location) {
		this.location = location;
	}

	public ArrayList<Organization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(ArrayList<Organization> organizations) {
		this.organizations = organizations;
	}

	public ArrayList<Immunization> getImmunizations() {
		return immunizations;
	}

	public void setImmunizations(ArrayList<Immunization> immunizations) {
		this.immunizations = immunizations;
	}

	

}
