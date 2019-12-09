package org.holbreich.alert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TransactionAnalyzer {

	private static final Logger LOG = LoggerFactory.getLogger(TransactionAnalyzer.class);
	
	private double ALERT_TRESHOLD = 10000;
	private WeirdTransactionAlerter alerter;

	public TransactionAnalyzer(WeirdTransactionAlerter alerter) {
		super();
		this.alerter = alerter;
	}

	public void processSingleTransaction(KtTransaction transaction) {
		LOG.debug("Processing TA: {}", transaction.getTransaction_id());

		if (transaction != null) {
			if (transaction.getValue() > ALERT_TRESHOLD || transaction.getValue() < 0 - ALERT_TRESHOLD) {
				alerter.alert(transaction);
			}
		}

	}

}
