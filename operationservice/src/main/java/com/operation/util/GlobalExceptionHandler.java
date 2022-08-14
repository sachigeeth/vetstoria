package com.operation.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectUpdateSemanticsDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.InvalidParameterException;


@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String FAILURE_MESSSAGE = "An error has been occurred while processing the request: ";
    private static final String NO_RECORDS_FOUND_MESSSAGE = "No records found for the specified criteria";
    private static final String CONSTRAINT_VIOLATION_MESSAGE = "The record already exists.";
    private static final String UNAUTHORIZED_MESSAGE = "Authentication Failed: Can't find username and password combination.";
    private static final String NO_ACCESS = "Access Denied.";
    private static final String CANNOT_UPDATE_CLOSED_RECORD = "Update failed since processing status was 'complete'";
    private static final String CANNOT_RETRIEVE_RECORD = "The record does not exist.";
    private static final String NULL_POINTER_MESSAGE = "Some mandatory fields are missing ";
    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = NoRecordsFoundException.class)
    public ResponseEntity handle(NoRecordsFoundException exception) {
        logError(exception);
        return new ResponseEntity<>(NO_RECORDS_FOUND_MESSSAGE, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public ResponseEntity handle(HttpMediaTypeNotSupportedException exception) {
        logError(exception);
        return new ResponseEntity<>(FAILURE_MESSSAGE, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(value = IncorrectUpdateSemanticsDataAccessException.class)
    public ResponseEntity handle(IncorrectUpdateSemanticsDataAccessException exception) {
        logError(exception);
        return new ResponseEntity<>(CANNOT_UPDATE_CLOSED_RECORD, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity handle(DataIntegrityViolationException exception) {
        logError(exception);
        return new ResponseEntity<>(CONSTRAINT_VIOLATION_MESSAGE, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DataRetrievalFailureException.class)
    public ResponseEntity handle(DataRetrievalFailureException exception) {
        logError(exception);
        return new ResponseEntity<>(CANNOT_RETRIEVE_RECORD, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity handle(AuthenticationException exception) {
        logError(exception);
        return new ResponseEntity<>(UNAUTHORIZED_MESSAGE, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity handle(AccessDeniedException exception) {
        logError(exception);
        return new ResponseEntity<>(NO_ACCESS, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handle(Exception exception) {
        logError(exception);
        return new ResponseEntity<>(exception.getCause().getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity handle(NullPointerException exception) {
        logError(exception);
        return new ResponseEntity<>(NULL_POINTER_MESSAGE, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidParameterException.class)
    public ResponseEntity handle(InvalidParameterException exception) {
        logError(exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private void logError(Exception exception) {
        log.error("GlobalExceptionHandler.handle(" + exception.getClass().getName() + "):" + exception.getMessage());
        exception.printStackTrace();
    }

}
