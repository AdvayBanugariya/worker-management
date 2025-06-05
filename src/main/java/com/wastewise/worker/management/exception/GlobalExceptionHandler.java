package com.wastewise.worker.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(WorkerNotFoundException.class)
    public ResponseEntity<String> handleWorkerNotFound(WorkerNotFoundException ex) {
        String errorMessage = String.format("Worker not found: %s (Status: %d)", ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        String errorMessage = String.format("Error: %s (Status: %d)", ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        String errorMessage = String.format("Unexpected error occurred: %s (Status: % " +
                ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalStateException .class)
    public ResponseEntity<String> handleIllegalState(IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
