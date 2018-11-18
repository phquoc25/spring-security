package com.qph.controleradvice;

import com.qph.exception.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ResourceNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity resourceNotFoundHandler(ResourceNotFoundException ex)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add("reason", ex.getMessage());
        return new ResponseEntity(headers, HttpStatus.NOT_FOUND);
    }
}
