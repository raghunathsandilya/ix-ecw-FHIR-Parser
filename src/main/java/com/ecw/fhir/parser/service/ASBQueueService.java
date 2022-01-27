package com.ecw.fhir.parser.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusErrorContext;
import com.azure.messaging.servicebus.ServiceBusException;
import com.azure.messaging.servicebus.ServiceBusFailureReason;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.ServiceBusReceivedMessage;
import com.azure.messaging.servicebus.ServiceBusReceivedMessageContext;
import com.ecw.producer.ASBProducer;
import com.ecw.utils.encryption.AES256;

@Service
public class ASBQueueService {

	private static final Logger logger = LoggerFactory.getLogger(ASBQueueService.class);

	@Autowired
	Environment environment;

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
			logger.info("Using QueueName:::::{}", environment.getProperty("queueName"));
			logger.info("Using ConnectionString:::::{}", environment.getProperty("connectionString"));
			response = ASBProducer.sendMsg(payloadContent, environment.getProperty("queueName"),
					environment.getProperty("connectionString"));
			logger.info("Response from Send Msg:::::{}", response);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String receiveMessages() {
		CountDownLatch countdownLatch = new CountDownLatch(1);
		String receivedMessages = null;
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
		System.out.println("Starting the processor");
		processorClient.start();
		try {
			TimeUnit.SECONDS.sleep(10L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Success";
	}

	private static void processMessage(ServiceBusReceivedMessageContext context)
			throws UnsupportedEncodingException, IOException {
		System.out.println("Here");
		ServiceBusReceivedMessage message = context.getMessage();
		System.out.printf("Processing message. Session: %s, Sequence #: %s. Contents: %s%n", message.getMessageId(),
				message.getSequenceNumber(), message.getBody());
		System.out.println("MessageBody::::{}"+message.getBody().toString());
		String decryptedString =  com.ecw.fhir.parser.util.AES256.decrypt(new String(message.getBody().toBytes()));
		System.out.println("Descrypted String ******************************"+decryptedString);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println("message processed at: " + now);
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
