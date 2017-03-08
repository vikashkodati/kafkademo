package com.tmobile.b2b.kafka.exception;

public class KafkaConsumerConfigurationException extends KafkaClientConsumerException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public KafkaConsumerConfigurationException(String message) {
        super(message);
    }
	
}
