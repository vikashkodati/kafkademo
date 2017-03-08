package com.tmobile.b2b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:kafkaconfig.xml")
public class SampleKafkaProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleKafkaProducerApplication.class, args);
	}
}
