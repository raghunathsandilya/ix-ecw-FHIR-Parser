package com.interopx.fhir.parser.processing;

import org.hl7.fhir.r4.model.Resource;
import org.springframework.stereotype.Service;

import com.interopx.fhir.parser.model.PatientDemographics;

@Service
public class PatientDemographicsProcessor {
	
	public PatientDemographics retrievePatientDemographics(Resource patient) {
		PatientDemographics patientDemographics = new PatientDemographics();
		
		patientDemographics.setPatientId(patient.getIdElement().getIdPart());
		
		return patientDemographics;
	}
}
