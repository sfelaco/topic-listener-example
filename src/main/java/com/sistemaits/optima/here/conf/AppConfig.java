package com.sistemaits.optima.here.conf;

import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQTopicConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

@Configuration
@EnableJms
public class AppConfig {
	
	  @Value("${spring.artemis.host}")
	  private String brokerUrl;
	  
	  @Value("${spring.artemis.user}")

	  private String brokerUsername;
	  
	  @Value("${spring.artemis.password}")
	  private String brokerPasswd;

	  @Bean
	  public ConnectionFactory receiverActiveMQConnectionFactory() throws JMSException {
		  ActiveMQConnectionFactory connectionFactory = new ActiveMQTopicConnectionFactory();
		  connectionFactory.setBrokerURL(brokerUrl);
	    return connectionFactory;
	  }

	  @Bean("pubSubFactory")
	  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() throws JMSException {
	    DefaultJmsListenerContainerFactory factory =
	        new DefaultJmsListenerContainerFactory();
	    factory.setPubSubDomain(true);
	    factory.setSubscriptionShared(true);
	    factory.setSubscriptionDurable(true);
	    factory
	        .setConnectionFactory(receiverActiveMQConnectionFactory());
	    factory.setConcurrency("5");
	    factory.setDestinationResolver(new DynamicDestinationResolver()); //or new DynamicDestinationResolver()

	    return factory;
	  }

	  
	  @Bean
		public JmsTemplate jmsTemplate() throws JMSException{
		    JmsTemplate template = new JmsTemplate();
		    template.setConnectionFactory(receiverActiveMQConnectionFactory());
		    template.setPubSubDomain(true);
		    template.setDeliveryMode(DeliveryMode.PERSISTENT);
			return template;
		}
	
}

