package com.tmobile.b2b.kafka.consumer;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.config.ConfigException;
import org.apache.log4j.Logger;

import com.tmobile.b2b.consumer.exception.ConsumerException;
import com.tmobile.b2b.kafka.configuration.KafkaConstants;
import com.tmobile.b2b.kafka.configuration.KafkaConsumerConfiguration;
import com.tmobile.b2b.kafka.exception.KafkaClientConsumerException;
import com.tmobile.b2b.kafka.exception.KafkaConsumerConfigurationException;
import com.tmobile.b2b.processor.MessageProcessor;

public class TmoKafkaConsumerClient implements com.tmobile.b2b.consumer.TopicConsumer<Integer, String> {

	private KafkaConsumer<Integer, String> consumer ;
	private ExecutorService executor;

	Logger logger = Logger.getLogger(TmoKafkaConsumerClient.class.getName());


	public KafkaConsumer<Integer, String> initKafkaConsumer(KafkaConsumerConfiguration consumerConfig) throws ConsumerException {


		logger.debug("Initializing consumer");

		Properties props = new Properties();
		props.put(KafkaConstants.BOOTSTRAP_SERVERS_CONFIG, consumerConfig.getBootStrapServers());
		props.put(KafkaConstants.GROUP_ID_CONFIG, consumerConfig.getGroupId());
		props.put(KafkaConstants.ENABLE_AUTO_COMMIT_CONFIG, consumerConfig.getAutoCommit());
		props.put(KafkaConstants.AUTO_COMMIT_INTERVAL_MS_CONFIG, consumerConfig.getAutoCommitIntervalMs());
		props.put(KafkaConstants.SESSION_TIMEOUT_MS_CONFIG, consumerConfig.getSessionTimeoutMs());
		props.put(KafkaConstants.KEY_DESERIALIZER_CLASS_CONFIG, consumerConfig.getKeyDeSerializer());
		props.put(KafkaConstants.VALUE_DESERIALIZER_CLASS_CONFIG, consumerConfig.getValueDeSerializer());


		try{
			this.consumer = new KafkaConsumer<>(props);
		}catch(ConfigException ce){
			logger.error("Configuration Exception caught while intializing consumer with configuraion :" + ce.getMessage());
			throw new KafkaConsumerConfigurationException(ce.getMessage());
		}catch(Exception e){
			logger.error("Exception caught while intializing consumer with configuraion :" + e.getMessage());
			throw new KafkaClientConsumerException(e.getMessage());
		}
		
		logger.debug("Consumer Initialized Successfully");


		return consumer;
	}

	@Override
	public void subscribe(String topic, MessageProcessor<Integer, String> processor, int pollInterval, int threadCount) throws ConsumerException  {

		logger.info("Subscribing to topic : " + topic);
		logger.info("Subscription parameters pollInterval:threadCount : " + pollInterval + ":"+threadCount);

		try{
		executor = Executors.newFixedThreadPool(threadCount);

		for(int i=0;i<threadCount;i++){

			logger.debug("Submitting consumer for topic" + topic + "ConsumerThread : " + i);
			executor.submit(new TmoKafkaConsumerThread<Integer,String>("consumerThread:"+i,consumer, topic, processor, pollInterval));
		}
		}catch(Exception e){
			logger.error("Exception caught while submitting executor task for consuming topic :"+e.getMessage());
			throw new ConsumerException(e.getMessage());
		}

	}

	@Override
	public void shutdownConsumer() {
		logger.debug("Shutting down Consumer");
		try{
			if(null != consumer){
				consumer.close();
			}
			
		}catch(Exception e){
			logger.error("Error while closing consumer" + e.getMessage());
		}finally{

		}

	}

	





}
