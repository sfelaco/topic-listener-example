package com.sistemaits.optima.here.controller;

import org.apache.activemq.artemis.jms.client.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/")
@Slf4j
public class REST {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	 
	@GetMapping
	public void send() {
		log.info("SEND####");
		jmsTemplate.convertAndSend(new ActiveMQTopic("topic"), "Hello world!");
	}

}
