package com.interopx.fhir.parser.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
import com.interopx.fhir.parser.model.Practitioner;
import com.interopx.fhir.parser.processors.AllergyIntoleranceProcessor;
import com.interopx.fhir.parser.processors.ConditionProcessor;
import com.interopx.fhir.parser.processors.EncounterProcessor;
import com.interopx.fhir.parser.processors.MedicationRequestProcessor;
import com.interopx.fhir.parser.processors.PatientDemographicsProcessor;
import com.interopx.fhir.parser.processors.PractitionerProcessor;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;

@Service
public class FhirParserService {

	private static final Logger logger = LoggerFactory.getLogger(FhirParserService.class);

	private static final FhirContext fhirContext = FhirContext.forR4();

	private static final IParser fhirParser = fhirContext.newJsonParser().setPrettyPrint(true);

	private static PatientDemographicsProcessor patientDemographicsProcessor = new PatientDemographicsProcessor();

	private static AllergyIntoleranceProcessor allergyProcessor = new AllergyIntoleranceProcessor();

	private static EncounterProcessor encounterProcessor = new EncounterProcessor();

	private static ConditionProcessor conditionProcessor = new ConditionProcessor();

	private static MedicationRequestProcessor medicationRequestProcessor = new MedicationRequestProcessor();

	public Patient processFhirObject(String fhirObject) {
		logger.info("Reading FHIR Object:::::{}", fhirObject);
		Patient patientObj = new Patient();
		try {
			Bundle bundle = (Bundle) fhirParser.parseResource(fhirObject);
			ArrayList<PatientDemographics> patientDemographicsList = new ArrayList<PatientDemographics>();
			ArrayList<AllergyIntolerance> allergies = new ArrayList<AllergyIntolerance>();
			ArrayList<Encounter> encounters = new ArrayList<Encounter>();
			ArrayList<Condition> conditions = new ArrayList<Condition>();
			ArrayList<MedicationRequest> medicationRequests = new ArrayList<MedicationRequest>();
			ArrayList<Practitioner> practitioners = new ArrayList<Practitioner>();
			ArrayList<String> serverUrls = new ArrayList<String>();
			if (!bundle.getEntry().isEmpty()) {
				for (BundleEntryComponent entryComp : bundle.getEntry()) {
					if (entryComp.hasFullUrl()) {
						serverUrls.add(entryComp.getFullUrl());
					}
					if (entryComp.hasResource()) {
						Resource resource = entryComp.getResource();
						if (resource.getResourceType().name().equals(ResourceType.Patient.toString())) {
							PatientDemographics patientdemographics = patientDemographicsProcessor.retrievePatientDemographics(resource,entryComp.getFullUrl());
							patientDemographicsList.add(patientdemographics);
						}
						if (resource.getResourceType().name().equals(ResourceType.AllergyIntolerance.toString())) {
							AllergyIntolerance allergy = allergyProcessor.retrieveAllergyIntolerance(resource,bundle,entryComp.getFullUrl());
							allergies.add(allergy);
						}
						if (resource.getResourceType().name().equals(ResourceType.Encounter.toString())) {
							Encounter encounter = encounterProcessor.retrieveEncounter(resource,bundle,entryComp.getFullUrl());
							encounters.add(encounter);
						}
						if (resource.getResourceType().name().equals(ResourceType.MedicationRequest.toString())) {
							MedicationRequest medicationRequest = medicationRequestProcessor
									.retrieveMedicationRequest(resource,bundle,entryComp.getFullUrl());
							medicationRequests.add(medicationRequest);
						}
						if (resource.getResourceType().name().equals(ResourceType.Condition.toString())) {
							Condition condition = conditionProcessor.retrieveCondition(resource,bundle,entryComp.getFullUrl());
							conditions.add(condition);
						}
					}
				}
			}

			if (patientDemographicsList != null) {
				patientObj.setPatientDemographics(patientDemographicsList);
			}
			if (allergies != null) {
				patientObj.setAllergyIntolerance(allergies);
			}
			if (encounters != null) {
				patientObj.setEncounters(encounters);
			}
			if (conditions != null) {
				patientObj.setCondition(conditions);
			}
			if (medicationRequests != null) {
				patientObj.setMedicationRequests(medicationRequests);
			}
			if (!serverUrls.isEmpty()) {
				patientObj.setServerUrls(serverUrls);
			}
			patientObj.setEcwPatientId("1");
			patientObj.setEcwPracticeId("1");
			patientObj.setRequestId("1");

		} catch (Exception e) {
			logger.info("Error in Processing the FHIR Object");
		}
		return patientObj;
	}

}
