package org.holbreich;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectReader;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/upload")
@Api()
public class FileUploadRestApi {
    @PostMapping
    @ApiOperation(value = "Make a POST request to upload the file", produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "The POST call is Successful"),
            @ApiResponse(code = 500, message = "The POST call is Failed"),
            @ApiResponse(code = 404, message = "The API could not be found") })
    public ResponseEntity<String> uploadFile(
            @ApiParam(name = "file", value = "Select the file to Upload", required = true) @RequestPart("file") MultipartFile file) {
        try {
          
        ObjectReader reader = CsvUtil.getReader(Transaction.class);
        reader.<Transaction>readValues(file.getInputStream()).forEachRemaining(item -> System.out.println(item));

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>("Done", HttpStatus.OK);
    }

}