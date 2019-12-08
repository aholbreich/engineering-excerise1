package org.holbreich;

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
   
	private long counter;
	private Logger LOG = LoggerFactory.getLogger(FileUploadRestApi.class);

	@RequestMapping(method = RequestMethod.POST) 
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        	
        	Reader reader;
			try {
				reader = new InputStreamReader(file.getInputStream());
				CsvParser
				.mapTo(Transaction.class)
				.forEach(reader, t ->processTransaction(t));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			LOG.info("Counter: {} ",counter);
        return new ResponseEntity<String>("Done", HttpStatus.OK);
	}
	
	public void processTransaction(Transaction transaction) {
		counter++;
	}
}