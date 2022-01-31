package com.interopx.fhir.parser.model;

import java.util.Date;

/**
 * The Telecom information such as email, fax, phone for Organization, Patient, Practitioner etc.
 * 
 * @author nbashyam
 *
 */
public class Telecom {
	
	/** The type of information contained in the object */
	public enum TelecomType {phone, fax, email, pager, url, sms, other }
	
	/** The information designated purpose of use */
	public enum TelecomUse {home, work, temp, old, mobile }
	
	/** The type of telecom information */
	private TelecomType type;
	
	/** The use for the telecom information */
	private TelecomUse  use;
	
	/** The actual value of the telecom info such as phone number, email id */
	private String      value;
	
	/** The date from which the telecom information was in use */
	private Date        startDate;
	
	/** The date after which the telecom information was not to be used */
	private Date        endDate;

	public TelecomType getType() {
		return type;
	}

	public void setType(TelecomType type) {
		this.type = type;
	}

	public TelecomUse getUse() {
		return use;
	}

	public void setUse(TelecomUse use) {
		this.use = use;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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
	
	

}
