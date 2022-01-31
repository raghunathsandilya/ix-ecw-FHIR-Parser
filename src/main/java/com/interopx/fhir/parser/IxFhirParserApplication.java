package com.interopx.fhir.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.interopx.fhir.parser.service.FhirParserService;

public class IxFhirParserApplication {

	public static void main(String[] args) {
		FhirParserService asbQueueService = new FhirParserService();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("D:\\InteropXWorkSpace-ecw\\Sample-Input.json"));
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
			asbQueueService.processFhirObject(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
