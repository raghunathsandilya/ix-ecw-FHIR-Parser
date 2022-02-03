package com.interopx.fhir.parser.util;

import java.util.Optional;

import org.hl7.fhir.exceptions.FHIRException;
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
import com.interopx.fhir.parser.model.Condition;
import com.interopx.fhir.parser.model.Telecom;
import com.interopx.fhir.parser.model.Condition.ConditionCategory;
import com.interopx.fhir.parser.model.Condition.ConditionClinicalStatus;
import com.interopx.fhir.parser.model.Condition.ConditionSeverity;
import com.interopx.fhir.parser.model.Condition.ConditionVerificationStatus;
import com.interopx.fhir.parser.model.Encounter;
import com.interopx.fhir.parser.model.Encounter.EncounterStatus;
import com.interopx.fhir.parser.model.Immunization;
import com.interopx.fhir.parser.model.Immunization.ImmunizationStatus;
import com.interopx.fhir.parser.model.Location.LocationStatus;
import com.interopx.fhir.parser.model.MedicationRequest;
import com.interopx.fhir.parser.model.Location;

/**
 * @author Raghunath Sandilya This Class includes the methods to convert the
 *         codes and Enum values received from FHIR Objects to understandable
 *         Enum values
 */
public class FhirUtil {

	public static BundleEntryComponent getResourceById(Bundle bundle, ResourceType resourceName, String resourceId) {
		BundleEntryComponent resource = null;
		if(bundle != null) {
			for (BundleEntryComponent bundleEntryComp : bundle.getEntry()) {
				if (bundleEntryComp.hasResource()) {
					Resource resourceObj = bundleEntryComp.getResource();
					if (resourceObj.getResourceType().name().equals(resourceName.name())
							&& resourceObj.getIdElement().getIdPart().equals(resourceId)) {
						resource = bundleEntryComp;
					}
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
	public static AllergyIntolerance.AllergyClinicalStatus getAllergyClinicalStatusEnum(String allergyClinicalStatus) {
		if (allergyClinicalStatus.equals("active"))
			return AllergyIntolerance.AllergyClinicalStatus.active;
		else if (allergyClinicalStatus.equals("inactive"))
			return AllergyIntolerance.AllergyClinicalStatus.inactive;
		else if (allergyClinicalStatus.equals("resolved"))
			return AllergyIntolerance.AllergyClinicalStatus.resolved;
		throw new FHIRException("Unknown AllergyintoleranceClinical code '"+allergyClinicalStatus+"'");
	}

	/**
	 * @param code
	 * @return
	 */
	public static AllergyIntolerance.AllergyVerificationStatus getAllergyVerificationStatusEnum(String allergyVerificationStatus) {
		if (allergyVerificationStatus.equals("unconfirmed"))
			return AllergyIntolerance.AllergyVerificationStatus.unconfirmed;
		else if (allergyVerificationStatus.equals("confirmed"))
			return AllergyIntolerance.AllergyVerificationStatus.confirmed;
		else if (allergyVerificationStatus.equals("refuted"))
			return AllergyIntolerance.AllergyVerificationStatus.refuted;
		throw new FHIRException("Unknown AllergyintoleranceVerification code '"+allergyVerificationStatus+"'");
	}

	/**
	 * @param allergyCategory
	 * @return
	 */
	public static AllergyIntolerance.AllergyCategory getAllergyCategory(String allergyCategory) {
		if(allergyCategory.equals("food"))
			return AllergyIntolerance.AllergyCategory.food;
		if(allergyCategory.equals("medication"))
			return AllergyIntolerance.AllergyCategory.medication;
		if(allergyCategory.equals("environment"))
			return AllergyIntolerance.AllergyCategory.environment;
		if(allergyCategory.equals("biologic"))
			return AllergyIntolerance.AllergyCategory.biologic;
		throw new FHIRException("Unknown AllergyintoleranceCategory code '"+allergyCategory+"'");
	}

	/**
	 * @param allergyCriticality
	 * @return
	 */
	public static AllergyIntolerance.AllergyCriticality getAllergyCriticality(
			String allergyCriticality) {
		if(allergyCriticality.equals("low"))
			return AllergyIntolerance.AllergyCriticality.low;
		if(allergyCriticality.equals("high"))
			return AllergyIntolerance.AllergyCriticality.high;
		if(allergyCriticality.equals("unable-to-assess"))
			return AllergyIntolerance.AllergyCriticality.unable_to_assess;
		throw new FHIRException("Unknown AllergyintoleranceCriticality code '"+allergyCriticality+"'");
	}

	/**
	 * @param allergySeverity
	 * @return
	 */
	public static AllergyReaction.AllergyReactionSeverity getAllergyReactionSeverity(
			String allergySeverity) {
		if(allergySeverity.equals("mild"))
			return AllergyReaction.AllergyReactionSeverity.mild;
		if(allergySeverity.equals("moderate"))
			return AllergyReaction.AllergyReactionSeverity.moderate;
		if(allergySeverity.equals("severe"))
			return AllergyReaction.AllergyReactionSeverity.severe;
		throw new FHIRException("Unknown AllergyintoleranceServerity code '"+allergySeverity+"'");
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
	
	/**
	 *  @param conditionstatus
	 *  @return
	 */
	public static Condition.ConditionClinicalStatus getConditionClinicalStatusEnum(String conditionstatus){
		if (conditionstatus.equals("active"))
			return Condition.ConditionClinicalStatus.active;
		else if (conditionstatus.equals("recurrence"))
			return Condition.ConditionClinicalStatus.recurrence;
		else if (conditionstatus.equals("relapse"))
			return Condition.ConditionClinicalStatus.relapse;
		else if (conditionstatus.equals("inactive"))
			return Condition.ConditionClinicalStatus.inactive;
		else if (conditionstatus.equals("remission"))
			return Condition.ConditionClinicalStatus.remission;
		else if (conditionstatus.equals("resolved"))
			return ConditionClinicalStatus.resolved;
		throw new FHIRException("Unknown Condition status code '"+conditionstatus+"'");
	}
	
	/**
	 *@param conditionverify
	 * @return
	 */
	public static Condition.ConditionVerificationStatus getConditionVerificationStatusEnum(String conditionverify){
		if (conditionverify.equals("unconfirmed"))
			return ConditionVerificationStatus.unconfirmed;
		else if( conditionverify.equals("provisional"))
			return ConditionVerificationStatus.provisional;
		else if (conditionverify.equals("differential"))
			return ConditionVerificationStatus.differential;
		else if (conditionverify.equals("confirmed"))
			return ConditionVerificationStatus.confirmed;
		else if (conditionverify.equals("refuted"))
			return ConditionVerificationStatus.refuted;
		else if (conditionverify.equals("entered-in-error"))
			return ConditionVerificationStatus.entered_in_error;
		throw new FHIRException("Unknown Condition Verification code '"+conditionverify+"'");

		}
	
	/**
	 *@param conditioncategory
	 * @return 
	 */
	public static Condition.ConditionCategory getConditionCategoryEnum(String conditioncategory){
		if (conditioncategory.equals("problem-list-item"))
			return ConditionCategory.problem_list_item;
		else if( conditioncategory.equals("encounter-diagnosis"))
			return ConditionCategory.encounter_diagnosis;
		else if (conditioncategory.equals("health-concern"))
			return ConditionCategory.health_concern;
		else if (conditioncategory.equals("death-diagnosis"))
			return ConditionCategory.death_diagnosis;
		throw new FHIRException("Unknown Condition category '"+conditioncategory+"'");
	}
	
	/**
	 *@param conditionseverity
	 * @return 
	 * 
	 */
	public static Condition.ConditionSeverity getConditionSeverityEnum(String conditionseverity){
		if (conditionseverity.equals("255604002"))
			return ConditionSeverity.mild;
		else if( conditionseverity.equals("6736007"))
			return ConditionSeverity.moderate;
		else if ( conditionseverity.equals("24484000"))
			return ConditionSeverity.severe;
		return null;
		
	}
	
	/**
	 * @param encounterStatus
	 * @return 
	 * 
	 */
	public static Encounter.EncounterStatus getEncounterStatusEnum( String encounterStatus){
		if( encounterStatus.equals("planned"))
			return EncounterStatus.planned; 
		if(encounterStatus.equals("arrived"))
			 return EncounterStatus.arrived;
		if(encounterStatus.equals("triaged"))
			 return EncounterStatus.triaged;
		if(encounterStatus.equals("in-progress"))
			return EncounterStatus.in_progress;
		if(encounterStatus.equals("onleave"))
			return EncounterStatus.onleave;
		if(encounterStatus.equals("finished"))
			return EncounterStatus.finished;
		if(encounterStatus.equals("cancelled"))
			return EncounterStatus.cancelled;
		throw new FHIRException("Unknown Encounter Status '"+encounterStatus+"'");
	}
	
	/**
	 * @param participanttype
	 * @return 
	 * 
	 */
	public static Encounter.ParticipantType getParticipantTypeEnum(String participanttype){
		if(participanttype.equals("ADM"))
			return Encounter.ParticipantType.ADM;
		if(participanttype.equals("ATND"))
			return Encounter.ParticipantType.ATND;
		if(participanttype.equals("CALLBCK"))
			return Encounter.ParticipantType.CALLBCK;
		if(participanttype.equals("CON"))
			return Encounter.ParticipantType.CON;
		if(participanttype.equals("DIS"))
			return Encounter.ParticipantType.DIS;
		if(participanttype.equals("ESC"))
			return Encounter.ParticipantType.ESC;
		if(participanttype.equals("REF"))
			return Encounter.ParticipantType.REF;
		if(participanttype.equals("SPRF"))
			return Encounter.ParticipantType.SPRF;
		if(participanttype.equals("PPRF"))
			return Encounter.ParticipantType.PPRF;
		if(participanttype.equals("PART"))
			return Encounter.ParticipantType.PART;
		if(participanttype.equals("transaltor"))
			return Encounter.ParticipantType.transaltor;
		if(participanttype.equals("emergency"))
			return Encounter.ParticipantType.emergency;
		throw new FHIRException("Unknown Participant type '"+participanttype+"'");
	
	}
	
	/**
	 *@param immunizationstatus
	 * @return 
	 * 
	 */
	public static Immunization.ImmunizationStatus getImmunizationStatusEnum(String immunizationstatus){
		if(immunizationstatus.equals("completed"))
			return Immunization.ImmunizationStatus.completed;
		else if (immunizationstatus.equals("not-done"))
			return Immunization.ImmunizationStatus.not_done;
		else if (immunizationstatus.equals("entered-in-error"))
			return Immunization.ImmunizationStatus.entered_in_error;
		throw new FHIRException("Unknown Immunization status '"+immunizationstatus+"'");
	}
	
	/**
	 * @param locationstatus
	 * @return 
	 * 
	 */
	public static Location.LocationStatus getLocationStatusEnum (String locationstatus){
	if (locationstatus.equals("active"))	
		return Location.LocationStatus.active;
	else if (locationstatus.equals("suspended"))
		return Location.LocationStatus.suspended;
	else if (locationstatus.equals("inactive"))
		return Location.LocationStatus.inactive;
	throw new FHIRException("Unknown location status '"+locationstatus+"'");
	}
	
	/**
	 *@param medicationstatus
	 * @return 
	 * 
	 */
	public static MedicationRequest.MedicationRequestStatus getMedicationRequestStatus(String medicationstatus){
		if (medicationstatus.equals("active"))	
			return MedicationRequest.MedicationRequestStatus.active;
		if (medicationstatus.equals("on-hold"))	
			return MedicationRequest.MedicationRequestStatus.on_hold;
		if (medicationstatus.equals("cancelled"))	
			return MedicationRequest.MedicationRequestStatus.cancelled;
		if (medicationstatus.equals("completed"))	
			return MedicationRequest.MedicationRequestStatus.completed;
		if (medicationstatus.equals("entered-in-error"))	
			return MedicationRequest.MedicationRequestStatus.entered_in_error;
		if (medicationstatus.equals("stopped"))	
			return MedicationRequest.MedicationRequestStatus.stopped;
		if (medicationstatus.equals("draft"))	
			return MedicationRequest.MedicationRequestStatus.draft;
		if (medicationstatus.equals("unknown"))	
			return MedicationRequest.MedicationRequestStatus.unknown;
		throw new FHIRException("Unknown MedicationRequest status '"+medicationstatus+"'");
		
	}
	
	/**
	 * @param medicationintent
	 * @return 
	 * 
	 */
	public static MedicationRequest.MedicationRequestIntent getMedicationRequestIntentEnum(String medicationintent){
		if (medicationintent.equals("proposal"))	
			return MedicationRequest.MedicationRequestIntent.proposal;
		if (medicationintent.equals("plan"))	
			return MedicationRequest.MedicationRequestIntent.plan;
		if (medicationintent.equals("order"))	
			return MedicationRequest.MedicationRequestIntent.order;
		if (medicationintent.equals("original-order"))	
			return MedicationRequest.MedicationRequestIntent.original_order;
		if (medicationintent.equals("reflex-order"))	
			return MedicationRequest.MedicationRequestIntent.reflex_order;
		if (medicationintent.equals("filler-order"))	
			return MedicationRequest.MedicationRequestIntent.filler_order;
		if (medicationintent.equals("instance-order"))	
			return MedicationRequest.MedicationRequestIntent.instance_order;
		if (medicationintent.equals("option"))	
			return MedicationRequest.MedicationRequestIntent.option;
		throw new FHIRException("Unknown MedicationRequestIntent '"+medicationintent+"'");
		}
	
	/**
	 * @param medicationcategory
	 * @return 
	 * 
	 */
	public static MedicationRequest.MedicationRequestCategory getMedicationRequestCategoryEnum(String medicationcategory){
		if (medicationcategory.equals("inpatient"))	
			return MedicationRequest.MedicationRequestCategory.inpatient;
		if (medicationcategory.equals("outpatient"))	
			return MedicationRequest.MedicationRequestCategory.outpatient;
		if (medicationcategory.equals("community"))	
			return MedicationRequest.MedicationRequestCategory.community;
		if (medicationcategory.equals("discharge"))	
			return MedicationRequest.MedicationRequestCategory.discharge;
		throw new FHIRException("Unknown MedicationRequestCategory '"+medicationcategory+"'");
	}
}
