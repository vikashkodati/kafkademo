package com.tmobile.b2b.request;

import java.io.Serializable;

public class KafkaMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer offsetKey;
	private String message;
	private String topics;
	
	public Integer getOffsetKey() {
		return offsetKey;
	}
	public void setOffsetKey(Integer offsetKey) {
		this.offsetKey = offsetKey;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTopics() {
		return topics;
	}
	public void setTopics(String topics) {
		this.topics = topics;
	}
	
	
}
