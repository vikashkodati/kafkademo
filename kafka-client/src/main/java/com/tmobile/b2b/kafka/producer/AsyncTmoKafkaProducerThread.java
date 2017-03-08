package com.tmobile.b2b.kafka.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;

public class AsyncTmoKafkaProducerThread<K,V> implements Runnable{

	private final KafkaProducer<K, V> producer;
	private final String topic;
	private final K key;
	private final V message;
	private Callback callback;
	
	Logger logger = Logger.getLogger(AsyncTmoKafkaProducerThread.class.getName());

	

	public AsyncTmoKafkaProducerThread(KafkaProducer<K, V> producer, String topic , K key, V message,Callback callback){

	
		this.producer = producer;
		this.topic = topic;
		this.key = key;
		this.message = message;
		this.callback = callback;
	}
	
	@Override
	public void run() {
	
		logger.info("Running the TMO Producer Thread");

		logger.debug("Sending async message");
		try{
			producer.send(new ProducerRecord<>(topic,key,message),callback);
		}catch (Exception e) {
			logger.error("Error during sync send " + e.getMessage());
		}
		
		logger.debug("Producer thread executed successfully");

	}
	
}
