package org.holbreich.upload.nats;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.nats.client.Connection;
import io.nats.client.Nats;

@Service
public class NatsConnectionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(NatsConnectionHandler.class);

	private transient Connection connection;
	
	private NatsConfig config;
	

	public NatsConnectionHandler(NatsConfig config) {
		super();
		this.config = config;
	}

	/**
	 * Connection can be null. 
	 * We tolerate this but it need to be handled by the client.
	 * @return Connection or null 
	 */
	protected synchronized Connection getConnection() {
		if (connection == null) {
			connection = createConnection();
		}
		return connection;
	}

	@PostConstruct
	protected Connection createConnection() {
		try {
			LOG.info("Connectiong to NATS via {}", config.getUrl());
			final Connection newConnection = Nats.connect(config.getUrl());
			LOG.info("A NATS Connection {} has been created", newConnection);
			return newConnection;
		} catch (Exception e) {
			LOG.error("Could not establish connection to NATS Server: {}", e);
			return null;
		}
	}

	@PreDestroy
	public void onDestroy() {
		LOG.info("Caught shutting down gracefully... ");

		try {
			connection.flush(Duration.ofSeconds(3));
			connection.close();
		} catch (TimeoutException | InterruptedException e) {
			LOG.error("Exception on flush and close of the connection to NATS", e);
		}
	}
}