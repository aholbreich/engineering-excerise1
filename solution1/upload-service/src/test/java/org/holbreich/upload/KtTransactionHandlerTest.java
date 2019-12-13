package org.holbreich.upload;

import java.io.IOException;

import org.holbreich.upload.nats.NatsConfig;
import org.holbreich.upload.nats.NatsSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class KtTransactionHandlerTest {

	@Mock
	private ConversionService conversionService;
	
	@Mock
	private NatsSender sender;
	
	private NatsConfig config = new NatsConfig();

	private KtTransactionHandler underTest;

	@BeforeEach
	void init() {
		config.setTopicName("testTopic");
		underTest = new KtTransactionHandler(conversionService, sender,config);
	}

	@Test
	void shouldConvertTransaction() {
		underTest.handleSingleTransaction(new KtTransaction("id", "descr", 19.18, "2019-01-10"));
		verify(conversionService).convert(any(KtTransaction.class), eq(String.class));
	}
	
	@Test
	void shouldSendTransaction() {
		when(conversionService.convert(any(KtTransaction.class), eq(String.class))).thenReturn("{}");
		underTest.handleSingleTransaction(new KtTransaction("id", "descr", 19.18, "2019-01-10"));
		verify(sender).sendMessage(anyString(), eq("testTopic"));
	}

	@Test
	void shouldReadCSVAndSendViaSender() throws IOException {
		 when(conversionService.convert(any(KtTransaction.class), eq(String.class))).thenReturn("{\"transactin_id\": \"test\"}");
		 underTest.parseAndHandleAllTransactions(this.getClass().getResourceAsStream("/statements5.csv"));
		 verify(sender, times(5)).sendMessage(anyString(), eq("testTopic"));
	}

}
