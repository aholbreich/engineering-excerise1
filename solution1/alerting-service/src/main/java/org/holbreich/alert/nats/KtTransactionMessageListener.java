package org.holbreich.alert.nats;

import java.nio.charset.StandardCharsets;

import org.holbreich.alert.KtTransaction;
import org.holbreich.alert.TransactionAnalyzer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import io.nats.client.Message;
import io.nats.client.MessageHandler;

@Service
public class KtTransactionMessageListener implements MessageHandler {

	private static final Logger LOG = LoggerFactory.getLogger(KtTransactionMessageListener.class);
	
	private ConversionService conversionService;
	
	private TransactionAnalyzer analyzer;

	public KtTransactionMessageListener(ConversionService conversionService, TransactionAnalyzer analyzer) {
		this.conversionService = conversionService;
		this.analyzer = analyzer;
	}

	@Override
	public void onMessage(Message msg) throws InterruptedException {
		LOG.debug("Message received {}", msg);
		if (msg != null) {
			analyzer.processSingleTransaction(conversionService.convert(new String(msg.getData(), StandardCharsets.UTF_8), KtTransaction.class));
		}
	}

}
