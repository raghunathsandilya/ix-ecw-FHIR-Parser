package com.interopx.fhir.parser.model;

import java.util.ArrayList;
import java.util.Date;

import com.interopx.fhir.parser.model.Encounter.EncounterStatus;

/**
 * The class is used to represent all types of problems, findings, disorders associated with a Patient 
 * 
 * @author nbashyam
 *
 */
public class Condition {
	
	/** The clinically relevant status of the condition */
	public enum ConditionClinicalStatus { active, recurrence, relapse, inactive, remission, resolved}
	
	/** The status of the condition from a chart perspective */
	public enum ConditionVerificationStatus { unconfirmed, provisional, differential, confirmed, refuted, entered_in_error }
	
	/** The Category for the condition */
	public enum ConditionCategory { problem_list_item, encounter_diagnosis, health_concern, death_diagnosis} 
	
	/** The severity of the condition */
	public enum ConditionSeverity { mild, moderate, severe} 
	
	/** The id of the condition in the system from where the data is retrieved */
	private String conditionId;
	
	/** The Meta data such as versionId, resourceUrl, lastModifiedTimeStamp */
	private MetaData meta;

	/** List of identifiers in the source system */
	private ArrayList<IdentifierElement> identifiers;
	
	/** The status of the condition */ 
	private ConditionClinicalStatus status;
	
	/** The Condition status relevant to the chart */
	private CodeElement verificationStatus;
	
	/** The category of the condition */
	private ArrayList<CodeElement> conditionCategory;
	
	/** Severity of the condition */
	private ConditionSeverity severity;
	
	/** The actual condition */
	private CodeElement conditionCode;
	
	/** The body sites associated with the condition */
	private ArrayList<CodeElement> conditionBodySite;
	
	/** The encounter where the condition was asserted */
	private String encounterId;
	
	/** The onset date time for the condition */
	private Date onsetDateTime;
	
	/** The abatement date for the condition */
	private Date abatementDateTime;
	
	/** The recorded date for the condition */
	private Date recordedDate;
	
	/** The practitioner recording the condition */
	private Practitioner recorderId;
	
	/** The Notes associated with the condition */
	private String notes;

	public String getConditionId() {
		return conditionId;
	}

	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
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

	public ConditionClinicalStatus getStatus() {
		return status;
	}

	public void setStatus(ConditionClinicalStatus status) {
		this.status = status;
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

	public ConditionSeverity getSeverity() {
		return severity;
	}

	public void setSeverity(ConditionSeverity severity) {
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

	public String getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(String encounterId) {
		this.encounterId = encounterId;
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

	public Practitioner getRecorderId() {
		return recorderId;
	}

	public void setRecorderId(Practitioner recorderId) {
		this.recorderId = recorderId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
}
