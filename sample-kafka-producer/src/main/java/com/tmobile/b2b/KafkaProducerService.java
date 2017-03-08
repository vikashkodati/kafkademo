package com.tmobile.b2b;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.kafka.clients.producer.Callback;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.tmobile.b2b.kafka.configuration.KafkaProducerConfiguration;
import com.tmobile.b2b.producer.TopicProducer;
import com.tmobile.b2b.producer.exception.ProducerException;
import com.tmobile.b2b.request.KafkaMessage;
import com.tmobile.b2b.request.KafkaOrderRequest;
import com.tmobile.b2b.request.SampleOrder;



@Component
public class KafkaProducerService {

	Logger logger = Logger.getLogger(KafkaProducerService.class.getName());


	@Autowired
	KafkaProducerConfiguration  producerConfig;

	@Autowired
	TopicProducer<Integer,String> producerClient;

	boolean isInit;

	@PostConstruct
	public void init(){

	}


	@PreDestroy
	public void destroy(){
		producerClient.shutdownProducer();
	}

	public String sendMessage(List<KafkaMessage> messages,boolean isSync) throws ProducerException{

		if(!isInit){
			producerClient.initKafkaProducer(producerConfig);
			isInit=true;

		}

		logger.debug("Message received isSync?:"+isSync);

		String status = null;
		Callback simpleCallback  = null;

		for(KafkaMessage message : messages){

			if(validateMessage(message)){

				logger.debug("Message validated");

				List<String> topicList = Arrays.asList(message.getTopics().split(","));

				logger.debug("Number of topics :" + topicList.size());

				for(String topic: topicList){

					try{

						if(isSync){

							producerClient.publishSync(topic, message.getOffsetKey(), message.getMessage());

						}else{
							simpleCallback = new SimpleKafkaProducerCallback(message.getMessage());
							producerClient.publishASyncWithCallback(topic, message.getOffsetKey(), message.getMessage(),simpleCallback);

						}

					}catch(Exception e){
						logger.error("Error occured while sending message :"+e.getMessage());
						return "failure";
					}
					status = "success";
				}


			}else{
				logger.error("Invalid message . Message is null");
				return "failure";

			}
		}


		return status;
	}



	private boolean validateMessage(KafkaMessage message) {

		if(message!=null){

			if(message.getMessage()==null || "".equalsIgnoreCase(message.getMessage())){
				return false;
			}else if(message.getOffsetKey()==null){
				return false;
			}else if(message.getTopics()==null || "".equalsIgnoreCase(message.getTopics())){
				return false;
			}
		}else {
			return false;
		}
		return true;
	}


	public String sendOrders(KafkaOrderRequest orders, boolean isSync) throws ProducerException {
		
		String status = null;
		Callback simpleCallback  = null;

		
		if(!isInit){
			producerClient.initKafkaProducer(producerConfig);
			isInit=true;
		}

		List<String> topicList = Arrays.asList(orders.getTopics().split(","));

		
		for(SampleOrder order : orders.getOrders()){

			if(order !=null){

				logger.debug("Order validated");


				logger.debug("Number of topics :" + topicList.size());
				
				Gson gson = new Gson();
				String orderData = gson.toJson(order, SampleOrder.class);

				for(String topic: topicList){

					try{

						if(isSync){

							producerClient.publishSync(topic, order.getOrderId(), orderData);

						}else{
							simpleCallback = new SimpleKafkaProducerCallback(orderData);
							producerClient.publishASyncWithCallback(topic, order.getOrderId(),orderData,simpleCallback);

						}

					}catch(Exception e){
						logger.error("Error occured while sending message :"+e.getMessage());
						return "failure";
					}
					status = "success";
				}


			}else{
				logger.error("Invalid message . Message is null");
				return "failure";

			}
		}


		return status;
	}


}
