package com.tmobile.b2b.request;

import java.io.Serializable;
import java.util.List;

public class KafkaOrderRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	List<SampleOrder> orders;
	String topics;

	public String getTopics() {
		return topics;
	}

	public void setTopics(String topics) {
		this.topics = topics;
	}

	public List<SampleOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<SampleOrder> orders) {
		this.orders = orders;
	}
	
	

}
