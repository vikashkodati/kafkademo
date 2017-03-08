package com.tmobile.b2b.producer;

import org.apache.kafka.clients.producer.Callback;

import com.tmobile.b2b.kafka.configuration.KafkaProducerConfiguration;
import com.tmobile.b2b.producer.exception.ProducerException;

public interface TopicProducer<K,V> {

	public void initKafkaProducer(KafkaProducerConfiguration producerConfig) throws ProducerException;
	
	boolean publishSync(String topic, K key, V message) throws ProducerException;

	boolean publishASyncWithCallback(String topic, Integer key, String message, Callback callback);

	public void shutdownProducer();


}
