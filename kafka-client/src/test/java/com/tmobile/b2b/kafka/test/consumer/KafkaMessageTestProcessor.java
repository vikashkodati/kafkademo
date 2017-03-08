package com.tmobile.b2b.kafka.test.consumer;

import com.tmobile.b2b.kafka.testcases.KafkaSyncMessageTest;
import com.tmobile.b2b.processor.MessageProcessor;

public class KafkaMessageTestProcessor implements MessageProcessor<Integer, String> {
	
	@Override
	public void onMessage(Integer key, String value) {
		String message = "key :" + key + " , Message : " + value; 
		
		if(KafkaSyncMessageTest.recievedMessage!=null){
			KafkaSyncMessageTest.recievedMessage = KafkaSyncMessageTest.recievedMessage +message;
		}else{
			KafkaSyncMessageTest.recievedMessage = message;
		}
		

	}

}
