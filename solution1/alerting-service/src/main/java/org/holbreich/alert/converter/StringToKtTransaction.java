package org.holbreich.alert.converter;

import org.holbreich.alert.KtTransaction;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
@ConfigurationPropertiesBinding
public class StringToKtTransaction implements Converter<String, KtTransaction> {

	public static final String DATE_FORMAT = "YYYY-mm-dd";
	private Gson gson = new GsonBuilder().setDateFormat(DATE_FORMAT).create();
	
	@Override
	public KtTransaction convert(String transactionJson) {
		
		if (transactionJson != null) {
			return gson.fromJson(transactionJson, KtTransaction.class);
		}
		return null;
	}

}
