package com.interopx.fhir.parser.processors;

import org.hl7.fhir.r4.model.Parameters.ParametersParameterComponent;
import org.hl7.fhir.r4.model.Property;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.StringType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.interopx.fhir.parser.model.Parameters;

@Service
public class ParametersProcessor {
	
	private static final Logger logger = LoggerFactory.getLogger(ParametersProcessor.class);
	

	public Parameters retrieveParameters(Resource parameter) {
		org.hl7.fhir.r4.model.Parameters parameterFhirObj = (org.hl7.fhir.r4.model.Parameters) parameter;
		Parameters parametersObj = new Parameters();
		if(parameterFhirObj.hasParameter()) {
			for(ParametersParameterComponent parameterComp: parameterFhirObj.getParameter()) {
				if(parameterComp.getName().equals("requestId")) {
					StringType stringObj =  (StringType) parameterComp.getValue();
					parametersObj.setRequestId(stringObj.getValue());
				}
				
				if(parameterComp.getName().equals("eCWPracticeId")) {
					StringType stringObj =  (StringType) parameterComp.getValue();
					parametersObj.setEcwPracticeId(stringObj.getValue());
				}
				
				if(parameterComp.getName().equals("patientId")) {
					StringType stringObj =  (StringType) parameterComp.getValue();
					parametersObj.setEcwPatientId(stringObj.getValue());
				}
			}
		}
		
		return parametersObj;
	}
}
