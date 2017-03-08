package com.tmobile.b2b;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tmobile.b2b.producer.exception.ProducerException;
import com.tmobile.b2b.request.KafkaMessage;
import com.tmobile.b2b.request.KafkaOrderRequest;
import com.tmobile.b2b.response.KafkaResponse;



@RestController
public class KafkaController {

	@Autowired
	KafkaProducerService producerService;
	
	@RequestMapping(value="/sendSyncMsg", method = RequestMethod.POST)
	public KafkaResponse sendMessage(@RequestBody List<KafkaMessage> messages) throws ProducerException{
		KafkaResponse response = new KafkaResponse();
		String responseString = producerService.sendMessage(messages,true);
		response.setStatus(responseString);
		return response;
	}
	
	@RequestMapping(value="/sendASyncMsg", method = RequestMethod.POST)
	public KafkaResponse sendAsyncMessage(@RequestBody List<KafkaMessage> messages) throws ProducerException{
		KafkaResponse response = new KafkaResponse();
		String responseString = producerService.sendMessage(messages,false);
		response.setStatus(responseString);
		return response;
	}
	
	@RequestMapping(value="/sendOrders", method = RequestMethod.POST)
	public KafkaResponse sendAsyncMessage(@RequestBody KafkaOrderRequest orders) throws ProducerException{
		KafkaResponse response = new KafkaResponse();
		String responseString = producerService.sendOrders(orders,false);
		response.setStatus(responseString);
		return response;
	}
	
	@ExceptionHandler({ ProducerException.class})
    public KafkaResponse handleException() {
		KafkaResponse response = new KafkaResponse();
		response.setStatus("exception");
		return response;
    }
	
}
 