package com.tmobile.b2b.kafka.producer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import javax.annotation.PreDestroy;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.config.ConfigException;
import org.apache.log4j.Logger;

import com.tmobile.b2b.kafka.configuration.KafkaConstants;
import com.tmobile.b2b.kafka.configuration.KafkaProducerConfiguration;
import com.tmobile.b2b.kafka.exception.KafkaClientProducerException;
import com.tmobile.b2b.kafka.exception.KafkaProducerConfigurationException;
import com.tmobile.b2b.producer.TopicProducer;

public class TmoKafkaProducerClient implements TopicProducer<Integer, String> {
	
	private KafkaProducer<Integer, String> producer ;
	
	Logger logger = Logger.getLogger(TmoKafkaProducerClient.class.getName());


	@Override
	public void initKafkaProducer(KafkaProducerConfiguration producerConfig) throws KafkaClientProducerException {
		logger.info("Initializing kafka producer");
		Properties props = new Properties();
		props.put(KafkaConstants.BOOTSTRAP_SERVERS_CONFIG , producerConfig.getBootStrapServers());
		props.put(KafkaConstants.CLIENT_ID, producerConfig.getClientId());
		props.put(KafkaConstants.KEY_SERIALIZER, producerConfig.getKeySerializer());
		props.put(KafkaConstants.VALUE_SERIALIZER, producerConfig.getValueSerializer());
		try{
			this.producer = new KafkaProducer<>(props);
		}catch(ConfigException ce){
			logger.error("Configuration Exception caught while intializing producer with configuraion :" + ce.getMessage());
			throw new KafkaProducerConfigurationException(ce.getMessage());
		}catch(Exception e){
			logger.error("Exception caught while intializing producer with configuraion :" + e.getMessage());
			throw new KafkaClientProducerException(e.getMessage());
		}
		logger.info("Initializing kafka producer complete");
	}


	
	
	@Override
	public boolean publishSync(String topic, Integer key, String message) throws KafkaClientProducerException {
		
		logger.info("Sending Synchronous message to topic : "+topic);
		logger.debug("message params " + key + ":" +message);
		
		try {
			producer.send(new ProducerRecord<>(topic,key,message)).get();
			logger.debug("Message Send: (" + key + ", " + message + ")");
		} catch (InterruptedException | ExecutionException e) {
			logger.error("Error during sync send " + e.getMessage());
			throw new KafkaClientProducerException(e.getMessage());
		}
		
		logger.info("Message thread executed");
		return true;

	}


	@Override
	public boolean publishASyncWithCallback(String topic, Integer key, String message, Callback callback){
		logger.info("Sending ASynchronous message to topic : "+topic);
		logger.debug("async message params " + key + ":" +message);
		
		AsyncTmoKafkaProducerThread<Integer,String> asyncPubThread = new AsyncTmoKafkaProducerThread<Integer, String>(producer, topic, key, message, callback);
		Thread thread = new Thread(asyncPubThread);
		thread.run();
		logger.info("Message thread executed");
		return true;
	}


	@Override
	public void shutdownProducer(){
		logger.info("Shutting down producer");
		try{
			if(producer !=null)
			producer.close();
		}catch(Exception e){
			logger.error("Exception while closing producer" + e.getMessage());
		}
		
		logger.info("Producer shutdown");	
	}

	
	@PreDestroy
	public void destory(){
		shutdownProducer();
	}

	
	
}
