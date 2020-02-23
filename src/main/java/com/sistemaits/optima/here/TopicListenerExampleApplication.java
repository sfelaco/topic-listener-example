package com.sistemaits.optima.here;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class TopicListenerExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(TopicListenerExampleApplication.class, args);
	}

}
