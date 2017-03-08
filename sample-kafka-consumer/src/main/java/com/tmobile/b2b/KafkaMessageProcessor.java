package com.tmobile.b2b;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.tmobile.b2b.processor.MessageProcessor;

@Component
public class KafkaMessageProcessor implements MessageProcessor<Integer, String> {

	Logger logger = Logger.getLogger(KafkaMessageProcessor.class.getName());

	
	@Override
	public void onMessage(Integer key, String value) {
		System.out.println("Message Recieved : Key " + key + ":::: Value : "+ value );
		logger.error("Message Recieved : Key " + key + ":::: Value : "+ value );
	}

}
