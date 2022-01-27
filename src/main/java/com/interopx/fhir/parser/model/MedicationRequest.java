package com.interopx.fhir.parser.model;

import java.util.Date;

public class MedicationRequest {
	
	private String medicationRequestId;
	private String status;
	private String intent;
	private CodeElement medication;
	private ReferenceElement patient;
	private Date authoredOnDateTime;
	private ReferenceElement requester;
	
	public String getMedicationRequestId() {
		return medicationRequestId;
	}
	public void setMedicationRequestId(String medicationRequestId) {
		this.medicationRequestId = medicationRequestId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIntent() {
		return intent;
	}
	public void setIntent(String intent) {
		this.intent = intent;
	}
	public CodeElement getMedication() {
		return medication;
	}
	public void setMedication(CodeElement medication) {
		this.medication = medication;
	}
	public ReferenceElement getPatient() {
		return patient;
	}
	public void setPatient(ReferenceElement patient) {
		this.patient = patient;
	}
	public Date getAuthoredOnDateTime() {
		return authoredOnDateTime;
	}
	public void setAuthoredOnDateTime(Date authoredOnDateTime) {
		this.authoredOnDateTime = authoredOnDateTime;
	}
	public ReferenceElement getRequester() {
		return requester;
	}
	public void setRequester(ReferenceElement requester) {
		this.requester = requester;
	}

}
