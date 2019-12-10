package org.holbreich.upload.converter;

import org.holbreich.upload.KtTransaction;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Converts transaction to String.
 * Conversion logic is captured here.
 * @ConfigurationPropertiesBinding is used here as 'springy' way to minimize boilerplate for Converter registration.
 * @author aho
 */
@Component
@ConfigurationPropertiesBinding
public class KtTransactionToString implements Converter<KtTransaction, String> {

	public static final String DATE_FORMAT = "YYYY-mm-dd";
	private Gson gson = new GsonBuilder().setDateFormat(DATE_FORMAT).create();

	@Override
	public String convert(KtTransaction transaction) {

		if (transaction != null) {
			return gson.toJson(transaction);
		}
		return null;
	}

}
