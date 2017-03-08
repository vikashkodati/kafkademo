package com.tmobile.b2b;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tmobile.b2b.consumer.TopicConsumer;
import com.tmobile.b2b.consumer.exception.ConsumerException;
import com.tmobile.b2b.kafka.configuration.KafkaConsumerConfiguration;
import com.tmobile.b2b.processor.MessageProcessor;

@Component
public class SimpleConsumer implements CommandLineRunner {

	Logger logger = Logger.getLogger(SimpleConsumer.class.getName());

	
	@Autowired
	KafkaConsumerConfiguration  consumerConfig;
	
	@Autowired
	TopicConsumer<Integer,String> consumerClient;
	
	@Autowired
	MessageProcessor<Integer, String> messageProcessor;
	
	@Override
	public void run(String... arg0) throws Exception {
		
		int pollInterval = 1000;
		int threadCount = 1; // Will need multiple threads when connecting to kafka cluster
		String topic = "default";
		if(arg0!=null && arg0.length > 0)
		{

			String topicInput = arg0[0];
			String pollIntervalInput = arg0[1];
			
			if(topicInput!=null && !"".equalsIgnoreCase(topicInput)){
				topic = topicInput;
			} 
			
			try{
				pollInterval = Integer.parseInt(pollIntervalInput);
			}catch(Exception e){
				
			}
			
			
		}
		
		runConsumer(topic,pollInterval,threadCount);
	}

	private void runConsumer(String topic, int pollInterval, int threadCount) throws ConsumerException {
		
		
		//Get ConsumerConfiguarion
		
			
		
		consumerClient.initKafkaConsumer(consumerConfig);
		
		consumerClient.subscribe(topic,messageProcessor , pollInterval, 1);
		
		
		
		
		
	}

	
}
