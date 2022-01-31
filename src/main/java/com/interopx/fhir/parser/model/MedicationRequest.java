package com.interopx.fhir.parser.model;

import java.util.ArrayList;
import java.util.Date;

import com.interopx.fhir.parser.model.Encounter.EncounterStatus;

/**
 * The class represents the medication request information associated with the patient 
 * 
 * @author nbashyam
 *
 */
public class MedicationRequest {
	
	/** The status of the MedicationRequest/order */
	public enum MedicationRequestStatus { active, on_hold, cancelled, completed, entered_in_error, stopped, draft, unknown}
	
	/** The intent of the Medication request */
	public enum MedicationRequestIntent { proposal, plan, order, original_order, reflex_order, filler_order, instance_order, option }
	
	/** The category of the MedicationRequest */
	public enum MedicationRequestCategory { inpatient, outpatient, community, discharge }
	
	/** The id of the MedicationRequest in the system from where the data is retrieved */
	private String medicationRequestId;
	
	/** The Meta data such as versionId, resourceUrl, lastModifiedTimeStamp */
	private MetaData meta;

	/** List of identifiers in the source system */
	private ArrayList<IdentifierElement> identifiers;
	
	/** The Status of the Medication Request */
	private MedicationRequestStatus status;
	
	/** The intent of the Medication Requestion */
	private MedicationRequestIntent intent;
	
	/** Medication Category indicating in what setting the medication order was created */
	private MedicationRequestCategory category;
	
	/** The actual medication */
	private CodeElement medication;
	
	/** The date when the request was authored */
	private Date authoredOnDateTime;
	
	/** The practitioner requesting the medication */
	private Practitioner requester;
	
	/** True if the medication was reported by Patient and not prescribed */
	private Boolean reported;
	
	/** The encounter where the medicationrequest was asserted */
	private String encounterId;
	
	/** The dosage instructions */
	private String dosageInstruction;
	
	/** The dosage SIG */
	private String dosageSig;
	
	/** As Needed Indicator */
	private Boolean asNeededFlag;
	
	/** The route for the medication in SNOMED codes */
	private CodeElement route;
	
	/** The body site where the medication is administered */
	private CodeElement bodySite; 
	
	/** The method used to administer the medication */
	private CodeElement method;
	
	/** The dose quantity */
	private Quantity doseQuantity;
	
	/** Patient Instructions */
	private String patientInstructions;
	
	/** Notes associated with the medication request */
	private String notes; 
	
	
	public MedicationRequestCategory getCategory() {
		return category;
	}
	public void setCategory(MedicationRequestCategory category) {
		this.category = category;
	}
	public Boolean getReported() {
		return reported;
	}
	public void setReported(Boolean reported) {
		this.reported = reported;
	}
	public String getEncounterId() {
		return encounterId;
	}
	public void setEncounterId(String encounterId) {
		this.encounterId = encounterId;
	}
	public String getDosageInstruction() {
		return dosageInstruction;
	}
	public void setDosageInstruction(String dosageInstruction) {
		this.dosageInstruction = dosageInstruction;
	}
	public String getDosageSig() {
		return dosageSig;
	}
	public void setDosageSig(String dosageSig) {
		this.dosageSig = dosageSig;
	}
	public Boolean getAsNeededFlag() {
		return asNeededFlag;
	}
	public void setAsNeededFlag(Boolean asNeededFlag) {
		this.asNeededFlag = asNeededFlag;
	}
	public CodeElement getRoute() {
		return route;
	}
	public void setRoute(CodeElement route) {
		this.route = route;
	}
	public CodeElement getBodySite() {
		return bodySite;
	}
	public void setBodySite(CodeElement bodySite) {
		this.bodySite = bodySite;
	}
	public CodeElement getMethod() {
		return method;
	}
	public void setMethod(CodeElement method) {
		this.method = method;
	}
	public Quantity getDoseQuantity() {
		return doseQuantity;
	}
	public void setDoseQuantity(Quantity doseQuantity) {
		this.doseQuantity = doseQuantity;
	}
	public String getPatientInstructions() {
		return patientInstructions;
	}
	public void setPatientInstructions(String patientInstructions) {
		this.patientInstructions = patientInstructions;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getMedicationRequestId() {
		return medicationRequestId;
	}
	public void setMedicationRequestId(String medicationRequestId) {
		this.medicationRequestId = medicationRequestId;
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
	public MedicationRequestStatus getStatus() {
		return status;
	}
	public void setStatus(MedicationRequestStatus status) {
		this.status = status;
	}
	public MedicationRequestIntent getIntent() {
		return intent;
	}
	public void setIntent(MedicationRequestIntent intent) {
		this.intent = intent;
	}
	public CodeElement getMedication() {
		return medication;
	}
	public void setMedication(CodeElement medication) {
		this.medication = medication;
	}
	public Date getAuthoredOnDateTime() {
		return authoredOnDateTime;
	}
	public void setAuthoredOnDateTime(Date authoredOnDateTime) {
		this.authoredOnDateTime = authoredOnDateTime;
	}
	public Practitioner getRequester() {
		return requester;
	}
	public void setRequester(Practitioner requester) {
		this.requester = requester;
	}
	
	

}
