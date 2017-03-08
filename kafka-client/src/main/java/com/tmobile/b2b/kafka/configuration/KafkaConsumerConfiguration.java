package com.tmobile.b2b.kafka.configuration;

public class KafkaConsumerConfiguration {

	private String bootStrapServers;
	private String groupId;
	private String autoCommit;
	private String autoCommitIntervalMs;
	private String sessionTimeoutMs;
	private String keyDeSerializer;
	private String valueDeSerializer;
	
	public String getBootStrapServers() {
		return bootStrapServers;
	}
	public void setBootStrapServers(String bootStrapServers) {
		this.bootStrapServers = bootStrapServers;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getAutoCommit() {
		return autoCommit;
	}
	public void setAutoCommit(String autoCommit) {
		this.autoCommit = autoCommit;
	}
	public String getAutoCommitIntervalMs() {
		return autoCommitIntervalMs;
	}
	public void setAutoCommitIntervalMs(String autoCommitIntervalMs) {
		this.autoCommitIntervalMs = autoCommitIntervalMs;
	}
	public String getSessionTimeoutMs() {
		return sessionTimeoutMs;
	}
	public void setSessionTimeoutMs(String sessionTimeoutMs) {
		this.sessionTimeoutMs = sessionTimeoutMs;
	}
	public String getKeyDeSerializer() {
		return keyDeSerializer;
	}
	public void setKeyDeSerializer(String keyDeSerializer) {
		this.keyDeSerializer = keyDeSerializer;
	}
	public String getValueDeSerializer() {
		return valueDeSerializer;
	}
	public void setValueDeSerializer(String valueDeSerializer) {
		this.valueDeSerializer = valueDeSerializer;
	}
	
	
}
