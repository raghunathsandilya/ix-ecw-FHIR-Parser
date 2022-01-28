package com.interopx.fhir.parser.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AllergyIntolerance {
	
	private String allergyId;
	private ArrayList<IdentifierElement> identifiers;
	private CodeElement clinicalStatus;
	private CodeElement verificationStatus;
	private String allergyType;
	private List<String> allergyCategory;
	private String allergyCriticality;
	private CodeElement allergyCode;
	private ReferenceElement patient;
	private ReferenceElement encounter;
	private Date onSetDateTime;
	private ReferenceElement recorderId;
	private Date lastOccurenceDateTime;
	private ArrayList<CodeElement> allergyReactionSubstance;
	private ArrayList<CodeElement> allergyReactionManifestation;
	private Date allergyReactionOnsetDateTime;
	private String allergyNotes;
	
	
	public String getAllergyId() {
		return allergyId;
	}
	public void setAllergyId(String allergyId) {
		this.allergyId = allergyId;
	}
	public ArrayList<IdentifierElement> getIdentifiers() {
		return identifiers;
	}
	public void setIdentifiers(ArrayList<IdentifierElement> identifiers) {
		this.identifiers = identifiers;
	}
	public CodeElement getClinicalStatus() {
		return clinicalStatus;
	}
	public void setClinicalStatus(CodeElement clinicalStatus) {
		this.clinicalStatus = clinicalStatus;
	}
	public CodeElement getVerificationStatus() {
		return verificationStatus;
	}
	public void setVerificationStatus(CodeElement verificationStatus) {
		this.verificationStatus = verificationStatus;
	}
	public String getAllergyType() {
		return allergyType;
	}
	public void setAllergyType(String allergyType) {
		this.allergyType = allergyType;
	}
	public List<String> getAllergyCategory() {
		return allergyCategory;
	}
	public void setAllergyCategory(List<String> allergyCategory) {
		this.allergyCategory = allergyCategory;
	}
	public String getAllergyCriticality() {
		return allergyCriticality;
	}
	public void setAllergyCriticality(String allergyCriticality) {
		this.allergyCriticality = allergyCriticality;
	}
	public CodeElement getAllergyCode() {
		return allergyCode;
	}
	public void setAllergyCode(CodeElement allergyCode) {
		this.allergyCode = allergyCode;
	}
	public ReferenceElement getPatient() {
		return patient;
	}
	public void setPatient(ReferenceElement patient) {
		this.patient = patient;
	}
	public ReferenceElement getEncounter() {
		return encounter;
	}
	public void setEncounter(ReferenceElement encounter) {
		this.encounter = encounter;
	}
	public Date getOnSetDateTime() {
		return onSetDateTime;
	}
	public void setOnSetDateTime(Date onSetDateTime) {
		this.onSetDateTime = onSetDateTime;
	}
	public ReferenceElement getRecorderId() {
		return recorderId;
	}
	public void setRecorderId(ReferenceElement recorderId) {
		this.recorderId = recorderId;
	}
	public Date getLastOccurenceDateTime() {
		return lastOccurenceDateTime;
	}
	public void setLastOccurenceDateTime(Date lastOccurenceDateTime) {
		this.lastOccurenceDateTime = lastOccurenceDateTime;
	}
	public ArrayList<CodeElement> getAllergyReactionSubstance() {
		return allergyReactionSubstance;
	}
	public void setAllergyReactionSubstance(ArrayList<CodeElement> allergyReactionSubstance) {
		this.allergyReactionSubstance = allergyReactionSubstance;
	}
	public ArrayList<CodeElement> getAllergyReactionManifestation() {
		return allergyReactionManifestation;
	}
	public void setAllergyReactionManifestation(ArrayList<CodeElement> allergyReactionManifestation) {
		this.allergyReactionManifestation = allergyReactionManifestation;
	}
	public Date getAllergyReactionOnsetDateTime() {
		return allergyReactionOnsetDateTime;
	}
	public void setAllergyReactionOnsetDateTime(Date allergyReactionOnsetDateTime) {
		this.allergyReactionOnsetDateTime = allergyReactionOnsetDateTime;
	}
	public String getAllergyNotes() {
		return allergyNotes;
	}
	public void setAllergyNotes(String allergyNotes) {
		this.allergyNotes = allergyNotes;
	}

}
