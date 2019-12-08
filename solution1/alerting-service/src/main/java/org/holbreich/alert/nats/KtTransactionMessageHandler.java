package org.holbreich.alert.nats;

import java.nio.charset.StandardCharsets;

import org.holbreich.alert.KtTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import io.nats.client.Message;
import io.nats.client.MessageHandler;

@Service
public class KtTransactionMessageHandler implements MessageHandler {

	private static final Logger LOG = LoggerFactory.getLogger(KtTransactionMessageHandler.class);
	
	private ConversionService conversionService;


	public KtTransactionMessageHandler(ConversionService conversionService) {
		this.conversionService = conversionService;
	}



	@Override
	public void onMessage(Message msg) throws InterruptedException {
	
		    String str = new String(msg.getData(), StandardCharsets.UTF_8);
		    System.out.println(str);
		
	}

}
