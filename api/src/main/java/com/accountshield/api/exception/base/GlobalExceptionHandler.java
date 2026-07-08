package com.accountshield.api.exception.base;

import com.accountshield.api.exception.EmailAlreadyExistsException;
import com.accountshield.api.exception.ErrorDetails;
import com.accountshield.api.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDetails> handleBadCredentialsException(BadCredentialsException exception,
                                                                      WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                "Username or password is incorrect!",
                request.getDescription(false),
                HttpStatus.UNAUTHORIZED.value()
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUsernameNotFoundException(UsernameNotFoundException exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                exception.getMessage(),
                request.getDescription(false),
                HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                "Some happen error occurred: " + exception.getMessage(),
                request.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleUserAlreadyExistsException(UserAlreadyExistsException exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                exception.getMessage(),
                request.getDescription(false),
                HttpStatus.CONFLICT.value()
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleEmailAlreadyExistsException(EmailAlreadyExistsException exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                exception.getMessage(),
                request.getDescription(false),
                HttpStatus.CONFLICT.value()
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }
}
