package org.holbreich.alert.nats;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import io.nats.client.Dispatcher;
import io.nats.client.MessageHandler;

@Service
public class NatsRegistrationService {

	private static final Logger LOG = LoggerFactory.getLogger(NatsRegistrationService.class);
	public static final String TOPIC = "transactions";

	private final NatsConnectionHandler connectionHandler;

	private final MessageHandler transactionHandler;

	private final ConfigurableApplicationContext context;

	private Dispatcher messageDispatcher;

	public NatsRegistrationService(NatsConnectionHandler connectionHandler,
			MessageHandler transactionHandler, ConfigurableApplicationContext appContext) {
		this.connectionHandler = connectionHandler;
		this.transactionHandler = transactionHandler;
		this.context = appContext;
	}

	@PostConstruct
	public void subscribe() {

		if (messageDispatcher == null) {
			try {
				this.messageDispatcher = createDispatcher();
			} catch (Exception e) {
				LOG.error("Exception thrown on subscribing to NATS Middleware", e);
				// Another strategy could be periodically reconnection logic.
				context.close();
			}
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
