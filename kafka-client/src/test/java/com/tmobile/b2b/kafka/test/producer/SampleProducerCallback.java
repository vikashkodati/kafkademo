package com.tmobile.b2b.kafka.test.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.tmobile.b2b.kafka.testcases.KafkaASyncMessageTest;

public class SampleProducerCallback implements Callback{
	
	
	@Override
	public void onCompletion(RecordMetadata metadata, Exception exception) {
		KafkaASyncMessageTest.isAsyncMessageSend = true;
		
	}

}
