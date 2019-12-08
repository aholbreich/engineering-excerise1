package org.holbreich.alert.nats;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import io.nats.client.Dispatcher;

@Service
public class NatsRegistrationService {

	private NatsConnectionHandler connectionHandler;
	
	public static final String TOPIC = "transactions";
	
	private KtTransactionMessageHandler transactionHandler;

	public NatsRegistrationService(NatsConnectionHandler connectionHandler, KtTransactionMessageHandler transactionHandler) {
		super();
		this.connectionHandler = connectionHandler;
		this.transactionHandler = transactionHandler;
	}

	@PostConstruct
	public void register() throws Exception  {
	

		// Create a dispatcher and inline message handler
		Dispatcher d = connectionHandler.getConnection().createDispatcher(transactionHandler);
		d.subscribe(TOPIC);

	}

}
