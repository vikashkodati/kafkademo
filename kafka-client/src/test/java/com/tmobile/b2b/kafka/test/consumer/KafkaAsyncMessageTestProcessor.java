package com.tmobile.b2b.kafka.test.consumer;

import com.tmobile.b2b.kafka.testcases.KafkaASyncMessageTest;
import com.tmobile.b2b.processor.MessageProcessor;

public class KafkaAsyncMessageTestProcessor implements MessageProcessor<Integer, String> {
	
	@Override
	public void onMessage(Integer key, String value) {
		String message = "key :" + key + " , Message : " + value; 
		
		if(KafkaASyncMessageTest.recievedMessage!=null){
			KafkaASyncMessageTest.recievedMessage = KafkaASyncMessageTest.recievedMessage +message;
		}else{
			KafkaASyncMessageTest.recievedMessage = message;
		}
	}

}
