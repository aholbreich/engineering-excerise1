package org.holbreich.alert.nats;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.MessageHandler;

/**
 * This Service handles the subscription for messages from NATS Middleware.
 * 
 * @author aho
 *
 */
@Service
public class NatsRegistrationService {

	private static final Logger LOG = LoggerFactory.getLogger(NatsRegistrationService.class);

	private final NatsConnectionHandler connectionHandler;

	private final MessageHandler transactionHandler;

	private final ConfigurableApplicationContext context;

	private final NatsConfig config;

	private transient Dispatcher messageDispatcher;

	public NatsRegistrationService(NatsConnectionHandler connectionHandler, MessageHandler transactionHandler,
			ConfigurableApplicationContext appContext, NatsConfig config) {
		this.connectionHandler = connectionHandler;
		this.transactionHandler = transactionHandler;
		this.context = appContext;
		this.config = config;
	}

	@PostConstruct
	public void subscribe() {

		if (messageDispatcher == null) {
			this.messageDispatcher = createDispatcher();
			LOG.info("Subscribing to topic: {}", config.getTopicName());
			messageDispatcher.subscribe(config.getTopicName());
		}
		else {
			// Alerting service makes not much sense without established connection.
			LOG.error("Cannot connect to NATS Middleware or subscribe. Decided to terminate...");
			context.close();
			// Another strategy could be periodically reconnection logic.
		}
	}

	private synchronized Dispatcher createDispatcher() {
		Connection connection = connectionHandler.getConnection();
		if (connection != null) {
			return connection.createDispatcher(transactionHandler);
		}
		return null;
	}

	@PreDestroy
	public void unsubscribe() {
		LOG.info("Unsubscribing from topic: {}", config.getTopicName());
		messageDispatcher.unsubscribe(config.getTopicName());
	}

}
