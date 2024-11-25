package com.example.citymanagement.exceptionHandler;

import com.example.citymanagement.exception.PersonValidateException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PersonValidateExceptionHandler {

    @ExceptionHandler(PersonValidateException.class)
    public ResponseEntity <String> handel(PersonValidateException personValidateException){
        return ResponseEntity.status(400).body(personValidateException.getMessage());
    }
}
