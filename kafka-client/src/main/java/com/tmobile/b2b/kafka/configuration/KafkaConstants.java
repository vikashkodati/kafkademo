package com.tmobile.b2b.kafka.configuration;

public interface KafkaConstants {

	public static final String BOOTSTRAP_SERVERS_CONFIG = "bootstrap.servers";
	public static final String GROUP_ID_CONFIG = "group.id";
	public static final String ENABLE_AUTO_COMMIT_CONFIG = "enable.auto.commit";
	public static final String AUTO_COMMIT_INTERVAL_MS_CONFIG = "auto.commit.interval.ms";
	public static final String SESSION_TIMEOUT_MS_CONFIG = "session.timeout.ms";
	public static final String KEY_DESERIALIZER_CLASS_CONFIG = "key.deserializer";
	public static final String VALUE_DESERIALIZER_CLASS_CONFIG = "value.deserializer";
	
	public static final String CLIENT_ID = "client.id";
	public static final String KEY_SERIALIZER = "key.serializer";
	public static final String VALUE_SERIALIZER = "value.serializer";

}
