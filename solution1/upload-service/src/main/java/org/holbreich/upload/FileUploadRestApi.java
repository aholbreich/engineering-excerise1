package org.holbreich.upload;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.simpleflatmapper.csv.CsvParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class FileUploadRestApi {

	private static final Logger LOG = LoggerFactory.getLogger(FileUploadRestApi.class);

	private KtTransactionHandler transactionHandler;

	public FileUploadRestApi(KtTransactionHandler transactionHandler) {
		this.transactionHandler = transactionHandler;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {

		Reader reader;
		try {
			reader = new InputStreamReader(file.getInputStream());
			CsvParser.mapTo(KtTransaction.class).forEach(reader, t -> transactionHandler.handleKtTransaction(t));
		} catch (IOException e) {
			LOG.error("Exception on the document processing. Can't continue ", e);
		}
		return new ResponseEntity<String>("Done", HttpStatus.OK);
	}

}