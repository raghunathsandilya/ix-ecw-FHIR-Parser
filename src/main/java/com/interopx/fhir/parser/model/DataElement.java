package com.interopx.fhir.parser.model;

public class DataElement {

	private String value;
	private String use;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getUse() {
		return use;
	}
	public void setUse(String use) {
		this.use = use;
	}
}
