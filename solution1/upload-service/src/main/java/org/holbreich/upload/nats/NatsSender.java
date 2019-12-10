package org.holbreich.upload.nats;

import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NatsSender {

	private static final Logger LOG = LoggerFactory.getLogger(NatsSender.class);
	
	private NatsConnectionHandler connectionHandler;

	public NatsSender(NatsConnectionHandler connectionHandler) {
		super();
		this.connectionHandler = connectionHandler;
	}

	public void sendMessage(String payload, String subject) {
		if(payload!=null && !payload.isEmpty()) {
			try {
				connectionHandler.getConnection().publish(subject,getBody(payload));
			} catch (Exception e) {
				LOG.error("Publishing to NATS server failed",e);
			}
		}
	}

	private byte[] getBody(String string) {
		return string.getBytes(StandardCharsets.UTF_8);
	}
}
