package com.tmobile.b2b.kafka.exception;

import com.tmobile.b2b.producer.exception.ProducerException;

public class KafkaClientProducerException extends ProducerException {

	private static final long serialVersionUID = 1L;
	
	public KafkaClientProducerException(String message) {
        super(message);
    }

}
