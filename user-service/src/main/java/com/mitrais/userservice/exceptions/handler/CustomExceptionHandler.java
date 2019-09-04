package com.mitrais.userservice.exceptions.handler;

import com.mongodb.MongoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mitrais.userservice.exceptions.model.DuplicateDataException;
import com.mitrais.userservice.exceptions.model.ServiceException;
import com.mitrais.userservice.exceptions.model.UserNotFoundException;

/**
 * Provide functionality to handle response entity exception.
 *
 * @author Rai Suardhyana Arijasa on 9/3/2019.
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

    /**
     * Provide handler for Mongo exception.
     *
     * @param exception exception
     * @return response entity
     */
    @ExceptionHandler({MongoException.class})
    public ResponseEntity handleMongoException(final MongoException exception) {
        log.warn("Processing mongo exception: {}", exception.getMessage());

        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Provide handler for Service exception.
     *
     * @param exception exception
     * @return response entity
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity handleServiceException(final ServiceException exception) {
        log.warn("Processing service exception: {}", exception.getMessage());

        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Provide handler for user not found exception.
     *
     * @param exception exception
     * @return response entity
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(final UserNotFoundException exception) {
        log.warn("Processing user not found exception: {}", exception.getMessage());

        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Provide handler for general exception.
     *
     * @param exception exception
     * @return response entity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleAbstractException(final Exception exception) {
        log.warn("Processing abstract exception: {}", exception.getMessage());

        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Provide handler for duplicate data exception.
     *
     * @param exception exception
     * @return response entity
     */
    @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity handleDuplicateDataException(final DuplicateDataException exception) {
        log.warn("Processing abstract exception: {}", exception.getMessage());

        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Provide argument not valid handler.
     *
     * @param exception method argument not valid exception
     * @param headers   HttpHeaders
     * @param status    HttpStatus
     * @param request   WebRequest
     * @return response entity object
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder errorBuilder = new StringBuilder();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorBuilder.append(fieldName).append(" : ").append(errorMessage).append("\n");
        });
        return new ResponseEntity<>(errorBuilder.toString(), HttpStatus.BAD_REQUEST);
    }
}
