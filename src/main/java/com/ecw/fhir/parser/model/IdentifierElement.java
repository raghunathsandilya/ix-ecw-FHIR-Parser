package com.ecw.fhir.parser.model;

import java.util.ArrayList;

public class IdentifierElement {
	
	private String systemUri;
	private String value;
	private ArrayList<CodeElement> identifierType;
	
	public String getSystemUri() {
		return systemUri;
	}
	public void setSystemUri(String systemUri) {
		this.systemUri = systemUri;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public ArrayList<CodeElement> getIdentifierType() {
		return identifierType;
	}
	public void setIdentifierType(ArrayList<CodeElement> identifierType) {
		this.identifierType = identifierType;
	}
}
