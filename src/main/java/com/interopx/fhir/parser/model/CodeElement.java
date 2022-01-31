package com.interopx.fhir.parser.model;

import java.util.ArrayList;

/**
 * The class represents codes used for identifying Medications, Problems, Procedures, Allergies, Encounter etc.
 * 
 * @author nbashyam
 *
 */
public class CodeElement {
	
	/** The actual code from the vocabulary */
	private String code;
	
	/** The display name for the code to be used for displaying to humans */
	private String display;
	
	/** The OID or URL of the CodeSystem that contains the code */
	private String system;
	
	/** When the code is not known, information may be captured as Text */
	private String text;
	
	/** The list of alternate codes for the above code in alternate code systems or local code systems */
	private ArrayList<CodeElement> translations;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ArrayList<CodeElement> getTranslations() {
		return translations;
	}

	public void setTranslations(ArrayList<CodeElement> translations) {
		this.translations = translations;
	}

	
}
