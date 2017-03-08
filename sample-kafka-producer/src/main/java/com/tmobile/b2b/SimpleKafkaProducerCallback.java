package com.tmobile.b2b;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.log4j.Logger;

public class SimpleKafkaProducerCallback implements Callback{
	
	private  final String callbackMessage;
	Logger logger = Logger.getLogger(SimpleKafkaProducerCallback.class.getName());

	public SimpleKafkaProducerCallback (String callbackMsg){
		this.callbackMessage = callbackMsg;
	}
	
	@Override
	public void onCompletion(RecordMetadata metadata, Exception exception) {
		logger.error("Successfully send message" + callbackMessage);
		
	}

}
