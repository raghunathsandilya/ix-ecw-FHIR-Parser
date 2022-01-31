package com.interopx.fhir.parser.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * The encounter associated with the Patient.
 * 
 * @author nbashyam
 *
 */
public class Encounter {
	
	/** The status of the encounter */
	public enum EncounterStatus { planned, arrived, triaged, in_progress, onleave, finished, cancelled }
	
	/** The Role of the participants */
	public enum ParticipantType { ADM, ATND, CALLBCK, CON, DIS, ESC, REF, SPRF, PPRF, PART, transaltor, emergency }
	
	/** The id of the encounter in the system from where the data is retrieved */
	private String encounterId;
	
	/** The Meta data such as versionId, resourceUrl, lastModifiedTimeStamp */
	private MetaData meta;

	/** List of identifiers in the source system */
	private ArrayList<IdentifierElement> identifiers;
	
	/** The status of the encounter */ 
	private EncounterStatus status;
	
	/** The encounter classification such as ambulatory, inpatient, emergency */
	private CodeElement encounterClass;
	
	/** The type of the encounter using CPT or ICD or appropriate code systems */
	private ArrayList<CodeElement> encounterType;

	/** The encounter start date time */
	private Date startDate;
	
	/** The encounter end time */
	private Date endDate;
	
	/** The reason(s) the encounter was performed */
	private ArrayList<CodeElement> reasonCode;
	
	/** The discharge disposition(s) for the encounter */
	private ArrayList<CodeElement> dischargeDisposition;
	
	/** The list of practitioners participating in the encounter */
	private HashMap<ParticipantType,Practitioner>   practitioners;
	
	/** The organization responsible for the encounter */
	private ArrayList<Organization> serviceProviders;
	
	/** The location where the encounter was performed */
	private ArrayList<Location>     locations;
	
	/** The notes for the encounter that may exist */
	private ArrayList<String>       notes;
	
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
	public EncounterStatus getStatus() {
		return status;
	}
	public void setStatus(EncounterStatus status) {
		this.status = status;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public ArrayList<CodeElement> getDischargeDisposition() {
		return dischargeDisposition;
	}
	public void setDischargeDisposition(ArrayList<CodeElement> dischargeDisposition) {
		this.dischargeDisposition = dischargeDisposition;
	}
	public HashMap<ParticipantType, Practitioner> getPractitioners() {
		return practitioners;
	}
	public void setPractitioners(HashMap<ParticipantType, Practitioner> practitioners) {
		this.practitioners = practitioners;
	}
	public ArrayList<Organization> getServiceProviders() {
		return serviceProviders;
	}
	public void setServiceProviders(ArrayList<Organization> serviceProviders) {
		this.serviceProviders = serviceProviders;
	}
	public ArrayList<Location> getLocations() {
		return locations;
	}
	public void setLocations(ArrayList<Location> locations) {
		this.locations = locations;
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
	
	public ArrayList<CodeElement> getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(ArrayList<CodeElement> reasonCode) {
		this.reasonCode = reasonCode;
	}
	
	public MetaData getMeta() {
		return meta;
	}
	public void setMeta(MetaData meta) {
		this.meta = meta;
	}
	public ArrayList<String> getNotes() {
		return notes;
	}
	public void setNotes(ArrayList<String> notes) {
		this.notes = notes;
	}
}
