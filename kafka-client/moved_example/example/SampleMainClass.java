package com.tmobile.kafka.example;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.tmobile.consumer.TopicConsumer;
import com.tmobile.kafka.configuration.KafkaConsumerConfiguration;
import com.tmobile.kafka.configuration.KafkaProducerConfiguration;
import com.tmobile.kafka.consumer.TmoKafkaConsumerClient;
import com.tmobile.kafka.producer.TmoKafkaProducerClient;
import com.tmobile.producer.TopicProducer;

public class SampleMainClass {

	public static void main(String[] args) {
		System.out.println("Hello Starting Kafka Example");
		
		KafkaConsumerConfiguration  consumerConfig = null;
		TopicConsumer<Integer,String> consumerClient = null;
		
		
		//Get ConsumerConfiguarion
		consumerConfig = getConsumerConfig();
		
		consumerClient = new TmoKafkaConsumerClient();
		
		
		consumerClient.initKafkaConsumer(consumerConfig);
		
		consumerClient.subscribe(KafkaProperties.TOPIC, new KafkaMessageProcessor(), 2000, 3);
		
		
		
		
		KafkaProducerConfiguration  producerConfig = null;
		TopicProducer<Integer,String> producerClient = null;
		
		producerConfig = getProducerConfig();
		producerClient = new TmoKafkaProducerClient();
		
		producerClient.initKafkaProducer(producerConfig);
		
		sendASyncMessage(producerClient,10);
		sendSyncMessage(producerClient,10);
		
		producerClient.shutdownProducer();
		
		
		
		
		

	}

	private static void sendSyncMessage(TopicProducer<Integer, String> producerClient, int numberOfMsgs) {
		for(int i=0;i<numberOfMsgs;i++){

			Date currentDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:S"); 
			String dateStr = sdf.format(currentDate);

			String messageString = "Sync Message : "+i+ ", sendAt :"+dateStr;
			producerClient.publishSync(KafkaProperties.TOPIC,new Integer(i),messageString);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private static void sendASyncMessage(TopicProducer<Integer, String> producerClient, int numberOfMsgs) {
		
		for(int i=0;i<numberOfMsgs;i++){

			Date currentDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:S"); 
			String dateStr = sdf.format(currentDate);

			String messageString = "ASync Message : "+i+ ", sendAt :"+dateStr;
			producerClient.publishASyncWithCallback(KafkaProperties.TOPIC,new Integer(i),messageString,new SimpleKafkaProducerCallback("Callback of message"+messageString));

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private static KafkaProducerConfiguration getProducerConfig() {
		KafkaProducerConfiguration producerConfig = new KafkaProducerConfiguration();
		
		//Properties will be replaced by proper configuration server values in RealWorld
		String bootStrapServers = KafkaProperties.KAFKA_SERVER_URL+":"+KafkaProperties.KAFKA_SERVER_PORT; 
		producerConfig.setBootStrapServers(bootStrapServers);
		
		producerConfig.setClientId(KafkaProperties.CLIENT_ID);
		producerConfig.setKeySerializer(KafkaProperties.KEY_SERIALIZER_CLASS_CONFIG);
		producerConfig.setValueSerializer(KafkaProperties.VALUE_SERIALIZER_CLASS_CONFIG);
		
		return producerConfig;
	}

	private static KafkaConsumerConfiguration getConsumerConfig() {
		
		
		KafkaConsumerConfiguration consumerConfig = new KafkaConsumerConfiguration();

		//Properties will be replaced by proper configuration server values in RealWorld
		String bootStrapServers = KafkaProperties.KAFKA_SERVER_URL+":"+KafkaProperties.KAFKA_SERVER_PORT; 
		consumerConfig.setBootStrapServers(bootStrapServers);
		consumerConfig.setAutoCommit(KafkaProperties.ENABLE_AUTO_COMMIT_CONFIG);
		consumerConfig.setAutoCommitIntervalMs(KafkaProperties.AUTO_COMMIT_INTERVAL_MS_CONFIG);
		consumerConfig.setGroupId(KafkaProperties.GROUP_ID_CONFIG);
		consumerConfig.setSessionTimeoutMs(KafkaProperties.SESSION_TIMEOUT_MS_CONFIG);
		consumerConfig.setKeyDeSerializer(KafkaProperties.KEY_DESERIALIZER_CLASS_CONFIG);
		consumerConfig.setValueDeSerializer(KafkaProperties.VALUE_DESERIALIZER_CLASS_CONFIG);
		return consumerConfig;
	}

}
