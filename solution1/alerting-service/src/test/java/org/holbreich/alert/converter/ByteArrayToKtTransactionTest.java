package org.holbreich.alert.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ByteArrayToKtTransactionTest {
	
	private ByteArrayToKtTransaction underTest = new ByteArrayToKtTransaction();

	@Test
	void shouldConvertCorrectlyId() {
		assertEquals("TestId", underTest.convert(getTestJSON().getBytes()).getTransaction_id());
	}
	
	@Test
	void shouldConvertCorrectlyTestDescription() {
		assertEquals("TestDescription", underTest.convert(getTestJSON().getBytes()).getDescription());
	}
	
	@Test
	void shouldConvertCorrectlyValue() {
		assertEquals(12345.1d, underTest.convert(getTestJSON().getBytes()).getValue());
	}
	
	@Test
	void shouldConvertCorrectlyDate() {
		assertEquals("2000-01", underTest.convert(getTestJSON().getBytes()).getDate());
	}

	private String getTestJSON() {
		return "{\"transaction_id\": \"TestId\", \"description\": \"TestDescription\", \"value\": 12345.1, \"date\": \"2000-01\"}";
	}

}
