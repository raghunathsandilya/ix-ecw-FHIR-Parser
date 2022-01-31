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
	
	/** First Name or Given Name of the Patient */
	private String firstName;
	
	/** Last Name of the Patient */
	private String lastName;
	
	/** Prefix to the Name such as Mr, Mrs, Dr etc. */
	private String prefix;
	
	/** Suffix to the Name such as Jr, III etc. */
	private String suffix;
	
	/** List of Middle Names or nick names */
	private HashSet<String> middleName;
	
	/** The Purpose on how the name can be used */
	private UseContext useContext;
	
	/** The name was active from date */
	private Date       startDate;
	
	/** The name was officially changed from date, and hence it is an old/previous name */
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
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
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
