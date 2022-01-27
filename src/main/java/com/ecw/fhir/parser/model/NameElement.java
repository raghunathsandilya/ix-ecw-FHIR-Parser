package com.ecw.fhir.parser.model;


public class NameElement {
	
	private String firstName;
	private String lastName;
	private String suffix;
	private String middleName;
	private String useContext;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getUseContext() {
		return useContext;
	}
	public void setUseContext(String useContext) {
		this.useContext = useContext;
	}

}
