package com.tmobile.kafka.example;

public class KafkaProperties {

	public static final String TOPIC = "tmotest";
	
	
	public static final String KAFKA_SERVER_URL = "localhost";
	public static final int KAFKA_SERVER_PORT = 9092;
	public static final int KAFKA_PRODUCER_BUFFER_SIZE = 64 * 1024;
	public static final int CONNECTION_TIMEOUT = 100000;
	public static final String CLIENT_ID = "SimpleConsumerDemoClient";

	public static final String GROUP_ID_CONFIG = "DemoConsumer";
	public static final String ENABLE_AUTO_COMMIT_CONFIG = "true";
	public static final String AUTO_COMMIT_INTERVAL_MS_CONFIG = "1000";
	public static final String SESSION_TIMEOUT_MS_CONFIG = "30000";
	public static final String KEY_DESERIALIZER_CLASS_CONFIG = "org.apache.kafka.common.serialization.IntegerDeserializer";
	public static final String VALUE_DESERIALIZER_CLASS_CONFIG = "org.apache.kafka.common.serialization.StringDeserializer";
	public static final String KEY_SERIALIZER_CLASS_CONFIG = "org.apache.kafka.common.serialization.IntegerSerializer";
	public static final String VALUE_SERIALIZER_CLASS_CONFIG = "org.apache.kafka.common.serialization.StringSerializer";


	
}
