package com.ecw.fhir.parser.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecw.fhir.parser.service.ASBQueueService;

@RestController
public class ParserController {

	@Autowired
	ASBQueueService queueService;
	
	@RequestMapping(value = "/sendMessages", method = RequestMethod.GET)
    public String sendMessageToQueue(HttpServletRequest request, HttpServletResponse response) {
        return queueService.sendMessage();
    }
	
	@RequestMapping(value = "/readMessages", method = RequestMethod.GET)
    public String readMessageFromQueue(HttpServletRequest request, HttpServletResponse response) {
		try {
			queueService.receiveMessages();
		}catch(Exception e) {
			System.out.println("Exception in receiving the messages");
		}
        return "Success";
    }
	
}
