package com.tmobile.b2b.consumer;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.tmobile.b2b.consumer.exception.ConsumerException;
import com.tmobile.b2b.kafka.configuration.KafkaConsumerConfiguration;
import com.tmobile.b2b.processor.MessageProcessor;

public interface TopicConsumer<K,V> {
	
	public KafkaConsumer<K, V> initKafkaConsumer(KafkaConsumerConfiguration consumerConfig) throws ConsumerException ;
		
	public void subscribe(String topic, MessageProcessor<K,V> processor, int pollInterval, int threadCount) throws ConsumerException ;
	
	public void shutdownConsumer();

}
