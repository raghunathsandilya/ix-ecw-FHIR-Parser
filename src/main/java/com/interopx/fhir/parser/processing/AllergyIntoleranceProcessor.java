package com.interopx.fhir.parser.processing;

import org.hl7.fhir.r4.model.AllergyIntolerance;
import org.hl7.fhir.r4.model.Resource;
import org.springframework.stereotype.Service;

@Service
public class AllergyIntoleranceProcessor {
	
	public AllergyIntolerance retrieveAllergyIntolerance(Resource allergy) {
		AllergyIntolerance allergyIntolerance  = new AllergyIntolerance();
		return allergyIntolerance;
	}

}
