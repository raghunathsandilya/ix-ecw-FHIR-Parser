package com.interopx.fhir.parser.model;

import java.util.ArrayList;

public class Encounter {
	
	private String encounterId;
	private ArrayList<IdentifierElement> identifiers;
	private String status;
	private CodeElement encounterClass;
	private ArrayList<CodeElement> encounterType;
	private ReferenceElement patient;
	private PeriodElement encounterPeriod;
	private ArrayList<CodeElement> reasonCode;
	private ArrayList<ReferenceElement> serviceDeliveryLocations;
	
	public String getEncounterId() {
		return encounterId;
	}
	public void setEncounterId(String encounterId) {
		this.encounterId = encounterId;
	}
	public ArrayList<IdentifierElement> getIdentifiers() {
		return identifiers;
	}
	public void setIdentifiers(ArrayList<IdentifierElement> identifiers) {
		this.identifiers = identifiers;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public CodeElement getEncounterClass() {
		return encounterClass;
	}
	public void setEncounterClass(CodeElement encounterClass) {
		this.encounterClass = encounterClass;
	}
	public ArrayList<CodeElement> getEncounterType() {
		return encounterType;
	}
	public void setEncounterType(ArrayList<CodeElement> encounterType) {
		this.encounterType = encounterType;
	}
	public ReferenceElement getPatient() {
		return patient;
	}
	public void setPatient(ReferenceElement patient) {
		this.patient = patient;
	}
	public PeriodElement getEncounterPeriod() {
		return encounterPeriod;
	}
	public void setEncounterPeriod(PeriodElement encounterPeriod) {
		this.encounterPeriod = encounterPeriod;
	}
	public ArrayList<CodeElement> getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(ArrayList<CodeElement> reasonCode) {
		this.reasonCode = reasonCode;
	}
	public ArrayList<ReferenceElement> getServiceDeliveryLocations() {
		return serviceDeliveryLocations;
	}
	public void setServiceDeliveryLocations(ArrayList<ReferenceElement> serviceDeliveryLocations) {
		this.serviceDeliveryLocations = serviceDeliveryLocations;
	}

}
