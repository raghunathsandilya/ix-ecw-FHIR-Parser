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
import com.interopx.fhir.parser.model.AllergyIntolerance;
import com.interopx.fhir.parser.model.Patient;
import com.interopx.fhir.parser.model.PatientDemographics;
import com.interopx.fhir.parser.processing.AllergyIntoleranceProcessor;
import com.interopx.fhir.parser.processing.PatientDemographicsProcessor;

import ca.uhn.fhir.context.FhirContext;

@Service
public class ASBQueueService {

	private static final Logger logger = LoggerFactory.getLogger(ASBQueueService.class);

	@Autowired
	Environment environment;
	
	@Autowired
	FhirContext fhirContext;
	
	@Autowired
	PatientDemographicsProcessor patientDemographicsProcessor;
	
	@Autowired
	AllergyIntoleranceProcessor allergyProcessor;

	public String loadFile() throws IOException {
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
		return content;
	}

	public String sendMessage() {
		String response = null;
		try {
			String payloadContent = loadFile();
			logger.info("Using Env Variable Connection String:::::{}",environment.getProperty("connectionString"));
			logger.info("Using Env Variable Queue Name:::::{}",environment.getProperty("queueName"));
			
		    ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
		            .connectionString(environment.getProperty("connectionString"))
		            .sender()
		            .queueName(environment.getProperty("queueName"))
		            .buildClient();

		    senderClient.sendMessage(new ServiceBusMessage(payloadContent));
		}  catch (IOException e) {
			e.printStackTrace();
		}
		return null;
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
		
		return "Success";
	}

	private String processMessage(ServiceBusReceivedMessageContext context)
			throws UnsupportedEncodingException, IOException {
		ServiceBusReceivedMessage message = context.getMessage();
		logger.info("Processing message:::::{}", message.getBody());
		String response = message.getBody().toString();
		processReceivedMessage(response);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		logger.info("message processed at: " + now);
		return response;
	}

	private String processReceivedMessage(String receivedMessage) {
		logger.info("Received Message:::::{}",receivedMessage);
		Bundle bundle = (Bundle) fhirContext.newJsonParser().parseResource(receivedMessage);
		logger.info("receivedId:::::{}",bundle.getIdElement().getIdPart());
		
		Patient patientObj = new Patient();
		PatientDemographics patientdemographics=null;
		ArrayList<AllergyIntolerance> allergies = null;
		if(!bundle.getEntry().isEmpty()) {
			for(BundleEntryComponent entryComp: bundle.getEntry()) {
				if(entryComp.hasResource()) {
					Resource resource = entryComp.getResource();
					if(resource.getResourceType().name().equals(ResourceType.Patient.toString())) {
						patientdemographics = patientDemographicsProcessor.retrievePatientDemographics(resource);
					}
					if(resource.getResourceType().name().equals(ResourceType.AllergyIntolerance.toString())) {
						org.hl7.fhir.r4.model.AllergyIntolerance allergy = null;
					}
					if(resource.getResourceType().name().equals(ResourceType.Encounter.toString())) {
						
					}
					if(resource.getResourceType().name().equals(ResourceType.MedicationRequest.toString())) {
						
					}
					if(resource.getResourceType().name().equals(ResourceType.Condition.toString())) {
						
					}
				}
			}
		}
		
		if(patientdemographics!=null) {
			patientObj.setPatientDemographics(patientdemographics);
		}
		
		
		
		
		try {
			
		}catch(Exception e) {
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
