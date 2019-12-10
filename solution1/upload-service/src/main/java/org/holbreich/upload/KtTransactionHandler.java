package org.holbreich.upload;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.holbreich.upload.nats.NatsConfig;
import org.holbreich.upload.nats.NatsSender;
import org.simpleflatmapper.csv.CsvParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

/**
 * KtTransactionHandler is responsible for the management of the Transaction.
 * Current implementation simply puts the Transaction to the Messaging
 * Middleware.
 * 
 * @author aho
 *
 */
@Service
public class KtTransactionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(KtTransactionHandler.class);

	private ConversionService conversionService;
	private NatsSender sender;
	private NatsConfig config;

	public KtTransactionHandler(ConversionService conversionService, NatsSender sender, NatsConfig config) {
		super();
		this.conversionService = conversionService;
		this.sender = sender;
		this.config = config;
	}

	/**
	 * Business Logic for the handling of single Transaction
	 * 
	 * @param transaction
	 * @throws Exception
	 */
	public void handleSingleTransaction(KtTransaction transaction) {
		sender.sendMessage(conversionService.convert(transaction, String.class), config.getTopicName());
	}

	/**
	 * Parses CSV InputStream into Transaction Objects and handles them individually
	 * 
	 * @param inputStream
	 * @throws IOException
	 */
	public void parseAndHandleAllTransactions(InputStream inputStream) throws IOException {
		LOG.debug("Start processing new transactions");
		Reader reader = new InputStreamReader(inputStream);
		CsvParser.mapTo(KtTransaction.class).forEach(reader, t -> handleSingleTransaction(t));
	}

}
