package org.holbreich.alert;

import org.holbreich.alert.converter.ByteArrayToKtTransaction;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;

public class TestDoubleOfConversionService implements ConversionService {

	private ByteArrayToKtTransaction converter = new ByteArrayToKtTransaction();

	@Override
	public boolean canConvert(Class<?> sourceType, Class<?> targetType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canConvert(TypeDescriptor sourceType, TypeDescriptor targetType) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T convert(Object source, Class<T> targetType) {
		return (T) converter.convert((byte[]) source);
	}
	@Override
	public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		// TODO Auto-generated method stub
		return null;
	}

}
