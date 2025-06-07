package com.wastewise.worker.management.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(WorkerNotFoundException.class)
    public ResponseEntity<String> handleWorkerNotFound(WorkerNotFoundException ex) {
        log.error("Worker not found exception thrown and handled");
        String errorMessage = String.format("Worker not found: %s (Status: %d)", ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        log.error("Resource not found exception thrown and handled");
        String errorMessage = String.format("Error: %s (Status: %d)", ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WorkersAlreadyAssignedException.class)
    public ResponseEntity<String> handleWorkersAlreadyAssignedException(WorkersAlreadyAssignedException ex){
        log.error("Worker already assigned exception thrown");
        String errorMessage = String.format("Error: %s (Status: %d)", ex.getMessage(), HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errorMessage,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        log.error("Unexpected error occured");
        String errorMessage = String.format("Unexpected error occurred: %s (Status: % " +
                ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalStateException .class)
    public ResponseEntity<String> handleIllegalState(IllegalStateException ex) {
        log.error("Illegal state exception thrown");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.error("Method argument not valid exception thrown");
        String errorMessages = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }

}
