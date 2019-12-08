package org.holbreich.alert.converter;

import org.holbreich.alert.KtTransaction;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class KtTransactionToString implements Converter<KtTransaction, String> {

	private static final char DELIM = ',';

	@Override
	public String convert(KtTransaction transaction) {
		if (transaction != null) {

			return new StringBuilder().append(transaction.getTransaction_id()).append(DELIM)
					.append(transaction.getDate()).append(DELIM).append(transaction.getDescription()).append(DELIM)
					.append(transaction.getValue()).toString();
		}
		return null;
	}

}
