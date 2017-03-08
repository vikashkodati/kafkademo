package com.tmobile.b2b.kafka.exception;

import com.tmobile.b2b.consumer.exception.ConsumerException;

public class KafkaClientConsumerException extends ConsumerException {

	private static final long serialVersionUID = 1L;
	
	public KafkaClientConsumerException(String message) {
        super(message);
    }

}
