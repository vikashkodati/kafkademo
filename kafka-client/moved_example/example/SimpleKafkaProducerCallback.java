package com.tmobile.kafka.example;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class SimpleKafkaProducerCallback implements Callback{
	
	private  final String callbackMessage;

	public SimpleKafkaProducerCallback (String callbackMsg){
		this.callbackMessage = callbackMsg;
	}
	
	@Override
	public void onCompletion(RecordMetadata metadata, Exception exception) {
		System.out.println("Successfully send message" + callbackMessage);
		
	}

}
