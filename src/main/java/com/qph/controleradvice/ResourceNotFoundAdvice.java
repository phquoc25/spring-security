package com.qph.controleradvice;

import com.qph.exception.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ResourceNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity resourceNotFoundHandler(ResourceNotFoundException ex)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add("reason", ex.getMessage());
        return ResponseEntity.notFound().headers(headers).build();
    }
}
