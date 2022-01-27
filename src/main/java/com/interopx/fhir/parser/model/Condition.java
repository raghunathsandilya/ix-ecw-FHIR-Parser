package com.interopx.fhir.parser.model;

import java.util.ArrayList;
import java.util.Date;

public class Condition {
	
	private String conditionId;
	private ArrayList<IdentifierElement> identifiers;
	private CodeElement clinicalStatus;
	private CodeElement verificationStatus;
	private ArrayList<CodeElement> conditionCategory;
	private CodeElement severity;
	private CodeElement conditionCode;
	private ArrayList<CodeElement> conditionBodySite;
	private ReferenceElement patient;
	private ReferenceElement encounter;
	private Date onsetDateTime;
	private Date abatementDateTime;
	private Date recordedDate;
	private String recorderId;
	private CodeElement conditionStageSummary;
	private ArrayList<ReferenceElement> conditionStageAssessment;
	private CodeElement conditionStageType;
	private CodeElement conditionEvidenceCode;
	private ArrayList<ReferenceElement> conditionEvidenceDetails;
	private String conditionNotes;
	
	public String getConditionId() {
		return conditionId;
	}
	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
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
	public ArrayList<CodeElement> getConditionCategory() {
		return conditionCategory;
	}
	public void setConditionCategory(ArrayList<CodeElement> conditionCategory) {
		this.conditionCategory = conditionCategory;
	}
	public CodeElement getSeverity() {
		return severity;
	}
	public void setSeverity(CodeElement severity) {
		this.severity = severity;
	}
	public CodeElement getConditionCode() {
		return conditionCode;
	}
	public void setConditionCode(CodeElement conditionCode) {
		this.conditionCode = conditionCode;
	}
	public ArrayList<CodeElement> getConditionBodySite() {
		return conditionBodySite;
	}
	public void setConditionBodySite(ArrayList<CodeElement> conditionBodySite) {
		this.conditionBodySite = conditionBodySite;
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
	public Date getOnsetDateTime() {
		return onsetDateTime;
	}
	public void setOnsetDateTime(Date onsetDateTime) {
		this.onsetDateTime = onsetDateTime;
	}
	public Date getAbatementDateTime() {
		return abatementDateTime;
	}
	public void setAbatementDateTime(Date abatementDateTime) {
		this.abatementDateTime = abatementDateTime;
	}
	public Date getRecordedDate() {
		return recordedDate;
	}
	public void setRecordedDate(Date recordedDate) {
		this.recordedDate = recordedDate;
	}
	public String getRecorderId() {
		return recorderId;
	}
	public void setRecorderId(String recorderId) {
		this.recorderId = recorderId;
	}
	public CodeElement getConditionStageSummary() {
		return conditionStageSummary;
	}
	public void setConditionStageSummary(CodeElement conditionStageSummary) {
		this.conditionStageSummary = conditionStageSummary;
	}
	public ArrayList<ReferenceElement> getConditionStageAssessment() {
		return conditionStageAssessment;
	}
	public void setConditionStageAssessment(ArrayList<ReferenceElement> conditionStageAssessment) {
		this.conditionStageAssessment = conditionStageAssessment;
	}
	public CodeElement getConditionStageType() {
		return conditionStageType;
	}
	public void setConditionStageType(CodeElement conditionStageType) {
		this.conditionStageType = conditionStageType;
	}
	public CodeElement getConditionEvidenceCode() {
		return conditionEvidenceCode;
	}
	public void setConditionEvidenceCode(CodeElement conditionEvidenceCode) {
		this.conditionEvidenceCode = conditionEvidenceCode;
	}
	public ArrayList<ReferenceElement> getConditionEvidenceDetails() {
		return conditionEvidenceDetails;
	}
	public void setConditionEvidenceDetails(ArrayList<ReferenceElement> conditionEvidenceDetails) {
		this.conditionEvidenceDetails = conditionEvidenceDetails;
	}
	public String getConditionNotes() {
		return conditionNotes;
	}
	public void setConditionNotes(String conditionNotes) {
		this.conditionNotes = conditionNotes;
	}

}
