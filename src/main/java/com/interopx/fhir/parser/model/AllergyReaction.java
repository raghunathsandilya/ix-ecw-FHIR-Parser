package com.interopx.fhir.parser.model;

import java.util.Date;

/**
 * The class captures the reactions of an allergy.
 * 
 * @author nbashyam
 *
 */
public class AllergyReaction {

	/** The severity of the allergy reaction */
	public enum AllergyReactionSeverity { mild, moderate, severe }
	
	/** The specific reaction */
	private CodeElement   reaction;
	
	/** The specific substance */
	private CodeElement   substance;
	
	/** The specific severity */
	private AllergyReactionSeverity      severity;
	
	/** When the reaction was observed */
	private Date reactionDate;

	public CodeElement getReaction() {
		return reaction;
	}

	public void setReaction(CodeElement reaction) {
		this.reaction = reaction;
	}

	public CodeElement getSubstance() {
		return substance;
	}

	public void setSubstance(CodeElement substance) {
		this.substance = substance;
	}

	public AllergyReactionSeverity getSeverity() {
		return severity;
	}

	public void setSeverity(AllergyReactionSeverity severity) {
		this.severity = severity;
	}

	public Date getReactionDate() {
		return reactionDate;
	}

	public void setReactionDate(Date reactionDate) {
		this.reactionDate = reactionDate;
	}
	
	
}
