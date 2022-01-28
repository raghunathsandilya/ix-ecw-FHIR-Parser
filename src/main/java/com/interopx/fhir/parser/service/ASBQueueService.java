package com.interopx.fhir.parser.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.ExampleScenario.FHIRResourceType;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusErrorContext;
import com.azure.messaging.servicebus.ServiceBusException;
import com.azure.messaging.servicebus.ServiceBusFailureReason;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.ServiceBusReceivedMessage;
import com.azure.messaging.servicebus.ServiceBusReceivedMessageContext;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.ecw.producer.ASBProducer;
import com.ecw.utils.encryption.Compression;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interopx.fhir.parser.model.AllergyIntolerance;
import com.interopx.fhir.parser.model.Encounter;
import com.interopx.fhir.parser.model.Patient;
import com.interopx.fhir.parser.model.PatientDemographics;
import com.interopx.fhir.parser.processing.AllergyIntoleranceProcessor;
import com.interopx.fhir.parser.processing.EncounterProcessor;
import com.interopx.fhir.parser.processing.PatientDemographicsProcessor;
import com.interopx.fhir.parser.util.AES256;
import com.interopx.fhir.parser.util.ParserUtil;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;

@Service
public class ASBQueueService {

	private static final Logger logger = LoggerFactory.getLogger(ASBQueueService.class);

	@Autowired
	Environment environment;

	@Autowired
	FhirContext fhirContext;

	@Autowired
	IParser fhirParser;

	@Autowired
	PatientDemographicsProcessor patientDemographicsProcessor;

	@Autowired
	AllergyIntoleranceProcessor allergyProcessor;

	@Autowired
	ParserUtil parserUtil;

	@Value("${sample.input}")
	private String sampleInput;

	public String loadFile() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(sampleInput));
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
		return content;
	}

	public String sendMessage() {
		String response = null;
		try {
			String payloadContent = loadFile();
			logger.info("Using Env Variable Connection String:::::{}", environment.getProperty("connectionString"));
			logger.info("Using Env Variable Queue Name:::::{}", environment.getProperty("queueName"));

			response = ASBProducer.sendMsg(payloadContent, environment.getProperty("queueName"),
					environment.getProperty("connectionString"));

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return response;
	}

	public String receiveMessages() {
		CountDownLatch countdownLatch = new CountDownLatch(1);
		ServiceBusProcessorClient processorClient = (new ServiceBusClientBuilder())
				.connectionString(environment.getProperty("connectionString")).processor()
				.queueName(environment.getProperty("queueName")).processMessage((t) -> {
					try {
						processMessage(t);
					} catch (IOException var2) {
						var2.printStackTrace();
					}

				}).processError((context) -> {
					processError(context, countdownLatch);
				}).buildProcessorClient();
		logger.info("Starting the processor");
		processorClient.start();
		try {
			TimeUnit.SECONDS.sleep(10L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		processorClient.close();

		return "Success";
	}

	private String processMessage(ServiceBusReceivedMessageContext context)
			throws UnsupportedEncodingException, IOException {
		ServiceBusReceivedMessage message = context.getMessage();
		logger.info("Processing message:::::{}", message.getBody());
		String response = com.ecw.utils.encryption.AES256.decrypt(message.getBody().toString());
		String decompressedString = Compression.gZipDecompression(response);
		logger.info(decompressedString);
		processReceivedMessage(decompressedString);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		logger.info("message processed at: " + now);
		return response;
	}

	private String processReceivedMessage(String receivedMessage) {
		logger.info("Received Message:::::{}", receivedMessage);
		try {
			Bundle bundle = (Bundle) fhirParser.parseResource(receivedMessage);
			logger.info("receivedId:::::{}", bundle.getIdElement().getIdPart());

			Patient patientObj = new Patient();
			PatientDemographics patientdemographics = null;
			ArrayList<AllergyIntolerance> allergies = new ArrayList<AllergyIntolerance>();
			if (!bundle.getEntry().isEmpty()) {
				for (BundleEntryComponent entryComp : bundle.getEntry()) {
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
						}
						if (resource.getResourceType().name().equals(ResourceType.MedicationRequest.toString())) {

						}
						if (resource.getResourceType().name().equals(ResourceType.Condition.toString())) {

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
			ObjectMapper mapper = new ObjectMapper();
			try {
				String json = mapper.writeValueAsString(patientObj);
				logger.info("CCDARefModelData:::::" + json);
				parserUtil.saveDataToFile(json, "D:\\InteropXWorkSpace-ecw\\Sample-Ouput.json");
			} catch (JsonProcessingException e) {
				logger.error("Error in Converting Object to String");
			}

		} catch (Exception e) {
			logger.info("Error in Processing the FHIR Object");
		}
		return new String();
	}

	private static void processError(ServiceBusErrorContext context, CountDownLatch countdownLatch) {
		System.out.printf("Error when receiving messages from namespace: '%s'. Entity: '%s'%n",
				context.getFullyQualifiedNamespace(), context.getEntityPath());
		if (!(context.getException() instanceof ServiceBusException)) {
			System.out.printf("Non-ServiceBusException occurred: %s%n", context.getException());
		} else {
			ServiceBusException exception = (ServiceBusException) context.getException();
			ServiceBusFailureReason reason = exception.getReason();
			if (reason != ServiceBusFailureReason.MESSAGING_ENTITY_DISABLED
					&& reason != ServiceBusFailureReason.MESSAGING_ENTITY_NOT_FOUND
					&& reason != ServiceBusFailureReason.UNAUTHORIZED) {
				if (reason == ServiceBusFailureReason.MESSAGE_LOCK_LOST) {
					System.out.printf("Message lock lost for message: %s%n", context.getException());
				} else if (reason == ServiceBusFailureReason.SERVICE_BUSY) {
					try {
						TimeUnit.SECONDS.sleep(1L);
					} catch (InterruptedException var5) {
						System.err.println("Unable to sleep for period of time");
					}
				} else {
					System.out.printf("Error source %s, reason %s, message: %s%n", context.getErrorSource(), reason,
							context.getException());
				}
			} else {
				System.out.printf("An unrecoverable error occurred. Stopping processing with reason %s: %s%n", reason,
						exception.getMessage());
				countdownLatch.countDown();
			}

		}
	}

}
