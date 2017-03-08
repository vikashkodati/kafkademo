package com.tmobile.b2b.kafka.exception;

public class KafkaProducerConfigurationException extends KafkaClientProducerException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public KafkaProducerConfigurationException(String message) {
        super(message);
    }
	
}
