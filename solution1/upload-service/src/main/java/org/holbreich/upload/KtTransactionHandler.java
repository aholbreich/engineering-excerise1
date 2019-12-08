package org.holbreich.upload;

import org.holbreich.upload.nats.NatsSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
public class KtTransactionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(KtTransactionHandler.class);
	public static final String TOPIC = "transactions";

	private ConversionService conversionService;
	private NatsSender sender;

	private long counter = 0;


	public KtTransactionHandler(ConversionService conversionService, NatsSender sender) {
		super();
		this.conversionService = conversionService;
		this.sender = sender;
	}

	/**
	 * 
	 * @param transaction
	 * @throws Exception
	 */
	public void handleKtTransaction(KtTransaction transaction) throws Exception {
		sender.sendMessage(conversionService.convert(transaction, String.class), TOPIC);
		countAndLog();
	}

	// Not required of course, just included to see some life in logs of this service.
	private void countAndLog() {
		counter++;
		if (counter % 1000 == 0) {
			
			LOG.info("Transactions processed {}", counter);
		}
	}
}
