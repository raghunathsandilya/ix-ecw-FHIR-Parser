package com.interopx.fhir.parser.processors;

import java.util.ArrayList;

import org.hl7.fhir.r4.model.Annotation;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Condition.ConditionEvidenceComponent;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.springframework.stereotype.Service;

import com.interopx.fhir.parser.model.CodeElement;
import com.interopx.fhir.parser.model.Condition;
import com.interopx.fhir.parser.model.ReferenceElement;
import com.interopx.fhir.parser.util.ParserUtil;

/**
 * This class will process the Condition FHIR Object and Converts it to Condition POJO
 * 
 * @author nbashyam
 *
 */
@Service
public class ConditionProcessor {
	
	public Condition retrieveCondition(Resource condition) {
		org.hl7.fhir.r4.model.Condition conditionFhirObj =  (org.hl7.fhir.r4.model.Condition) condition;
		Condition conditionObj = new Condition();
		
		
		if(conditionFhirObj.hasId()) {
			conditionObj.setConditionId(conditionFhirObj.getIdElement().getIdPart());
		}
		
		if(conditionFhirObj.hasIdentifier()) {
			conditionObj.setIdentifiers(ParserUtil.readIdentfiers(conditionFhirObj.getIdentifier()));
		}
		
		if(conditionFhirObj.hasClinicalStatus()) {
	//		conditionObj.setClinicalStatus(ParserUtil.readCodeElements(conditionFhirObj.getClinicalStatus()));
		}
		
		if(conditionFhirObj.hasVerificationStatus()) {
			conditionObj.setVerificationStatus(ParserUtil.readCodeElements(conditionFhirObj.getVerificationStatus()));
		}
		
		if(conditionFhirObj.hasCategory()) {
			ArrayList<CodeElement> conditionCategories = new ArrayList<CodeElement>();
			for(CodeableConcept concept: conditionFhirObj.getCategory()) {
				conditionCategories.add(ParserUtil.readCodeElements(concept));
			}
			conditionObj.setConditionCategory(conditionCategories);
		}
		
		if(conditionFhirObj.hasSeverity()) {
//			conditionObj.setSeverity(ParserUtil.readCodeElements(conditionFhirObj.getSeverity()));
		}
		
		if(conditionFhirObj.hasCode()) {
			conditionObj.setConditionCode(ParserUtil.readCodeElements(conditionFhirObj.getCode()));
		}
		
		if(conditionFhirObj.hasBodySite()) {
			ArrayList<CodeElement> bodySites = new ArrayList<CodeElement>();
			for(CodeableConcept concept: conditionFhirObj.getBodySite()) {
				bodySites.add(ParserUtil.readCodeElements(concept));
			}
			conditionObj.setConditionBodySite(bodySites);
		}
		
		if(conditionFhirObj.hasSubject()) {
//			conditionObj.setPatient(ParserUtil.readReferenceElement(conditionFhirObj.getSubject()));
		}
		
		if(conditionFhirObj.hasEncounter()) {
	//		conditionObj.setEncounter(ParserUtil.readReferenceElement(conditionFhirObj.getEncounter()));
		}
		
		if(conditionFhirObj.hasOnsetDateTimeType()) {
			conditionObj.setOnsetDateTime(conditionFhirObj.getOnsetDateTimeType().getValue());
		}
		
		if(conditionFhirObj.hasAbatementDateTimeType()) {
			conditionObj.setAbatementDateTime(conditionFhirObj.getAbatementDateTimeType().getValue());
		}
		
		if(conditionFhirObj.hasRecordedDate()) {
			conditionObj.setRecordedDate(conditionFhirObj.getRecordedDate());
		}
		
		if(conditionFhirObj.hasRecorder()) {
//			conditionObj.setRecorderId(ParserUtil.readReferenceElement(conditionFhirObj.getRecorder()));
		}
		
		
		if(conditionFhirObj.hasNote()) {
			conditionObj.setNotes(conditionFhirObj.getNote().get(0).getText());
		}
		
		return conditionObj;
		
	}

}
