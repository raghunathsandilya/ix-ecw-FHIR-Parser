package com.interopx.fhir.parser.processing;

import java.util.ArrayList;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Encounter.EncounterLocationComponent;
import org.hl7.fhir.r4.model.Resource;
import org.springframework.stereotype.Service;

import com.interopx.fhir.parser.model.CodeElement;
import com.interopx.fhir.parser.model.Encounter;
import com.interopx.fhir.parser.model.ReferenceElement;
import com.interopx.fhir.parser.util.ParserUtil;

@Service
public class EncounterProcessor {

	public Encounter retrieveEncounter(Resource encounter) {
		org.hl7.fhir.r4.model.Encounter encounterFhirObj =  (org.hl7.fhir.r4.model.Encounter) encounter;
		Encounter encounterObj = new Encounter();
		
		if(encounterFhirObj.hasId()) {
			encounterObj.setEncounterId(encounterFhirObj.getIdElement().getIdPart());
		}

		if(encounterFhirObj.hasIdentifier()) {
			encounterObj.setIdentifiers(ParserUtil.readIdentfiers(encounterFhirObj.getIdentifier()));
		}
		
		if(encounterFhirObj.hasStatus()) {
			encounterObj.setStatus(encounterFhirObj.getStatus().name());
		}
		
		if(encounterFhirObj.hasClass_()) {
			encounterObj.setEncounterClass(ParserUtil.readCodingElemenets(encounterFhirObj.getClass_()));
		}
		
		if(encounterFhirObj.hasType()) {
			ArrayList<CodeElement> encounterTypes = new ArrayList<CodeElement>();
			for(CodeableConcept concept: encounterFhirObj.getType()) {
				CodeElement codeElement = ParserUtil.readCodeElements(concept);
				encounterTypes.add(codeElement);
			}
			encounterObj.setEncounterType(encounterTypes);
		}
		
		if(encounterFhirObj.hasSubject()) {
			encounterObj.setPatient(ParserUtil.readReferenceElement(encounterFhirObj.getSubject()));
		}
		
		if(encounterFhirObj.hasPeriod()) {
			encounterObj.setEncounterPeriod(ParserUtil.readPeriod(encounterFhirObj.getPeriod()));
		}
		
		if(encounterFhirObj.hasReasonCode()) {
			ArrayList<CodeElement> codeElements = new ArrayList<CodeElement>();
			for(CodeableConcept concept: encounterFhirObj.getReasonCode()) {
				CodeElement codeElement = ParserUtil.readCodeElements(concept);
				codeElements.add(codeElement);
			}
			encounterObj.setReasonCode(codeElements);
		}
		
		if(encounterFhirObj.hasLocation()) {
			ArrayList<ReferenceElement> referenceElements = new ArrayList<ReferenceElement>();
			for(EncounterLocationComponent locationComp: encounterFhirObj.getLocation()) {
				if(locationComp.hasLocation()) {
				referenceElements.add(ParserUtil.readReferenceElement(locationComp.getLocation()));	
				}
			}
			encounterObj.setServiceDeliveryLocations(referenceElements);
		}
		return encounterObj;
	}
}
