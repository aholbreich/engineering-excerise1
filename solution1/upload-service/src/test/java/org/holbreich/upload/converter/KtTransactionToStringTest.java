package org.holbreich.upload.converter;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.holbreich.upload.KtTransaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class KtTransactionToStringTest {

	private KtTransactionToString underTest = new KtTransactionToString();

	@Test
	void shouldConvertNullToNull() {
		assertNull(underTest.convert(null));
	}

	@Test
	void shouldConverntNotNullTransactionToNotNullString() {
		String converted = underTest.convert(getNewDefaultTransaction());
		assertNotNull(converted);
	}

	@Test
	void shouldConvertToJsonFormat() {

	}

	@Nested
	class shouldConvertToJson {

		private String converted;

		@BeforeEach
		void prepare() {
			converted = underTest.convert(getNewDefaultTransaction());
		}

		@Test
		@DisplayName("starts with '{'")
		void startsWith() {
			assertTrue(converted.startsWith("{"));
		}

		@Test
		@DisplayName("Ends with '}'")
		void endsWith() {
			assertTrue(converted.endsWith("}"));
		}

		@ParameterizedTest
		@ValueSource(strings = { "transaction_id", "description", "value", "date" })
		void hasAttributes(String attribute) {
			assertTrue(converted.contains(attribute));
		}
	}

	private KtTransaction getNewDefaultTransaction() {
		return new KtTransaction("b58ecf2c-f4d8-4e5b-ba4f-70a92898198c", "Weird transaction", -10025.00, "2019-09-04");
	}

}
