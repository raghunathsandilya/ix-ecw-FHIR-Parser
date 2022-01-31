package com.interopx.fhir.parser.model;

/**
 * The DataType represents Quantities used for Medication Doses, Lab Result Values, Vital Sign results etc.
 * 
 * @author nbashyam
 *
 */
public class Quantity {

	/** The actual value to be reprsented */
	private String value;
	
	/** The Units of Measure for the value */
	private CodeElement units;	
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public CodeElement getUnits() {
		return units;
	}
	public void setUnits(CodeElement units) {
		this.units = units;
	}
	
	
	
}
