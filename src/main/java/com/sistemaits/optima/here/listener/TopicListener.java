package com.sistemaits.optima.here.listener;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TopicListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(TopicListener.class);

	
	@JmsListener(destination = "topic", containerFactory = "pubSubFactory",
			subscription="consumerGroup1", concurrency = "5" )
	public void receive(Object message) throws JMSException {
		TextMessage msg = (TextMessage)message;
		log.info("Message received:" + msg.getBody(String.class));
	}

}
