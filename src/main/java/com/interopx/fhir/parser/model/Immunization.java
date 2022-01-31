package com.interopx.fhir.parser.model;

import java.util.Date;

/**
 * The Immunizations associated with the Patient.
 * 
 * @author nbashyam
 *
 */
public class Immunization {
	
	public enum ImmunizationStatus { completed, not_done, entered_in_error }
	
	/** The resource id for the immunization */
	private String id;
	
	/** The Metadata for the resource which contains lastModifiedTimestamp, version and resourceUrl. */
	private MetaData meta;
	
	/** Whether the immunization was completed or not */
	private ImmunizationStatus status;
	
	/** The reason if the immunizatin was not completed */
	private String statusReason;
	
	/** The actual immunization administered */
	private CodeElement Code;
	
	/** The Date the immunization was administered */ 
	private Date immunizationDate;
	
	/** Information about immunization administration timing when date is not know. */ 
	private String immunizationInfo;
	
	/** Encounter where the immunization was administered */
	private String encounterId;
	
	/** The Date the immunization was recorded in the EMR */ 
	private Date immunizationRecordedDate;
	
	/** Confirmation that the information provided is from the person who administerd the immunization */
	private Boolean primarySource;
	
	/** Lot Number of the Immunization */
	private String lotNumber;
	
	/** Organization Name of the Manufacturer */
	private String organizationName;
	
	/** Immunization expiration Date */
	private Date expirationDate;
	
	/** Body Site where the immunization was provided using SNOMED Codes */
	private CodeElement bodySite; 
	
	/** How the immunization was provided (orally, injection) using SNOMED Codes */
	private CodeElement routeCode;
	
	/** Immunization Dose quantity if known */
	private Quantity doseQuantity;
	
	/** The Practitioner who administered the immunization */
	private Practitioner administeringPhysician;
	
	/** Notes about the immunization */
	private String notes;
	
	/** Dose Number of the immunization */
	private String doseNumber;
	
	/** Dose Series indicating the total doses required for immunity */
	private String doseSeriesNumber;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ImmunizationStatus getStatus() {
		return status;
	}

	public void setStatus(ImmunizationStatus status) {
		this.status = status;
	}

	public String getStatusReason() {
		return statusReason;
	}

	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}

	public CodeElement getCode() {
		return Code;
	}

	public void setCode(CodeElement code) {
		Code = code;
	}

	public Date getImmunizationDate() {
		return immunizationDate;
	}

	public void setImmunizationDate(Date immunizationDate) {
		this.immunizationDate = immunizationDate;
	}

	public String getImmunizationInfo() {
		return immunizationInfo;
	}

	public void setImmunizationInfo(String immunizationInfo) {
		this.immunizationInfo = immunizationInfo;
	}

	public String getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(String encounterId) {
		this.encounterId = encounterId;
	}

	public Date getImmunizationRecordedDate() {
		return immunizationRecordedDate;
	}

	public void setImmunizationRecordedDate(Date immunizationRecordedDate) {
		this.immunizationRecordedDate = immunizationRecordedDate;
	}

	public Boolean getPrimarySource() {
		return primarySource;
	}

	public void setPrimarySource(Boolean primarySource) {
		this.primarySource = primarySource;
	}

	public String getLotNumber() {
		return lotNumber;
	}

	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public CodeElement getBodySite() {
		return bodySite;
	}

	public void setBodySite(CodeElement bodySite) {
		this.bodySite = bodySite;
	}

	public CodeElement getRouteCode() {
		return routeCode;
	}

	public void setRouteCode(CodeElement routeCode) {
		this.routeCode = routeCode;
	}

	public Quantity getDoseQuantity() {
		return doseQuantity;
	}

	public void setDoseQuantity(Quantity doseQuantity) {
		this.doseQuantity = doseQuantity;
	}

	public Practitioner getAdministeringPhysician() {
		return administeringPhysician;
	}

	public void setAdministeringPhysician(Practitioner administeringPhysician) {
		this.administeringPhysician = administeringPhysician;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getDoseNumber() {
		return doseNumber;
	}

	public void setDoseNumber(String doseNumber) {
		this.doseNumber = doseNumber;
	}

	public String getDoseSeriesNumber() {
		return doseSeriesNumber;
	}

	public void setDoseSeriesNumber(String doseSeriesNumber) {
		this.doseSeriesNumber = doseSeriesNumber;
	}

	

	
	
}
