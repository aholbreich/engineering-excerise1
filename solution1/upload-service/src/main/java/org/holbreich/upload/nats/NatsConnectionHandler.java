package org.holbreich.upload.nats;

import java.io.IOException;
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

	protected synchronized Connection getConnection() throws Exception {
		if (connection == null) {
			connection = createConnection();
		}
		return connection;
	}

	@PostConstruct
	protected Connection createConnection() throws IOException, Exception {
		final Connection newConnection = Nats.connect();
		LOG.info("A NATS Connection {} has been created", newConnection);
		return newConnection;
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