package com.tmobile.b2b.kafka.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.tmobile.b2b.kafka.testcases.KafkaASyncMessageTest;
import com.tmobile.b2b.kafka.testcases.KafkaSyncMessageTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	KafkaSyncMessageTest.class,
	KafkaASyncMessageTest.class
})

public class KafkaTestSuite {

}
