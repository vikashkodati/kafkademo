package com.tmobile.b2b.kafka.testcases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.tmobile.b2b.consumer.TopicConsumer;
import com.tmobile.b2b.consumer.exception.ConsumerException;
import com.tmobile.b2b.kafka.configuration.KafkaConsumerConfiguration;
import com.tmobile.b2b.kafka.configuration.KafkaProducerConfiguration;
import com.tmobile.b2b.kafka.consumer.TmoKafkaConsumerClient;
import com.tmobile.b2b.kafka.producer.TmoKafkaProducerClient;
import com.tmobile.b2b.kafka.test.consumer.KafkaAsyncMessageTestProcessor;
import com.tmobile.b2b.kafka.test.producer.SampleProducerCallback;
import com.tmobile.b2b.producer.TopicProducer;

public class KafkaASyncMessageTest {

	public static boolean isAsyncMessageSend;

	public static String recievedMessage = null;

	//Increase waittime if testcase fails.
	private final static Integer waitTime = 2000;

	private String messagePrefix = "JunitASyncMsg";
	private String topic = "junitasynctopic";

	private KafkaConsumerConfiguration  consumerConfig = null;
	private TopicConsumer<Integer,String> consumerClient = null;

	private KafkaProducerConfiguration  producerConfig = null;
	private TopicProducer<Integer,String> producerClient = null;

	@Before
	public void setUp() throws Exception {

		recievedMessage = null;

		producerConfig = getProducerConfig();
		producerClient = new TmoKafkaProducerClient();
		producerClient.initKafkaProducer(producerConfig);

		consumerConfig = getConsumerConfig();



	}



	@Test
	public void testASyncMessage() throws IOException, ConsumerException {

		String message = messagePrefix +":"+ getRandom()+ ":" + System.currentTimeMillis();

		producerClient.publishASyncWithCallback(topic,1,message,new SampleProducerCallback());

		int loopCount = 5;
		while(true){
			loopCount--;
			if(isAsyncMessageSend || loopCount==0){
				break;
			}else{
				try {
					Thread.sleep(waitTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		consumerClient = new TmoKafkaConsumerClient();
		consumerClient.initKafkaConsumer(consumerConfig);

		consumerClient.subscribe(topic, new KafkaAsyncMessageTestProcessor(), 1000, 1);

		loopCount = 5;
		while(true){
			loopCount--;
			if(recievedMessage!=null || loopCount==0){
				break;
			}else{
				try {
					Thread.sleep(waitTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		assertNotNull(recievedMessage);
		assertTrue(recievedMessage.contains(message));     
	}


	private String getRandom() {

		return UUID.randomUUID().toString().substring(0, 6);

	}



	private KafkaConsumerConfiguration getConsumerConfig() {

		KafkaConsumerConfiguration consumerConfig = new KafkaConsumerConfiguration();

		consumerConfig.setBootStrapServers("localhost:9092");
		consumerConfig.setAutoCommit("true");
		consumerConfig.setAutoCommitIntervalMs("100");
		consumerConfig.setGroupId("junitconsumergrp");
		consumerConfig.setSessionTimeoutMs("15000");
		consumerConfig.setKeyDeSerializer("org.apache.kafka.common.serialization.IntegerDeserializer");
		consumerConfig.setValueDeSerializer("org.apache.kafka.common.serialization.StringDeserializer");
		return consumerConfig;
	}


	private static KafkaProducerConfiguration getProducerConfig() {
		KafkaProducerConfiguration producerConfig = new KafkaProducerConfiguration();

		producerConfig.setBootStrapServers("localhost:9092");

		producerConfig.setClientId("JunitProducerTest");
		producerConfig.setKeySerializer("org.apache.kafka.common.serialization.IntegerSerializer");
		producerConfig.setValueSerializer("org.apache.kafka.common.serialization.StringSerializer");
		return producerConfig;
	}

}
