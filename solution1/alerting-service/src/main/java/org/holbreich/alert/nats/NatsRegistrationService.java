package org.holbreich.alert.nats;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.nats.client.Dispatcher;

@Service
public class NatsRegistrationService {

	private static final Logger LOG = LoggerFactory.getLogger(NatsRegistrationService.class);
	public static final String TOPIC = "transactions";

	private NatsConnectionHandler connectionHandler;

	private KtTransactionMessageListener transactionHandler;

	private Dispatcher messageDispatcher;

	public NatsRegistrationService(NatsConnectionHandler connectionHandler,
			KtTransactionMessageListener transactionHandler) {
		this.connectionHandler = connectionHandler;
		this.transactionHandler = transactionHandler;
	}

	@PostConstruct
	public void subscribe() throws Exception {

		if (messageDispatcher == null) {
			this.messageDispatcher = createDispatcher();
		}
		LOG.info("Subscribing to topic: {}", TOPIC);
		messageDispatcher.subscribe(TOPIC);

	}

	private synchronized Dispatcher createDispatcher() throws Exception {
		return connectionHandler.getConnection().createDispatcher(transactionHandler);
	}

	@PreDestroy
	public void unsubscribe() {
		LOG.info("Unsubscribing from topic: {}", TOPIC);
		messageDispatcher.unsubscribe(TOPIC);
	}

}
