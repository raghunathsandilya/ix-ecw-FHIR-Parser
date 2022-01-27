package com.interopx.fhir.parser.model;

public class PatientLanguage {
	
	private String languageCode;
	private Boolean preferredInd;
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	public Boolean getPreferredInd() {
		return preferredInd;
	}
	public void setPreferredInd(Boolean preferredInd) {
		this.preferredInd = preferredInd;
	}

}
