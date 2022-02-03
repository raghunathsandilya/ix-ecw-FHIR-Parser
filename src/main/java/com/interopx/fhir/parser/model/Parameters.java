package com.interopx.fhir.parser.model;

import java.util.ArrayList;

/**
 * The class for representing Parameters Resource received in Bundle.
 * 
 * @author nbashyam
 *
 */
public class Parameters {
	
	/** The PatientId within the eCW Practice */
	private String ecwPatientId;
	
	/** The PracticeId requesting the data */
	private String ecwPracticeId;
	
	/** The Data that was retrieved based on the request represented by the RequestId */
	private String requestId;

	public String getEcwPatientId() {
		return ecwPatientId;
	}

	public void setEcwPatientId(String ecwPatientId) {
		this.ecwPatientId = ecwPatientId;
	}

	public String getEcwPracticeId() {
		return ecwPracticeId;
	}

	public void setEcwPracticeId(String ecwPracticeId) {
		this.ecwPracticeId = ecwPracticeId;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	

}
