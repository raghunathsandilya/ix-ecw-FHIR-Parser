package com.ecw.fhir.parser.model;

import java.util.ArrayList;

public class Patient {
	
	private PatientDemographics patientDemographics;
	private ArrayList<AllergyIntolerance> allergyIntolerance;
	private ArrayList<Condition> condition;
	private ArrayList<Encounter> encounters;
	private ArrayList<MedicationRequest> medicationRequests;

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
		return condition;
	}

	public void setCondition(ArrayList<Condition> condition) {
		this.condition = condition;
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


}
