package com.interopx.fhir.parser.util;

import java.util.Optional;

import org.hl7.fhir.r4.model.AllergyIntolerance.AllergyIntoleranceCategory;
import org.hl7.fhir.r4.model.AllergyIntolerance.AllergyIntoleranceCriticality;
import org.hl7.fhir.r4.model.AllergyIntolerance.AllergyIntoleranceSeverity;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.r4.model.ContactPoint.ContactPointUse;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;

import com.interopx.fhir.parser.model.AllergyIntolerance;
import com.interopx.fhir.parser.model.AllergyReaction;
import com.interopx.fhir.parser.model.Telecom;

/**
 * @author Raghunath Sandilya This Class includes the methods to convert the
 *         codes and Enum values received from FHIR Objects to understandable
 *         Enum values
 */
public class FhirUtil {

	public static Resource getResourceById(Bundle bundle, ResourceType resourceName, String resourceId) {
		Resource resource = null;
		for (BundleEntryComponent bundleEntryComp : bundle.getEntry()) {
			if (bundleEntryComp.hasResource()) {
				Resource resourceObj = bundleEntryComp.getResource();
				if (resourceObj.getResourceType().name().equals(resourceName.name())
						&& resourceObj.getIdElement().getIdPart().equals(resourceId)) {
					resource = resourceObj;
				}
			}
		}
		return resource;
	}

	/**
	 * This method will take the input as code and returns Clinical Status Enum
	 * value of AllergyIntolerance
	 * 
	 * @param code
	 * @return
	 */
	public static AllergyIntolerance.AllergyClinicalStatus getAllergyClinicalStatusEnum(String code) {
		if (code.equalsIgnoreCase("active"))
			return AllergyIntolerance.AllergyClinicalStatus.active;
		else if (code.equalsIgnoreCase("inactive"))
			return AllergyIntolerance.AllergyClinicalStatus.inactive;
		else if (code.equalsIgnoreCase("resolved"))
			return AllergyIntolerance.AllergyClinicalStatus.resolved;
		else
			return AllergyIntolerance.AllergyClinicalStatus.inactive;
	}

	/**
	 * @param code
	 * @return
	 */
	public static AllergyIntolerance.AllergyVerificationStatus getAllergyVerificationStatusEnum(String code) {
		if (code.equalsIgnoreCase("unconfirmed"))
			return AllergyIntolerance.AllergyVerificationStatus.unconfirmed;
		else if (code.equalsIgnoreCase("confirmed"))
			return AllergyIntolerance.AllergyVerificationStatus.confirmed;
		else if (code.equalsIgnoreCase("refuted"))
			return AllergyIntolerance.AllergyVerificationStatus.refuted;
		else
			return AllergyIntolerance.AllergyVerificationStatus.entered_in_error;
	}

	/**
	 * @param allergyCategory
	 * @return
	 */
	public static AllergyIntolerance.AllergyCategory getAllergyCategory(AllergyIntoleranceCategory allergyCategory) {
		return AllergyIntolerance.AllergyCategory.values()[allergyCategory.ordinal()];
	}

	/**
	 * @param allergyCriticality
	 * @return
	 */
	public static AllergyIntolerance.AllergyCriticality getAllergyCriticality(
			AllergyIntoleranceCriticality allergyCriticality) {
		return AllergyIntolerance.AllergyCriticality.values()[allergyCriticality.ordinal()];
	}

	/**
	 * @param allergySeverity
	 * @return
	 */
	public static AllergyReaction.AllergyReactionSeverity getAllergyReactionSeverity(
			AllergyIntoleranceSeverity allergySeverity) {
		return AllergyReaction.AllergyReactionSeverity.values()[allergySeverity.ordinal()];
	}

	/**
	 * @param contactPointSystem
	 * @return
	 */
	public static Telecom.TelecomType getTelecomType(ContactPointSystem contactPointSystem) {
		return Telecom.TelecomType.values()[contactPointSystem.ordinal()];
	}

	/**
	 * @param contactPointUse
	 * @return
	 */
	public static Telecom.TelecomUse getTelecomUse(ContactPointUse contactPointUse) {
		return Telecom.TelecomUse.values()[contactPointUse.ordinal()];
	}

}
