package com.tmobile.b2b.processor;

public interface MessageProcessor<K,V> {

	public void onMessage(K key, V value);
}
