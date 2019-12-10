package org.holbreich.upload;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/upload")
public class FileUploadRestApi {

	private KtTransactionHandler transactionHandler;

	public FileUploadRestApi(KtTransactionHandler transactionHandler) {
		this.transactionHandler = transactionHandler;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {

		try {
			transactionHandler.parseAndHandleAllTransactions(file.getInputStream());
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Exception on the document processing. Can't continue with provided document", e);
		}
		return new ResponseEntity<String>("Done", HttpStatus.OK);
	}

}