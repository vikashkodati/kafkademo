package com.tmobile.b2b.kafka.configuration;

public class KafkaProducerConfiguration{
	
	private String bootStrapServers;
	private String clientId;
	private String keySerializer;
	private String valueSerializer;
	private String maxBlockMs;
	
	
	public String getBootStrapServers() {
		return bootStrapServers;
	}
	public void setBootStrapServers(String bootStrapServers) {
		this.bootStrapServers = bootStrapServers;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getKeySerializer() {
		return keySerializer;
	}
	public void setKeySerializer(String keySerializer) {
		this.keySerializer = keySerializer;
	}
	public String getValueSerializer() {
		return valueSerializer;
	}
	public void setValueSerializer(String valueSerializer) {
		this.valueSerializer = valueSerializer;
	}
	public String getMaxBlockMs() {
		return maxBlockMs;
	}
	public void setMaxBlockMs(String maxBlockMs) {
		this.maxBlockMs = maxBlockMs;
	}
	
	

}
