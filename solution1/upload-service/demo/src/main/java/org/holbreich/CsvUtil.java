package org.holbreich;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

//TODO
public class CsvUtil {

	private static CsvMapper mapper = new CsvMapper();

	public static <T> ObjectReader getReader(Class<T> clazz) {
		CsvSchema schema = mapper.schemaFor(clazz).withHeader();
		return mapper.readerFor(clazz).with(schema);

	}

}
