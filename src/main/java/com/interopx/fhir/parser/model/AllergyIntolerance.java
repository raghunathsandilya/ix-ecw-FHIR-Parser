package com.interopx.fhir.parser.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The List of Allergies and Intolerances associated with the Patient
 * 
 * @author nbashyam
 *
 */
public class AllergyIntolerance {
	
	/** The clinically relevant status of the allergy */
	public enum AllergyClinicalStatus { active, inactive, resolved }
	
	/** The status of the allergy from a chart perspective */
	public enum AllergyVerificationStatus { unconfirmed, confirmed, refuted, entered_in_error }
	
	/** The Category for the Allergy */
	public enum AllergyCategory { food, medication, environment, biologic} 
	
	/** The criticality of the allergy */
	public enum AllergyCriticality { low, high, unable_to_assess }
	
	/** The id of the allergy in the system from where the data is retrieved */
	private String allergyId;
	
	/** The Meta data such as versionId, resourceUrl, lastModifiedTimeStamp */
	private MetaData meta;
	
	/** List of identifiers in the source system */
	private ArrayList<IdentifierElement> identifiers;
	
	/** The clinical status of the allergy */
	private AllergyClinicalStatus clinicalStatus;
	
	/** The verification status of the allergy */
	private AllergyVerificationStatus verificationStatus;
	
	/** The category of the Allergy */
	private AllergyCategory allergyCategory;
	
	/** The AllergyCriticality of the allergy */
	private AllergyCriticality allergyCriticality;
	
	/** The substance code for the allergy */
	private CodeElement allergyCode; 
	
	/** The encounter where the allergy was asserted/recorded */
	private String encounterId;
	
	/** The onset date for the allergy */
	private Date onSetDateTime;
	
	/** The recorded date for the allergy in the chart */
	private Date recordedDateTime;
	
	/** The last occurence date for the allergy */
	private Date lastOccurenceDateTime;
	
	/** The practitioner recording the allergy */
	private Practitioner recorder;
	
	/** The practitioner asserting the allergy */
	private Practitioner asserter;
	
	/** Allergy Reactions */ 
	private ArrayList<AllergyReaction> reactions;
	
	/** Notes associated with the allergy entry */
	private String notes;
	
	
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
	
	public CodeElement getAllergyCode() {
		return allergyCode;
	}
	public void setAllergyCode(CodeElement allergyCode) {
		this.allergyCode = allergyCode;
	}
	
	public Date getOnSetDateTime() {
		return onSetDateTime;
	}
	public void setOnSetDateTime(Date onSetDateTime) {
		this.onSetDateTime = onSetDateTime;
	}
	
	public Date getLastOccurenceDateTime() {
		return lastOccurenceDateTime;
	}
	public void setLastOccurenceDateTime(Date lastOccurenceDateTime) {
		this.lastOccurenceDateTime = lastOccurenceDateTime;
	}
	public MetaData getMeta() {
		return meta;
	}
	public void setMeta(MetaData meta) {
		this.meta = meta;
	}
	public AllergyClinicalStatus getClinicalStatus() {
		return clinicalStatus;
	}
	public void setClinicalStatus(AllergyClinicalStatus clinicalStatus) {
		this.clinicalStatus = clinicalStatus;
	}
	public AllergyVerificationStatus getVerificationStatus() {
		return verificationStatus;
	}
	public void setVerificationStatus(AllergyVerificationStatus verificationStatus) {
		this.verificationStatus = verificationStatus;
	}
	public AllergyCategory getAllergyCategory() {
		return allergyCategory;
	}
	public void setAllergyCategory(AllergyCategory allergyCategory) {
		this.allergyCategory = allergyCategory;
	}
	public AllergyCriticality getAllergyCriticality() {
		return allergyCriticality;
	}
	public void setAllergyCriticality(AllergyCriticality allergyCriticality) {
		this.allergyCriticality = allergyCriticality;
	}
	public String getEncounterId() {
		return encounterId;
	}
	public void setEncounterId(String encounterId) {
		this.encounterId = encounterId;
	}
	public Date getRecordedDateTime() {
		return recordedDateTime;
	}
	public void setRecordedDateTime(Date recordedDateTime) {
		this.recordedDateTime = recordedDateTime;
	}
	public Practitioner getRecorder() {
		return recorder;
	}
	public void setRecorder(Practitioner recorder) {
		this.recorder = recorder;
	}
	public Practitioner getAsserter() {
		return asserter;
	}
	public void setAsserter(Practitioner asserter) {
		this.asserter = asserter;
	}
	public ArrayList<AllergyReaction> getReactions() {
		return reactions;
	}
	public void setReactions(ArrayList<AllergyReaction> reactions) {
		this.reactions = reactions;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	

}
