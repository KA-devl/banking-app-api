package com.banking.bankapi.handlers;

import com.banking.bankapi.exceptions.ObjectValidationException;
import com.banking.bankapi.exceptions.OperationNonPermittedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice // This annotation is used to handle exceptions globally
public class GlobalExceptionHandler {

    //Everytime an ObjectValidationException is thrown, this method will be called to handle it
    @ExceptionHandler(ObjectValidationException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(ObjectValidationException e) {
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage("Object not valid exceptiopn has occured")
                .errorSource(e.getViolationSource())
                .validationErrors(e.getViolations())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(representation);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(EntityNotFoundException e) {
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage(e.getMessage())
                .errorSource(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(representation);
    }

    @ExceptionHandler(OperationNonPermittedException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(OperationNonPermittedException e) {
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage(e.getErrorMsg())
                .errorSource(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(representation);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(DataIntegrityViolationException e) {
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage("User already exists with the provided email")
//                .errorSource(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(representation);
    }
}
