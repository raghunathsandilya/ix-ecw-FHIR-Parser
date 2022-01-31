package com.interopx.fhir.parser.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * The NameElement DataType is used store human names.
 * 
 * @author nbashyam
 *
 */
public class NameElement {
	
	/** The Enumberation to indicate how the name should be interpreted */
	public enum UseContext {usual, official, temp, nickname, anonymous, old, maiden }
	
	private String firstName;
	private String lastName;
	private String suffix;
	private HashSet<String> middleName;
	private UseContext useContext;
	private Date       startDate;
	private Date       endDate; 
	
	public NameElement() {
		middleName = new HashSet<String>();
	}
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
	public HashSet<String> getMiddleName() {
		return middleName;
	}
	public void setMiddleName(HashSet<String> middleName) {
		this.middleName = middleName;
	}
	public UseContext getUseContext() {
		return useContext;
	}
	public void setUseContext(UseContext useContext) {
		this.useContext = useContext;
	}

	public void addMiddleName(String mn) {
		
		this.middleName.add(mn);
	}
}
