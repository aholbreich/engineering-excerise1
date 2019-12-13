package org.holbreich.alert;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StdOutAlertPrinterTest {

	private StdOutAlertPrinter underTest;
	private ByteArrayOutputStream outputBuffer;
	
	static final String BEGIN_OF_ALERT ="Found transaction over alert threshold:\n" + "description: Weird transaction,\n";
	
	@BeforeEach
	void init() {
		underTest = new StdOutAlertPrinter();

		// Redirect System.out to buffer
		outputBuffer= new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputBuffer));
	}

	
	@Test
	void shouldPrintMessageToConsoleInTheRightFormatt() throws IOException {

		underTest.alert(new KtTransaction("12345", "TestDescription", 1111d, "2019-01"));
		outputBuffer.flush();
		assertEquals(BEGIN_OF_ALERT + "value: 1111.00,\ndate: 2019-01\n", new String(outputBuffer.toByteArray()));
	}


}
