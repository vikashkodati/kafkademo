package com.tmobile.b2b;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ImportResource("classpath:kafkaconfig.xml")
@SpringBootTest
public class SampleKafkaConsumerApplicationTests {

	@Test
	public void contextLoads() {
	}

}
