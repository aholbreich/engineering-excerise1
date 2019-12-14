package org.holbreich.alert;

import org.holbreich.alert.converter.ByteArrayToKtTransaction;
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

import io.nats.client.Message;

@ExtendWith(MockitoExtension.class)
class TransactionAnalyzerTest {

	private TransactionAnalyzer underTest;
	private ConversionService conversionService = new TestDoubleOfConversionService();

	@Mock
	private WeirdTransactionAlerter alerter;

	@Mock
	private Message msg;

	@BeforeEach
	void init() {
		underTest = new TransactionAnalyzer(alerter, conversionService);
	}

	@Test
	void shouldNotAlertUnderTresholdValue() throws InterruptedException {
		when(msg.getData()).thenReturn(getTestJSON("10000").getBytes());
		underTest.onMessage(msg);
		verify(alerter, times(0)).alert(any()); // no alerts
	}

	@Test
	void shouldAlertInCaseOverPositiveTresholdValue() throws InterruptedException {
		when(msg.getData()).thenReturn(getTestJSON("10001").getBytes());
		underTest.onMessage(msg);
		verify(alerter, times(1)).alert(any());
	}

	@Test
	void shouldAlertInCaseUnderNegavitTresholdValue() throws InterruptedException {
		when(msg.getData()).thenReturn(getTestJSON("-10001").getBytes());
		underTest.onMessage(msg);
		verify(alerter, times(1)).alert(any());
	}

	private String getTestJSON(String valueAsString) {
		return "{\"transaction_id\": \"TestId\", \"description\": \"TestDescription\", \"value\": " + valueAsString
				+ ", \"date\": \"2000-01\"}";
	}

}
