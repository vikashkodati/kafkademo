package com.tmobile.b2b.kafka.consumer;

import java.util.Collections;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.log4j.Logger;

import com.tmobile.b2b.processor.MessageProcessor;

public class TmoKafkaConsumerThread<K,V> implements Runnable{

	private KafkaConsumer<K,V> consumer;
	private String topic;
	private MessageProcessor<K,V> processor;
	private int pollInterval;
	private String consumerName;
	private boolean work;
	
	Logger logger = Logger.getLogger(TmoKafkaConsumerThread.class.getName());

	
	public TmoKafkaConsumerThread(String name,KafkaConsumer<K,V> consumer,String topic,MessageProcessor<K,V> processor, int pollInterval){
	
		this.consumer = consumer;
		this.topic = topic;
		this.processor = processor;
		this.pollInterval = pollInterval;
		this.consumerName = name;
		work = true;
	
	}
	
	
	public void run(){

		logger.info("Starting consumer : "+ consumerName);
		while(work){
			logger.debug(consumerName+" : Time is " + System.currentTimeMillis());

			consumer.subscribe(Collections.singletonList(this.topic));
			ConsumerRecords<K,V> records = consumer.poll(pollInterval);
			for (ConsumerRecord<K,V> record : records) {
				logger.info("Record received in consumer "+ consumerName);
				processor.onMessage(record.key(), record.value());
			}

		}
		
		consumer.close();
		
	}


	public void stopWork(){
		
		this.work = false;
		logger.debug(consumerName+" : Work stopped ");

	}
	
	
	

	
	
}
