package com.example.store.Handler;


import com.example.store.Exception.ElementAlreadyExistException;
import com.example.store.Exception.ElementNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<?> elementNotFound(ElementNotFoundException ex){

        Map<String,Object> error = new HashMap<>();
        error.put("message",ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ElementAlreadyExistException.class)
    public ResponseEntity<?> handlerElementAlreadyExistException(ElementAlreadyExistException ex){

        Map<String,Object> error = new HashMap<>();
        error.put("message",ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }
}
