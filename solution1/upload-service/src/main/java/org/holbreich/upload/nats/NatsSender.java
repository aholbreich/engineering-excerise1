package org.holbreich.upload.nats;

import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;

@Service
public class NatsSender {

	private NatsConnectionHandler connectionHandler;

	public NatsSender(NatsConnectionHandler connectionHandler) {
		super();
		this.connectionHandler = connectionHandler;
	}

	public void sendMessage(String payload, String subject) throws Exception  {
		if(payload!=null && !payload.isEmpty()) {
			connectionHandler.getConnection().publish(subject,getBody(payload));
		}
	}

	private byte[] getBody(String string) {
		return string.getBytes(StandardCharsets.UTF_8);
	}
}
