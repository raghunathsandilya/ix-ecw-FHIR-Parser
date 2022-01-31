package com.interopx.fhir.parser.model;

import java.util.ArrayList;

/**
 * The IdentifierElement is used to represent identifiers such as MRN, SSN, MedicaidId, NPI etc
 * 
 * @author nbashyam
 *
 */
public class IdentifierElement {
	
	/** The Url of the System issuing the identifier */
	private String systemUri;
	
	/** The Value of the Identifier */
	private String value;
	
	/** The Type of the Identifier */
	private CodeElement identifierType;
	
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
	public CodeElement getIdentifierType() {
		return identifierType;
	}
	public void setIdentifierType(CodeElement identifierType) {
		this.identifierType = identifierType;
	}
}
