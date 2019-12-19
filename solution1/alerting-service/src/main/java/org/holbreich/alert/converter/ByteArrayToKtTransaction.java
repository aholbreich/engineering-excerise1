package org.holbreich.alert.converter;

import java.nio.charset.StandardCharsets;

import org.holbreich.alert.KtTransaction;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Converts JSON (received as byte array) to {@link KtTransaction} object. This
 * specific converter is automatically registered.
 * 
 * @author aho
 *
 */
@Component
@ConfigurationPropertiesBinding
public class ByteArrayToKtTransaction implements Converter<byte[], KtTransaction> {

	public static final String DATE_FORMAT = "YYYY-mm-dd";
	private Gson gson = new GsonBuilder().setDateFormat(DATE_FORMAT).create();

	@Override
	public KtTransaction convert(byte[] transactionMessageBytes) {

		if (transactionMessageBytes != null) {
			return gson.fromJson(new String(transactionMessageBytes, StandardCharsets.UTF_8), KtTransaction.class);
		}
		return null;
	}

}
