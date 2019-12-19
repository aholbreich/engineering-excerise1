package org.holbreich.alert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import io.nats.client.Message;
import io.nats.client.MessageHandler;

/**
 * Analyzer contains the business logic of the Alerting Service.
 * It gets message if it's received from Messaging Middleware and apply the analyze. 
 * @author aho
 *
 */
@Service
public class TransactionAnalyzer implements MessageHandler {

	private static final Logger LOG = LoggerFactory.getLogger(TransactionAnalyzer.class);

	private double ALERT_TRESHOLD = 10000;
	private WeirdTransactionAlerter alerter;

	private ConversionService conversionService;

	public TransactionAnalyzer(WeirdTransactionAlerter alerter, ConversionService conversionService) {
		super();
		this.alerter = alerter;
		this.conversionService = conversionService;
	}

	@Override
	public void onMessage(Message msg) throws InterruptedException {
		LOG.debug("Message received");
		if (msg != null) {
			KtTransaction transaction = conversionService.convert(msg.getData(),KtTransaction.class);
			
			if (transaction != null) {
				LOG.debug("Processing TA: {}", transaction.getTransaction_id());
				if (transaction.getValue() > ALERT_TRESHOLD || transaction.getValue() < 0 - ALERT_TRESHOLD) {
					alerter.alert(transaction);
				}
			}
		}
	}

}
