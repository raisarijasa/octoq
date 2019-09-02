package com.mitrais.questionservice.exceptions.handler;

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

import com.mitrais.questionservice.exceptions.model.DataNotFoundException;
import com.mitrais.questionservice.exceptions.model.ServiceException;

/**
 * Provide functionality to manipulate Exception Handler.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

    /**
     * Handler of service exception
     *
     * @param exception service exception
     * @return response entity object
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity handleServiceException(final ServiceException exception) {
        log.warn("Processing service exception: {}", exception.getMessage());
        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Data not found handler
     *
     * @param exception data not found exception
     * @return response entity object
     */
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(final DataNotFoundException exception) {
        log.warn("Processing user not found exception: {}", exception.getMessage());
        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Argument not valid handler
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

    /**
     * Exception hanler
     *
     * @param exception any exception
     * @return response entity object
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleAbstractException(final Exception exception) {
        log.warn("Processing abstract exception: {}", exception.getMessage());
        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
}
