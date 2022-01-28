package com.interopx.fhir.parser.processing;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.AllergyIntolerance;
import org.hl7.fhir.r4.model.AllergyIntolerance.AllergyIntoleranceCategory;
import org.hl7.fhir.r4.model.AllergyIntolerance.AllergyIntoleranceReactionComponent;
import org.hl7.fhir.r4.model.Annotation;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Enumeration;
import org.hl7.fhir.r4.model.Resource;
import org.springframework.stereotype.Service;

import com.interopx.fhir.parser.model.CodeElement;
import com.interopx.fhir.parser.util.ParserUtil;

@Service
public class AllergyIntoleranceProcessor {
	
	public com.interopx.fhir.parser.model.AllergyIntolerance retrieveAllergyIntolerance(Resource allergy) {
		AllergyIntolerance allergyIntolerance  = (AllergyIntolerance) allergy;
		
		com.interopx.fhir.parser.model.AllergyIntolerance allergyDetails  = new com.interopx.fhir.parser.model.AllergyIntolerance();
		
		if(allergyIntolerance.hasIdElement()) {
			allergyDetails.setAllergyId(allergyIntolerance.getIdElement().getIdPart());	
		}
		
		if(allergyIntolerance.hasIdentifier()) {
			allergyDetails.setIdentifiers(ParserUtil.readIdentfiers(allergyIntolerance.getIdentifier()));
		}
		
		if(allergyIntolerance.hasClinicalStatus()) {
			allergyDetails.setClinicalStatus(ParserUtil.readCodeElements(allergyIntolerance.getClinicalStatus()));
		}
		
		if(allergyIntolerance.hasVerificationStatus()) {
			allergyDetails.setVerificationStatus(ParserUtil.readCodeElements(allergyIntolerance.getVerificationStatus()));
		}
		
		if(allergyIntolerance.hasType()) {
			allergyDetails.setAllergyType(allergyIntolerance.getType().name());
		}
		
		if(allergyIntolerance.hasCategory()) {
			if(!allergyIntolerance.getCategory().isEmpty()) {
				List<String> allergyCategoriesList = new ArrayList<String>(); 
				List<Enumeration<AllergyIntoleranceCategory>> allergyCategories = allergyIntolerance.getCategory();
				for(Enumeration<AllergyIntoleranceCategory> allergyCategoryEnum: allergyCategories) {
					allergyCategoriesList.add(allergyCategoryEnum.asStringValue());
				}
				allergyDetails.setAllergyCategory(allergyCategoriesList);
			}
		}
		
		if(allergyIntolerance.hasCriticality()) {
			allergyDetails.setAllergyCriticality(allergyIntolerance.getCriticality().name());
		}
		
		if(allergyIntolerance.hasCode()) {
			allergyDetails.setAllergyCode(ParserUtil.readCodeElements(allergyIntolerance.getCode()));
		}
		
		if(allergyIntolerance.hasPatient()) {
			allergyDetails.setPatient(ParserUtil.readReferenceElement(allergyIntolerance.getPatient()));
		}
		
		if(allergyIntolerance.hasEncounter()) {
			allergyDetails.setEncounter(ParserUtil.readReferenceElement(allergyIntolerance.getEncounter()));
		}
		
		if(allergyIntolerance.hasOnsetDateTimeType()) {
			allergyDetails.setOnSetDateTime(allergyIntolerance.getOnsetDateTimeType().getValue());
		}
		
		if(allergyIntolerance.hasRecorder()) {
			allergyDetails.setRecorderId(ParserUtil.readReferenceElement(allergyIntolerance.getRecorder()));
		}
		
		if(allergyIntolerance.hasLastOccurrence()) {
			allergyDetails.setLastOccurenceDateTime(allergyIntolerance.getLastOccurrence());
		}
		
		if(allergyIntolerance.hasReaction()) {
			List<AllergyIntoleranceReactionComponent> allergyReactions = allergyIntolerance.getReaction();
			ArrayList<CodeElement> allergySubstances = new ArrayList<CodeElement>();
			ArrayList<CodeElement> allergyReactionManifestations =  new ArrayList<CodeElement>();
			for(AllergyIntoleranceReactionComponent reactionComp: allergyReactions) {
				CodeElement allergySubstanceCodeElement = ParserUtil.readCodeElements(reactionComp.getSubstance());
				allergySubstances.add(allergySubstanceCodeElement);
				if(reactionComp.hasManifestation()) {
					for(CodeableConcept concept: reactionComp.getManifestation()) {
						CodeElement allergyReactionManifestationsCodeElement = ParserUtil.readCodeElements(concept);
						allergyReactionManifestations.add(allergyReactionManifestationsCodeElement);
					}
				}
				
				if(reactionComp.hasOnset()) {
					allergyDetails.setAllergyReactionOnsetDateTime(reactionComp.getOnset());
				}
			}
			allergyDetails.setAllergyReactionSubstance(allergySubstances);
			allergyDetails.setAllergyReactionManifestation(allergyReactionManifestations);
		}
		
		if(allergyIntolerance.hasNote()) {
			for(Annotation allergyNote: allergyIntolerance.getNote()) {
				allergyDetails.setAllergyNotes(allergyDetails.getAllergyNotes()+","+allergyNote.getText());
			}
		}
		
		return allergyDetails;
	}

}
