package com.interopx.fhir.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interopx.fhir.parser.model.Patient;
import com.interopx.fhir.parser.service.FhirParserService;

public class IxFhirParserApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(IxFhirParserApplication.class);

	public static void main(String[] args) {
		FhirParserService fhirParserService = new FhirParserService();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("//users//nbashyam//Downloads//ecw//Sample-Input.json"));
			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			String ls = System.getProperty("line.separator");

			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}

			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			reader.close();
			String content = stringBuilder.toString();
			Patient patientModelData = fhirParserService.processFhirObject(content);
			
			ObjectMapper mapper = new ObjectMapper();
			try {
				String json = mapper.writeValueAsString(patientModelData);
				logger.info("Patient Model Data:::::" + json);
			} catch (JsonProcessingException e) {
				logger.error("Error in Converting Object to String");
			}
		} catch (IOException e) {
			logger.error("Error in processing the request");
		}
	}

}
