package com.interopx.fhir.parser.processors;

import org.hl7.fhir.r4.model.Resource;
import org.springframework.stereotype.Service;

import com.interopx.fhir.parser.model.MedicationRequest;
import com.interopx.fhir.parser.util.ParserUtil;


/**
 * This class will process the MedicationRequest FHIR Object and Converts it to MedicationRequest POJO
 * 
 * @author nbashyam
 *
 */
@Service
public class MedicationRequestProcessor {
	
	public MedicationRequest retrieveMedicationRequest(Resource medicationRequest) {
		org.hl7.fhir.r4.model.MedicationRequest medicationRequestFhirObj = (org.hl7.fhir.r4.model.MedicationRequest) medicationRequest;
		
		MedicationRequest medicaiontRequestObj = new MedicationRequest();
		
		if(medicationRequestFhirObj.hasId()) {
			medicaiontRequestObj.setMedicationRequestId(medicationRequestFhirObj.getIdElement().getIdPart());
		}
		
		if(medicationRequestFhirObj.hasStatus()) {
	//		medicaiontRequestObj.setStatus(medicationRequestFhirObj.getStatus().name());
		}
		
		if(medicationRequestFhirObj.hasIntent()) {
	//		medicaiontRequestObj.setIntent(medicationRequestFhirObj.getIntent().name());
		}
		
		if(medicationRequestFhirObj.hasMedicationCodeableConcept()) {
			medicaiontRequestObj.setMedication(ParserUtil.readCodeElements(medicationRequestFhirObj.getMedicationCodeableConcept()));
		}
		
		if(medicationRequestFhirObj.hasAuthoredOn()) {
			medicaiontRequestObj.setAuthoredOnDateTime(medicationRequestFhirObj.getAuthoredOn());
		}
		
		if(medicationRequestFhirObj.hasRequester()) {
//			medicaiontRequestObj.setRequester(ParserUtil.readReferenceElement(medicationRequestFhirObj.getRequester()));
		}
		
		return medicaiontRequestObj;
	}
}
