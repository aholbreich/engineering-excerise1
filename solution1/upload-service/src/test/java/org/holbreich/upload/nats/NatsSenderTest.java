package org.holbreich.upload.nats;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import io.nats.client.Connection;

@ExtendWith(MockitoExtension.class)
class NatsSenderTest {

	private NatsSender underTest;
	
	@Mock
	private NatsConnectionHandler connectionHandler; 
	
	@Mock private Connection connection; 
	
	@Captor
	private ArgumentCaptor<byte[]> rawMessageCaptor;
	
	@BeforeEach
	public void init() {
		underTest = new NatsSender(connectionHandler);
	}
	
	@Test
	void shouldNotThrowExceptionWhenConnectionIsNotReady() {
		underTest.sendMessage("TestMessage", "TestSubject");
	}

	@Test
	void shouldSendCorrectTopic() {
		sendTestMessageWithTestsubject();
		verify(connection).publish(eq("TestSubject"), any());
	}
	
	@Test
	void shouldSendCorrectMessage() {
		sendTestMessageWithTestsubject();
		verify(connection).publish(any(), rawMessageCaptor.capture());
		assertEquals("TestMessage", new String(rawMessageCaptor.getValue(),StandardCharsets.UTF_8));
	}

	private void sendTestMessageWithTestsubject() {
		when(connectionHandler.getConnection()).thenReturn(connection);
		underTest.sendMessage("TestMessage", "TestSubject");
	}

}
