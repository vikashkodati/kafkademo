package com.tmobile.kafka.example;

import com.tmobile.processor.MessageProcessor;

public class KafkaMessageProcessor implements MessageProcessor<Integer, String> {

	@Override
	public void onMessage(Integer key, String value) {
		System.out.println("Message Recieved : Key " + key + ":::: Value : "+ value );		
	}

}
