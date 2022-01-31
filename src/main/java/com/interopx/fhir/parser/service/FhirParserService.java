package com.interopx.fhir.parser.service;

import java.util.ArrayList;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interopx.fhir.parser.model.AllergyIntolerance;
import com.interopx.fhir.parser.model.Condition;
import com.interopx.fhir.parser.model.Encounter;
import com.interopx.fhir.parser.model.MedicationRequest;
import com.interopx.fhir.parser.model.Patient;
import com.interopx.fhir.parser.model.PatientDemographics;
import com.interopx.fhir.parser.processing.AllergyIntoleranceProcessor;
import com.interopx.fhir.parser.processing.ConditionProcessor;
import com.interopx.fhir.parser.processing.EncounterProcessor;
import com.interopx.fhir.parser.processing.MedicationRequestProcessor;
import com.interopx.fhir.parser.processing.PatientDemographicsProcessor;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;

@Service
public class FhirParserService {

	private static final Logger logger = LoggerFactory.getLogger(FhirParserService.class);

	private static final FhirContext fhirContext = FhirContext.forR4();
	
	private static final IParser fhirParser = fhirContext.newJsonParser().setPrettyPrint(true); 

	private static final PatientDemographicsProcessor patientDemographicsProcessor = new PatientDemographicsProcessor();

	private static final AllergyIntoleranceProcessor allergyProcessor = new AllergyIntoleranceProcessor();

	private static final EncounterProcessor encounterProcessor = new EncounterProcessor();
	
	private static final ConditionProcessor conditionProcessor = new ConditionProcessor();
	
	private static final MedicationRequestProcessor medicationRequestProcessor = new MedicationRequestProcessor(); 

	public Patient processFhirObject(String fhirObject) {
		logger.info("Received Message:::::{}", fhirObject);
		Patient patientObj = new Patient();
		try {
			Bundle bundle = (Bundle) fhirParser.parseResource(fhirObject);
			logger.info("Parsing the Bundle Done:::::");
			PatientDemographics patientdemographics = null;
			ArrayList<AllergyIntolerance> allergies = new ArrayList<AllergyIntolerance>();
			ArrayList<Encounter> encounters = new ArrayList<Encounter>();
			ArrayList<Condition> conditions = new ArrayList<Condition>();
			ArrayList<MedicationRequest> medicationRequests = new ArrayList<MedicationRequest>();
			ArrayList<String> serverUrls =  new ArrayList<String>();
			if (!bundle.getEntry().isEmpty()) {
				for (BundleEntryComponent entryComp : bundle.getEntry()) {
					if(entryComp.hasFullUrl()) {
						serverUrls.add(entryComp.getFullUrl());
					}
					if (entryComp.hasResource()) {
						Resource resource = entryComp.getResource();
						if (resource.getResourceType().name().equals(ResourceType.Patient.toString())) {
							patientdemographics = patientDemographicsProcessor.retrievePatientDemographics(resource);
						}
						if (resource.getResourceType().name().equals(ResourceType.AllergyIntolerance.toString())) {
							AllergyIntolerance allergy = allergyProcessor.retrieveAllergyIntolerance(resource);
							allergies.add(allergy);
						}
						if (resource.getResourceType().name().equals(ResourceType.Encounter.toString())) {
							Encounter encounter = encounterProcessor.retrieveEncounter(resource);
							encounters.add(encounter);
						}
						if (resource.getResourceType().name().equals(ResourceType.MedicationRequest.toString())) {
							MedicationRequest medicationRequest = medicationRequestProcessor.retrieveMedicationRequest(resource);
							medicationRequests.add(medicationRequest);
						}
						if (resource.getResourceType().name().equals(ResourceType.Condition.toString())) {
							Condition  condition = conditionProcessor.retrieveCondition(resource);
							conditions.add(condition);
						}
					}
				}
			}

			if (patientdemographics != null) {
				patientObj.setPatientDemographics(patientdemographics);
			}
			if (allergies != null) {
				patientObj.setAllergyIntolerance(allergies);
			}
			if (encounters != null) {
				patientObj.setEncounters(encounters);
			}
			if(conditions!=null) {
				patientObj.setCondition(conditions);
			}
			if(medicationRequests != null) {
				patientObj.setMedicationRequests(medicationRequests);
			}
			if(!serverUrls.isEmpty()) {
				patientObj.setServerUrls(serverUrls);
			}
			patientObj.setEcwPatientId("1");
			patientObj.setEcwPracticeId("1");
			patientObj.setRequestId("1");
			ObjectMapper mapper = new ObjectMapper();
			try {
				String json = mapper.writeValueAsString(patientObj);
				logger.info("PatientModelData:::::" + json);
				// parserUtil.saveDataToFile(json, sampleOutput);
			} catch (JsonProcessingException e) {
				logger.error("Error in Converting Object to String");
			}

		} catch (Exception e) {
			logger.info("Error in Processing the FHIR Object");
		}
		return patientObj;
	}

}
